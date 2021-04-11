package ph.apper.usermanagement.service;


import at.favre.lib.crypto.bcrypt.BCrypt;
import ph.apper.usermanagement.domain.Authentication;
import ph.apper.usermanagement.domain.User;
import ph.apper.usermanagement.domain.VerificationCode;
import ph.apper.usermanagement.exception.InvalidAuthenticationCredentialException;
import ph.apper.usermanagement.exception.InvalidUserRegistrationRequestException;
import ph.apper.usermanagement.exception.InvalidVerificationRequestException;
import ph.apper.usermanagement.exception.UserNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import ph.apper.usermanagement.payload.*;
import ph.apper.usermanagement.repository.*;
import ph.apper.usermanagement.util.IdService;
import ph.apper.usermanagement.util.UserServiceUtil;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Profile({"dev", "prod"})
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final VerificationCodeRepository verificationCodeRepository;
    private final AuthenticationRepository authenticationRepository;

    public UserServiceImpl(UserRepository userRepository, VerificationCodeRepository verificationCodeRepository, AuthenticationRepository authenticationRepository) {
        this.userRepository = userRepository;
        this.verificationCodeRepository = verificationCodeRepository;
        this.authenticationRepository = authenticationRepository;
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public UserRegistrationResponse register(UserRegistrationRequest request) throws InvalidUserRegistrationRequestException {

        if (isRegisteredAndVerifiedUser(request.getEmail())) {
            throw new InvalidUserRegistrationRequestException("email already registered");
        }

        // get user id
        String userId = IdService.getNextUserId();

        LOGGER.info("Generated User ID: {}", userId);
        // save registration details as User with ID
        User newUser = new User(userId);
        newUser.setFirstName(request.getFirstName());
        newUser.setLastName(request.getLastName());
        newUser.setEmail(request.getEmail());
        newUser.setPassword(BCrypt.withDefaults().hashToString(4, request.getPassword().toCharArray()));
        newUser.setDateRegistered(LocalDateTime.now());

        String code = IdService.generateCode(6);
        LOGGER.info("Generated verification code: {}", code);

        VerificationCode verificationCode = new VerificationCode(request.getEmail(), code);

        userRepository.save(newUser);
        verificationCodeRepository.save(verificationCode);

        return new UserRegistrationResponse(code);
    }

    @Override
    public void verify(String email, String verificationCode) throws InvalidVerificationRequestException {
        if (isRegisteredAndVerifiedUser(email)) {
            throw new InvalidVerificationRequestException("email already registered");
        }

        VerificationCode verifiedUserEmail = verificationCodeRepository.findByEmailAndCode(email, verificationCode)
                .orElseThrow(() -> new InvalidVerificationRequestException("Invalid verification request"));

        verificationCodeRepository.delete(verifiedUserEmail);

        User user = userRepository.findByEmail(email).orElseThrow();
        user.setVerified(true);
//        user.setAuth(true);
        user.setDateVerified(LocalDateTime.now());

        userRepository.save(user);
    }

    @Override
    public UserAuthenticationResponse authenticate(String email, String password) throws InvalidAuthenticationCredentialException {
        try {
            User user = userRepository.findByEmail(email).orElseThrow();
            BCrypt.Result verify = BCrypt.verifyer().verify(password.toCharArray(), user.getPassword());
            user.setAuth(true);

            String accountId = IdService.generateAccountId(6);
            LOGGER.info("Generated account id: {}", accountId);

            Authentication authentication = new Authentication(user.getEmail(), accountId);
            authenticationRepository.save(authentication);

            if (user.isVerified() && user.isAuth() && verify.verified) {
                user.setLastLogin(LocalDateTime.now());
                // set account ID to user as well to get the user via account ID
                user.setAccountId(accountId);
                userRepository.save(user);

                return new UserAuthenticationResponse(accountId);
            }
        } catch (Exception e) {
            throw new InvalidAuthenticationCredentialException("Invalid auth credentials", e);
        }

        throw new InvalidAuthenticationCredentialException("Invalid auth credentials");
    }

    @Override
    public GetUserResponse getUser(String id) throws UserNotFoundException {
        User user = getUserById(id);
        return UserServiceUtil.toGetUserDataResponse(user);
    }



    private boolean isRegisteredAndVerifiedUser(String email) {
        Optional<User> emailQ = userRepository.findByEmail(email);
        return emailQ.isPresent() && emailQ.get().isVerified();
    }

    private User getUserById(String id) throws UserNotFoundException {
        return userRepository.findByAccountId(id).orElseThrow(() -> new UserNotFoundException("User " + id + " not found"));
    }
}
