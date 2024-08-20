package org.example.services;

import org.example.dao.MenuItemDAO;
import org.example.models.MenuItem;

import java.util.List;

public class MenuService {
    private final MenuItemDAO menuItemDAO;

    // Constructor
    public MenuService() {
        this.menuItemDAO = new MenuItemDAO();
    }

    // Method to add a new menu item
    public boolean addMenuItem(MenuItem menuItem) {
        if (menuItem == null || menuItem.getName() == null || menuItem.getName().isEmpty()) {
            throw new IllegalArgumentException("Menu item and its name must not be null or empty.");
        }
        // Additional business logic can be added here if needed
        return menuItemDAO.addMenuItem(menuItem);
    }

    // Method to get all menu items
    public List<MenuItem> getAllMenuItems() {
        return menuItemDAO.getAllMenuItems();
    }

    // Method to update an existing menu item
    public boolean updateMenuItem(String itemName, MenuItem updatedMenuItem) {
        if (itemName == null || itemName.isEmpty() || updatedMenuItem == null) {
            throw new IllegalArgumentException("Item name and updated menu item must not be null or empty.");
        }
        // Additional validation or business logic can be added here if needed
        return menuItemDAO.updateMenuItem(itemName, updatedMenuItem);
    }

    // Method to delete a menu item by name
    public boolean deleteMenuItem(String itemName) {
        if (itemName == null || itemName.isEmpty()) {
            throw new IllegalArgumentException("Item name must not be null or empty.");
        }
        // Additional business logic can be added here if needed
        return menuItemDAO.deleteMenuItem(itemName);
    }

    // Method to find a menu item by name
    public MenuItem findMenuItemByName(String itemName) {
        if (itemName == null || itemName.isEmpty()) {
            throw new IllegalArgumentException("Item name must not be null or empty.");
        }
        return menuItemDAO.findMenuItemByName(itemName);
    }
}

