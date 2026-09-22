package com.aegis.eurekaserver;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Eureka Server Application Tests
 * Verifies that the Eureka Server context loads correctly
 */
@SpringBootTest
class EurekaServerApplicationTests {

    @Test
    void contextLoads() {
        // Verifies the Spring context loads without errors
        System.out.println("Eureka Server Context Loaded Successfully!");
    }
}
