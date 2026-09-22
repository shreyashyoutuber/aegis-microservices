package com.aegis.authservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Auth Service - Main Application
 * Aegis Emergency Response System
 *
 * Handles JWT Login and Registration for:
 * - Emergency Officials
 * - Field Responders
 *
 * Runs on Port: 8085
 * Registers with Eureka at: http://localhost:2026
 */
@SpringBootApplication
@EnableDiscoveryClient
public class AuthServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthServiceApplication.class, args);
        System.out.println("============================================");
        System.out.println("  AEGIS AUTH SERVICE STARTED               ");
        System.out.println("  Login  : POST http://localhost:8085/auth/login    ");
        System.out.println("  Register: POST http://localhost:8085/auth/register ");
        System.out.println("============================================");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
