package com.aegis.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * API Gateway - Main Application
 * Aegis Emergency Response System
 *
 * Routes all incoming requests to the appropriate microservices.
 *
 * Runs on Port: 8080
 * Registers with Eureka at: http://localhost:2026
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ApiGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
        System.out.println("============================================");
        System.out.println("  AEGIS API GATEWAY STARTED                 ");
        System.out.println("  Port   : 8080                             ");
        System.out.println("  Routes : /auth/** -> auth-service         ");
        System.out.println("============================================");
    }
}
