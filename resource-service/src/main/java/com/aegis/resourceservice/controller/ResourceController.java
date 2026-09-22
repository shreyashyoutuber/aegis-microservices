package com.aegis.resourceservice.controller;

import com.aegis.resourceservice.dto.ResourceRequest;
import com.aegis.resourceservice.dto.ResourceResponse;
import com.aegis.resourceservice.dto.StatusUpdateRequest;
import com.aegis.resourceservice.model.ResourceStatus;
import com.aegis.resourceservice.model.ResourceType;
import com.aegis.resourceservice.service.ResourceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * ResourceController - REST API endpoints for Resource Management.
 *
 * Endpoints:
 * POST   /resources              - Register a new resource
 * GET    /resources              - Get all resources (filter by status/type)
 * GET    /resources/{id}         - Get a specific resource
 * PUT    /resources/{id}         - Update resource details
 * PUT    /resources/{id}/status  - Update resource status (dispatch/recall)
 * DELETE /resources/{id}         - Delete a resource
 */
@RestController
@RequestMapping("/resources")
@SuppressWarnings("null")
public class ResourceController {

    /** Service layer for resource logic. */
    private final ResourceService resourceService;

    /**
     * Constructor injection.
     *
     * @param resourceService - service layer for resource logic
     */
    public ResourceController(
            final ResourceService resourceService) {
        this.resourceService = resourceService;
    }

    /**
     * Register a new emergency resource.
     *
     * @param request - resource data from client
     * @return 201 CREATED with the new resource
     */
    @PostMapping
    public ResponseEntity<ResourceResponse> createResource(
            @RequestBody final ResourceRequest request) {
        final ResourceResponse response =
                resourceService.createResource(request);
        return ResponseEntity
                .status(HttpStatus.CREATED).body(response);
    }

    /**
     * Get all resources, with optional filters.
     *
     * @param status - optional status filter
     * @param type   - optional type filter
     * @return list of resources
     */
    @GetMapping
    public ResponseEntity<List<ResourceResponse>> getAllResources(
            @RequestParam(required = false)
            final ResourceStatus status,
            @RequestParam(required = false)
            final ResourceType type) {

        if (type != null && status != null) {
            return ResponseEntity.ok(
                    resourceService.getByTypeAndStatus(type, status));
        }
        if (status != null) {
            return ResponseEntity.ok(
                    resourceService.getByStatus(status));
        }
        if (type != null) {
            return ResponseEntity.ok(
                    resourceService.getByType(type));
        }
        return ResponseEntity.ok(
                resourceService.getAllResources());
    }

    /**
     * Get a specific resource by ID.
     *
     * @param id - the resource ID
     * @return the resource or 404 if not found
     */
    @GetMapping("/{id}")
    public ResponseEntity<ResourceResponse> getResourceById(
            @PathVariable final Long id) {
        try {
            return ResponseEntity.ok(
                    resourceService.getResourceById(id));
        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Update the details of an existing resource.
     *
     * @param id      - resource ID to update
     * @param request - new details
     * @return updated resource
     */
    @PutMapping("/{id}")
    public ResponseEntity<ResourceResponse> updateResource(
            @PathVariable final Long id,
            @RequestBody final ResourceRequest request) {
        try {
            return ResponseEntity.ok(
                    resourceService.updateResource(id, request));
        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Update the status of a resource (dispatch or recall).
     *
     * @param id      - resource ID to update
     * @param request - new status
     * @return updated resource
     */
    @PutMapping("/{id}/status")
    public ResponseEntity<ResourceResponse> updateStatus(
            @PathVariable final Long id,
            @RequestBody final StatusUpdateRequest request) {
        try {
            return ResponseEntity.ok(
                    resourceService.updateStatus(id, request));
        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Delete a resource by ID.
     *
     * @param id - resource ID to delete
     * @return 204 NO CONTENT on success
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResource(
            @PathVariable final Long id) {
        try {
            resourceService.deleteResource(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }
    }
}
