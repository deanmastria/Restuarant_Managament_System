package org.example.dao;

import org.example.models.Order;
import org.example.models.OrderItem;

import org.example.utils.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {

    // Method to insert a new order into the database
    public int insertOrder(Order order) {
        String sql = "INSERT INTO Orders(userId, items, totalPrice, status) VALUES(?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            pstmt.setInt(1, order.getUserId());
            pstmt.setString(2, serializeOrderItems(order.getItems()));
            pstmt.setDouble(3, order.getTotalPrice());
            pstmt.setString(4, order.getStatus());
            pstmt.executeUpdate();

            // Retrieve the generated order ID
            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    // Method to update the status of an order
    public void updateOrderStatus(int orderId, String newStatus) {
        String sql = "UPDATE Orders SET status = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, newStatus);
            pstmt.setInt(2, orderId);
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to retrieve all orders
    public List<Order> getAllOrders() {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT * FROM Orders";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                List<OrderItem> items = deserializeOrderItems(rs.getString("items"));
                Order order = new Order(
                        rs.getInt("userId"),
                        items,
                        rs.getDouble("totalPrice"),
                        rs.getString("status")
                );
                order.setId(rs.getInt("id"));
                orders.add(order);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return orders;
    }

    // Utility methods for serializing and deserializing order items
    public String serializeOrderItems(List<OrderItem> items) {
        StringBuilder sb = new StringBuilder();
        for (OrderItem item : items) {
            sb.append(item.getItemName()).append(":").append(item.getQuantity()).append(";");
        }
        return sb.toString();
    }

    private List<OrderItem> deserializeOrderItems(String itemsStr) {
        List<OrderItem> items = new ArrayList<>();
        String[] itemPairs = itemsStr.split(";");
        for (String pair : itemPairs) {
            String[] parts = pair.split(":");
            if (parts.length == 2) {
                items.add(new OrderItem(parts[0], Integer.parseInt(parts[1])));
            }
        }
        return items;
    }
}
