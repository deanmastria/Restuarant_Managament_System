package org.example.dao;

import org.example.models.Table;
import org.example.utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TableDAO {

    // Get a list of all available tables
    public List<Table> getAvailableTables(int partySize) {
        List<Table> availableTables = new ArrayList<>();
        String sql = "SELECT * FROM Tables WHERE status = 'Available' AND size >= ?";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, partySize);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Table table = new Table(
                        rs.getInt("id"),
                        rs.getInt("size"),
                        rs.getString("status")
                );
                availableTables.add(table);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return availableTables;
    }

    // Update the status of a table
    public boolean updateTableStatus(int tableId, String status) {
        String sql = "UPDATE Tables SET status = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, status);
            pstmt.setInt(2, tableId);

            return pstmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Get a specific table by ID
    public Table getTableById(int tableId) {
        String sql = "SELECT * FROM Tables WHERE id = ?";
        Table table = null;

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, tableId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                table = new Table(
                        rs.getInt("id"),
                        rs.getInt("size"),
                        rs.getString("status")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return table;
    }

    // Get all tables
    public List<Table> getAllTables() {
        List<Table> tables = new ArrayList<>();
        String sql = "SELECT * FROM Tables";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Table table = new Table(
                        rs.getInt("id"),
                        rs.getInt("size"),
                        rs.getString("status")
                );
                tables.add(table);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return tables;
    }
}


