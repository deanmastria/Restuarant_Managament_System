package org.example.dao;

import org.example.models.Inventory;

import org.example.utils.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class InventoryDAO {

    // Method to decrease the quantity of an ingredient
    public void updateInventory(String ingredientName, int quantityUsed) {
        String sql = "UPDATE Inventory SET quantity = quantity - ? WHERE ingredientName = ?";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, quantityUsed);
            pstmt.setString(2, ingredientName);
            pstmt.executeUpdate();

            // Check if the inventory level is low after the update
            checkLowInventory(ingredientName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to check if an ingredient is running low
    public void checkLowInventory(String ingredientName) {
        String sql = "SELECT quantity FROM Inventory WHERE ingredientName = ?";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, ingredientName);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                int quantity = rs.getInt("quantity");
                if (quantity < 10) { // Assuming 10 as the low inventory threshold
                    System.out.println("Alert: " + ingredientName + " is running low. Current quantity: " + quantity);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to get the list of all inventory items
    public List<Inventory> getInventoryList() {
        List<Inventory> inventoryList = new ArrayList<>();
        String sql = "SELECT * FROM Inventory";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Inventory inventory = new Inventory(
                        rs.getInt("id"),
                        rs.getString("ingredientName"),
                        rs.getInt("quantity")
                );
                inventoryList.add(inventory);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return inventoryList;
    }
}