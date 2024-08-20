//package org.example;
//
//import org.example.models.User;
//import org.example.services.AuthService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.*;
//
//public class AuthServiceTest {
//    private AuthService authService;
//
//    @BeforeEach
//    public void setUp() {
//        authService = new AuthService();
//    }

//    @Test
//    public void testRegisterAndLoginUser() {
//        //Register a new user
//        authService.registerUser("Manager", "Password123", "Staff");
//
//        //Attempt to login with credentials
//        User user = authService.loginUser("Dean", "Password123");
//        assertNotNull(user, "User should be able to log in with correct credentials");
//        assertEquals("Staff", user.getRole(), "Role should be 'Staff'");
//    }
//
//    @Test
//    public void testLoginWithInvalidCredentials() {
//        authService.registerUser("Dean", "Password123", "Staff");
//
//        //Attempt to login with invalid credentials
//        User user = authService.loginUser("Dean", "NOOOOO");
//        assertNull(user, "Login should fail, incorrect password");
//    }
//
//    @Test
//    public void testLoginWithNonExistentUser() {
//        User user = authService.loginUser("Jackie", "Password123");
//        assertNull(user, "Login should fail for non registered user");
//    }
//}

