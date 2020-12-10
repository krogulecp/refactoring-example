package pl.practice.refactoringexample.example2;

import com.opencsv.CSVReader;
import org.apache.commons.lang3.StringUtils;

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

        csvReader.forEach(line -> {
            Person person = parseLine(line);
            System.out.println(person.getFirstName() + " " + person.getLastName() + " " + person.getAge());
        });
    }

    private Person parseLine(String line[]){
        validatePerson(line);

        String firstName = line[0];
        String lastName = line[1];
        int age = Integer.parseInt(line[2]);

        return new Person(firstName, lastName, age);
    }

    boolean validatePerson(String line[]){
        if (StringUtils.isNotBlank(line[0])){
            throw new RuntimeException("Person must have firstName");
        }
        if (StringUtils.isNotBlank(line[1])){
            throw new RuntimeException("Person must have lastName");
        }
        if (StringUtils.isNotBlank(line[2])){
            throw new RuntimeException("Person must have age");
        } else {

            try {
                int age = Integer.parseInt(line[2]);
                if (age < 0 || age > 120){
                    throw new RuntimeException("Age must be value between 0 and 120");
                }
            } catch (NumberFormatException exception){
                throw new RuntimeException("Age is not valid", exception);
            }
        }

    }
}
