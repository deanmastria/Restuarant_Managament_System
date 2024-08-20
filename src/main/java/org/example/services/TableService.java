package org.example.services;

import org.example.dao.TableDAO;
import org.example.models.Table;
import java.util.List;

public class TableService {
    private final TableDAO tableDAO;

    public TableService() {
        this.tableDAO = new TableDAO();
    }

    // Method to seat a party and update the table status
    public Table seatParty(int partySize) {
        List<Table> availableTables = tableDAO.getAvailableTables(partySize);

        if (availableTables.isEmpty()) {
            System.out.println("No available tables for the party size of " + partySize);
            return null;
        }

        // Seat the party at the first available table
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
}
