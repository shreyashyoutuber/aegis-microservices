package com.aegis.incidentservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Incident Service - Main Application
 * Aegis Emergency Response System
 *
 * Manages emergency incidents: fire, flood, medical, earthquake, etc.
 *
 * Runs on Port: 8081
 * Registers with Eureka at: http://localhost:2026
 *
 * APIs:
 * POST   /incidents        - Report a new incident
 * GET    /incidents        - Get all incidents
 * GET    /incidents/{id}   - Get one incident by ID
 * PUT    /incidents/{id}   - Update incident status
 * DELETE /incidents/{id}   - Delete an incident
 */
@SpringBootApplication
@EnableDiscoveryClient
public class IncidentServiceApplication {

    public static void main(final String[] args) {
        SpringApplication.run(IncidentServiceApplication.class, args);
        System.out.println("==============================================");
        System.out.println("  AEGIS INCIDENT SERVICE STARTED             ");
        System.out.println("  Port   : 8081                              ");
        System.out.println("  APIs   : POST/GET /incidents               ");
        System.out.println("==============================================");
    }
}
