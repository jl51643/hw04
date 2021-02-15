package hr.fer.oprpp1.hw04.db;

import java.util.Objects;

/**
 * Model of record in database
 */
public class StudentRecord {

    /**
     * jmbag of stident
     */
    private String jmbag;

    /**
     * Last name of student
     */
    private String lastName;

    /**
     * First name of student
     */
    private String firstName;

    /**
     * Student's grade
     */
    private int finalGrade;

    /**
     * Constructing student record
     *
     * @param jmbag jmbag of stident
     * @param lastName last name of student
     * @param firstName first name of student
     * @param finalGrade student's grade
     */
    public StudentRecord(String jmbag, String lastName, String firstName, int finalGrade) {
        this.jmbag = jmbag;
        this.lastName = lastName;
        this.firstName = firstName;
        this.finalGrade = finalGrade;
    }

    /**
     * @return returns jmbag of student
     */
    public String getJmbag() {
        return jmbag;
    }

    /**
     * @return returns last name of student
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @return returns firs name of student
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @return returns student's grade
     */
    public int getFinalGrade() {
        return finalGrade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentRecord that = (StudentRecord) o;
        return Objects.equals(jmbag, that.jmbag);
    }

    @Override
    public int hashCode() {
        return Objects.hash(jmbag);
    }

    @Override
    public String toString() {
        return "| " + jmbag + " | " + lastName + " | " + firstName + " | " + finalGrade + " |";
    }
}
