package org.example.models;

public class User {
    private String username;
    private String passwordHash;    // Store the hashed password
    private String role;        //"Manager" or "staff"

    public User(String username, String passwordHash, String role) {
        this.username = username;
        this.passwordHash = passwordHash;
        this.role = role;
    }

    // Getters & Setters
    public String getUsername() {
        return username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public String getRole() {
        return role;
    }
}
