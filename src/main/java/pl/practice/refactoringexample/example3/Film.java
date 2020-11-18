package pl.practice.refactoringexample.example3;

class Film {
    private final String title;
    private final int length;

    public Film(String title, int length) {
        this.title = title;
        this.length = length;
    }

    public String getTitle() {
        return title;
    }

    public int getLength() {
        return length;
    }
}
