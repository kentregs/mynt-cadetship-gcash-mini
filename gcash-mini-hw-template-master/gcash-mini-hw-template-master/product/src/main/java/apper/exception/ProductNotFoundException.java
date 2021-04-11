package apper.exception;

public class ProductNotFoundException extends Exception{
    public ProductNotFoundException (String message) {
        super(message);
    }

    public ProductNotFoundException (String message, Throwable cause) {
        super(message, cause);
    }
}