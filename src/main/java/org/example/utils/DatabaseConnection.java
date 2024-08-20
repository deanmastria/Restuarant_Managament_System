package org.example.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:sqlite:restaurant.db";  // Path to your SQLite DB file

    public static Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL);
            if (conn != null) {
                System.out.println("Connected to SQLite database.");
            }
        } catch (SQLException e) {
            System.err.println("Failed to connect to SQLite database: " + e.getMessage());
            e.printStackTrace();
        }
        return conn;
    }
}
