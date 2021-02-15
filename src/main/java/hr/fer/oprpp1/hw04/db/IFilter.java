package hr.fer.oprpp1.hw04.db;

/**
 * Model of filter
 */
public interface IFilter {

    /**
     * Returns true if given filter accepts given record
     *
     * @param record record in database
     * @return returns true if given filter accepts given record
     */
    boolean accepts(StudentRecord record);
}
