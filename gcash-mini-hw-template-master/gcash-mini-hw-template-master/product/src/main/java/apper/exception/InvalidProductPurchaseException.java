package apper.exception;

public class InvalidProductPurchaseException extends Exception{
    public InvalidProductPurchaseException (String message) {
        super(message);
    }

    public InvalidProductPurchaseException (String message, Throwable cause) {
        super(message, cause);
    }
}