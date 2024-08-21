package org.example.controllers;

import org.example.models.Table;
import org.example.services.TableService;

import java.util.Scanner;

public class TableController {
    private final TableService tableService;

    public TableController() {
        this.tableService = new TableService();

    }

    // Method to seat a party based on user input
    public void seatParty() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of people in the party: ");
        int partySize = scanner.nextInt();

        Table seatedTable = tableService.seatParty(partySize);

        if (seatedTable != null) {
            System.out.println("Successfully seated the party at: " + seatedTable);
        } else {
            System.out.println("Unable to seat the party at this time.");
        }
    }
}

