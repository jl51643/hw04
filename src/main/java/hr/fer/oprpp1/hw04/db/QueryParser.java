package hr.fer.oprpp1.hw04.db;

import hr.fer.oprpp1.hw04.db.lexer.LexerException;
import hr.fer.oprpp1.hw04.db.lexer.QueryLexer;
import hr.fer.oprpp1.hw04.db.lexer.Token;
import hr.fer.oprpp1.hw04.db.lexer.TokenType;
import java.util.LinkedList;
import java.util.List;


/**
 * Model of query parser
 */
public class QueryParser {

    /**
     * text of query
     */
    private String query;

    /**
     * lexical analyzer
     */
    private QueryLexer lexer;

    /**
     * Constructing query parser
     *
     * @param query text of query
     */
    public QueryParser(String query) {
        this.query = query;
        //this.lexer = new QueryLexer(query);
    }

    /**
     * Returns true if query is in form jmbag = "xxx"
     *
     * @return returns true if query is in form jmbag = "xxx"
     */
    public boolean isDirectQuery() {
        List<Token> tokens = parse();
        /*Direct queries have 3 elements and END_OF_LINE token*/
        if (tokens.size() != 4)
            return false;
        /*First token must be attribute*/
        if (!tokens.get(0).getType().equals(TokenType.ATTRIBUTE))
            return false;
        /*Attribute must be jmbag*/
        else if (!tokens.get(0).getValue().equals("jmbag"))
            return false;
        /*Second attribute must be operator*/
        if (!tokens.get(1).getType().equals(TokenType.OPERATOR))
            return false;
        /*operator must be "="*/
        else if (!tokens.get(1).getValue().equals("="))
            return false;
        /*Third token must be string literal*/
        if (!tokens.get(2).getType().equals(TokenType.STRING_LITERAL))
            return false;
        return true;
    }

    /**
     * Returns value of jmbag from query
     *
     * @return returns value of jmbag from query
     * @throws IllegalStateException if method is called for not direct query
     */
    public String getQueriedJMBAG() {
        if (!this.isDirectQuery())
            throw new IllegalStateException("Can not return queried jmbag because query is not direct query");
        List<Token> tokens = parse();
        return tokens.get(2).getValue();
    }

    /**
     * Returns list of <code>ConditionalExpressions</code> from query
     *
     * @return returns list of <code>ConditionalExpressions</code> from query
     * @throws ParserException if parser comes into unexpected state
     */
    public List<ConditionalExpression> getQuery() {
        List<ConditionalExpression> query = new LinkedList<>();
        List<Token> tokens = parse();
        /*if query is direct query return it*/
        if (isDirectQuery()) {
            query.add(new ConditionalExpression(FieldValueGetters.JMBAG, tokens.get(2).getValue(), ComparisonOperators.EQUALS));
            return query;
        }
        /*else process query*/
        return query = processQuery(tokens);
    }

    /**
     * Processes given collection of tokens into list of <code>ConditionalExpression</code> extracted from query
     *
     * @param tokens collection of tokens
     * @return returns list of <code>ConditionalExpression</code> extracted from query
     * @throws ParserException if parser comes into unexpected state
     */
    private static List<ConditionalExpression> processQuery(List<Token> tokens) {
        List<ConditionalExpression> query = new LinkedList<>();
        int i = 0;
        while (i < tokens.size()) {
            IFieldValueGetter field = null;
            String stringLiteral = null;
            IComparisonOperator operator = null;
            if (tokens.get(i).getType().equals(TokenType.ATTRIBUTE))
                field = setField(tokens.get(i));
            if (tokens.get(++i).getType().equals(TokenType.OPERATOR))
                operator = setOperator(tokens.get(i));
            if (tokens.get(++i).getType().equals(TokenType.STRING_LITERAL)) {
                stringLiteral = tokens.get(i).getValue();
            } else
                throw new ParserException("Invalid token type. Expected string literal but got " + tokens.get(i).getType() + " " + tokens.get(i).getValue() + ".");
            i++;
            if (!(tokens.get(i).getType().equals(TokenType.LOGICAL_AND))
                    && !(tokens.get(i).getType().equals(TokenType.END_OF_LINE)))
                throw new ParserException("Invalid token type. Expected logical AND or END_OF_LINE but got " + tokens.get(i).getType() + " " + tokens.get(i).getValue() + ".");
            query.add(new ConditionalExpression(field, stringLiteral, operator));
            i++;
        }
        return query;
    }

    /**
     * Determines and returns operator of query
     *
     * @param token operator token
     * @return returns operator of query
     * @throws ParserException if parser comes into unexpected state
     */
    private static IComparisonOperator setOperator(Token token) {
        switch (token.getValue()) {
            case "<" -> {
                return ComparisonOperators.LESS;
            }
            case "<=" -> {
                return ComparisonOperators.LESS_OR_EQUALS;
            }
            case ">" -> {
                return ComparisonOperators.GREATER;
            }
            case ">=" -> {
                return ComparisonOperators.GREATER_OR_EQUALS;
            }
            case "=" -> {
                return ComparisonOperators.EQUALS;
            }
            case "!=" -> {
                return ComparisonOperators.NOT_EQUALS;
            }
            case "LIKE" -> {
                return ComparisonOperators.LIKE;
            }
            default -> throw new ParserException("Invalid token type. Expected operator but got " + token.getType() + " " + token.getValue() + ".");
        }
    }

    /**
     * Sets field of query
     *
     * @param token field token
     * @return returns field name of query
     * @throws ParserException if parser comes into unexpected state
     */
    private static IFieldValueGetter setField(Token token) {
        switch (token.getValue()) {
            case "firstName" -> {
                return FieldValueGetters.FIRST_NAME;
            }
            case "lastName" -> {
                return FieldValueGetters.LAST_NAME;
            }
            case "jmbag" -> {
                return FieldValueGetters.JMBAG;
            }
            default -> throw new ParserException("Invalid token type. Expected attribute but got " + token.getType() + " " + token.getValue() + ".");
        }
    }

    /**
     * Parses query into list of tokens
     *
     * @return returns list of tokens
     * @throws ParserException if given query could not be parsed
     */
    private List<Token> parse() {
        try {
            this.lexer = new QueryLexer(query);
        } catch (LexerException e) {
            System.err.println(e.getMessage());
            //e.printStackTrace();
        }
        List<Token> tokens = new LinkedList<>();
        if (lexer == null)
            throw new ParserException("Could not parse query " + this.query);
        while (lexer.hasNextToken())
            try {
                tokens.add(lexer.getNextToken());
            } catch (LexerException e) {
                System.err.println(e.getMessage());
                //e.printStackTrace();
                throw new ParserException("Problem occurred while parsing query");
            }
        return tokens;
    }
}
