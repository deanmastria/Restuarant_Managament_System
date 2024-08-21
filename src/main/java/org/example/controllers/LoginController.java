package org.example.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.models.Role;
import org.example.services.AuthService;

import java.io.IOException;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

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
    private Label loginStatusLabel;

    private AuthService authService;

    public LoginController() {
        authService = new AuthService();  // Initialize AuthService to handle login and role checks
    }

    @FXML
    public void handleLoginAction() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        // Check if username or password fields are empty
        if (username.isEmpty() || password.isEmpty()) {
            loginStatusLabel.setText("Please enter both username and password.");
            return;
        }

        boolean isAuthenticated = authService.login(username, password);  // Use the 'login' method from AuthService

        if (isAuthenticated) {
            Role userRole = authService.getRole(username);  // Get the Role enum directly

            if (userRole != null) {
                loginStatusLabel.setText("Login successful! Role: " + userRole);
                configurePermissions(userRole);  // Configure permissions based on the user's role
                // Buttons will be enabled based on the user's role, no automatic navigation
            } else {
                loginStatusLabel.setText("Error: Role not found for user.");
            }
        } else {
            loginStatusLabel.setText("Login failed. Incorrect username or password.");
        }
    }

    private void configurePermissions(Role role) {
        if (role == Role.STAFF) {
            // Staff permissions - enable buttons
            menuButton.setDisable(false);
            orderButton.setDisable(false);
            tableButton.setDisable(false);
            inventoryButton.setDisable(true);  // Restricted for staff
            reportButton.setDisable(true);     // Restricted for staff
        } else if (role == Role.MANAGER) {
            // Manager permissions - enable buttons
            menuButton.setDisable(false);
            orderButton.setDisable(false);
            tableButton.setDisable(false);
            inventoryButton.setDisable(false); // Accessible for managers
            reportButton.setDisable(false);    // Accessible for managers
        }
    }

    @FXML
    public void handleMenuButtonAction() {
        navigateToPage("menu.fxml", "Menu Management");
    }

    @FXML
    public void handleOrderButtonAction() {
        navigateToPage("order.fxml", "Order Management");
    }

    @FXML
    public void handleTableButtonAction() {
        navigateToPage("table.fxml", "Table Management");
    }

    @FXML
    public void handleInventoryButtonAction() {
        navigateToPage("inventory.fxml", "Table Management");
    }

    @FXML
    public void handleReportButtonAction() {
        navigateToPage("report.fxml", "Table Management");
    }

    // Navigate to a new page based on the FXML file and title
    private void navigateToPage(String fxmlFile, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/" + fxmlFile));
            Stage stage = (Stage) usernameField.getScene().getWindow(); // Get the current stage
            Scene scene = new Scene(loader.load()); // Load the new scene
            stage.setScene(scene);  // Set the new scene on the current stage
            stage.setTitle(title);  // Set the title for the new scene
            stage.show();  // Show the stage with the new scene
        } catch (IOException e) {
            e.printStackTrace();
            loginStatusLabel.setText("Error loading page: " + fxmlFile);
        }
    }

    // Method to clean up resources (if needed)
    public void cleanup() {
        authService = null;  // If additional cleanup is necessary
    }
}



