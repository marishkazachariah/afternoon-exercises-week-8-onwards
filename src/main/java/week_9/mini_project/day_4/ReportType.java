package week_9.mini_project.day_4;
// Exercise 1
public enum ReportType {
    EMPLOYEE("week_9.mini_project.day_4.EmployeeReport"),
    DEPARTMENT("week_9.mini_project.day_4.DepartmentReport"),
    HIERARCHY("week_9.mini_project.day_4.HierarchyReport");

    private final String displayName;

    ReportType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
