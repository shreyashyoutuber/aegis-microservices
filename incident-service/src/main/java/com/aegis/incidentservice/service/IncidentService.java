package com.aegis.incidentservice.service;

import com.aegis.incidentservice.dto.IncidentRequest;
import com.aegis.incidentservice.dto.IncidentResponse;
import com.aegis.incidentservice.dto.StatusUpdateRequest;
import com.aegis.incidentservice.model.Incident;
import com.aegis.incidentservice.model.IncidentStatus;
import com.aegis.incidentservice.model.IncidentType;
import com.aegis.incidentservice.repository.IncidentRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * IncidentService - Business Logic for Incident Management.
 *
 * Handles all operations:
 * - Create a new incident
 * - Get all incidents
 * - Get incident by ID
 * - Update incident status
 * - Delete incident
 * - Filter by status or type
 */
@Service
public class IncidentService {

    private final IncidentRepository incidentRepository;

    /**
     * Constructor injection.
     *
     * @param incidentRepository - JPA repository for incidents
     */
    public IncidentService(final IncidentRepository incidentRepository) {
        this.incidentRepository = incidentRepository;
    }

    // =============================================
    // CREATE
    // =============================================

    /**
     * Report a new emergency incident.
     *
     * @param request - incident details from client
     * @return the saved incident as a response DTO
     */
    public IncidentResponse createIncident(final IncidentRequest request) {
        final Incident incident = new Incident();
        incident.setTitle(request.getTitle());
        incident.setDescription(request.getDescription());
        incident.setType(request.getType());
        incident.setStatus(IncidentStatus.REPORTED);
        incident.setLocation(request.getLocation());
        incident.setReportedBy(request.getReportedBy());
        incident.setCreatedAt(LocalDateTime.now());
        incident.setUpdatedAt(LocalDateTime.now());

        final Incident saved = incidentRepository.save(incident);
        return toResponse(saved);
    }

    // =============================================
    // READ
    // =============================================

    /**
     * Get all incidents from the database.
     *
     * @return list of all incidents
     */
    public List<IncidentResponse> getAllIncidents() {
        return incidentRepository.findAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    /**
     * Get a single incident by its ID.
     *
     * @param id - incident ID
     * @return the incident if found
     * @throws RuntimeException if not found
     */
    public IncidentResponse getIncidentById(final Long id) {
        final Incident incident = incidentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Incident not found with ID: " + id));
        return toResponse(incident);
    }

    /**
     * Get all incidents filtered by status.
     *
     * @param status - the status to filter by
     * @return list of matching incidents
     */
    public List<IncidentResponse> getByStatus(final IncidentStatus status) {
        return incidentRepository.findByStatus(status)
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    /**
     * Get all incidents filtered by type.
     *
     * @param type - the type to filter by
     * @return list of matching incidents
     */
    public List<IncidentResponse> getByType(final IncidentType type) {
        return incidentRepository.findByType(type)
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    // =============================================
    // UPDATE
    // =============================================

    /**
     * Update the status of an existing incident.
     *
     * @param id      - incident ID to update
     * @param request - new status to apply
     * @return the updated incident
     * @throws RuntimeException if not found
     */
    public IncidentResponse updateStatus(final Long id,
                                         final StatusUpdateRequest request) {
        final Incident incident = incidentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Incident not found with ID: " + id));

        incident.setStatus(request.getStatus());
        incident.setUpdatedAt(LocalDateTime.now());

        final Incident updated = incidentRepository.save(incident);
        return toResponse(updated);
    }

    // =============================================
    // DELETE
    // =============================================

    /**
     * Delete an incident by its ID.
     *
     * @param id - incident ID to delete
     * @throws RuntimeException if not found
     */
    public void deleteIncident(final Long id) {
        if (!incidentRepository.existsById(id)) {
            throw new RuntimeException("Incident not found with ID: " + id);
        }
        incidentRepository.deleteById(id);
    }

    // =============================================
    // HELPER - Convert Entity to Response DTO
    // =============================================

    /**
     * Maps an Incident entity to an IncidentResponse DTO.
     *
     * @param incident - the entity to convert
     * @return the DTO
     */
    private IncidentResponse toResponse(final Incident incident) {
        return new IncidentResponse(
                incident.getId(),
                incident.getTitle(),
                incident.getDescription(),
                incident.getType(),
                incident.getStatus(),
                incident.getLocation(),
                incident.getReportedBy(),
                incident.getCreatedAt(),
                incident.getUpdatedAt()
        );
    }
}
