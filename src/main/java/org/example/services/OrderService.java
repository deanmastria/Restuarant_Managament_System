package org.example.services;

import org.example.dao.InventoryDAO;
import org.example.dao.OrderDAO;
import org.example.models.Order;
import org.example.models.OrderItem;
import java.util.List;

public class OrderService {
    private OrderDAO orderDAO = new OrderDAO();
    private InventoryService inventoryService = new InventoryService();

    // Method to process a new order
    public int processOrder(Order order) {
        // Insert the order into the database
        int orderId = orderDAO.insertOrder(order);

        // Update the inventory based on the items in the order
        for (OrderItem item : order.getItems()) {
            inventoryService.updateInventoryAfterOrder(item.getItemName(), item.getQuantity());
        }

        // Update order status to 'Preparing'
        orderDAO.updateOrderStatus(orderId, "Preparing");

        return orderId;
    }

    // Method to update the status of an order
    public void updateOrderStatus(int orderId, String newStatus) {
        orderDAO.updateOrderStatus(orderId, newStatus);
    }

    // Method to get all orders
    public List<Order> getAllOrders() {
        return orderDAO.getAllOrders();
    }
}
