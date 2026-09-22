package com.aegis.eurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Eureka Service Discovery Server
 * Aegis Emergency Response System
 *
 * This server acts as the central service registry.
 * All microservices (Incident, Resource, Response, Gateway)
 * register themselves here so they can discover each other.
 *
 * Dashboard available at: http://localhost:8761
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaServerApplication.class, args);
        System.out.println("========================================");
        System.out.println("  AEGIS EUREKA SERVER STARTED           ");
        System.out.println("  Dashboard: http://localhost:2026       ");
        System.out.println("========================================");
    }
}
