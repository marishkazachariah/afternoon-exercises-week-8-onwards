package week_9.mini_project;

import java.util.*;

// Exercises 3, 4, 5, 6
public class EmployeeHashMap {
    public static Map<Integer, Employee> employeeMap = new HashMap<>();

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

        EmployeeHashMap employeeHashMap = new EmployeeHashMap();

        try {
            addEmployee(employee1);
            addEmployee(employee2);
            addEmployee(employee3);
            addEmployee(employee4);

            Employee duplicateEmployee = new Employee("Mary Kate", 1, "HR", "Assistant", 10, employee1HiringDate, 52000);
            addEmployee(duplicateEmployee);
        } catch (DuplicateEmployeeIdException e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("Filtered Employees by Department:\n" + employeeHashMap.filterByDepartment("Engineering"));
        System.out.println("Filtered Employees by Job Title:\n" + employeeHashMap.filterByDepartment("HR Manager"));
        System.out.println("Employees by Manager ID:\n" + employeeHashMap.filterByManager(11));

        employeeHashMap.updateEmployee();

        Employee searchResult = searchEmployee(3);
        System.out.println(searchResult);

        employeeHashMap.deleteEmployee(4);
    }

    // Exercise 8
    public static void addEmployee(Employee employee) throws DuplicateEmployeeIdException {
        int id = employee.getId();
        if (employeeMap.containsKey(id)) {
            throw new DuplicateEmployeeIdException("Employee with ID " + id + " already exists.");
        } else {
            employeeMap.put(id, employee);
        }
    }

//    public static void addEmployee(Employee employee) {
//        employeeMap.put(employee.getId(), employee);
//    }

    public static Employee searchEmployee(int employeeID) {
        return employeeMap.get(employeeID);
    }

    public void updateEmployee() {
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
            if (inputToChange == 1) {
                System.out.println("Enter new department");
                String newDepartment = sc.nextLine();
                employee.setDepartment(newDepartment);
                System.out.println("Updated job title for employee id: " + id);
            } else if (inputToChange == 2) {
                System.out.println("Enter new job title");
                String newJobTitle = sc.nextLine();
                employee.setJobTitle(newJobTitle);
                System.out.println("Updated job title for employee id: " + id);
            } else if (inputToChange == 3) {
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
        sc.close();
    }

    public void deleteEmployee(int employeeID) {
        employeeMap.remove(employeeID);
    }

    public List<Employee> filterByDepartment(String department) {
        return employeeMap.values()
                .stream()
                .filter(employee -> employee.getDepartment().equalsIgnoreCase(department))
                .toList();
    }

    public List<Employee> filterByJobTitle(String jobTitle) {
        return employeeMap.values()
                .stream()
                .filter(employee -> employee.getJobTitle().equalsIgnoreCase(jobTitle))
                .toList();
    }

    public List<Employee> filterByManager(int managerID) {
        return employeeMap.values()
                .stream()
                .filter(employee -> employee.getManagerID() == managerID)
                .toList();
    }
}
