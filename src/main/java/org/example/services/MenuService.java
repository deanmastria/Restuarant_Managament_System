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
    public void addMenuItem(MenuItem menuItem) {

        menuItemDAO.addMenuItem(menuItem);
    }

    // Method to get all menu items
    public List<MenuItem> getAllMenuItems() {
        return menuItemDAO.getAllMenuItems();
    }

    // Method to update an existing menu item
    public void updateMenuItem(String itemName, MenuItem updatedMenuItem) {
        // You can add any validation or business logic here if needed
        menuItemDAO.updateMenuItem(itemName, updatedMenuItem);
    }

    // Method to delete a menu item by name
    public void deleteMenuItem(String itemName) {
        // You can add any business logic here if needed
        menuItemDAO.deleteMenuItem(itemName);
    }

    // Method to find a menu item by name
    public MenuItem findMenuItemByName(String itemName) {
        return menuItemDAO.findMenuItemByName(itemName);
    }
}
