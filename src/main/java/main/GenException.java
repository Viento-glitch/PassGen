package main;

public class GenException extends Exception {
    public GenException(String message, Throwable cause) {
        super(message, cause);
    }

    public GenException(String massage) {
        super(massage);
    }
}
