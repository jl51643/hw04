package hr.fer.oprpp1.hw04.db;

/**
 * Exception thrown when query parser comes into unexpected state
 */
public class ParserException extends RuntimeException {

    /**
     * Constructing new parser exception
     *
     * @param message message with exception
     */
    public ParserException(String message) {
        super(message);
    }
}
