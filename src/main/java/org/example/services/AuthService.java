package org.example.services;

import org.example.LoginSystem;
import org.example.models.Role;

public class AuthService {
    private final LoginSystem loginSystem;

    public AuthService() {
        loginSystem = new LoginSystem();
    }

    /**
     * Authenticates the user with the provided username and password.
     *
     * @param username The username of the user.
     * @param password The password of the user.
     * @return True if authentication is successful, false otherwise.
     */
    public boolean login(String username, String password) {
        try {
            return loginSystem.authenticate(username, password);
        } catch (Exception e) {
            System.err.println("Error during login: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Registers a new user with the provided username, password, and role.
     *
     * @param username The username of the new user.
     * @param password The password of the new user.
     * @param role     The role of the new user.
     */
    public void register(String username, String password, Role role) {
        try {
            loginSystem.addUser(username, password, role);
        } catch (Exception e) {
            System.err.println("Error during registration: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Retrieves the role of the specified user.
     *
     * @param username The username of the user.
     * @return The role of the user, or null if the user does not exist.
     */
    public Role getRole(String username) {
        try {
            return loginSystem.getUserRole(username);
        } catch (Exception e) {
            System.err.println("Error retrieving role: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Checks if a user with the specified username already exists.
     *
     * @param username The username to check.
     * @return True if the user exists, false otherwise.
     */
    public boolean userExists(String username) {
        try {
            return loginSystem.userExists(username);
        } catch (Exception e) {
            System.err.println("Error checking user existence: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}

