package ph.apper.service;

import org.springframework.context.annotation.Bean;
import ph.apper.exception.InvalidAccountCreationRequestException;
import ph.apper.exception.InvalidAuthenticationRequestException;
import ph.apper.exception.InvalidVerificationRequestException;
import ph.apper.exception.UserNotFoundException;
import ph.apper.payload.AccountCreationRequest;
import ph.apper.payload.AccountCreationResponse;
import ph.apper.payload.AuthenticationResponse;
import ph.apper.payload.UserData;

public interface UserService {
    AccountCreationResponse create(AccountCreationRequest request) throws InvalidAccountCreationRequestException;
    void verify(String email, String verificationCode) throws InvalidVerificationRequestException;
    AuthenticationResponse authenticate(String email, String password) throws InvalidAuthenticationRequestException;
    //    List<UserData> getAllUsers(boolean excludeUnverified, boolean excludeInactive);
    UserData getAccount(String id) throws UserNotFoundException;
//    void deleteUser(String id) throws UserNotFoundException;
}