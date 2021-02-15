package hr.fer.oprpp1.hw04.db;

/**
 * Model of comparison expression
 */
public class ConditionalExpression {

    /**
     * Field in database
     */
    private IFieldValueGetter fieldGetter;

    /**
     * String which is compared with given field in database
     */
    private String stringLiteral;

    /**
     * Operator of comparison
     */
    private  IComparisonOperator comparisonOperator;

    /**
     * Constructing conditional expression
     *
     * @param fieldGetter field in database
     * @param stringLiteral string which is compared with given field in database
     * @param comparisonOperator operator of comparison
     */
    public ConditionalExpression(IFieldValueGetter fieldGetter, String stringLiteral, IComparisonOperator comparisonOperator) {
        this.fieldGetter = fieldGetter;
        this.stringLiteral = stringLiteral;
        this.comparisonOperator = comparisonOperator;
    }

    /**
     * @return returns this fieldGetter
     */
    public IFieldValueGetter getFieldGetter() {
        return fieldGetter;
    }

    /**
     * @return returns this stringLiteral
     */
    public String getStringLiteral() {
        return stringLiteral;
    }

    /**
     * @return returns this comparisonOperator
     */
    public IComparisonOperator getComparisonOperator() {
        return comparisonOperator;
    }
}
