package hr.fer.oprpp1.hw04.db;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class StudentDatabaseTest {

    @Test
    public void testConstructor() {

        List<String> data = new LinkedList<>();
        data.add("123\tLukačević\tJosip\t5");
        data.add("123\tNepoznat\tNetko\t3");
        assertThrows(IllegalArgumentException.class , () -> {StudentDatabase database = new StudentDatabase(data);});

    }

    @Test
    public void forJMBAG() throws IOException {
        List<String> lines = Files.readAllLines(
                Paths.get("./database.txt"),
                StandardCharsets.UTF_8
        );
        StudentDatabase studentDatabase = new StudentDatabase(lines);
        assertEquals("0000000001", studentDatabase.forJMBAG("0000000001").getJmbag());
    }

    @Test
    public void filter() throws IOException {

        List<String> lines = Files.readAllLines(
                Paths.get("./database.txt"),
                StandardCharsets.UTF_8
        );
        StudentDatabase studentDatabase = new StudentDatabase(lines);
        List<StudentRecord> allStudents = studentDatabase.filter(new AlwaysTrue());
        List<StudentRecord> nonStudents = studentDatabase.filter(new AlwaysFalse());

        allStudents.stream().forEach(System.out::println);
        System.out.println("\n ============================= \n");
        nonStudents.stream().forEach(System.out::println);
        System.out.println("\n ============================= \n");

    }

    private class AlwaysTrue implements IFilter {

        @Override
        public boolean accepts(StudentRecord record) {
            return true;
        }
    }

    private class AlwaysFalse implements IFilter {

        @Override
        public boolean accepts(StudentRecord record) {
            return false;
        }
    }
}