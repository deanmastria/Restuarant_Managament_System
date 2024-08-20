package org.example;


import org.example.models.MenuItem;
import org.example.services.MenuService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MenuServiceTest {
    private MenuService menuService;

    @BeforeEach
    public void setUp() {
        menuService = new MenuService();
        clearDatabase();  // Clear the database before each test
    }

    // Method to clear the MenuItems table
    private void clearDatabase() {
        String sql = "DELETE FROM MenuItems";  // SQL query to clear the table

        try (Connection conn = org.example.utils.DatabaseConnection.connect();
             Statement stmt = conn.createStatement()) {

            stmt.executeUpdate(sql);
            System.out.println("Database has been cleared.");
        } catch (Exception e) {
            System.out.println("Failed to clear the database: " + e.getMessage());
        }
    }


    @Test
    public void testAddIceCreamMenuItem() {
        MenuItem item = new MenuItem(
                "Vanilla Ice Cream",
                "Classic vanilla flavor",
                5,
                3.99,
                Arrays.asList("Vanilla Flavor", "Sprinkles"));  // Ice cream: flavor + toppings
        menuService.addMenuItem(item);

        List<MenuItem> items = menuService.getAllMenuItems();
        assertEquals(1, items.size(), "The number of menu items should be 1 after adding one item.");
        assertEquals("Vanilla Ice Cream", items.get(0).getName(), "The added item's name should be 'Vanilla Ice Cream'.");
    }

    @Test
    public void testAddMilkshakeMenuItem() {
        MenuItem item = new MenuItem(
                "Chocolate Milkshake",
                "Delicious chocolate milkshake",
                7,
                5.99,
                Arrays.asList("Chocolate Flavor", "Whipped Cream", "Milk"));  // Milkshake: ice cream flavor + toppings + milk
        menuService.addMenuItem(item);

        List<MenuItem> items = menuService.getAllMenuItems();
        assertEquals(1, items.size(), "The number of menu items should be 1 after adding one item.");
        assertEquals("Chocolate Milkshake", items.get(0).getName(), "The added item's name should be 'Chocolate Milkshake'.");
    }

    @Test
    public void testGetAllMenuItems() {
        menuService.addMenuItem(new MenuItem(
                "Vanilla Ice Cream", "Classic vanilla flavor", 5, 3.99,
                Arrays.asList("Vanilla Flavor", "Sprinkles")));  // Ice cream
        menuService.addMenuItem(new MenuItem(
                "Strawberry Milkshake", "Refreshing strawberry milkshake", 7, 5.99,
                Arrays.asList("Strawberry Flavor", "Whipped Cream", "Milk")));  // Milkshake

        List<MenuItem> items = menuService.getAllMenuItems();
        assertEquals(2, items.size(), "The number of menu items should be 2 after adding two items.");
    }

    @Test
    public void testUpdateIceCreamMenuItem() {
        MenuItem item = new MenuItem(
                "Strawberry Ice Cream", "Fresh strawberry flavor", 5, 4.49,
                Arrays.asList("Strawberry Flavor", "Sprinkles"));  // Ice cream
        menuService.addMenuItem(item);

        MenuItem updatedItem = new MenuItem(
                "Strawberry Ice Cream", "Updated description", 6, 4.99,
                Arrays.asList("Strawberry Flavor", "Sprinkles"));  // Updated ice cream
        menuService.updateMenuItem("Strawberry Ice Cream", updatedItem);

        MenuItem foundItem = menuService.findMenuItemByName("Strawberry Ice Cream");
        assertNotNull(foundItem, "The updated item should exist in the menu.");
        assertEquals("Updated description", foundItem.getDescription(), "The description should be updated.");
    }

    @Test
    public void testDeleteMenuItem() {
        MenuItem item = new MenuItem(
                "Mango Ice Cream", "Tropical mango flavor", 5, 4.49,
                Arrays.asList("Mango Flavor", "Sprinkles"));  // Ice cream
        menuService.addMenuItem(item);

        menuService.deleteMenuItem("Mango Ice Cream");

        MenuItem foundItem = menuService.findMenuItemByName("Mango Ice Cream");
        assertNull(foundItem, "The item should be null after deletion.");
    }

    @Test
    public void testFindMenuItemByName() {
        MenuItem item = new MenuItem(
                "Pistachio Milkshake", "Nutty pistachio milkshake", 7, 5.99,
                Arrays.asList("Pistachio Flavor", "Whipped Cream", "Milk"));  // Milkshake
        menuService.addMenuItem(item);

        MenuItem foundItem = menuService.findMenuItemByName("Pistachio Milkshake");
        assertNotNull(foundItem, "The item should be found in the menu.");
        assertEquals("Pistachio Milkshake", foundItem.getName(), "The found item's name should be 'Pistachio Milkshake'.");
    }
}