package hr.fer.oprpp1.hw04.db.lexer;

/**
 * Exception thrown when lexer comes into unexpected state
 */
public class LexerException extends RuntimeException{

    /**
     * Constructing lexer exception
     *
     * @param message message with exception
     */
    public LexerException(String message) {
        super(message);
    }
}
