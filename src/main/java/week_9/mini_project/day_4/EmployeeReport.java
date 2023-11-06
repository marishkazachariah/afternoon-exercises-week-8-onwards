package week_9.mini_project.day_4;
import week_9.mini_project.Employee;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class EmployeeReport implements Report{
    @Override
    public void generateReport() {
        System.out.println("Generating Employee Report");
    }

    // Exercise 4
    public void generateReportEmployee(ReportType reportType, Employee employee) {
        try {
            Class<?> employeeClass = Employee.class;

            System.out.println("Class name: " + employeeClass.getName());

            System.out.println("Fields:");
            Field[] fields = employeeClass.getDeclaredFields();
            for (Field field : fields) {
                System.out.println("  " + field.getType().getSimpleName() + " " + field.getName());
            }

            System.out.println("Methods:");
            Method[] methods = employeeClass.getDeclaredMethods();
            for (Method method : methods) {
                System.out.println("  " + method.getName());
            }

            Method getNameMethod = employeeClass.getMethod("getName");
            String name = (String) getNameMethod.invoke(employee);
            System.out.println("Invoked getName method: " + name);

        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
