package controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import payload.*;
import exception.*;
import service.*;

import javax.validation.Valid;

@RestController
@RequestMapping("account")
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Create Account
    @PostMapping
    public ResponseEntity<AccountCreationResponse> create (
            @Valid @RequestBody AccountCreationRequest request,
            @RequestHeader("Reference-Number") String referenceNumber) throws InvalidAccountCreationRequestException {
        LOGGER.info("Account creation request with ref. no. {} received", referenceNumber);

        AccountCreationResponse response = userService.create(request);

        LOGGER.info("Account creation with ref. no. {} successful!", referenceNumber);

        return ResponseEntity.ok(response);
    }

    // Verify Account
    @PostMapping("verify")
    public ResponseEntity<AuthenticationRequest> verify (
            @Valid @RequestBody VerificationRequest request,
            @RequestHeader("Reference-Number") String referenceNumber) throws InvalidVerificationRequestException {
        LOGGER.info("Account verification request with ref. no. {} received", referenceNumber);

        userService.verify(request.getEmail(), request.getVerificationCode());

        LOGGER.info("Account verification with ref. no. {} successful!", referenceNumber);

        return ResponseEntity.ok(new AuthenticationRequest("Verification success!"));
    }

    // Authenticate Account
    @PostMapping("authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate (
            @Valid @RequestBody AccountCreationResponse request,
            @RequestHeader("Reference-Number") String referenceNumber) throws InvalidAuthenticationRequestException {

        LOGGER.info("Account authentication request with ref. no. {} received", referenceNumber);

        userService.authenticate(request.getEmail(), request.getPassword());

        LOGGER.info("Account authentication request with ref. no. {} successful!", referenceNumber);
        return ResponseEntity.ok(new AuthenticationResponse("Authentication success"));
    }

    // Get Account
    @GetMapping("{id}")
    public ResponseEntity<UserData> getUser(@PathVariable("id") String userId) throws UserNotFoundException {
        return ResponseEntity.ok(userService.getAccount(userId));
    }
}
