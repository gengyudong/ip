package geng.ui;

/**
 * Custom exception class to handle errors in the Duke application.
 * This class extends the {@link Exception} class and allows for
 * more specific error handling in the application.
 */
public class GengException extends Exception {
    /**
     * Constructs a new GengException with the specified detail message.
     *
     * @param message The detail message of the Exception.
     */
    public GengException(String message) {
        super(message);
    }
}
