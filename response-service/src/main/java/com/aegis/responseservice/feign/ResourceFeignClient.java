package com.aegis.responseservice.feign;

import java.util.List;
import java.util.Map;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * ResourceFeignClient - Feign declarative REST client for Resource Service.
 *
 * Communicates with RESOURCE-SERVICE registered in Eureka.
 * Used to find available resources and update their status to DEPLOYED.
 */
@FeignClient(name = "RESOURCE-SERVICE")
public interface ResourceFeignClient {

    /**
     * Get all resources from the Resource Service.
     *
     * @return list of resource maps
     */
    @GetMapping("/resources")
    List<Map<String, Object>> getAllResources();

    /**
     * Get all resources filtered by status.
     *
     * @param status - the status to filter by (e.g. AVAILABLE)
     * @return list of matching resources
     */
    @GetMapping("/resources?status={status}")
    List<Map<String, Object>> getResourcesByStatus(
            @PathVariable("status") String status);

    /**
     * Get a specific resource by ID.
     *
     * @param id - resource ID
     * @return resource data as a map
     */
    @GetMapping("/resources/{id}")
    Map<String, Object> getResourceById(@PathVariable("id") Long id);

    /**
     * Update the status of a resource (e.g., AVAILABLE to DEPLOYED).
     *
     * @param id      - resource ID
     * @param request - status update body
     * @return updated resource
     */
    @PutMapping("/resources/{id}/status")
    Map<String, Object> updateResourceStatus(
            @PathVariable("id") Long id,
            @RequestBody Map<String, String> request);
}
