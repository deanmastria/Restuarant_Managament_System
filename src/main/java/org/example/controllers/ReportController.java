package org.example.controllers;

import javafx.fxml.FXML;
import org.example.services.ReportService;
import java.time.LocalDate;

public class ReportController {
    private final ReportService reportService = new ReportService();

    @FXML
    private void handleGenerateSalesReport() {
        LocalDate startDate = LocalDate.of(2024, 8, 1);  // Example start date
        LocalDate endDate = LocalDate.of(2024, 8, 31);  // Example end date
        reportService.generateSalesReport("path/to/your/sales_report.csv", startDate, endDate);
    }

    @FXML
    private void handleGenerateWeeklyReport() {
        reportService.generateWeeklySalesReport("path/to/your/weekly_sales_report.csv");
    }
}
