package org.example.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;

public class MainDirectoryController {

    @FXML
    private Button menuButton;

    @FXML
    private Button orderButton;

    @FXML
    private Button tableButton;

    @FXML
    private Button inventoryButton;

    @FXML
    private Button reportButton;

    @FXML
    public void initialize() {
        // Perform initial setup or validations on buttons if needed
        validateButtons();
    }

    @FXML
    public void navigateToMenuManagement() {
        loadFXMLScene("/fxml/menu.fxml");
    }

    @FXML
    public void navigateToOrderManagement() {
        loadFXMLScene("/fxml/order.fxml");
    }

    @FXML
    public void navigateToTableManagement() {
        loadFXMLScene("/fxml/table.fxml");
    }

    @FXML
    public void navigateToInventoryManagement() {
        loadFXMLScene("/fxml/inventory.fxml");
    }

    @FXML
    public void navigateToReportManagement() {
        loadFXMLScene("/fxml/report.fxml");
    }

    private void loadFXMLScene(String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Stage stage = (Stage) menuButton.getScene().getWindow();
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace(); // This is the existing exception handling mechanism
        }
    }

    private void validateButtons() {
        // Example: Ensure buttons are visible and enabled
        menuButton.setVisible(true);
        orderButton.setVisible(true);
        tableButton.setVisible(true);
        inventoryButton.setVisible(true);
        reportButton.setVisible(true);
    }
}