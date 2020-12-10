package pl.practice.refactoringexample.example2;

public class AgeParser {

    public static final int MAX_AGE = 120;
    public static final int MIN_AGE = 0;

    static Integer parse(String ageToParse){
        int parsedAge;
        if (ageToParse == null || ageToParse.isEmpty()){
            throw new RuntimeException("Person must have age");
        } else {
            try {
                parsedAge = Integer.parseInt(ageToParse);
            } catch (NumberFormatException exception){
                throw new RuntimeException("Age is not valid", exception);
            }
        }

        if (parsedAge < MIN_AGE || parsedAge > MAX_AGE){
            throw new RuntimeException("Age must be value between 0 and 120");
        }
        return parsedAge;
    }
}
