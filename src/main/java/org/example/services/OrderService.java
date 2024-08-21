package org.example.services;

import org.example.dao.OrderDAO;
import org.example.models.Order;

import java.util.List;

public class OrderService {
    private final OrderDAO orderDAO = new OrderDAO();

    // Method to create and process a new order
    public Order createOrder(Order order) {
        int orderId = orderDAO.insertOrder(order);
        if (orderId != -1) {
            order.setId(orderId);
            return order;
        }
        return null;
    }

    // Method to update the status of an order
    public void updateOrderStatus(int orderId, String newStatus) {
        orderDAO.updateOrderStatus(orderId, newStatus);
    }

    // Method to delete an order
    public boolean deleteOrder(Order order) {
        return orderDAO.deleteOrder(order.getId());
    }

    // Method to get all orders
    public List<Order> getAllOrders() {
        return orderDAO.getAllOrders();
    }
}

