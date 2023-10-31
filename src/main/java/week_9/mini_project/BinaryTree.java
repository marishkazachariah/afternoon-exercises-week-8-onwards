package week_9.mini_project;

import java.util.Calendar;

// Exercise 2
public class BinaryTree {
    private Node root;

    public BinaryTree() {
        this.root = null;
    }

    public Node getRoot() {
        return root;
    }

    // Exercise 3
    public void addEmployee(Employee employee) {
        root = addEmployee(root, employee);
    }

    private Node addEmployee(Node root, Employee employee) {
        if(root == null) {
            root = new Node(employee);
            return root;
        } else if (employee.getId() == root.getEmployee().getId()) {
            System.err.println("Error: Employee with ID " + employee.getId() + " already exists.");
            return root;
        } else if (employee.getSalary() < root.getEmployee().getSalary()) {
            Node newNode = addEmployee(root.getLeft(), employee);
            root.setLeft(newNode);
        } else if (employee.getSalary() > root.getEmployee().getSalary()) {
            Node newNode = addEmployee(root.getRight(), employee);
            root.setRight(newNode);
        }

        return root;
    }

    // Exercise 4
    public Employee findLowestCommonManager(int employeeID1, int employeeID2) {
        return findLowestCommonManager(root, employeeID1, employeeID2);
    }
    private Employee findLowestCommonManager(Node node, int employeeID1, int employeeID2) {
        if (node.getEmployee().getId() == employeeID1 || node.getEmployee().getId() == employeeID2) {
            return node.getEmployee();
        }

        Employee leftResult = findLowestCommonManager(node.getLeft(), employeeID1, employeeID2);
        Employee rightResult = findLowestCommonManager(node.getRight(), employeeID1, employeeID2);

        if(leftResult != null && rightResult != null) {
            return node.getEmployee();
        }

        return leftResult != null ? leftResult : rightResult;
    }

    // Exercise 5
    public void printHierarchy(Node node, int depth) {
        if (node == null) {
            return;
        }

        printHierarchy(node.getRight(), depth + 1);

        System.out.println("  ".repeat(Math.max(0, depth)) + node.getEmployee().getName());

        printHierarchy(node.getLeft(), depth + 1);
    }


    public static void main(String[] args) {
        Calendar employee1HiringDate = Calendar.getInstance();
        employee1HiringDate.set(2022, Calendar.OCTOBER, 31);

        Calendar employee2HiringDate = Calendar.getInstance();
        employee2HiringDate.set(2019, Calendar.MARCH, 1);

        Calendar employee3HiringDate = Calendar.getInstance();
        employee3HiringDate.set(2023, Calendar.JANUARY, 4);

        Calendar employee4HiringDate = Calendar.getInstance();
        employee4HiringDate.set(2022, Calendar.JULY, 15);

        Employee employee1 = new Employee("John Graham", 1, "Executive", "CEO", 10, employee1HiringDate, 100000.0);
        Employee employee2 = new Employee("Alice Smith", 3, "Engineering", "Software Engineer", 11, employee2HiringDate, 55000.0);
        Employee employee3 = new Employee("Jane Goodall", 2, "Engineering", "Product Owner", 12, employee3HiringDate, 78000.0);
        Employee employee4 = new Employee("Mary Kay", 4, "Marketing", "Social Media Assistant", 13, employee4HiringDate, 45000.0);

        // Adding employees to the organization hierarchy
        BinaryTree binaryTree = new BinaryTree();
        binaryTree.addEmployee(employee1);
        binaryTree.addEmployee(employee2);
        binaryTree.addEmployee(employee3);
        binaryTree.addEmployee(employee4);

//        System.out.println(binaryTree.getRoot());
        binaryTree.printHierarchy(binaryTree.getRoot(), 0);
    }
}
