package ph.apper.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import ph.apper.exception.InvalidAccountCreationRequestException;
import ph.apper.exception.InvalidAuthenticationRequestException;
import ph.apper.exception.InvalidVerificationRequestException;
import ph.apper.exception.UserNotFoundException;
import ph.apper.payload.GenericResponse;

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
            InvalidAuthenticationRequestException.class,
            InvalidAccountCreationRequestException.class,
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

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public GenericResponse handleUncaughtExceptions(Exception e) {
        LOGGER.error("Internal error", e);
        return new GenericResponse(e.getMessage());
    }
}
