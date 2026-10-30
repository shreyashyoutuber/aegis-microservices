package com.aegis.responseservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Response Service - Main Application
 * Aegis Emergency Response System
 *
 * The brain of AEGIS: matches incidents to available resources
 * and dispatches them automatically.
 * Runs on Port: 8083
 * Registers with Eureka at: http://localhost:2026
 *
 * APIs:
 * POST   /dispatch              - Dispatch a resource to an incident
 * GET    /dispatch              - Get all dispatch records
 * GET    /dispatch/{id}         - Get a specific dispatch record
 * PUT    /dispatch/{id}/status  - Update dispatch status
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class ResponseServiceApplication {

    public static void main(final String[] args) {
        SpringApplication.run(ResponseServiceApplication.class, args);
        System.out.println("==============================================");
        System.out.println("  AEGIS RESPONSE SERVICE STARTED              ");
        System.out.println("  Port   : 8083                               ");
        System.out.println("  APIs   : POST/GET /dispatch                 ");
        System.out.println("==============================================");
    }
}
