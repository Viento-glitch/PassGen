package main;

public class CesarException extends Exception {
    public CesarException(String message) {
        super(message);
    }

    public CesarException(String message, Throwable cause) {
        super(message, cause);
    }
}
