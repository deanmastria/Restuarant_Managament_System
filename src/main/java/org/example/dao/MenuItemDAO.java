package org.example.dao;

import org.example.models.MenuItem;
import org.example.utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MenuItemDAO {

    // Method to add a new menu item
    public void addMenuItem(MenuItem menuItem) {
        String sql = "INSERT INTO MenuItems(name, description, preparationTime, price, ingredients) VALUES(?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, menuItem.getName());
            pstmt.setString(2, menuItem.getDescription());
            pstmt.setInt(3, menuItem.getPreparationTime());
            pstmt.setDouble(4, menuItem.getPrice());
            pstmt.setString(5, String.join(",", menuItem.getIngredients()));  // Convert ingredients list to a comma-separated string

            pstmt.executeUpdate();
            System.out.println("Menu item added successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Method to retrieve all menu items
    public List<MenuItem> getAllMenuItems() {
        String sql = "SELECT * FROM MenuItems";
        List<MenuItem> menuItems = new ArrayList<>();

        try (Connection conn = DatabaseConnection.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            // Loop through the result set and create MenuItem objects
            while (rs.next()) {
                MenuItem menuItem = new MenuItem(
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getInt("preparationTime"),
                        rs.getDouble("price"),
                        Arrays.asList(rs.getString("ingredients").split(","))
                );
                menuItems.add(menuItem);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return menuItems;
    }

    // Method to update an existing menu item by name
    public void updateMenuItem(String itemName, MenuItem updatedMenuItem) {
        String sql = "UPDATE MenuItems SET description = ?, preparationTime = ?, price = ?, ingredients = ? WHERE name = ?";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, updatedMenuItem.getDescription());
            pstmt.setInt(2, updatedMenuItem.getPreparationTime());
            pstmt.setDouble(3, updatedMenuItem.getPrice());
            pstmt.setString(4, String.join(",", updatedMenuItem.getIngredients()));
            pstmt.setString(5, itemName);

            pstmt.executeUpdate();
            System.out.println("Menu item updated successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Method to delete a menu item by name
    public void deleteMenuItem(String itemName) {
        String sql = "DELETE FROM MenuItems WHERE name = ?";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, itemName);
            pstmt.executeUpdate();
            System.out.println("Menu item deleted successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Method to find a menu item by name
    public MenuItem findMenuItemByName(String itemName) {
        String sql = "SELECT * FROM MenuItems WHERE name = ?";
        MenuItem menuItem = null;

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, itemName);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                menuItem = new MenuItem(
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getInt("preparationTime"),
                        rs.getDouble("price"),
                        Arrays.asList(rs.getString("ingredients").split(","))
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return menuItem;
    }
}
