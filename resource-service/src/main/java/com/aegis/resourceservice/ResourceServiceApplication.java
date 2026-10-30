package com.aegis.resourceservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * ResourceServiceApplication - Main entry point for the Resource Service.
 * This service manages emergency resources such as:
 * - Ambulances
 * - Fire Trucks
 * - Police Cars
 * - Rescue Boats
 * - Helicopters
 */
@SpringBootApplication
@SuppressWarnings("null")
public class ResourceServiceApplication {
    public static void main(final String[] args) {
        SpringApplication.run(ResourceServiceApplication.class, args);
    }
}
