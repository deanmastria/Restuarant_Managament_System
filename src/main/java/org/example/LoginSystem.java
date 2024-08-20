package org.example;

import org.example.models.Role; // Import the Role class from the models package

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.regex.Pattern;

public class LoginSystem {

    private Connection connection;

    public LoginSystem() {
        try {
            // Establish SQLite connection to restaurant.db
            connection = DriverManager.getConnection("jdbc:sqlite:restaurant.db");
        } catch (SQLException e) {
            System.err.println("Failed to establish SQLite connection: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Allow injection of a mock connection for testing
    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public void addUser(String username, String password, Role role) {
        if (!isValidUsername(username)) {
            System.out.println("Invalid username format.");
            return;
        }

        try {
            String hashedPassword = hashPassword(password);
            String sql = "INSERT INTO Users (username, passwordHash, role) VALUES (?, ?, ?)";
            try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
                pstmt.setString(1, username);
                pstmt.setString(2, hashedPassword);
                pstmt.setString(3, role.toString());
                pstmt.executeUpdate();
                System.out.println("User " + username + " added successfully.");
            }
        } catch (SQLException e) {
            System.err.println("Error adding user to the database: " + e.getMessage());
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            System.err.println("Error hashing the password: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public boolean authenticate(String username, String password) {
        try {
            String hashedPassword = hashPassword(password);
            String sql = "SELECT passwordHash FROM Users WHERE username = ?";
            try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
                pstmt.setString(1, username);
                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    boolean isAuthenticated = rs.getString("passwordHash").equals(hashedPassword);
                    if (isAuthenticated) {
                        System.out.println("User " + username + " authenticated successfully.");
                    } else {
                        System.out.println("Authentication failed for user " + username + ".");
                    }
                    return isAuthenticated;
                }
            }
        } catch (SQLException e) {
            System.err.println("Error authenticating user: " + e.getMessage());
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            System.err.println("Error hashing the password: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    public Role getUserRole(String username) {
        try {
            String sql = "SELECT role FROM Users WHERE username = ?";
            try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
                pstmt.setString(1, username);
                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    Role role = Role.valueOf(rs.getString("role"));
                    System.out.println("User " + username + " has role: " + role);
                    return role;
                } else {
                    System.out.println("User " + username + " not found.");
                }
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving user role: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public boolean userExists(String username) {
        try {
            String sql = "SELECT COUNT(*) FROM Users WHERE username = ?";
            try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
                pstmt.setString(1, username);
                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    boolean exists = rs.getInt(1) > 0;
                    System.out.println("User " + username + " exists: " + exists);
                    return exists;
                }
            }
        } catch (SQLException e) {
            System.err.println("Error checking if user exists: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    private String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hash = md.digest(password.getBytes());
        StringBuilder hexString = new StringBuilder();
        for (byte b : hash) {
            hexString.append(String.format("%02x", b));
        }
        return hexString.toString();
    }

    // Validate username using Regex
    private boolean isValidUsername(String username) {
        String regex = "^[a-zA-Z0-9_]{5,15}$";
        return Pattern.matches(regex, username);
    }

    // Close the database connection when no longer needed
    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Database connection closed.");
            } catch (SQLException e) {
                System.err.println("Error closing the database connection: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}

