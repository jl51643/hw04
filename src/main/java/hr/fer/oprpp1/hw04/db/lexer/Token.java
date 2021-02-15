package hr.fer.oprpp1.hw04.db.lexer;

/**
 * Model of token of lexical analyze
 */
public class Token {

    /**
     * Type of token
     */
    private TokenType type;

    /**
     * Value of token
     */
    private String value;

    /**
     * Constructs token
     *
     * @param type type of token
     * @param value value of token
     */
    public Token(TokenType type, String value) {
        this.type = type;
        this.value = value;
    }

    /**
     * @return returns type of token
     */
    public TokenType getType() {
        return type;
    }

    /**
     * @return returns value of token
     */
    public String getValue() {
        return value;
    }
}
