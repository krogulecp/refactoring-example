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
        try(final CSVReader csvReader = new CSVReader(newBufferedReader)){
            csvReader.forEach(line -> {
                String firstName = prepareStringField(line[0], "firstName");
                String lastName = prepareStringField(line[1], "lastName");
                int age = AgeParser.parse(line[2]);
                final Person person = new Person(firstName, lastName, age);
                System.out.println(person.getFirstName() + " " + person.getLastName() + " " + person.getAge());
            });
        } catch (Exception e){
            System.out.println("Problem with reading file");
        }

    }

    private String prepareStringField(String fieldToParse, String fieldName) {
        String firstName;
        if (fieldToParse == null || fieldToParse.isEmpty()){
            throw new RuntimeException("Person must have " + fieldName);
        } else {
            firstName = fieldToParse;
        }
        return firstName;
    }
}
