package org.example;


import org.example.models.MenuItem;
import org.example.services.MenuService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MenuServiceTest {
    private MenuService menuService;

    @BeforeEach
    public void setUp() {
        menuService = new MenuService();
    }

    @Test
    public void testAddMenuItem() {
        MenuItem item = new MenuItem("Vanilla Ice Cream", "Classic vanilla flavor", 5, 3.99, Arrays.asList("Milk", "Sugar", "Vanilla"));
        menuService.addMenuItem(item);

        List<MenuItem> items = menuService.getAllMenuItems();
        assertEquals(1, items.size(), "The number of menu items should be 1 after adding one item.");
        assertEquals("Vanilla Ice Cream", items.get(0).getName(), "The added item's name should be 'Vanilla Ice Cream'.");
    }

    @Test
    public void testGetAllMenuItems() {
        menuService.addMenuItem(new MenuItem("Vanilla Ice Cream", "Classic vanilla flavor", 5, 3.99, Arrays.asList("Milk", "Sugar", "Vanilla")));
        menuService.addMenuItem(new MenuItem("Chocolate Ice Cream", "Rich chocolate flavor", 5, 4.99, Arrays.asList("Milk", "Sugar", "Cocoa")));

        List<MenuItem> items = menuService.getAllMenuItems();
        assertEquals(2, items.size(), "The number of menu items should be 2 after adding two items.");
    }

    @Test
    public void testUpdateMenuItem() {
        MenuItem item = new MenuItem("Strawberry Ice Cream", "Fresh strawberry flavor", 5, 4.49, Arrays.asList("Milk", "Sugar", "Strawberries"));
        menuService.addMenuItem(item);

        MenuItem updatedItem = new MenuItem("Strawberry Ice Cream", "Updated description", 6, 4.99, Arrays.asList("Milk", "Sugar", "Strawberries"));
        menuService.updateMenuItem("Strawberry Ice Cream", updatedItem);

        MenuItem foundItem = menuService.findMenuItemByName("Strawberry Ice Cream");
        assertNotNull(foundItem, "The updated item should exist in the menu.");
        assertEquals("Updated description", foundItem.getDescription(), "The description should be updated.");
    }

    @Test
    public void testDeleteMenuItem() {
        MenuItem item = new MenuItem("Mango Ice Cream", "Tropical mango flavor", 5, 4.49, Arrays.asList("Milk", "Sugar", "Mango"));
        menuService.addMenuItem(item);

        menuService.deleteMenuItem("Mango Ice Cream");

        MenuItem foundItem = menuService.findMenuItemByName("Mango Ice Cream");
        assertNull(foundItem, "The item should be null after deletion.");
    }

    @Test
    public void testFindMenuItemByName() {
        MenuItem item = new MenuItem("Pistachio Ice Cream", "Nutty pistachio flavor", 5, 4.99, Arrays.asList("Milk", "Sugar", "Pistachio"));
        menuService.addMenuItem(item);

        MenuItem foundItem = menuService.findMenuItemByName("Pistachio Ice Cream");
        assertNotNull(foundItem, "The item should be found in the menu.");
        assertEquals("Pistachio Ice Cream", foundItem.getName(), "The found item's name should be 'Pistachio Ice Cream'.");
    }
}