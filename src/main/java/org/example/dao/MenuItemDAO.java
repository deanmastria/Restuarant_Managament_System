package org.example.dao;

import org.example.models.MenuItem;
import org.example.utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MenuItemDAO {

    private static final Logger logger = Logger.getLogger(MenuItemDAO.class.getName());

    // Method to add a new menu item
    public boolean addMenuItem(MenuItem menuItem) {
        String sql = "INSERT INTO MenuItems(name, description, preparationTime, price, ingredients) VALUES(?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, menuItem.getName());
            pstmt.setString(2, menuItem.getDescription());
            pstmt.setInt(3, menuItem.getPreparationTime());
            pstmt.setDouble(4, menuItem.getPrice());
            pstmt.setString(5, String.join(",", menuItem.getIngredients()));  // Convert ingredients list to a comma-separated string

            pstmt.executeUpdate();
            logger.log(Level.INFO, "Menu item added successfully.");
            return true;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error adding menu item: " + e.getMessage(), e);
            return false;
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
            logger.log(Level.SEVERE, "Error retrieving menu items: " + e.getMessage(), e);
        }

        return menuItems;
    }

    // Method to update an existing menu item by name
    public boolean updateMenuItem(String itemName, MenuItem updatedMenuItem) {
        String sql = "UPDATE MenuItems SET description = ?, preparationTime = ?, price = ?, ingredients = ? WHERE name = ?";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, updatedMenuItem.getDescription());
            pstmt.setInt(2, updatedMenuItem.getPreparationTime());
            pstmt.setDouble(3, updatedMenuItem.getPrice());
            pstmt.setString(4, String.join(",", updatedMenuItem.getIngredients()));
            pstmt.setString(5, itemName);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                logger.log(Level.INFO, "Menu item updated successfully.");
                return true;
            } else {
                logger.log(Level.WARNING, "Menu item not found for update.");
                return false;
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error updating menu item: " + e.getMessage(), e);
            return false;
        }
    }

    // Method to delete a menu item by name
    public boolean deleteMenuItem(String itemName) {
        String sql = "DELETE FROM MenuItems WHERE name = ?";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, itemName);
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                logger.log(Level.INFO, "Menu item deleted successfully.");
                return true;
            } else {
                logger.log(Level.WARNING, "Menu item not found for deletion.");
                return false;
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error deleting menu item: " + e.getMessage(), e);
            return false;
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
            logger.log(Level.SEVERE, "Error finding menu item: " + e.getMessage(), e);
        }

        return menuItem;
    }
}
