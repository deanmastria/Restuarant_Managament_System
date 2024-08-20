package org.example.models;

public class User {
    private String username;
    private String passwordHash;    // Store the hashed password
    private Role role;        //"Manager" or "staff"

    public User(String username, String passwordHash, Role role) {
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

    public Role getRole() {
        return role;
    }
}
