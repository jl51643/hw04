package hr.fer.oprpp1.hw04.db.lexer;

/**
 * Model of lexical analyzer
 */
public class QueryLexer {

    /**
     * Array of characters of query
     */
    private char[] data;

    /**
     * Lest generated token
     */
    private Token currentToken;

    /**
     * Current index in data array
     */
    private int currentIndex;

    /**
     * Constructing lexical analyzer
     *
     * @param query query to analyze
     * @throws LexerException if lexical analyzer gets invalid query
     */
    public QueryLexer(String query) {
        query = query.trim();
        if (!query.startsWith("query"))
            throw new LexerException("Expected to get query line but got " + query +".");
        /*Removing word "query" from query statement*/
        this.data = query.substring("query".length(), query.length()).toCharArray();
        this.currentIndex = 0;
        this.currentToken = getCurrentToken();

    }

    /**
     * @return Returns current token
     */
    public Token getCurrentToken() {
        return this.currentToken;
    }

    /**
     * Checks if there is more tokens to generate
     *
     * @return returns true if there is more tokens to generate
     */
    public boolean hasNextToken() {
        if (currentToken == null)
            return true;
        return this.currentToken.getType() != TokenType.END_OF_LINE;
    }

    /**
     * Generates next token
     *
     * @return returns next token
     * @throws LexerException if lexical analyzer comes into unexpected state
     */
    public Token getNextToken() {
        /*checks if there is more tokens to generate*/
        if (this.currentToken != null && this.currentToken.getType().equals(TokenType.END_OF_LINE)) {
            throw new LexerException("No more tokens to generate.");
        }
        if (this.currentIndex == this.data.length) {
            return this.currentToken = new Token(TokenType.END_OF_LINE, null);
        }
        if (currentIndex < data.length && Character.isWhitespace(data[currentIndex])) {
            skipWhitespaces();
        }
        /*generates new token of type ATTRIBUTE, OPERATOR (LIKE operator) or LOGICAL_AND */
        if (currentIndex < data.length && Character.isLetter(data[currentIndex])) {
            return currentToken = generateToken();
        }
        /*generates new token of type STRING_LITERAL*/
        if (currentIndex < data.length && data[currentIndex] == '"') {
            return this.currentToken = generateStringLiteralToken();
        }
        if (currentIndex < data.length) {
            switch (data[currentIndex]) {
                case '<', '>' -> {
                    /*generates < or <= operator token*/
                    return this.currentToken = generateOperatorToken();
                }
                case '=' -> {
                    return this.currentToken = new Token(TokenType.OPERATOR, Character.toString(data[currentIndex++]));
                }
                case '!' -> {
                    if (currentIndex + 1 < data.length && data[currentIndex + 1] == '=') {
                        String operatorValue = "";
                        operatorValue += data[currentIndex++];
                        operatorValue += data[currentIndex++];
                        return new Token(TokenType.OPERATOR, operatorValue);
                    } else if (currentIndex + 1 < data.length) {
                        throw new LexerException("Unexpected symbol " + data[currentIndex + 1]);
                    }
                }
            }
        }
        throw new LexerException("Unexpected symbol " + data[currentIndex] + ".");
    }

    /**
     * Returns new OPERATOR token
     *
     * @return returns new OPERATOR token
     */
    private Token generateOperatorToken() {
        String operatorValue = "";
        if (currentIndex + 1 < data.length && data[currentIndex + 1] != '=')
            operatorValue += data[currentIndex++];
        else if (currentIndex + 1 < data.length && data[currentIndex + 1] == '=')
            operatorValue += Character.toString(data[currentIndex++]) + Character.toString(data[currentIndex++]);

        return this.currentToken = new Token(TokenType.OPERATOR, operatorValue);
    }

    /**
     * Returns new STRING_LITERAL token
     *
     * @return returns new STRING_LITERAL token
     */
    private Token generateStringLiteralToken() {
        /*Skipping quote symbol*/
        currentIndex++;

        String tokenValue = "";
        while (currentIndex < data.length && data[currentIndex] != '"')
            tokenValue += data[currentIndex++];
        /*Skipping quote symbol*/
        currentIndex++;

        return this.currentToken = new Token(TokenType.STRING_LITERAL, tokenValue);
    }

    /**
     * Generates new token of type ATTRIBUTE, OPERATOR or LOGICAL_AND
     *
     * @return returns new token
     */
    private Token generateToken() {
        String tokenValue = "";
        while (currentIndex < data.length && Character.isLetter(data[currentIndex])) {
            tokenValue += data[currentIndex++];
        }
        TokenType type = determineTokenType(tokenValue);

        return new Token(type, tokenValue);
    }

    /** Returns type of token based on it's value
     * @param value value of token
     * @return returns type of token based on it's value
     */
    private TokenType determineTokenType(String value) {
        if (value.equalsIgnoreCase("AND"))
            return TokenType.LOGICAL_AND;
         else if (value.equals("LIKE"))
            return TokenType.OPERATOR;
        else
            return TokenType.ATTRIBUTE;
    }

    /**
     * Skips whitespaces
     */
    private void skipWhitespaces() {
        while (currentIndex < data.length && Character.isWhitespace(data[currentIndex]))
            currentIndex++;
    }
}
