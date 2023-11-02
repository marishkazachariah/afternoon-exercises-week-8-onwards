package week_9.mini_project.day_4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReportGeneratorTest {
    @Test
    void testGenerateEmployeeReport() {
        ReportType reportType = ReportType.EMPLOYEE;

        Report report = ReportGenerator.generateReport(reportType);

        assertNotNull(report);
        assertEquals(EmployeeReport.class, report.getClass());
        assertTrue(report instanceof EmployeeReport);
    }

    @Test
    void testGenerateDepartmentReport() {
        ReportType reportType = ReportType.DEPARTMENT;

        Report report = ReportGenerator.generateReport(reportType);

        assertNotNull(report);
        assertEquals(DepartmentReport.class, report.getClass());
        assertTrue(report instanceof DepartmentReport);
    }

    @Test
    void testGenerateHierarchyReport() {
        ReportType reportType = ReportType.HIERARCHY;

        Report report = ReportGenerator.generateReport(reportType);

        assertNotNull(report);
        assertEquals(HierarchyReport.class, report.getClass());
        assertTrue(report instanceof HierarchyReport);
    }
}