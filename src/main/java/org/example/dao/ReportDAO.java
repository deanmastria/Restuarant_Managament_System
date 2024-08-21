package org.example.dao;

import org.example.utils.DatabaseConnection;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ReportDAO {

    // Method to generate a sales report for a specific date range
    public void generateSalesReport(String filePath, LocalDate startDate, LocalDate endDate) {
        String sql = "SELECT * FROM Sales WHERE date >= ? AND date <= ?";

        // Format dates to match your SQLite date format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedStartDate = startDate.format(formatter);
        String formattedEndDate = endDate.format(formatter);

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             FileWriter writer = new FileWriter(filePath)) {

            // Set parameters for the SQL query
            pstmt.setString(1, formattedStartDate);
            pstmt.setString(2, formattedEndDate);

            try (ResultSet rs = pstmt.executeQuery()) {

                // Write CSV header
                writer.append("SaleID,OrderID,Revenue,Date\n");

                // Write data rows
                while (rs.next()) {
                    writer.append(rs.getInt("id") + ",");
                    writer.append(rs.getInt("orderId") + ",");
                    writer.append(rs.getDouble("revenue") + ",");
                    writer.append(rs.getString("date") + "\n");
                }
            }

            System.out.println("Sales CSV report has been generated for the date range: "
                    + formattedStartDate + " to " + formattedEndDate + " at: " + filePath);

        } catch (IOException | SQLException e) {
            System.err.println("Error generating sales report: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Method to generate a weekly sales report
    public void generateWeeklySalesReport(String filePath) {
        LocalDate today = LocalDate.now();
        LocalDate startOfWeek = today.minusDays(today.getDayOfWeek().getValue() - 1);  // Start of the week (Monday)
        LocalDate endOfWeek = startOfWeek.plusDays(6);  // End of the week (Sunday)

        generateSalesReport(filePath, startOfWeek, endOfWeek);
    }
}
