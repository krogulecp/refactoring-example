package pl.practice.refactoringexample.example3;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class FilmService {

    public static void main(String[] args) throws IOException, URISyntaxException {
        System.out.println(new FilmService().findFilmsLongerThan120minutes().stream().map(film -> film.getTitle() + " " + film.getLength()).collect(Collectors.joining("\n")));
    }

    public List<Film> findFilmsLongerThan120minutes() throws URISyntaxException, IOException {
        Path filmsDbPath = Paths.get(ClassLoader.getSystemResource("films11.db").toURI());
        List<String> allFilmLines = Files.readAllLines(filmsDbPath);

        final List<Film> foundFilms = new ArrayList<>();

        for (var line : allFilmLines){
            String[] lineParts = line.split(" ");
            if (lineParts.length != 3){
                throw new RuntimeException("Invalid film record");
            }

            if (!lineParts[2].isEmpty() && Integer.parseInt(lineParts[2]) > 120) {
                foundFilms.add(new Film(lineParts[2], Integer.parseInt(lineParts[3])));
            }
        }

        return foundFilms;
    }
}
