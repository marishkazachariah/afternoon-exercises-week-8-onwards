package week_9.mini_project.day_4;

import java.lang.reflect.Constructor;
import java.util.Scanner;

public class ReportGenerator {
    // Exercise 2
    public static Report generateReport(ReportType reportType) {
        try {
            Class<?> reportClass = Class.forName(reportType.getDisplayName());
            Constructor<?> constructor = reportClass.getConstructor();
            return (Report) constructor.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Exercise 3

    public static ReportType selectedType(int input) {
        switch (input) {
            case 1 -> {
                return ReportType.EMPLOYEE;
            }
            case 2 -> {
                return ReportType.DEPARTMENT;
            }
            case 3 -> {
                return ReportType.HIERARCHY;
            }
            default -> System.out.println("Invalid input");
        }
        throw new NullPointerException("Invalid value");
    }

    public static void chooseReportToGenerate() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Choose which report to generate by typing the numerical value");
        System.out.println("1. Employee Report");
        System.out.println("2. Department Report");
        System.out.println("3. Hierarchy Report");

        int input = sc.nextInt();

        ReportType selectedReportType = selectedType(input);
        Report report = generateReport(selectedReportType);
        if (report != null) {
            report.generateReport();
        }

        sc.close();
    }

    public static void main(String[] args) {
        // Exercise 2
        /*
        Report report = generateReport(ReportType.EMPLOYEE);
        if (report != null) {
            report.generateReport();
        }
        */

        // Exercise 3
        chooseReportToGenerate();
    }
}
