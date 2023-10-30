package week_9.mini_project;

public class Employee {
    private String name;
    private int id;
    private String department;
    private String jobTitle;
    private int managerID;

    public Employee(String name, int id, String department, String jobTitle, int managerID) {
        this.name = name;
        this.id = id;
        this.department = department;
        this.jobTitle = jobTitle;
        this.managerID = managerID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getManagerID() {
        return managerID;
    }

    public void setManagerID(int managerID) {
        this.managerID = managerID;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    @Override
    public String toString() {
        return "Employee: " + name + ", ID: " + id + ", Department: " + department + ", Job Title: " + jobTitle + ", Manager ID: " + managerID  + "\n";
    }
}
