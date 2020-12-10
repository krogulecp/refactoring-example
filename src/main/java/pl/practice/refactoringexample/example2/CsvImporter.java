package pl.practice.refactoringexample.example2;

import com.opencsv.CSVReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

class CsvImporter {

    public static final String FIRST_NAME = "Person must have firstName";
    public static final String LAST_NAME = "Person must have lastName";
    public static final String AGE = "Person must have age";
    public static final String AGE_IS_NOT_VALID = "Age is not valid";
    public static final String BETWEEN_0_AND_120 = "Age must be value between 0 and 120";
    private String name;

    public static void main(String[] args) throws IOException, URISyntaxException {
        new CsvImporter().importCsv();
    }

    public void importCsv() throws URISyntaxException, IOException {
        final CSVReader csvReader = getReader();

        csvReader.forEach(line -> {
            String firstName = null;
            String lastName = null;
            int age = 0;
            check(line);

        });
    }

    private void check(String[] line) {
        int age;
        String lastName;
        String firstName;
        if (line[0] == null || line[0].isEmpty()){
            throw new RuntimeException(FIRST_NAME);
        } else {
            firstName = line[0];
        }

        if (line[1] == null || line[1].isEmpty()){
            throw new RuntimeException(LAST_NAME);
        } else {
            lastName = line[1];
        }

        if (line[2] == null || line[2].isEmpty()){
            throw new RuntimeException(AGE);
        } else {
            try {
                age = Integer.parseInt(line[2]);
            } catch (NumberFormatException exception){
                throw new RuntimeException(AGE_IS_NOT_VALID, exception);
            }
        }

        if (age < 0 || age > 120){
            throw new RuntimeException(BETWEEN_0_AND_120);
        }

        final Person person = new Person(firstName, lastName, age);

        System.out.println(person.getFName() + " " + person.getLName() + " " + person.getAge());
    }

    private CSVReader getReader() throws IOException, URISyntaxException {
        name = "source.csv";
        final BufferedReader newBufferedReader = Files.newBufferedReader(Paths.get(ClassLoader.getSystemResource(name).toURI()));
        return new CSVReader(newBufferedReader);
    }
}
