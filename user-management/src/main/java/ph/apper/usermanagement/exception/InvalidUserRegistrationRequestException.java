package ph.apper.usermanagement.exception;

public class InvalidUserRegistrationRequestException extends Exception{
    public InvalidUserRegistrationRequestException(String message) {
        super(message);
    }

    public InvalidUserRegistrationRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
