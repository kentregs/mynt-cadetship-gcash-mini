package ph.apper.service;

import org.springframework.context.annotation.Bean;
import ph.apper.exception.*;
import ph.apper.payload.*;

public interface UserService {
    AccountCreationResponse create(AccountCreationRequest request) throws InvalidAccountCreationRequestException;
    void verify(String email, String verificationCode) throws InvalidVerificationRequestException;
    AuthenticationResponse authenticate(String email, String password) throws InvalidAuthenticationRequestException;
    //    List<UserData> getAllUsers(boolean excludeUnverified, boolean excludeInactive);
    UserData getAccount(String id) throws UserNotFoundException;
    void transfer(String sender, String receiver, Double amount) throws TransferAmountRequestException, UserNotFoundException;
    void updateUser(UpdateUserRequest request) throws UserNotFoundException;
}