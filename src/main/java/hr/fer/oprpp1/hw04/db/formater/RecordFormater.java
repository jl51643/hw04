package hr.fer.oprpp1.hw04.db.formater;

import hr.fer.oprpp1.hw04.db.StudentRecord;

import java.util.LinkedList;
import java.util.List;
import java.util.OptionalInt;

public class RecordFormater {

    public static List<String> format(List<StudentRecord> records) {

        List<String> outputs = new LinkedList<>();

        if (records.size() == 0) {
            outputs.add("Records selected :\t" + records.size());
            return outputs;
        }

        OptionalInt maxLastNameLength = records.stream()
                .mapToInt(r -> r.getLastName().length())
                .max();
        OptionalInt maxFirstNameLength = records.stream()
                .mapToInt(r -> r.getFirstName().length())
                .max();
        String separator = generateSeparator(maxLastNameLength.orElse(0), maxFirstNameLength.orElse(0));
        outputs.add(separator);
        for (StudentRecord record : records) {
            String output = "| " + record.getJmbag() + " | "
                    + record.getLastName()
                    + fillWithBlanks(record.getLastName().length(), maxLastNameLength.getAsInt())
                    + " | " + record.getFirstName()
                    +  fillWithBlanks(record.getFirstName().length(), maxFirstNameLength.getAsInt())
                    + " | "
                    + record.getFinalGrade() + " |";
            outputs.add(output);
        }
        outputs.add(separator);
        outputs.add("Records selected :\t" + records.size());

        return outputs;
    }

    private static String fillWithBlanks(int value, int toFill) {
        return " ".repeat(toFill-value);
    }

    private static String generateSeparator(int maxLastNameLength, int maxFirstNameLength) {
        final int JMBAG_LENGTH = 10;
        return "+=" + "=".repeat(JMBAG_LENGTH) +
                "=+=" + "=".repeat(maxLastNameLength) +
                "=+=" + "=".repeat(maxFirstNameLength) + "=+===+";
    }
}
