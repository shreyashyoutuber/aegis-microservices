package com.aegis.authservice;

import com.aegis.authservice.dto.LoginRequest;
import com.aegis.authservice.dto.RegisterRequest;
import com.aegis.authservice.dto.AuthResponse;
import com.aegis.authservice.service.AuthService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Auth Service Integration Tests
 * Tests the complete flow: Register → Login → Get Token
 */
@SpringBootTest
class AuthServiceApplicationTests {

    @Autowired
    private AuthService authService;

    @Autowired
    private com.aegis.authservice.controller.AuthController authController;

    @Test
    void contextLoads() {
        // Verifies Spring context loads without errors
        System.out.println("Auth Service Context Loaded Successfully!");
    }

    @Test
    void testUserRegistration() {
        // Test: Register a new official
        RegisterRequest request = new RegisterRequest("test_official", "password123", "OFFICIAL");
        String result = authService.register(request);

        assertTrue(result.contains("successfully"));
        System.out.println("Registration Result: " + result);
    }

    @Test
    void testUserLogin() {
        // First register
        RegisterRequest registerRequest = new RegisterRequest("test_responder", "pass123", "RESPONDER");
        authController.register(registerRequest);

        // Then login
        LoginRequest loginRequest = new LoginRequest("test_responder", "pass123");
        AuthResponse response = authController.login(loginRequest).getBody();

        assertNotNull(response.getToken());
        assertEquals("test_responder", response.getUsername());
        assertEquals("RESPONDER", response.getRole());
        System.out.println("JWT Token: " + response.getToken());
    }

    @Test
    void testDuplicateRegistration() {
        // Register same user twice
        RegisterRequest request = new RegisterRequest("duplicate_user", "pass123", "OFFICIAL");
        authService.register(request);

        String result = authService.register(request);
        assertTrue(result.startsWith("Error"));
        System.out.println("Duplicate Registration Result: " + result);
    }
}
