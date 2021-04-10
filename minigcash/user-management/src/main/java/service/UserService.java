package service;

import exception.*;
import payload.*;

public interface UserService {
    AccountCreationResponse create(AccountCreationRequest request) throws InvalidAccountCreationRequestException;
    void verify(String email, String verificationCode) throws InvalidVerificationRequestException;
    AuthenticationResponse authenticate(String email, String password) throws InvalidAuthenticationRequestException;
    //    List<UserData> getAllUsers(boolean excludeUnverified, boolean excludeInactive);
    UserData getAccount(String id) throws UserNotFoundException;
//    void deleteUser(String id) throws UserNotFoundException;
}
