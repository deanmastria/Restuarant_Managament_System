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
                navigateToMainDirectory();  // Navigate to the Main Directory after successful login
            } else {
                loginStatusLabel.setText("Error: Role not found for user.");
            }
        } else {
            loginStatusLabel.setText("Login failed. Incorrect username or password.");
        }
    }

    private void navigateToMainDirectory() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MainDirectory.fxml"));
            Stage stage = (Stage) usernameField.getScene().getWindow(); // Get the current stage
            Scene scene = new Scene(loader.load()); // Load the new scene
            stage.setScene(scene);  // Set the new scene on the current stage
            stage.setTitle("Main Directory");  // Set the title for the new scene
            stage.show();  // Show the stage with the new scene
        } catch (IOException e) {
            e.printStackTrace();
            loginStatusLabel.setText("Error loading Main Directory.");
        }
    }

    // Method to clean up resources (if needed)
    public void cleanup() {
        authService = null;  // If additional cleanup is necessary
    }
}




