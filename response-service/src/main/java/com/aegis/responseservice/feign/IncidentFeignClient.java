package com.aegis.responseservice.feign;

import java.util.List;
import java.util.Map;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * IncidentFeignClient - Feign declarative REST client for Incident Service.
 *
 * Communicates with INCIDENT-SERVICE registered in Eureka.
 * No manual HTTP code needed; Spring Cloud OpenFeign handles everything.
 */
@FeignClient(name = "INCIDENT-SERVICE")
public interface IncidentFeignClient {

    /**
     * Get all incidents from the Incident Service.
     *
     * @return list of incident maps
     */
    @GetMapping("/incidents")
    List<Map<String, Object>> getAllIncidents();

    /**
     * Get a specific incident by ID.
     *
     * @param id - incident ID
     * @return incident data as a map
     */
    @GetMapping("/incidents/{id}")
    Map<String, Object> getIncidentById(@PathVariable("id") Long id);
}
