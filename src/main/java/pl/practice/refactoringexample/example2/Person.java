package pl.practice.refactoringexample.example2;

class Person {
    private final String firstName;
    private final String lastName;
    private final int age;

    public Person(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public String getFName() {
        return firstName;
    }

    public String getLName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }
}
