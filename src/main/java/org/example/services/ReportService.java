package org.example.services;

import org.example.dao.ReportDAO;
import java.time.LocalDate;

public class ReportService {

    private final ReportDAO reportDAO = new ReportDAO();

    // Method to generate a sales report for a specific date range
    public void generateSalesReport(String filePath, LocalDate startDate, LocalDate endDate) {
        // Any additional business logic can be added here

        // Delegate to DAO to generate the report
        reportDAO.generateSalesReport(filePath, startDate, endDate);
    }

    // Method to generate a weekly sales report
    public void generateWeeklySalesReport(String filePath) {
        // Any additional business logic can be added here

        // Delegate to DAO to generate the weekly report
        reportDAO.generateWeeklySalesReport(filePath);
    }
}
