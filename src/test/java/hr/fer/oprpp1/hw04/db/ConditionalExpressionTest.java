package hr.fer.oprpp1.hw04.db;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConditionalExpressionTest {

    @Test
    void getFieldGetter() {
        StudentRecord record = new StudentRecord("123", "Horvat", "Ivan", 2);
        ConditionalExpression c = new ConditionalExpression(new FieldValueGetters().FIRST_NAME, "123", new ComparisonOperators().GREATER);
        assertEquals("Ivan", c.getFieldGetter().get(record));

    }

    @Test
    void getStringLiteral() {
        StudentRecord record = new StudentRecord("123", "Horvat", "Ivan", 2);
        ConditionalExpression c = new ConditionalExpression(new FieldValueGetters().FIRST_NAME, "123", new ComparisonOperators().GREATER);
        assertEquals("123", c.getStringLiteral());
    }

    @Test
    void getComparisonOperator() {
        StudentRecord record = new StudentRecord("123", "Horvat", "Ivan", 2);
        ConditionalExpression c = new ConditionalExpression(new FieldValueGetters().FIRST_NAME, "123", new ComparisonOperators().GREATER);
        assertTrue(c.getComparisonOperator().satisfied("Ivan", "Boris"));
    }
}