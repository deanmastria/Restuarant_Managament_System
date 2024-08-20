package org.example;

import org.example.models.Role;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.services.AuthService;
import org.example.utils.DatabaseInitializer;

public class App extends Application {

    public static void main(String[] args) {
        // Initialize the database
        DatabaseInitializer.initialize();

        // Launch the JavaFX application
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            // Load the login screen
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/login.fxml"));
            Scene scene = new Scene(loader.load());

            primaryStage.setTitle("Restaurant Management System - Login");
            primaryStage.setScene(scene);
            primaryStage.show();

            // Register a manager and a staff member after the database initialization
            registerDefaultUsers();
        } catch (Exception e) {
            System.err.println("Failed to load the login screen: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void registerDefaultUsers() {
        AuthService authService = new AuthService();

        try {
            // Log database connection and path
            System.out.println("Registering default users...");

            // Check if the manager already exists
            if (!authService.userExists("manager1")) {
                // Register a manager
                authService.register("manager1", "password123", Role.MANAGER);
                System.out.println("Manager registered.");
            } else {
                System.out.println("Manager already exists.");
            }

            // Check if the staff member already exists
            if (!authService.userExists("staff1")) {
                // Register a staff member
                authService.register("staff1", "password456", Role.STAFF);
                System.out.println("Staff member registered.");
            } else {
                System.out.println("Staff member already exists.");
            }
        } catch (Exception e) {
            System.err.println("Error during user registration: " + e.getMessage());
            e.printStackTrace();
        }
    }
}



