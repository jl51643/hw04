package hr.fer.oprpp1.hw04.db;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Model of database
 */
public class StudentDatabase {

    /**
     * List of records in database
     */
    private List<StudentRecord> studentRecords;

    /**
     * Map of database records indexed by jmbag
     */
    private Map<String, StudentRecord> index;

    /**
     * Constructing database
     *
     * @param database list of string records in database
     * @throws IllegalArgumentException if occurred duplicated jmbag or invalid final grade
     */
    public StudentDatabase(List<String> database) {

        studentRecords = new LinkedList<>();

        index = new HashMap<>();

        for (String s : database) {
            String[] record = s.split("\\t");
            if (record[0].equals("")) {
                throw new IllegalArgumentException("JMBAG must be given");
            }
            if (index.containsKey(record[0])) {
                throw new IllegalArgumentException("Student with JMBAG " + record[0] + "already exists");
            }

            if (Integer.parseInt(record[record.length-1]) < 1 || Integer.parseInt(record[record.length-1]) > 5)
                throw new IllegalArgumentException("Final grade must be from interval [1-5]");
            StudentRecord sr = new StudentRecord(record[0], record[1], record[2], Integer.parseInt(record[record.length-1]));
            studentRecords.add(sr);
            index.put(record[0], sr);
        }

    }

    /**
     * Returns <code>StudentRecord</code> with given jmbag
     *
     * @param jmbag jmbag of searched student
     * @return returns <code>StudentRecord</code> with given jmbag
     */
    public StudentRecord forJMBAG(String jmbag) {
        if (!this.index.containsKey(jmbag))
            return null;
        return index.get(jmbag);
    }

    /**
     * Returns list of records which filter accepts
     *
     * @param filter filter of records
     * @return returns list of records which filter accepts
     */
    public List<StudentRecord> filter(IFilter filter) {
        List<StudentRecord> tmp = new LinkedList<>();
        try {
            for (StudentRecord sr : studentRecords) {
                    if (filter.accepts(sr))
                        tmp.add(sr);
            }
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }

        return tmp;
    }
}
