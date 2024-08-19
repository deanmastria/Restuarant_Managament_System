package org.example.services;

import org.example.models.User;
import org.example.utils.HashUtils;

import java.util.HashMap;
import java.util.Map;

public class AuthService {
    private Map<String, User> userDatabase = new HashMap<>(); // Simulated Database

    //Method to register new user
    public void registerUser(String username, String Password, String Role) {
        String HashedPassword = HashUtils.hashPassword(Password);
        userDatabase.put(username, new User(username, HashedPassword, Role));
    }

    //Method to authenticate User
    public User loginUser(String username, String password) {
        User user = userDatabase.get(username);
        if (user != null && HashUtils.verifyPassword(password, user.getPasswordHash())) {

            return user;        //Login successful
        }
        return null;    //login failed
    }
}
