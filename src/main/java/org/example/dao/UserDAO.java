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

    /**
     * Adds a new user to the database.
     *
     * @param username     The username of the new user.
     * @param passwordHash The hashed password of the new user.
     * @param role         The role of the new user.
     * @throws SQLException If a database error occurs.
     */
    public void addUser(String username, String passwordHash, Role role) throws SQLException {
        if (username == null || username.isEmpty() || passwordHash == null || passwordHash.isEmpty()) {
            throw new IllegalArgumentException("Username and password must not be null or empty.");
        }

        String sql = "INSERT INTO Users (username, passwordHash, role) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, passwordHash);
            pstmt.setString(3, role.name());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error adding user to the database: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Finds a user by their username.
     *
     * @param username The username of the user to find.
     * @return The User object, or null if not found.
     * @throws SQLException If a database error occurs.
     */
    public User findUserByUsername(String username) throws SQLException {
        if (username == null || username.isEmpty()) {
            throw new IllegalArgumentException("Username must not be null or empty.");
        }

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
        } catch (SQLException e) {
            System.err.println("Error finding user by username: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Authenticates a user by checking their username and password hash.
     *
     * @param username     The username of the user.
     * @param passwordHash The hashed password of the user.
     * @return True if the user is authenticated, false otherwise.
     * @throws SQLException If a database error occurs.
     */
    public boolean authenticateUser(String username, String passwordHash) throws SQLException {
        if (username == null || username.isEmpty() || passwordHash == null || passwordHash.isEmpty()) {
            throw new IllegalArgumentException("Username and password must not be null or empty.");
        }

        String sql = "SELECT * FROM Users WHERE username = ? AND passwordHash = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, passwordHash);
            ResultSet rs = pstmt.executeQuery();
            return rs.next(); // Returns true if a matching user is found
        } catch (SQLException e) {
            System.err.println("Error authenticating user: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Retrieves the role of a user by their username.
     *
     * @param username The username of the user.
     * @return The Role of the user, or null if not found.
     * @throws SQLException If a database error occurs.
     */
    public Role getUserRole(String username) throws SQLException {
        if (username == null || username.isEmpty()) {
            throw new IllegalArgumentException("Username must not be null or empty.");
        }

        String sql = "SELECT role FROM Users WHERE username = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return Role.valueOf(rs.getString("role"));
            } else {
                return null; // Role not found or user doesn't exist
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving user role: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Closes the database connection when done.
     */
    public void close() {
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

    // Additional methods for updating or deleting users can be added here as needed
}

