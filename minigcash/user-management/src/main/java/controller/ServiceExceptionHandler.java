package controller;

import exception.InvalidAccountCreationRequestException;
import exception.InvalidAuthenticationRequestException;
import exception.InvalidVerificationRequestException;
import exception.UserNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import payload.AuthenticationRequest;

import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class ServiceExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceExceptionHandler.class);

    @ExceptionHandler({ConstraintViolationException.class, MissingRequestHeaderException.class})
    public ResponseEntity<AuthenticationRequest> handleConstraintViolationException(Exception e) {
        LOGGER.error("Constraints violation", e);
        AuthenticationRequest response = new AuthenticationRequest(e.getMessage());
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<AuthenticationRequest> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        LOGGER.error("Invalid method argument", e);
        FieldError fieldError = e.getBindingResult().getFieldError();

        AuthenticationRequest response = new AuthenticationRequest(fieldError.getField() + ": " + fieldError.getDefaultMessage());
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler({
            InvalidAccountCreationRequestException.class,
            InvalidAuthenticationRequestException.class,
            InvalidVerificationRequestException.class,
            UserNotFoundException.class
    })
    public ResponseEntity<AuthenticationRequest> handleLogicExceptions(Exception e) {
        LOGGER.error("Service error", e);
        AuthenticationRequest response = new AuthenticationRequest(e.getMessage());

        if (e instanceof UserNotFoundException) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.badRequest().body(response);
    }
}
