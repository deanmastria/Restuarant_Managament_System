package org.example.controllers;

import org.example.models.MenuItem;
import org.example.services.InventoryService;

public class InventoryController {
    private InventoryService inventoryService = new InventoryService();

    // Method to process an order and update inventory
    public void processOrder(MenuItem menuItem) {
        for (String ingredient : menuItem.getIngredients()) {
            inventoryService.updateInventoryAfterOrder(ingredient, 1); // Assuming 1 unit of each ingredient is used
        }
        System.out.println("Inventory updated for menu item: " + menuItem.getName());
    }
}
