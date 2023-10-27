package week_8.day_4;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/*
Create a more complex class (e.g., Student) with additional attributes
like address, GPA, a list of courses taken, and a list of hobbies.
Implement serialization for this class.
 */
public class Student implements Serializable {
    private String name;
    private String address;
    private double gpa;
    List<String> courses;
    List<String> hobbies;

    public Student(String name, String address, double gpa) {
        this.name = name;
        this.address = address;
        this.gpa = gpa;
        this.courses = new ArrayList<>();
        this.hobbies = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public double getGpa() {
        return gpa;
    }

    public List<String> getCourses() {
        return courses;
    }

    public List<String> getHobbies() {
        return hobbies;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    public void setCourses(List<String> courses) {
        this.courses = courses;
    }

    public void addCourse(String course) {
        courses.add(course);
    }

    public void addHobby(String hobby) {
        hobbies.add(hobby);
    }

    @Override
    public String toString() {
        return "Student{name='" + name  + ", address='" + address + ", courses='" + courses + ", hobbies='" + hobbies + "'}";
    }
}
