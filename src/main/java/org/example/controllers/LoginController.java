package org.example.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.example.models.Role;
import org.example.services.AuthService;

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
            } else {
                loginStatusLabel.setText("Error: Role not found for user.");
            }
        } else {
            loginStatusLabel.setText("Login failed. Incorrect username or password.");
        }
    }

    private void configurePermissions(Role role) {
        if (role == Role.STAFF) {
            // Staff permissions
            menuButton.setDisable(false);
            orderButton.setDisable(false);
            tableButton.setDisable(false);
            inventoryButton.setDisable(true);  // Restricted for staff
            reportButton.setDisable(true);     // Restricted for staff
        } else if (role == Role.MANAGER) {
            // Manager permissions
            menuButton.setDisable(false);
            orderButton.setDisable(false);
            tableButton.setDisable(false);
            inventoryButton.setDisable(false); // Accessible for managers
            reportButton.setDisable(false);    // Accessible for managers
        }
    }

    // Method to clean up resources (if needed)
    public void cleanup() {
        authService = null;  // If additional cleanup is necessary
    }
}


