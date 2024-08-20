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
    public void start(Stage primaryStage) throws Exception {
        // Load the login screen
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/login.fxml"));
        Scene scene = new Scene(loader.load());

        primaryStage.setTitle("Restaurant Management System - Login");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Register a manager and a staff member after the database initialization
        registerDefaultUsers();
    }

    private void registerDefaultUsers() {
        AuthService authService = new AuthService();

        try {
            // Log database connection and path
            System.out.println("Registering default users...");

            // Register a manager
            authService.register("manager1", "password123", Role.MANAGER);
            System.out.println("Manager registered.");

            // Register a staff member
            authService.register("staff1", "password456", Role.STAFF);
            System.out.println("Staff member registered.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


