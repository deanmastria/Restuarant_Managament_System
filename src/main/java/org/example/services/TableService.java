package org.example.services;

import org.example.dao.TableDAO;
import org.example.models.Table;
import org.example.models.Order;

import java.util.List;

public class TableService {
    private final TableDAO tableDAO;

    public TableService() {
        this.tableDAO = new TableDAO();
    }

    // Get available tables with optional party size
    public List<Table> getAvailableTables(int partySize) {
        return tableDAO.getAvailableTables(partySize);
    }

    // Seat a party and update the table status
    public Table seatParty(int partySize) {
        List<Table> availableTables = tableDAO.getAvailableTables(partySize);

        if (availableTables.isEmpty()) {
            System.out.println("No available tables for the party size of " + partySize);
            return null;
        }

        Table tableToSeat = availableTables.get(0);
        boolean success = tableDAO.updateTableStatus(tableToSeat.getId(), "Occupied");

        if (success) {
            System.out.println("Seated party at Table ID: " + tableToSeat.getId());
            return tableToSeat;
        } else {
            System.out.println("Failed to seat the party.");
            return null;
        }
    }

    // Assign a table to an order
    public void assignTableToOrder(int tableId, Order order) {
        order.setTable(tableId);
        tableDAO.updateTableStatus(tableId, "Occupied");
        // Assuming you have an `updateOrder()` method in `OrderDAO` to persist this change
    }

    // Method to free a table (unseat party) and update the table status
    public boolean freeTable(int tableId) {
        Table table = tableDAO.getTableById(tableId);

        if (table == null || !table.getStatus().equals("Occupied")) {
            System.out.println("Table ID: " + tableId + " is not occupied or does not exist.");
            return false;
        }

        boolean success = tableDAO.updateTableStatus(tableId, "Available");

        if (success) {
            System.out.println("Freed Table ID: " + tableId);
        } else {
            System.out.println("Failed to free Table ID: " + tableId);
        }

        return success;
    }

    // Method to retrieve all tables
    public List<Table> getAllTables() {
        return tableDAO.getAllTables();
    }

    // Method to get available tables
    public List<Table> getAvailableTables() {
        return tableDAO.getAvailableTables(0);  // Pass 0 or a default value
    }

    // Method to check the status of a specific table
    public String getTableStatus(int tableId) {
        Table table = tableDAO.getTableById(tableId);

        if (table == null) {
            System.out.println("Table ID: " + tableId + " does not exist.");
            return null;
        }

        return table.getStatus();
    }
}


