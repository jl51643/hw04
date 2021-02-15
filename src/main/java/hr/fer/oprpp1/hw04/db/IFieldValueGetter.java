package hr.fer.oprpp1.hw04.db;

/**
 * Strategy pattern for obtaining a requested field value
 */
public interface IFieldValueGetter {

    /**
     * Returns value of field in given record
     *
     * @param record record in database
     * @return returns value of field in given record
     */
    String get(StudentRecord record);
}
