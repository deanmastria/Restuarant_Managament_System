package org.example.dao;

import org.example.models.Role;
import org.example.models.User;
import org.example.utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    private Connection connection;

    public UserDAO() {
        // Initialize the connection using your existing DatabaseConnection class
        this.connection = DatabaseConnection.connect();
    }

    public void addUser(String username, String passwordHash, Role role) throws SQLException {
        String sql = "INSERT INTO Users (username, passwordHash, role) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, passwordHash);
            pstmt.setString(3, role.name());
            pstmt.executeUpdate();
        }
    }

    public User findUserByUsername(String username) throws SQLException {
        String sql = "SELECT * FROM Users WHERE username = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String passwordHash = rs.getString("passwordHash");
                Role role = Role.valueOf(rs.getString("role"));
                return new User(username, passwordHash, role);
            } else {
                return null; // User not found
            }
        }
    }

    public boolean authenticateUser(String username, String passwordHash) throws SQLException {
        String sql = "SELECT * FROM Users WHERE username = ? AND passwordHash = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, passwordHash);
            ResultSet rs = pstmt.executeQuery();
            return rs.next(); // Returns true if a matching user is found
        }
    }

    public Role getUserRole(String username) throws SQLException {
        String sql = "SELECT role FROM Users WHERE username = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return Role.valueOf(rs.getString("role"));
            } else {
                return null; // Role not found or user doesn't exist
            }
        }
    }

    // Additional methods for updating or deleting users can be added here as needed
}
