package org.example.services;

import org.example.LoginSystem;
import org.example.models.Role;

public class AuthService {
    private final LoginSystem loginSystem;

    public AuthService() {
        loginSystem = new LoginSystem();
    }

    public boolean login(String username, String password) {
        return loginSystem.authenticate(username, password);
    }

    public void register(String username, String password, Role role) {
        loginSystem.addUser(username, password, role);
    }

    public String getRole(String username) {
        return loginSystem.getUserRole(username).toString();
    }
}