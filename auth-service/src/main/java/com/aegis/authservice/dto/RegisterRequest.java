package com.aegis.authservice.dto;

/**
 * RegisterRequest DTO
 * Carries username, password, and role sent by user during registration
 */
public class RegisterRequest {
    private String username;
    private String password;
    private String role; // OFFICIAL or RESPONDER

    public RegisterRequest() {
    }

    public RegisterRequest(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
