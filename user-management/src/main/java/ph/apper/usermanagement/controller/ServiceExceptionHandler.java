package ph.apper.usermanagement.controller;

import ph.apper.usermanagement.exception.InvalidAuthenticationCredentialException;
import ph.apper.usermanagement.exception.InvalidUserRegistrationRequestException;
import ph.apper.usermanagement.exception.InvalidVerificationRequestException;
import ph.apper.usermanagement.exception.UserNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ph.apper.usermanagement.payload.GenericResponse;

import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class ServiceExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceExceptionHandler.class);

    @ExceptionHandler({ConstraintViolationException.class, MissingRequestHeaderException.class})
    public ResponseEntity<GenericResponse> handleConstraintViolationException(Exception e) {
        LOGGER.error("Constraints violation", e);
        GenericResponse response = new GenericResponse(e.getMessage());
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<GenericResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        LOGGER.error("Invalid method argument", e);
        FieldError fieldError = e.getBindingResult().getFieldError();

        GenericResponse response = new GenericResponse(fieldError.getField() + ": " + fieldError.getDefaultMessage());
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler({
            InvalidAuthenticationCredentialException.class,
            InvalidUserRegistrationRequestException.class,
            InvalidVerificationRequestException.class,
            UserNotFoundException.class
    })
    public ResponseEntity<GenericResponse> handleLogicExceptions(Exception e) {
        LOGGER.error("Service error", e);
        GenericResponse response = new GenericResponse(e.getMessage());

        if (e instanceof UserNotFoundException) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.badRequest().body(response);
    }
}
