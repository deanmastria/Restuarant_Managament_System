package org.example.dao;

import org.example.models.Order;
import org.example.models.MenuItem;
import org.example.utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OrderDAO {

    // Method to add a new order
    public void addOrder(Order order) {
        String sql = "INSERT INTO Orders(userId, items, totalPrice, status) VALUES(?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, order.getUserId());
            pstmt.setString(2, getItemsAsString(order.getItems()));
            pstmt.setDouble(3, order.getTotalPrice());
            pstmt.setString(4, order.getStatus());

            pstmt.executeUpdate();
            System.out.println("Order added successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Method to retrieve all orders
    public List<Order> getAllOrders() {
        String sql = "SELECT * FROM Orders";
        List<Order> orders = new ArrayList<>();

        try (Connection conn = DatabaseConnection.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Order order = new Order(
                        rs.getInt("id"),
                        rs.getInt("userId"),
                        getItemsFromString(rs.getString("items")),
                        rs.getDouble("totalPrice"),
                        rs.getString("status")
                );
                orders.add(order);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return orders;
    }

    // Method to update an order's status
    public void updateOrderStatus(int orderId, String status) {
        String sql = "UPDATE Orders SET status = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, status);
            pstmt.setInt(2, orderId);

            pstmt.executeUpdate();
            System.out.println("Order status updated successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Method to delete an order by ID
    public void deleteOrder(int orderId) {
        String sql = "DELETE FROM Orders WHERE id = ?";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, orderId);
            pstmt.executeUpdate();
            System.out.println("Order deleted successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Method to find an order by ID
    public Order findOrderById(int orderId) {
        String sql = "SELECT * FROM Orders WHERE id = ?";
        Order order = null;

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, orderId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                order = new Order(
                        rs.getInt("id"),
                        rs.getInt("userId"),
                        getItemsFromString(rs.getString("items")),
                        rs.getDouble("totalPrice"),
                        rs.getString("status")
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return order;
    }

    // Convert list of MenuItem objects to a comma-separated string
    private String getItemsAsString(List<MenuItem> items) {
        List<String> itemNames = new ArrayList<>();
        for (MenuItem item : items) {
            itemNames.add(item.getName());
        }
        return String.join(",", itemNames);
    }

    // Convert comma-separated string to list of MenuItem objects
    private List<MenuItem> getItemsFromString(String itemsString) {
        List<MenuItem> items = new ArrayList<>();
        List<String> itemNames = Arrays.asList(itemsString.split(","));
        for (String itemName : itemNames) {
            items.add(new MenuItem(itemName, "", 0, 0, new ArrayList<>()));  // You may need to fetch the full MenuItem details from the database if needed
        }
        return items;
    }
}
