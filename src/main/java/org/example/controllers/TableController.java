package org.example.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.example.models.Table;
import org.example.services.TableService;

import java.util.List;

public class TableController {

    @FXML
    private ListView<Table> tableListView;

    @FXML
    private TextField partySizeField;

    @FXML
    private Button seatPartyButton;

    @FXML
    private Button freeTableButton;

    @FXML
    private Label statusLabel;

    private final TableService tableService = new TableService();

    @FXML
    public void initialize() {
        // Load all tables into the ListView on initialization
        loadTableData();
    }

    // Method to load all tables into the ListView
    private void loadTableData() {
        List<Table> tables = tableService.getAllTables();
        tableListView.getItems().addAll(tables);
    }

    // Method to handle seating a party
    @FXML
    public void handleSeatParty() {
        String partySizeText = partySizeField.getText();
        if (partySizeText == null || partySizeText.isEmpty()) {
            showAlert("Error", "Please enter a valid party size.");
            return;
        }

        int partySize;
        try {
            partySize = Integer.parseInt(partySizeText);
        } catch (NumberFormatException e) {
            showAlert("Error", "Party size must be a number.");
            return;
        }

        Table seatedTable = tableService.seatParty(partySize);
        if (seatedTable != null) {
            showAlert("Success", "Seated party at Table ID: " + seatedTable.getId());
            refreshTableData();
        } else {
            showAlert("Error", "No available tables for the party size.");
        }
    }

    // Method to handle freeing a table
    @FXML
    public void handleFreeTable() {
        Table selectedTable = tableListView.getSelectionModel().getSelectedItem();

        if (selectedTable == null) {
            showAlert("Error", "Please select a table to free.");
            return;
        }

        boolean success = tableService.freeTable(selectedTable.getId());
        if (success) {
            showAlert("Success", "Table ID: " + selectedTable.getId() + " is now available.");
            refreshTableData();
        } else {
            showAlert("Error", "Failed to free the table.");
        }
    }

    // Method to refresh the table data in the ListView
    private void refreshTableData() {
        tableListView.getItems().clear();
        loadTableData();
    }

    // Utility method to show an alert
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}


