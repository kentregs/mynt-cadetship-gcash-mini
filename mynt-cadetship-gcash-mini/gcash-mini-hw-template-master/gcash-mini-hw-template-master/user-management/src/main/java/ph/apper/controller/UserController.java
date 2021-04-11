package ph.apper.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ph.apper.exception.InvalidAccountCreationRequestException;
import ph.apper.exception.InvalidAuthenticationRequestException;
import ph.apper.exception.InvalidVerificationRequestException;
import ph.apper.exception.UserNotFoundException;
import ph.apper.payload.*;
import ph.apper.service.UserService;

import javax.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("account")
@ComponentScan("ph.apper")
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    public final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Create Account
    @PostMapping
    public ResponseEntity<AccountCreationResponse> create (
            @Valid @RequestBody AccountCreationRequest request) throws InvalidAccountCreationRequestException {
//        LOGGER.info("Account creation request with ref. no. {} received", referenceNumber);
        LOGGER.info("Account creation request received");

        AccountCreationResponse response = userService.create(request);

//        LOGGER.info("Account creation with ref. no. {} successful!", referenceNumber);
        LOGGER.info("Account creation request successful!");

        return ResponseEntity.ok(response);
    }

    // Verify Account
    @PostMapping("verify")
    public ResponseEntity<GenericResponse> verify (
            @Valid @RequestBody VerificationRequest request) throws InvalidVerificationRequestException {
//        LOGGER.info("Account verification request with ref. no. {} received", referenceNumber);
        LOGGER.info("Account verification request received");

        userService.verify(request.getEmail(), request.getVerificationCode());

//        LOGGER.info("Account verification with ref. no. {} successful!", referenceNumber);
        LOGGER.info("Account verification request successful!");

        return ResponseEntity.ok(new GenericResponse("Verification success!"));
    }

    // Authenticate Account
    @PostMapping("authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate (
            @Valid @RequestBody AuthenticationRequest request) throws InvalidAuthenticationRequestException {
//        LOGGER.info("Account authentication request with ref. no. {} received", referenceNumber);
        LOGGER.info("Account authentication request received");

        AuthenticationResponse response = userService.authenticate(request.getEmail(), request.getPassword());

//        LOGGER.info("Account authentication request with ref. no. {} successful!", referenceNumber);
        LOGGER.info("Account authentication request successful!");

        return ResponseEntity.ok(response);
    }

    // Get Account
    @GetMapping("{id}")
    public ResponseEntity<UserData> getUser(@PathVariable("id") String userId) throws UserNotFoundException {
        return ResponseEntity.ok(userService.getAccount(userId));
    }
}