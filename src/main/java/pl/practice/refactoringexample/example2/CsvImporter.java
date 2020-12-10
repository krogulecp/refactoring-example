package pl.practice.refactoringexample.example2;

import com.opencsv.CSVReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

class CsvImporter {

    public static final String SOURCE = "source.csv";

    public static void main(String[] args) throws IOException, URISyntaxException {
        new CsvImporter().importCsv();
    }

    public void importCsv() throws URISyntaxException, IOException {
        final BufferedReader newBufferedReader = Files.newBufferedReader(getPath());
        final CSVReader csvReader = new CSVReader(newBufferedReader);

        csvReader.forEach(line -> {
            getLine(line);
        });
    }

    private void getLine(String[] line) {
        String firstName = null;
        String lastName = null;
        int age = 0;

        firstName = getFirstName(line, 0, "Person must have firstName");
        lastName = getLastName(line);
        age = getAge(line);
        checkAgeRange(age);
        final Person person = new Person(firstName, lastName, age);
        printPersonInfo(person);
    }

    private void printPersonInfo(Person person) {
        System.out.println(person.getFirstName() + " " + person.getLastName() + " " + person.getAge());
    }

    private void checkAgeRange(int age) {
        if (age < 0 || age > 120){
            throw new RuntimeException("Age must be value between 0 and 120");
        }
    }

    private int getAge(String[] line) {
        int age;
        if (line[2] == null || line[2].isEmpty()){
            throw new RuntimeException("Person must have age");
        } else {
            try {
                age = Integer.parseInt(line[2]);
            } catch (NumberFormatException exception){
                throw new RuntimeException("Age is not valid", exception);
            }
        }
        return age;
    }

    private String getLastName(String[] line) {
        String lastName;
        if (line[1] == null || line[1].isEmpty()){
            throw new RuntimeException("Person must have lastName");
        } else {
            lastName = line[1];
        }
        return lastName;
    }

    private String getFirstName(String[] line, int i, String s) {
        String firstName;
        if (line[i] == null || line[i].isEmpty()) {
            throw new RuntimeException(s);
        } else {
            firstName = line[i];
        }
        return firstName;
    }


    private Path getPath() throws URISyntaxException {
        return Paths.get(ClassLoader.getSystemResource(SOURCE).toURI());
    }
}
