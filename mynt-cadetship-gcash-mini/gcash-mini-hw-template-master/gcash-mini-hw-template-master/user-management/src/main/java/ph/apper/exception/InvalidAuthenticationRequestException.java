package ph.apper.exception;

public class InvalidAuthenticationRequestException extends Exception {
    public InvalidAuthenticationRequestException(String message) {
        super(message);
    }

    public InvalidAuthenticationRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}