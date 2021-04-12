package ph.apper.service;

import at.favre.lib.crypto.bcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import ph.apper.domain.Activity;
import ph.apper.domain.User;
import ph.apper.domain.VerificationCode;
import ph.apper.exception.*;
import ph.apper.payload.*;
import ph.apper.util.IdService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@Profile({"dev", "prod"})
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    private final List<User> users = new ArrayList<>();
    private final List<VerificationCode> verificationCodes = new ArrayList<>();
    private final List<Activity> activities = new ArrayList<>();

    @Override
    public AccountCreationResponse create (AccountCreationRequest request)
            throws InvalidAccountCreationRequestException {
        if (isRegisteredAndVerifiedUser(request.getEmail())) {
            throw new InvalidAccountCreationRequestException("Account already created!");
        }

        // init new User object
        User newAcc = new User();

        newAcc.setFirstName(request.getFirstName());
        newAcc.setLastName(request.getLastName());
        newAcc.setEmail(request.getEmail());
        newAcc.setPassword(BCrypt.withDefaults().hashToString(4, request.getPassword().toCharArray()));
        newAcc.setDateRegistered(LocalDateTime.now());
        newAcc.setBalance(50.0);

        String code = IdService.generateCode(6);
        VerificationCode verificationCode = new VerificationCode(request.getEmail(), code);
        LOGGER.info("Generated verification code: {}", verificationCode);

        // save new activity details
        Activity newAct = new Activity(request.getEmail());

        newAct.setAction("ACCOUNT CREATION");
        newAct.setEmail(request.getEmail());
        newAct.setIdentifier("email=" + request.getEmail());

        // add new details to their respective lists
        users.add(newAcc);
        verificationCodes.add(verificationCode);
        activities.add(newAct);

        return new AccountCreationResponse(code);
    }

    @Override
    public void verify (String email, String code) throws InvalidVerificationRequestException {
        if (isRegisteredAndVerifiedUser(email)) {
            throw new InvalidVerificationRequestException("Account already verified!");
        }

        VerificationCode verifiedUserEmail = verificationCodes.stream()
                .filter(verificationCode -> email.equals(verificationCode.getEmail()) && code.equals(verificationCode.getCode()))
                .findFirst()
                .orElseThrow(() -> new InvalidVerificationRequestException("Invalid verification request"));

        verificationCodes.remove(verifiedUserEmail);

        User user = getUserByEmail(email);
        user.setVerified(true);
//        user.setActive(true);
        user.setDateVerified(LocalDateTime.now());

        Activity act = getActivityByEmail(email);
        act.setAction("VERIFICATION");
        act.setIdentifier("email = " + user.getEmail());

        // add new activity to list
        activities.add(act);
    }

    @Override
    public AuthenticationResponse authenticate (String email, String password) throws InvalidAuthenticationRequestException {
        if (isRegisteredAndVerifiedUser(email)) {
            // query for user with matching email
            User user = getUserByEmail(email);
            // verify password
            BCrypt.Result verify = BCrypt.verifyer().verify(password.toCharArray(), user.getPassword());
            boolean chk = verify.verified;

            if (!(isAuthenticated(email)) && chk) {
                // generate Account Id
                String accId = "ACCT" + IdService.getNextUserId();
                LOGGER.info("Generated Account ID: {}", accId);

                // set new attributes for corresponding user
                user.setAccId(accId);
                user.setAuthenticated(true);
                user.setDateAuthenticated(LocalDateTime.now());

                Activity act = getActivityByEmail(email);
                act.setAction("AUTHENTICATION");
                act.setIdentifier("account id = " + accId);

                // add new activity to list
                activities.add(act);

                return new AuthenticationResponse(accId);
            }
            else {
                if (chk)
                    throw new InvalidAuthenticationRequestException("Account already authenticated!");
                else
                    throw new InvalidAuthenticationRequestException("Incorrect password!");
            }
        }
        else {
            System.out.println("test");
            throw new InvalidAuthenticationRequestException("Unverified account!");
        }
    }

    @Override
    public UserData getAccount (String accId) throws UserNotFoundException {
        // query verified and authenticated account using given account ID
        User u = getUserById(accId);

        return UserServiceUtil.toUserData(u);
    }

    @Override
    public void transfer (String senderId, String receiverId, Double amount) throws TransferAmountRequestException, UserNotFoundException{

        User u1 = getUserById(senderId);
        User u2 = getUserById(receiverId);

        if (!(u1.getBalance() < amount)) {
            LOGGER.info("Transfer of Php {} between {} and {}", amount, senderId, receiverId);
            u1.setBalance(u1.getBalance() - amount);
            LOGGER.info("{} new balance: {}", u1.getAccId(), u1.getBalance());
            u2.setBalance(u2.getBalance() + amount);
            LOGGER.info("{} new balance: {}", u2.getAccId(), u2.getBalance());

            Activity act = getActivityByEmail(u1.getEmail());
            act.setAction("TRANSFER");
            act.setIdentifier("email = " + u1.getEmail());

            // add new activity to list
            activities.add(act);
        }

        else throw new TransferAmountRequestException("Not enough funds!");
    }

    @Override
    public void updateUser(UpdateUserRequest request) throws UserNotFoundException {
        Integer index = getUserIndexById(request.getAccId());  //gets index
        User user = getUserById(request.getAccId());   // gets the original user data
        user.setBalance(request.getBalance()); //updates the balance to that from the post request
        // update list
        users.set(index, user);
    }

    private Integer getUserIndexById(String accId) throws UserNotFoundException {
        int index = users.stream().map(user -> user.getAccId()).collect(Collectors.toList()).indexOf(accId);
        return index; //gets index of a particular user in the 'list' DB
    }

    private User getUserById(String accId) throws UserNotFoundException {
        return users.stream()
                .filter(user -> accId.equals(user.getAccId()))
                .findFirst()
                .orElseThrow(() -> new UserNotFoundException("User with Account ID" + accId + " not found"));
    }

    private User getUserByEmail(String email) {
        return users.stream()
                .filter(user -> email.equals(user.getEmail()))
                .findFirst()
                .orElseThrow();
    }

    private Activity getActivityByEmail(String email) {
        return activities.stream()
                .filter(activity -> email.equals(activity.getEmail()))
                .findFirst()
                .orElseThrow();
    }

    private boolean isAuthenticated(String email) {
        try {
            return getUserByEmail(email).isAuthenticated();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private boolean isRegisteredAndVerifiedUser(String email) {
        try {
            return getUserByEmail(email).isVerified();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}