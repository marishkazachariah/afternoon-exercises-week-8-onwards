package week_8.day_5;

import java.io.*;
import java.nio.file.Path;
import java.util.*;

public class Utils {
    public static final String FILE_PATH = "src/main/java/week_8/day_5/students.ser";

    public Utils() {
    }

    public void serialize(Object obj, String filePath) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(filePath);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(obj);
            objectOutputStream.flush();
        } catch (Exception e) {
            System.err.println("Error while serializing: " + e.getMessage());
        }
    }

    public Object deserialize(String filename) throws IOException, ClassNotFoundException {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(filename))) {
            return objectInputStream.readObject();
        }
    }

    public void searchStudent(List<Student> students) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter either the name, id, or hobby you want to look up");
        String query = sc.nextLine();
        boolean isFound = false;
        for (Student student : students) {
            if (student.getName().equalsIgnoreCase(query) || student.getId().equalsIgnoreCase(query)
                    || student.getHobbies().contains(query.toLowerCase())) {
                System.out.println("Student: ");
                System.out.println("Name: " + student.getName());
                System.out.println("ID: " + student.getId());
                System.out.println("Hobbies: " + student.getHobbies());
                isFound = true;
                break;
            }
        }
        if (!isFound) {
            System.out.println("Yielded 0 results");
        }
        sc.close();
    }

    // Exercise 4 and 9
    public void deleteStudent(List<Student> students) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter either of the id of the student you want to delete (case sensitive)");
        String query = sc.nextLine();
        System.out.println("Are you sure you want to delete this student with id: " +
                query + "? Type either yes or no");
        String confirmation = sc.nextLine();
        if (confirmation.equalsIgnoreCase("yes")) {
            students.removeIf(student -> student.getId().equals(query));
            serialize(students, FILE_PATH);
            System.out.println("Student with " + query + " deleted successfully.");
        } else if (confirmation.equalsIgnoreCase("no")) {
            System.out.println("Deletion operation cancelled");
        } else {
            System.out.println("Student id: " + query + " not found/invalid.");
        }
        sc.close();
    }

    public void exerciseFive(List<Student> students) {
        double totalGPA = 0;
        for (Student student : students) {
            totalGPA += student.getGpa();
            System.out.println("Student: " + student.getName());
            System.out.println("GPA: " + student.getGpa());
            System.out.println("Hobbies: " + student.getHobbies());
            System.out.println("**********");
        }
        double averageGPA = totalGPA / students.size();
        System.out.println("Average GPA of all students: " + averageGPA);
    }

    // updated method for extracting average gpa for Exercise 10
    public double averageGPA(List<Student> students) {
        double totalGPA = 0;
        for (Student student : students) {
            totalGPA += student.getGpa();
            System.out.println("Student: " + student.getName());
            System.out.println("GPA: " + student.getGpa());
            System.out.println("Hobbies: " + student.getHobbies());
            System.out.println("**********");
        }
        return totalGPA / students.size();
    }

    public void updateStudent(List<Student> students) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the ID of the student you want to update (case sensitive):");
        String query = sc.nextLine();

        for (Student student : students) {
            if (student.getId().equals(query)) {
                System.out.println("Student found. Enter updated information:");
                List<String> updatedCourses = new ArrayList<>();
                List<String> updatedGrades = new ArrayList<>();

                System.out.println("Enter the updated courses. type \"next\" when you are finished adding courses.");
                while (true) {
                    String course = sc.nextLine();
                    if (course.equalsIgnoreCase("next")) {
                        student.setCourses(updatedCourses);
                        break;
                    }
                    updatedCourses.add(course);
                }

                System.out.println("Enter the updated grades. type \"next\" when you are finished adding courses.");
                while (true) {
                    String course = sc.nextLine();
                    if (course.equalsIgnoreCase("next")) {
                        student.setCourses(updatedCourses);
                        break;
                    }
                    updatedGrades.add(course);
                }

                System.out.println("Student information updated successfully:");
                System.out.println(student);
                break;
            }
        }

        sc.close();

        serialize(students, FILE_PATH);
        System.out.println("Student information updated and serialized.");
    }

    // Exercise 7
    public void exerciseSeven(List<Student> students, String courseQuery) {
        boolean isFound = false;
        System.out.println("Students taking course: " + courseQuery + ": ");

        for (Student student : students) {
            List<String> courses = student.getCourses();

            for (String course : courses) {
                if (course.equalsIgnoreCase(courseQuery)) {
                    System.out.println("Name: " + student.getName());
                    System.out.println("Grade: " + student.getGrades());
                    System.out.println("**********");
                    isFound = true;
                    break;
                }
            }
        }

        if (!isFound) {
            System.out.println("Students not taking this course: " + courseQuery);
        }
    }

    // Exercise 8
    public void exerciseEight(List<Student> students) {
        Map<String, Integer> gradeCount = new HashMap<>();
        gradeCount.put("80-100 : Above Average", 0);
        gradeCount.put("70-79 : Average", 0);
        gradeCount.put("50 - 69 : Below Average", 0);
        gradeCount.put("0-49 : Failing", 0);

        for (Student student : students) {
            System.out.println(student.getAverage());

            String gradeRange = getGradeRange(student.getAverage());
            gradeCount.put(gradeRange, gradeCount.get(gradeRange) + 1);
        }

        System.out.println("Grade Summary:");
        for (Map.Entry<String, Integer> entry : gradeCount.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + (entry.getValue() == 1 ? " student" : " students") );
        }
    }

    private String getGradeRange(double gpa) {
        if (gpa >= 80) {
            return "80-100 : Above Average";
        } else if (gpa >= 70) {
            return "70-79 : Average";
        }  else if (gpa >= 50) {
            return "50 - 69 : Below Average";
        } else {
            return "0-49 : Failing";
        }
    }

    // Exercise 10
    public void printSummaryReport(List<Student> students, String fileName) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            writer.println("Summary Report of Students");
            writer.println("Average GPA: " + averageGPA(students));
            writer.println("Total number of students: " + students.size() + "\n");
            writer.println("Students:");
            for (Student student : students) {
                writer.println("Name: " +student.getName());
                writer.println("Student ID: " +student.getId());
                writer.println("Age: " +student.getAge());
                writer.println("GPA: " +student.getGpa());
                writer.println("Courses");
                writer.println(student.getCourses());
                writer.println("Grades");
                writer.println(student.getGrades());
                writer.println("Grade Average: " + student.getAverage());
                writer.println("*************");
            }
            System.out.println("Report exported successfully");
        } catch (IOException e) {
            System.out.println("Error while exporting summary report: " + e.getMessage());
        }
    }

}
