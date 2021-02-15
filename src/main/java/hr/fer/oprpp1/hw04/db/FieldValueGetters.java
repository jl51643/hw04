package hr.fer.oprpp1.hw04.db;

/**
 * Model of field value getter
 */
public class FieldValueGetters {

    /**
     * First Name field
     */
    public static final IFieldValueGetter FIRST_NAME = record -> {return record.getFirstName();};

    /**
     * Last Name field
     */
    public static final IFieldValueGetter LAST_NAME = record -> {return record.getLastName();};

    /**
     * JMBAG field
     */
    public static final IFieldValueGetter JMBAG = record -> {return record.getJmbag();};

}
