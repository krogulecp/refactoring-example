package pl.practice.refactoringexample.example1;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class FilmService {

    public static final String FILMS_FILE = "films.db";

    public static void main(String[] args) throws IOException, URISyntaxException {
        System.out.println(printFilms(new FilmService().findFilmsLongerThan(120)));
    }

    private static String printFilms(List<Film> filmsLongerThan) throws URISyntaxException, IOException {
        return filmsLongerThan.stream().map(film -> film.getTitle() + " " + film.getLength()).collect(Collectors.joining("\n"));

    }

    public List<Film> findFilmsLongerThan(int filmLength) throws URISyntaxException, IOException {
        List<String> allFilmLines = getFilmLines();

        final List<Film> foundFilms = new ArrayList<>();

        for (var line : allFilmLines){
            String[] lineParts = line.split(",");
            if (lineParts.length != 3){
                throw new RuntimeException("Invalid film record");
            }

            if (!lineParts[2].isEmpty() && Integer.parseInt(lineParts[2]) > filmLength) {
                foundFilms.add(new Film(lineParts[1], Integer.parseInt(lineParts[2])));
            }
        }

        return foundFilms;
    }

    private List<String> getFilmLines() throws URISyntaxException, IOException {
        Path filmsDbPath = Paths.get(ClassLoader.getSystemResource(FILMS_FILE).toURI());
        return Files.readAllLines(filmsDbPath);
    }
}
