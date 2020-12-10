package pl.practice.refactoringexample.example2;

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
        final BufferedReader newBufferedReader = Files.newBufferedReader(Paths.get(ClassLoader.getSystemResource("source.csv").toURI()));
        final CSVReader csvReader = new CSVReader(newBufferedReader);

        csvReader.forEach(this::showPersonInfo);
    }

    private void showPersonInfo(String[] line) {
        String firstName = parseFirstName(line[0]);
        String lastName = parseLastName(line[1]);
        int age = parseAge(line[2]);

        checkAge(age);

        final Person person = new Person(firstName, lastName, age);

        System.out.println(person);
    }

    private String parseFirstName(String firstName) {
        if (firstName == null || firstName.isEmpty()) {
            throw new RuntimeException("Person must have firstName");
        }
        return firstName;
    }

    private String parseLastName(String lastName) {
        if (lastName == null || lastName.isEmpty()) {
            throw new RuntimeException("Person must have lastName");
        }
        return lastName;
    }

    private int parseAge(String ageString) {
        if (ageString == null || ageString.isEmpty()) {
            throw new RuntimeException("Person must have age");
        }
        try {
            return Integer.parseInt(ageString);
        } catch (NumberFormatException exception) {
            throw new RuntimeException("Age is not valid", exception);
        }
    }

    private void checkAge(int age) {
        if (age < 0 || age > 120) {
            throw new RuntimeException("Age must be value between 0 and 120");
        }
    }
}
