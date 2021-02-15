package hr.fer.oprpp1.hw04.db;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class QueryParserTest {

    @Test
    public void testIsDirectQuery() {
        QueryParser parser = new QueryParser("query jmbag = \"123\"");
        assertTrue(parser.isDirectQuery());
        parser = new QueryParser("query jmbag < \"123\"");
        assertFalse(parser.isDirectQuery());
        parser = new QueryParser("query firstName = \"Abc\"");
        assertFalse(parser.isDirectQuery());
    }

    @Test
    void getQueriedJMBAG() {
        QueryParser parser = new QueryParser("query jmbag = \"123\"");
        assertEquals("123", parser.getQueriedJMBAG());
        parser = new QueryParser("query firstName < \"ABC\"");
        QueryParser finalParser = parser;
        assertThrows(IllegalStateException.class, () -> finalParser.getQueriedJMBAG());
    }

    @Test
    void getQuery() {
        QueryParser parser = new QueryParser("query jmbag = \"123\"");
        List<ConditionalExpression> list = parser.getQuery();
        assertTrue(list.get(0).getComparisonOperator().satisfied(list.get(0).getFieldGetter().get(new StudentRecord("123", "Horvat", "Ivan", 3)), list.get(0).getStringLiteral()));
    }
}