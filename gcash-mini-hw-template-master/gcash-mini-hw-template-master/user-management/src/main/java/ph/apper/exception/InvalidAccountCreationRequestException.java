package ph.apper.exception;

public class InvalidAccountCreationRequestException extends Exception{
    public InvalidAccountCreationRequestException(String message) {
        super(message);
    }

    public InvalidAccountCreationRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}