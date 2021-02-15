package hr.fer.oprpp1.hw04.db;

import hr.fer.oprpp1.hw04.db.formater.RecordFormater;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class StudentDB {

    public static void main(String[] args) throws IOException {

        /*File f = new File("c:/oprpp1/domaće zadaće/hw04-0036516431/src/main/resources");
        File[] files = f.listFiles();
        for (File file : files)
            System.out.println(file.getName());*/

        List<String> lines = Files.readAllLines(
                Paths.get("./database.txt"),
                StandardCharsets.UTF_8
        );

        StudentDatabase database = new StudentDatabase(lines);

        Scanner sc = new Scanner(System.in);
        while (true) {
            String query = sc.nextLine();
            if (query.equals("exit")) {
                System.out.println("Goodbye!");
                System.exit(0);
            }
            QueryParser queryParser = new QueryParser(query);

            if (queryParser.isDirectQuery()) {
                List<StudentRecord> records = new LinkedList<>();
                records.add(database.forJMBAG(queryParser.getQueriedJMBAG()));
                List<String> outputs = RecordFormater.format(records);
                System.out.println("Using index for record retrieval.");
                outputs.forEach(System.out::println);
                //formatDirectQueryPrint(database, queryParser.getQueriedJMBAG());
            } else {
                QueryFilter filter = new QueryFilter(queryParser.getQuery());
                List<StudentRecord> records = database.filter(filter);
                List<String> outputs = RecordFormater.format(records);
                outputs.forEach(System.out::println);
            }
        }
    }
}
