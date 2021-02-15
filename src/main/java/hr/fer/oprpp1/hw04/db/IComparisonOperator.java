package hr.fer.oprpp1.hw04.db;

/**
 * Strategy pattern for comparing values
 */
public interface IComparisonOperator {

    /**
     * Returns true if two strings satisfy comparison operation
     *
     * @param value1 string value to compare
     * @param value2 string value in database
     * @return returns true if two strings satisfy comparison operation
     */
    boolean satisfied(String value1, String value2);
}
