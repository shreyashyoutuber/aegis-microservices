package com.aegis.authservice.controller;

import com.aegis.authservice.dto.AuthResponse;
import com.aegis.authservice.dto.LoginRequest;
import com.aegis.authservice.dto.RegisterRequest;
import com.aegis.authservice.service.AuthService;
import com.aegis.authservice.model.User;
import com.aegis.authservice.repository.UserRepository;
import com.aegis.authservice.security.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * AuthController - REST API Controller
 *
 * Exposes 2 public APIs:
 * 1. POST /auth/register - Register new official or responder
 * 2. POST /auth/login    - Login and receive JWT token
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    public AuthController(AuthService authService, AuthenticationManager authenticationManager, JwtUtil jwtUtil, UserRepository userRepository) {
        this.authService = authService;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
    }

    // =============================================
    // POST /auth/register
    // =============================================

    /**
     * Register a new Emergency Official or Field Responder
     */
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request) {
        String result = authService.register(request);

        if (result.startsWith("Error")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    // =============================================
    // POST /auth/login
    // =============================================

    /**
     * Login and receive JWT Token
     */
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        // Authenticate using Spring Security
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        // Load user from DB
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Generate token
        String token = jwtUtil.generateToken(user.getUsername(), user.getRole());

        AuthResponse response = new AuthResponse(
                token,
                user.getUsername(),
                user.getRole(),
                "Login successful!"
        );

        return ResponseEntity.ok(response);
    }

    // =============================================
    // GET /auth/health
    // =============================================

    /**
     * Simple health check endpoint
     */
    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("Auth Service is running on Port 8085");
    }
}
