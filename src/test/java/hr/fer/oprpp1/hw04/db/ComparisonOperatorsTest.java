package hr.fer.oprpp1.hw04.db;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ComparisonOperatorsTest {

    @Test
    public void testLessOperator() {
        ComparisonOperators c = new ComparisonOperators();
        assertFalse(c.LESS.satisfied("Ivan", "Boris"));
        assertTrue(c.LESS.satisfied("ANA", "Zdenka"));

    }

    @Test
    public void testLessOrEqualsOperator() {
        ComparisonOperators c = new ComparisonOperators();
        assertFalse(c.LESS_OR_EQUALS.satisfied("Ivan", "Boris"));
        assertTrue(c.LESS_OR_EQUALS.satisfied("ANA", "Zdenka"));
        assertTrue(c.LESS_OR_EQUALS.satisfied("Josip", "Josip"));
    }

    @Test
    public void testGreaterOperator() {
        ComparisonOperators c = new ComparisonOperators();
        assertTrue(c.GREATER.satisfied("Ivan", "Boris"));
        assertFalse(c.GREATER.satisfied("ANA", "Zdenka"));

    }

    @Test
    public void testGreaterOrEqualsOperator() {
        ComparisonOperators c = new ComparisonOperators();
        assertTrue(c.GREATER_OR_EQUALS.satisfied("Ivan", "Boris"));
        assertFalse(c.GREATER_OR_EQUALS.satisfied("ANA", "Zdenka"));
        assertTrue(c.LESS_OR_EQUALS.satisfied("Josip", "Josip"));
    }

    @Test
    public void testEqualsOperator() {
        ComparisonOperators c = new ComparisonOperators();
        assertFalse(c.EQUALS.satisfied("Ivan", "Boris"));
        assertFalse(c.EQUALS.satisfied("ANA", "Zdenka"));
        assertTrue(c.EQUALS.satisfied("Josip", "Josip"));
    }

    @Test
    public void testNotEqualsOperator() {
        ComparisonOperators c = new ComparisonOperators();
        assertTrue(c.NOT_EQUALS.satisfied("Ivan", "Boris"));
        assertTrue(c.NOT_EQUALS.satisfied("ANA", "Zdenka"));
        assertFalse(c.NOT_EQUALS.satisfied("Josip", "Josip"));
    }

    @Test
    public void testLikeOperator() {
        ComparisonOperators c = new ComparisonOperators();
        assertTrue(c.LIKE.satisfied("Ivan", "I*n"));
        assertTrue(c.LIKE.satisfied("ANA", "*A"));
        assertTrue(c.LIKE.satisfied("Josip", "Josip"));
        assertFalse(c.LIKE.satisfied("AAA", "AA*AA"));
        assertTrue(c.LIKE.satisfied("AAAA", "AA*AA"));
        assertFalse(c.LIKE.satisfied("MARKO", "ma*"));
        assertTrue(c.LIKE.satisfied("MARKO", "MA*"));
        assertThrows(IllegalArgumentException.class, () -> c.LIKE.satisfied("ABC", "a*a*"));
    }
}