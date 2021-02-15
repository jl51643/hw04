package hr.fer.oprpp1.hw04.db;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class QueryFilterTest {

    @Test
    public void testAccepts() {
        StudentRecord record = new StudentRecord("123", "Horvat", "Ivan", 5);
        QueryParser parser = new QueryParser("query jmbag = \"123\"");
        QueryFilter filter = new QueryFilter(parser.getQuery());
        assertTrue(filter.accepts(record));
        parser = new QueryParser("query jmbag > \"10\" and firstName>=\"A\"");
        filter = new QueryFilter(parser.getQuery());
        assertTrue(filter.accepts(record));

        parser = new QueryParser("query jmbag < \"10\" ");
        filter = new QueryFilter(parser.getQuery());
        assertFalse(filter.accepts(record));
    }
}