package org.example.models;

public class User {
    private final String username;
    private final String passwordHash;  // Store the hashed password
    private final Role role;            // "Manager" or "Staff"

    // Constructor
    public User(String username, String passwordHash, Role role) {
        this.username = username;
        this.passwordHash = passwordHash;
        this.role = role;
    }

    // Getters
    public String getUsername() {
        return username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public Role getRole() {
        return role;
    }

    // Override toString for better logging and debugging
    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", role=" + role +
                '}';
    }
}
