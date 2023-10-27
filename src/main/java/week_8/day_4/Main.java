package week_8.day_4;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static final String STUDENT_FILE_PATH = "src/main/java/week_8/day_4/student_data.ser";
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        // Exercise 1
        Student student1 = new Student("Kate", "33 Queue Road", 3.2);
        student1.addCourse("Physics");
        student1.addCourse("Chemistry");
        student1.addCourse("English");

//        serialize(student1, STUDENT_FILE_PATH);
//        Student deserializedStudent = (Student) deserialize(STUDENT_FILE_PATH);
//        System.out.println(deserializedStudent);

        // Exercise 2
        Student student2 = new Student("Joe", "2 Mullholland Drive", 3.2);
        Student student3 = new Student("Sadie", "3423 Kingston Road", 3.8);
        Student student4 = new Student("Smith", "111 Rodeo Drive", 4.0);
        Student student5 = new Student("Moe", "44 Kensington Ave", 2.2);
        List<Student> students = new ArrayList<>();
        students.add(student1);
        students.add(student2);
        students.add(student3);
        students.add(student4);
        students.add(student5);

        serialize(students, STUDENT_FILE_PATH);
        List<?> deserializedStudents = (List<?>) deserialize(STUDENT_FILE_PATH);
        for (Object student: deserializedStudents) {
            System.out.println(student);
        }

        // Exercise 4
        updateStudentsGPA(student1);

        // Exercise 5
        createBackupOfData();
    }

    // Exercise 1
    public static void serialize(Object obj, String filePath) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(filePath);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(obj);
            objectOutputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Object deserialize(String filename) throws IOException, ClassNotFoundException {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(filename))) {
            return objectInputStream.readObject();
        }
    }

    // Exercise 4
    public static void updateStudentsGPA(Student student) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Original student data:" + student);

        if(student != null) {
            System.out.println("Enter updated GPA:");
            double updatedGPA = sc.nextDouble();
            student.setGpa(updatedGPA);
        }

        System.out.println("Enter the courses you want to add. type \"next\" when you are finished adding courses.");
        while(true) {
            String course = sc.nextLine();
            if (course.equalsIgnoreCase("next")) {
                break;
            }
            if (student == null) throw new AssertionError();
            student.addCourse(course);
        }

        System.out.println("Enter the hobbies you want to add. type \"done\" when you are finished adding courses.");
        while(true) {
            String course = sc.nextLine();
            if (course.equalsIgnoreCase("done")) {
                break;
            }
            if (student == null) throw new AssertionError();
            student.addHobby(course);
        }

        serialize(student, STUDENT_FILE_PATH);
    }

    // Exercise 5
    public static void createBackupOfData() {
        try {
            Path originalFile = Paths.get(STUDENT_FILE_PATH);
            Path copyOfFile = Paths.get("src/main/java/week_8/day_4/student_data_backup.ser");
            Files.copy(originalFile, copyOfFile, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Backup created successfully");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
