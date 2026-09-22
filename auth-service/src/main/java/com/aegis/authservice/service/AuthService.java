package com.aegis.authservice.service;

import com.aegis.authservice.dto.RegisterRequest;
import com.aegis.authservice.model.User;
import com.aegis.authservice.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * AuthService - Business Logic for Authentication
 *
 * Implements UserDetailsService so Spring Security can load users from DB
 *
 * Handles:
 * 1. User Registration - save new user with encrypted password
 * 2. User Login - verify credentials and generate JWT token
 * 3. Load User by Username - used by Spring Security internally
 */
@Service
public class AuthService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // =============================================
    // REGISTER
    // =============================================

    /**
     * Register a new user (Official or Responder)
     * 
     * @param request - contains username, password, role
     * @return success message
     */
    public String register(RegisterRequest request) {

        // Check if username already exists
        if (userRepository.existsByUsername(request.getUsername())) {
            return "Error: Username already exists!";
        }

        // Validate role
        String role = request.getRole().toUpperCase();
        if (!role.equals("OFFICIAL") && !role.equals("RESPONDER")) {
            return "Error: Role must be OFFICIAL or RESPONDER";
        }

        // Create new user with encrypted password
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword())); // BCrypt encrypt
        user.setRole(role);

        // Save to database
        userRepository.save(user);

        return "User registered successfully! Username: " + request.getUsername() + ", Role: " + role;
    }

    // =============================================
    // LOAD USER BY USERNAME (Spring Security)
    // =============================================

    /**
     * Load user details from DB for Spring Security authentication
     * Called internally by Spring Security during token validation
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole())));
    }
}
