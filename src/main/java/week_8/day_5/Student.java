package week_8.day_5;

import java.io.Serializable;
import java.util.List;

public class Student implements Serializable {
    private String name;
    private int age;
    private String id;
    private double gpa;
    private List<String> courses;
    private List<String> hobbies;
    private List<String> grades;

    public Student(String name, int age, String id, double gpa, List<String> courses, List<String> hobbies, List<String> grades) {
        this.name = name;
        this.age = age;
        this.id = id;
        this.gpa = gpa;
        this.courses = courses;
        this.hobbies = hobbies;
        this.grades = grades;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getCourses() {
        return courses;
    }

    public void setCourses(List<String> courses) {
        this.courses = courses;
    }

    public List<String> getHobbies() {
        return hobbies;
    }

    public void setHobbies(List<String> hobbies) {
        this.hobbies = hobbies;
    }

    public List<String> getGrades() {
        return grades;
    }

    public void setGrades(List<String> grades) {
        this.grades = grades;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    public double getAverage() {
        int total = 0;
        for(String grade: grades) {
            total += Integer.parseInt(grade);
        }
        return (double) total / grades.size();
    }

    @Override
    public String toString() {
        return " {name='" + name  + ", age='" + age + ", id='" + id +
                ", courses='" + courses + ", hobbies='" + hobbies +
                ", grades='" + grades + "'}";
    }
}
