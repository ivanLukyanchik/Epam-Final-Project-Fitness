package by.epam.fitness.util;

/**
 * The type Util exception.
 */
public class UtilException extends Exception {
    /**
     * Instantiates a new Util exception.
     */
    public UtilException() {
    }

    /**
     * Instantiates a new Util exception.
     *
     * @param message the message
     */
    UtilException(String message) {
        super(message);
    }

    /**
     * Instantiates a new Util exception.
     *
     * @param message the message
     * @param cause   the cause
     */
    UtilException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Instantiates a new Util exception.
     *
     * @param cause the cause
     */
    public UtilException(Throwable cause) {
        super(cause);
    }
}
