package ph.apper.usermanagement.controller;

import ph.apper.usermanagement.payload.*;
import ph.apper.usermanagement.exception.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ph.apper.usermanagement.service.UserService;

import javax.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("account")
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserRegistrationResponse> register(
            @Valid @RequestBody UserRegistrationRequest request,
            @RequestHeader("Reference-Number") String referenceNumber) throws InvalidUserRegistrationRequestException {
        LOGGER.info("User registration {} received", referenceNumber);

        UserRegistrationResponse response = userService.register(request);

        LOGGER.info("User registration {} successful", referenceNumber);

        return ResponseEntity.ok(response);
    }

    @PostMapping("verify")
    public ResponseEntity<GenericResponse> verify(
            @Valid @RequestBody VerificationRequest request,
            @RequestHeader("Reference-Number") String referenceNumber) throws InvalidVerificationRequestException {
        LOGGER.info("User verification {} received", referenceNumber);

        userService.verify(request.getEmail(), request.getVerificationCode());

        LOGGER.info("User verification {} successful", referenceNumber);

        return ResponseEntity.ok(new GenericResponse("verification success"));
    }

    @PostMapping("authenticate")
    public ResponseEntity<UserAuthenticationResponse> authenticate(
            @Valid @RequestBody AuthenticationRequest request,
            @RequestHeader("Reference-Number") String referenceNumber) throws InvalidAuthenticationCredentialException {

        LOGGER.info("User login {} received", referenceNumber);

        UserAuthenticationResponse accountId = userService.authenticate(request.getEmail(), request.getPassword());

        LOGGER.info("User authentication {} successful", referenceNumber);

        return ResponseEntity.ok(accountId);
    }

    @GetMapping("{id}")
    public ResponseEntity<GetUserResponse> getUser(@PathVariable("id") String userId) throws UserNotFoundException {
        return ResponseEntity.ok(userService.getUser(userId));
    }


}
