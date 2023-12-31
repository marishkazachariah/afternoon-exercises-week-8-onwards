package week_9.mini_project;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;
import java.util.stream.Collectors;

// Exercise 2
public class EmployeeHashSet {
    public static Set<Employee> employeeSet = new HashSet<>();

    public static void main(String[] args) {
        Calendar employee1HiringDate = Calendar.getInstance();
        employee1HiringDate.set(2022, Calendar.OCTOBER, 31);

        Calendar employee2HiringDate = Calendar.getInstance();
        employee2HiringDate.set(2019, Calendar.MARCH, 1);

        Calendar employee3HiringDate = Calendar.getInstance();
        employee3HiringDate.set(2023, Calendar.JANUARY, 4);

        Calendar employee4HiringDate = Calendar.getInstance();
        employee4HiringDate.set(2022, Calendar.JULY, 15);

        Employee employee1 = new Employee("John Graham", 1, "HR", "HR Manager", 10, employee1HiringDate, 60000);
        Employee employee2 = new Employee("Alice Smith", 2, "Engineering", "Software Engineer", 11, employee2HiringDate, 55000);
        Employee employee3 = new Employee("Jane Goodall", 3, "Engineering", "Product Owner", 12, employee3HiringDate, 78000);
        Employee employee4 = new Employee("Mary Kay", 4, "Marketing", "Social Media Assistant", 13, employee4HiringDate, 45000);

        employeeSet.add(employee1);
        employeeSet.add(employee2);
        employeeSet.add(employee3);
        employeeSet.add(employee4);

        System.out.println("Employees in the HashSet:");
        for (Employee employee : employeeSet) {
            System.out.println(employee);
        }

        searchEmployeeByID(3);

        generateEmployeeStatistics(employeeSet);
    }

    public static void searchEmployeeByID(int id) {
        Employee foundEmployee = null;
        for (Employee employee : employeeSet) {
            if (employee.getId() == id) {
                foundEmployee = employee;
                break;
            }
        }

        if (foundEmployee != null) {
            System.out.println("Employee with ID " + id + ": " + foundEmployee);
        } else {
            System.out.println("Employee with ID " + id + " not found.");
        }
    }

    // Exercise 7
    public static void generateEmployeeStatistics(Set<Employee> employees) {
        int totalEmployees = employees.size();

        Map<String, Integer> employeesByDepartment = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.summingInt(e -> 1)));


        try (PrintWriter writer = new PrintWriter(new File("src/main/java/week_9/mini_project/employee_report.txt"))) {
            writer.println("**Employee Statistics**\n");
            writer.println("Total Employees: " + totalEmployees);
            writer.println("\nEmployees per Department:");
            for (Map.Entry<String, Integer> entry : employeesByDepartment .entrySet()) {
                writer.println(entry.getKey() + ": " + entry.getValue() + " employees");
            }
            System.out.println("Employee report have been successfully saved to 'employee_statistics.txt'.");
        } catch (FileNotFoundException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
}
