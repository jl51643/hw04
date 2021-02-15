package hr.fer.oprpp1.hw04.db;

import java.util.List;

/**
 * Model of query filter
 */
public class QueryFilter implements IFilter {

    private List<ConditionalExpression> list;

    public QueryFilter(List<ConditionalExpression> list) {
        this.list = list;
    }

    /**
     * Returns true if given filter accepts given record
     *
     * @param record record in database
     * @return returns true if given filter accepts given record
     */
    @Override
    public boolean accepts(StudentRecord record) {
        for (ConditionalExpression expression : this.list) {
            if (!expression.getComparisonOperator().satisfied(expression.getFieldGetter().get(record), expression.getStringLiteral()))
                return false;
        }
        return true;
    }
}
