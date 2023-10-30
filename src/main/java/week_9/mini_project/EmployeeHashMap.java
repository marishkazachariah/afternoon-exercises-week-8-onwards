package week_9.mini_project;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

// Exercises 3, 4, 5, 6
public class EmployeeHashMap {
    public static Map<Integer, Employee> employeeMap = new HashMap<>();

    public static void main(String[] args) {
        Employee employee1 = new Employee("John Graham", 1, "HR", "HR Manager", 10);
        Employee employee2 = new Employee("Alice Smith", 2, "Engineering", "Software Engineer", 11);
        Employee employee3 = new Employee("Jane Goodall", 3, "Engineering", "Product Owner", 12);
        Employee employee4 = new Employee("Mary Kay", 4, "Marketing", "Social Media Assistant", 11);
        addEmployee(employee1);
        addEmployee(employee2);
        addEmployee(employee3);
        addEmployee(employee4);

        System.out.println("Filtered Employees by Department:\n" + filterByDepartment("Engineering"));
        System.out.println("Filtered Employees by Job Title:\n" + filterByDepartment("HR Manager"));
        System.out.println("Employees by Manager ID:\n" + filterByManager(11));

        updateEmployee();

        Employee searchResult = searchEmployee(3);
        System.out.println(searchResult);

        deleteEmployee(4);
    }

    public static void addEmployee(Employee employee) {
        employeeMap.put(employee.getId(), employee);
    }

    public static Employee searchEmployee(int employeeID) {
        return employeeMap.get(employeeID);
    }

    public static void updateEmployee() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the id of the employee you wish to update: ");
        int id = sc.nextInt();
        Employee employee = employeeMap.get(id);
        if (employee != null) {
            System.out.println("Type which section you want to update");
            System.out.println("1. Department");
            System.out.println("2. Job Title");
            System.out.println("3. Manager ID");
            int inputToChange = sc.nextInt();
            if(inputToChange == 1) {
                System.out.println("Enter new department");
                String newDepartment = sc.nextLine();
                employee.setDepartment(newDepartment);
                System.out.println("Updated job title for employee id: " + id);
            } else if (inputToChange == 2) {
                System.out.println("Enter new job title");
                String newJobTitle = sc.nextLine();
                employee.setJobTitle(newJobTitle);
                System.out.println("Updated job title for employee id: " + id);
            } else if(inputToChange == 3) {
                System.out.println("Enter new manager ID");
                int newManagerID = sc.nextInt();
                employee.setManagerID(newManagerID);
                System.out.println("Updated manager ID for employee id: " + id);
            } else {
                System.err.println("Invalid choice");
            }
        } else {
            System.err.println("Employee with " + id + " not found");
        }
    }

    public static void deleteEmployee(int employeeID) {
        employeeMap.remove(employeeID);
    }

    public static List<Employee> filterByDepartment(String department) {
        return employeeMap.values()
                .stream()
                .filter(employee -> employee.getDepartment().equalsIgnoreCase(department))
                .toList();
    }

    public static List<Employee> filterByJobTitle(String jobTitle) {
        return employeeMap.values()
                .stream()
                .filter(employee -> employee.getJobTitle().equalsIgnoreCase(jobTitle))
                .toList();
    }

    public static List<Employee> filterByManager(int managerID) {
        return employeeMap.values()
                .stream()
                .filter(employee -> employee.getManagerID() == managerID)
                .toList();
    }
}
