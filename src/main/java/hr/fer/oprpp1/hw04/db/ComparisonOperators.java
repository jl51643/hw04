package hr.fer.oprpp1.hw04.db;

import java.util.Arrays;

/**
 * Model of comparison operators
 */
public class ComparisonOperators {

    /**
     * Operator "<"
     */
    public static final IComparisonOperator LESS = (value1, value2) -> {return value1.compareTo(value2) < 0;};

    /**
     * Operator "<="
     */
    public static final IComparisonOperator LESS_OR_EQUALS = (value1, value2) -> {return value1.compareTo(value2) <= 0;};

    /**
     * Operator ">"
     */
    public static final IComparisonOperator GREATER = (value1, value2) -> {return value1.compareTo(value2) > 0;};

    /**
     * Operator ">="
     */
    public static final IComparisonOperator GREATER_OR_EQUALS = (value1, value2) -> {return value1.compareTo(value2) >= 0;};

    /**
     * Operator "="
     */
    public static final IComparisonOperator EQUALS = (value1, value2) -> {return value1.compareTo(value2) == 0;};

    /**
     * Operator "!="
     */
    public static final IComparisonOperator NOT_EQUALS = (value1, value2) -> {return value1.compareTo(value2) != 0;};

    /**
     * Operator "LIKE"
     */
    public static final IComparisonOperator LIKE = (value1, value2) -> {
        if (!value2.contains("*"))
            return ComparisonOperators.EQUALS.satisfied(value1, value2);

        long countOfWildcardSymbols = value2.chars().filter(c -> c == '*').count();
        if (countOfWildcardSymbols > 1l)
            throw new IllegalArgumentException("Wildcard \"*\" can be used only once in string literal.");

        if (value2.endsWith("*")) {
            return value1.startsWith(value2.substring(0, value2.length()-1));
        }

        String[] s = value2.split("\\*");

        if (s[0].equals(""))
            return value1.endsWith(/*value2*/s[1]);

        if ((s[0].length() + s[1].length()) > value1.length())
            return false;

        if (value1.startsWith(s[0]) && value1.endsWith(s[1]))
            return true;
        return false;
    };

}
