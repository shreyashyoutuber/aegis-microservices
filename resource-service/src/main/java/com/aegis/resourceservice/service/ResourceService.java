package com.aegis.resourceservice.service;

import com.aegis.resourceservice.dto.ResourceRequest;
import com.aegis.resourceservice.dto.ResourceResponse;
import com.aegis.resourceservice.dto.StatusUpdateRequest;
import com.aegis.resourceservice.model.Resource;
import com.aegis.resourceservice.model.ResourceStatus;
import com.aegis.resourceservice.model.ResourceType;
import com.aegis.resourceservice.repository.ResourceRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * ResourceService - Business Logic for Resource Management.
 *
 * Handles all operations:
 * - Register a new resource
 * - Get all resources
 * - Get resource by ID
 * - Update resource details
 * - Update resource status (dispatch/recall)
 * - Delete resource
 * - Filter by status, type, or both
 */
@Service
@SuppressWarnings("null")
public class ResourceService {

    /** JPA repository for resources. */
    private final ResourceRepository resourceRepository;

    /**
     * Constructor injection.
     *
     * @param resourceRepository - JPA repository for resources
     */
    public ResourceService(
            final ResourceRepository resourceRepository) {
        this.resourceRepository = resourceRepository;
    }

    // =============================================
    // CREATE
    // =============================================

    /**
     * Register a new emergency resource.
     *
     * @param request - resource details from client
     * @return the saved resource as a response DTO
     */
    public ResourceResponse createResource(
            final ResourceRequest request) {
        final Resource resource = new Resource();
        resource.setName(request.getName());
        resource.setType(request.getType());
        resource.setStatus(ResourceStatus.AVAILABLE);
        resource.setLocation(request.getLocation());
        resource.setContactInfo(request.getContactInfo());
        resource.setCreatedAt(LocalDateTime.now());
        resource.setUpdatedAt(LocalDateTime.now());

        final Resource saved = resourceRepository.save(resource);
        return toResponse(saved);
    }

    // =============================================
    // READ
    // =============================================

    /**
     * Get all resources from the database.
     *
     * @return list of all resources
     */
    public List<ResourceResponse> getAllResources() {
        return resourceRepository.findAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    /**
     * Get a single resource by its ID.
     *
     * @param id - resource ID
     * @return the resource if found
     * @throws RuntimeException if not found
     */
    public ResourceResponse getResourceById(final Long id) {
        final Resource resource = resourceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Resource not found with ID: " + id));
        return toResponse(resource);
    }

    /**
     * Get all resources filtered by status.
     *
     * @param status - the status to filter by
     * @return list of matching resources
     */
    public List<ResourceResponse> getByStatus(
            final ResourceStatus status) {
        return resourceRepository.findByStatus(status)
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    /**
     * Get all resources filtered by type.
     *
     * @param type - the type to filter by
     * @return list of matching resources
     */
    public List<ResourceResponse> getByType(
            final ResourceType type) {
        return resourceRepository.findByType(type)
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    /**
     * Get all resources filtered by type and status.
     *
     * @param type   - the type to filter by
     * @param status - the status to filter by
     * @return list of matching resources
     */
    public List<ResourceResponse> getByTypeAndStatus(
            final ResourceType type,
            final ResourceStatus status) {
        return resourceRepository
                .findByTypeAndStatus(type, status)
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    // =============================================
    // UPDATE
    // =============================================

    /**
     * Update the details of an existing resource.
     *
     * @param id      - resource ID to update
     * @param request - new details to apply
     * @return the updated resource
     * @throws RuntimeException if not found
     */
    public ResourceResponse updateResource(
            final Long id,
            final ResourceRequest request) {
        final Resource resource = resourceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Resource not found with ID: " + id));

        resource.setName(request.getName());
        resource.setType(request.getType());
        resource.setLocation(request.getLocation());
        resource.setContactInfo(request.getContactInfo());
        resource.setUpdatedAt(LocalDateTime.now());

        final Resource updated = resourceRepository.save(resource);
        return toResponse(updated);
    }

    /**
     * Update only the status of a resource.
     * Used when dispatching or recalling a resource.
     *
     * @param id      - resource ID to update
     * @param request - new status to apply
     * @return the updated resource
     * @throws RuntimeException if not found
     */
    public ResourceResponse updateStatus(
            final Long id,
            final StatusUpdateRequest request) {
        final Resource resource = resourceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Resource not found with ID: " + id));

        resource.setStatus(request.getStatus());
        resource.setUpdatedAt(LocalDateTime.now());

        final Resource updated = resourceRepository.save(resource);
        return toResponse(updated);
    }

    // =============================================
    // DELETE
    // =============================================

    /**
     * Delete a resource by its ID.
     *
     * @param id - resource ID to delete
     * @throws RuntimeException if not found
     */
    public void deleteResource(final Long id) {
        if (!resourceRepository.existsById(id)) {
            throw new RuntimeException(
                    "Resource not found with ID: " + id);
        }
        resourceRepository.deleteById(id);
    }

    // =============================================
    // HELPER - Convert Entity to Response DTO
    // =============================================

    /**
     * Maps a Resource entity to a ResourceResponse DTO.
     *
     * @param resource - the entity to convert
     * @return the DTO
     */
    private ResourceResponse toResponse(final Resource resource) {
        return new ResourceResponse(
                resource.getId(),
                resource.getName(),
                resource.getType(),
                resource.getStatus(),
                resource.getLocation(),
                resource.getContactInfo(),
                resource.getCreatedAt(),
                resource.getUpdatedAt()
        );
    }
}
