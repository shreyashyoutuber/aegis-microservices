package com.aegis.resourceservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * ResourceServiceApplication - Main entry point for the Resource Service.
 *
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

    /**
     * Main method to start the Resource Service.
     *
     * @param args - command line arguments
     */
    public static void main(final String[] args) {
        SpringApplication.run(ResourceServiceApplication.class, args);
    }
}
