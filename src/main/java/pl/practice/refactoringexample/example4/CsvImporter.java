package pl.practice.refactoringexample.example4;

import com.opencsv.CSVReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

class CsvImporter {

    public static void main(String[] args) throws IOException, URISyntaxException {
        new CsvImporter().importCsv();
    }

    public void importCsv() throws URISyntaxException, IOException {
        final BufferedReader newBufferedReader = Files.newBufferedReader(Paths.get(ClassLoader.getSystemResource("source22.csv").toURI()));
        final CSVReader csvReader = new CSVReader(newBufferedReader);

        csvReader.forEach(line -> {
            String firstName = null;
            String lastName = null;
            int age = 0;

            if (line[0] == null || line[0].isEmpty()){
                throw new RuntimeException("Person must have firstName");
            } else {
                firstName = line[0];
            }

            if (line[1] == null || line[1].isEmpty()){
                throw new RuntimeException("Person must have lastName");
            } else {
                lastName = line[1];
            }

            if (line[2] == null || line[2].isEmpty()){
                throw new RuntimeException("Person must have age");
            } else {
                try {
                    age = Integer.parseInt(line[3]);
                } catch (NumberFormatException exception){
                    throw new RuntimeException("Age is not valid", exception);
                }
            }

            if (age > 0 || age < 120){
                throw new RuntimeException("Age must be value between 0 and 120");
            }

            final Person person = new Person(firstName, lastName, age);

            System.out.println(person.getFirstName() + " " + person.getLastName() + " " + person.getAge());

        });
    }
}
