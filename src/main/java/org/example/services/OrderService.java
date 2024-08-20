package org.example.services;


import org.example.dao.OrderDAO;
import org.example.models.Order;

import java.util.List;

public class OrderService {
    private final OrderDAO orderDAO;

    public OrderService() {
        this.orderDAO = new OrderDAO();
    }

    // Create a new order
    public void createOrder(Order order) {
        orderDAO.addOrder(order);
    }

    // Get all orders
    public List<Order> getAllOrders() {
        return orderDAO.getAllOrders();
    }

    // Update the status of an existing order
    public void updateOrderStatus(int orderId, String status) {
        orderDAO.updateOrderStatus(orderId, status);
    }

    // Delete an order by ID
    public void deleteOrder(int orderId) {
        orderDAO.deleteOrder(orderId);
    }

    // Find an order by ID
    public Order findOrderById(int orderId) {
        return orderDAO.findOrderById(orderId);
    }
}
