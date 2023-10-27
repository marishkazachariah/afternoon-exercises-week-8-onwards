package week_8.day_5;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@SuppressWarnings("unchecked")
public class Main {
    private static final String FILE_PATH = "src/main/java/week_8/day_5/students.ser";
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        // Exercise 1 - Serialize
        ArrayList<Student> students = new ArrayList<>();
        students.add(new Student("Nathan", 34, "32", 3.2, List.of("Math", "Science"),
                List.of("Reading", "Kayaking"), List.of("68", "83")));
        students.add(new Student("Maria", 22, "48", 3.9, List.of("Math", "Art"),
                List.of("Cycling", "Swimming"), List.of("90", "88")));
        students.add(new Student("Moe", 23, "42", 2.5, List.of("English", "Biology"),
                List.of("Cooking", "Shopping"), List.of("55", "75")));

        Utils utils = new Utils();
        utils.serialize(students, FILE_PATH);

        // Deserialize
        List<Student> deserializedStudents = (List<Student>) utils.deserialize(FILE_PATH);

        int studentCount = 1;
        for (Object student: deserializedStudents) {
            System.out.println("Student " + (studentCount++) +student);
        }

        // Exercise 2
        utils.searchStudent(deserializedStudents);

        // Exercise 4
        utils.deleteStudent(students);

        // Exercise 5
        utils.exerciseFive(students);

        // Exercise 6
        utils.updateStudent(students);

        // Exercise 7
        utils.exerciseSeven(students, "Math");

        // Exercise 8
        utils.exerciseEight(students);

        // Exercise 10
        utils.printSummaryReport(students, "src/main/java/week_8/day_5/summaryReport.txt");
    }
}
