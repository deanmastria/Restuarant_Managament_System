package org.example.services;

import org.example.dao.InventoryDAO;

public class InventoryService {

    private InventoryDAO inventoryDAO = new InventoryDAO();

    // Method to update the inventory based on the ordered items
    public void updateInventoryAfterOrder(String itemName, int quantityUsed) {
        inventoryDAO.updateInventory(itemName, quantityUsed);
    }
}