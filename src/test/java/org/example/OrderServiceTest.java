package org.example;

import org.example.models.MenuItem;
import org.example.models.Order;
import org.example.services.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class OrderServiceTest {
    private OrderService orderService;

    @BeforeEach
    public void setUp() {
        orderService = new OrderService();
        clearDatabase();  // Clear the database before each test
    }

    // Method to clear the Orders table
    private void clearDatabase() {
        String sqlOrders = "DELETE FROM Orders";
        String sqlMenuItems = "DELETE FROM MenuItems";  // Ensure menu items are cleared if needed for your tests

        try (Connection conn = org.example.utils.DatabaseConnection.connect();
             Statement stmt = conn.createStatement()) {

            stmt.executeUpdate(sqlOrders);
            stmt.executeUpdate(sqlMenuItems);
            System.out.println("Database has been cleared.");
        } catch (Exception e) {
            System.out.println("Failed to clear the database: " + e.getMessage());
        }
    }

    @Test
    public void testCreateOrder() {
        MenuItem item1 = new MenuItem("Vanilla Ice Cream", "Classic vanilla flavor", 5, 3.99, Arrays.asList("Vanilla Flavor", "Sprinkles"));
        MenuItem item2 = new MenuItem("Chocolate Ice Cream", "Rich chocolate flavor", 5, 4.99, Arrays.asList("Chocolate Flavor", "Cocoa"));
        List<MenuItem> items = Arrays.asList(item1, item2);

        Order order = new Order(1, 1, items, 8.98, "waiting");
        orderService.createOrder(order);

        List<Order> orders = orderService.getAllOrders();
        assertEquals(1, orders.size(), "The number of orders should be 1 after creating one order.");
        assertEquals("waiting", orders.get(0).getStatus(), "The order status should be 'waiting'.");
    }

    @Test
    public void testGetAllOrders() {
        MenuItem item1 = new MenuItem("Vanilla Ice Cream", "Classic vanilla flavor", 5, 3.99, Arrays.asList("Vanilla Flavor", "Sprinkles"));
        MenuItem item2 = new MenuItem("Chocolate Ice Cream", "Rich chocolate flavor", 5, 4.99, Arrays.asList("Chocolate Flavor", "Cocoa"));
        List<MenuItem> items1 = Arrays.asList(item1);
        List<MenuItem> items2 = Arrays.asList(item2);

        Order order1 = new Order(1, 1, items1, 3.99, "waiting");
        Order order2 = new Order(2, 2, items2, 4.99, "preparing");
        orderService.createOrder(order1);
        orderService.createOrder(order2);

        List<Order> orders = orderService.getAllOrders();
        assertEquals(2, orders.size(), "The number of orders should be 2 after creating two orders.");
    }

    @Test
    public void testUpdateOrderStatus() {
        MenuItem item = new MenuItem("Vanilla Ice Cream", "Classic vanilla flavor", 5, 3.99, Arrays.asList("Vanilla Flavor", "Sprinkles"));
        Order order = new Order(1, 1, Arrays.asList(item), 3.99, "waiting");
        orderService.createOrder(order);

        // Verify the order exists before updating
        Order createdOrder = orderService.findOrderById(1);
        assertNotNull(createdOrder, "The order should exist in the database before updating.");

        // Update the order status
        orderService.updateOrderStatus(1, "ready");

        // Verify the updated order
        Order updatedOrder = orderService.findOrderById(1);
        assertNotNull(updatedOrder, "The updated order should exist in the database.");
        assertEquals("ready", updatedOrder.getStatus(), "The order status should be updated to 'ready'.");
    }

    @Test
    public void testDeleteOrder() {
        MenuItem item = new MenuItem("Chocolate Ice Cream", "Rich chocolate flavor", 5, 4.99, Arrays.asList("Chocolate Flavor", "Cocoa"));
        Order order = new Order(1, 1, Arrays.asList(item), 4.99, "waiting");
        orderService.createOrder(order);

        orderService.deleteOrder(1);

        Order foundOrder = orderService.findOrderById(1);
        assertNull(foundOrder, "The order should be null after deletion.");
    }

    @Test
    public void testFindOrderById() {
        MenuItem item = new MenuItem("Strawberry Ice Cream", "Fresh strawberry flavor", 5, 4.49, Arrays.asList("Strawberry Flavor", "Sprinkles"));
        Order order = new Order(1, 1, Arrays.asList(item), 4.49, "waiting");
        orderService.createOrder(order);

        Order foundOrder = orderService.findOrderById(1);
        assertNotNull(foundOrder, "The order should be found in the database.");
        assertEquals(1, foundOrder.getId(), "The found order's ID should be 1.");
    }
}
