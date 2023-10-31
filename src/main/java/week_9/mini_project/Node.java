package week_9.mini_project;

// Exercise 2
public class Node {
    private Employee employee;
    private Node left;
    private Node right;

    public Node(Employee employee) {
        this.employee = employee;
        this.left = null;
        this.right = null;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "Node {\n" +
                "employee: " + employee + ",\n" +
                "left node: " + left + ",\n" +
                "employee" + right + ",\n" +
                "}";
    }
}
