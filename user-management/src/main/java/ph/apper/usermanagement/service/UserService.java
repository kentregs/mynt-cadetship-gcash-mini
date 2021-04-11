package ph.apper.usermanagement.service;


import ph.apper.usermanagement.exception.InvalidAuthenticationCredentialException;
import ph.apper.usermanagement.exception.InvalidUserRegistrationRequestException;
import ph.apper.usermanagement.exception.InvalidVerificationRequestException;
import ph.apper.usermanagement.exception.UserNotFoundException;
import ph.apper.usermanagement.payload.*;

public interface UserService {
    UserRegistrationResponse register(UserRegistrationRequest request) throws InvalidUserRegistrationRequestException;
    void verify(String email, String verificationCode) throws InvalidVerificationRequestException;
    UserAuthenticationResponse authenticate(String email, String password) throws InvalidAuthenticationCredentialException;
    GetUserResponse getUser(String id) throws UserNotFoundException;
}
