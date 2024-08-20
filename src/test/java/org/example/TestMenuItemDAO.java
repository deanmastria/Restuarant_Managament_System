package org.example;

import org.example.dao.MenuItemDAO;
import org.example.models.MenuItem;

import java.util.Arrays;
import java.util.List;

public class TestMenuItemDAO {
    public static void main(String[] args) {
        MenuItemDAO menuItemDAO = new MenuItemDAO();

        // Create a new menu item
        MenuItem iceCream = new MenuItem("Vanilla Ice Cream", "Classic vanilla flavor", 5, 3.99, Arrays.asList("Milk", "Sugar", "Vanilla"));
        menuItemDAO.addMenuItem(iceCream);

        // Retrieve all menu items
        List<MenuItem> menuItems = menuItemDAO.getAllMenuItems();
        for (MenuItem item : menuItems) {
            System.out.println(item);
        }

        // Update a menu item
        MenuItem updatedIceCream = new MenuItem("Vanilla Ice Cream", "Updated description", 6, 4.49, Arrays.asList("Milk", "Sugar", "Vanilla"));
        menuItemDAO.updateMenuItem("Vanilla Ice Cream", updatedIceCream);

        // Find a menu item by name
        MenuItem foundItem = menuItemDAO.findMenuItemByName("Vanilla Ice Cream");
        System.out.println("Found item: " + foundItem);

        // Delete a menu item
        menuItemDAO.deleteMenuItem("Vanilla Ice Cream");
    }
}
