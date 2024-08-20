package org.example.controllers;

import org.example.models.Order;
import org.example.services.OrderService;
import java.util.List;

public class OrderController {
    private OrderService orderService = new OrderService();

    // Method to create a new order
    public int createOrder(Order order) {
        return orderService.processOrder(order);
    }

    // Method to update the status of an order
    public void updateOrderStatus(int orderId, String newStatus) {
        orderService.updateOrderStatus(orderId, newStatus);
    }

    // Method to get all orders
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }
}
