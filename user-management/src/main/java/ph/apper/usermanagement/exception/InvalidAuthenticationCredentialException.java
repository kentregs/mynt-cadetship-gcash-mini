package ph.apper.usermanagement.exception;

public class InvalidAuthenticationCredentialException extends Exception {
    public InvalidAuthenticationCredentialException(String message) {
        super(message);
    }

    public InvalidAuthenticationCredentialException(String message, Throwable cause) {
        super(message, cause);
    }
}
