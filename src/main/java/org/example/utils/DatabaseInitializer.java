package org.example.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class DatabaseInitializer {
    public static void initialize() {
        try (Connection conn = DatabaseConnection.connect();
             Statement stmt = conn.createStatement()) {

            // Create necessary tables
            TableCreation.createUsersTable(stmt);
            TableCreation.createMenuItemsTable(stmt);
            TableCreation.createOrdersTable(stmt);
            TableCreation.createTablesTable(stmt);
            TableCreation.createInventoryTable(stmt);
            TableCreation.createSalesTable(stmt);

            // Seed data if not already seeded
            if (!isTableDataSeeded(conn, "Tables")) {
                DataSeeder.seedTables(conn);
            }
            if (!isTableDataSeeded(conn, "Inventory")) {
                DataSeeder.seedInventory(conn);
            }
            if (!isTableDataSeeded(conn, "MenuItems")) {
                DataSeeder.seedMenuItems(conn);
            }

            System.out.println("Database has been initialized.");
        } catch (Exception e) {
            System.err.println("Error during database initialization: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static boolean isTableDataSeeded(Connection conn, String tableName) {
        String sql = "SELECT COUNT(*) FROM " + tableName;
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getInt(1) > 0;  // If count > 0, data is already seeded
            }
        } catch (Exception e) {
            System.err.println("Error checking data in " + tableName + ": " + e.getMessage());
        }
        return false;
    }
}








