package ph.apper.exception;

public class TransferAmountRequestException extends Exception{
    public TransferAmountRequestException(String message) {
        super(message);
    }

    public TransferAmountRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}