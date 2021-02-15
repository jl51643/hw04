package hr.fer.oprpp1.hw04.db;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FieldValueGettersTest {

    @Test
    public void testFirstNameGetter() {
        FieldValueGetters f = new FieldValueGetters();
        assertEquals("Petar", f.FIRST_NAME.get(new StudentRecord("123", "Peric", "Petar", 4)));
    }

    @Test
    public void testLastNameGetter() {
        FieldValueGetters f = new FieldValueGetters();
        assertEquals("Peric", f.LAST_NAME.get(new StudentRecord("123", "Peric", "Petar", 4)));
    }

    @Test
    public void testJMBAGGetter() {
        FieldValueGetters f = new FieldValueGetters();
        assertEquals("123", f.JMBAG.get(new StudentRecord("123", "Peric", "Petar", 4)));
    }
}