package pl.practice.refactoringexample.example2;

import com.opencsv.CSVReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

class CsvImporter {

    public static final String PERSON_AGE_ERROR = "Person must have age";

    public static void main(String[] args) throws IOException, URISyntaxException {
        new CsvImporter().importCsv();
    }

    public void importCsv() throws URISyntaxException, IOException {
        final BufferedReader newBufferedReader = Files.newBufferedReader(Paths.get(ClassLoader.getSystemResource("source.csv").toURI()));
        final CSVReader csvReader = new CSVReader(newBufferedReader);

        importPersonData(csvReader);
    }

    private void importPersonData(CSVReader csvReader) {
        csvReader.forEach(line -> {

            final Person person =
                    new Person(
                            getName(line, 0, "Person must have firstName"),
                            getName(line, 1, "Person must have lastName"),
                            getAge(line));

            System.out.println(person.getFirstName() + " " + person.getLastName() + " " + person.getAge());

        });
    }

    private void checkAge(int age) {
        if (age < 0 || age > 120){
            throw new RuntimeException("Age must be value between 0 and 120");
        }
    }

    private int getAge(String[] line) {
        int age;
        if (line[2] == null || line[2].isEmpty()){
            throw new RuntimeException(PERSON_AGE_ERROR);
        } else {
            try {
                age = Integer.parseInt(line[2]);
            } catch (NumberFormatException exception){
                throw new RuntimeException("Age is not valid", exception);
            }
        }
        checkAge(age);
        return age;
    }

    private String getName(String[] line, int i, String s) {
        String name;
        if (line[i] == null || line[i].isEmpty()) {
            throw new RuntimeException(s);
        } else {
            name = line[i];
        }
        return name;
    }

}
