package com.aegis.incidentservice.controller;

import com.aegis.incidentservice.dto.IncidentRequest;
import com.aegis.incidentservice.dto.IncidentResponse;
import com.aegis.incidentservice.dto.StatusUpdateRequest;
import com.aegis.incidentservice.model.IncidentStatus;
import com.aegis.incidentservice.model.IncidentType;
import com.aegis.incidentservice.service.IncidentService;
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
 * IncidentController - REST API endpoints for Incident Management.
 *
 * Endpoints:
 * POST   /incidents            - Report a new incident
 * GET    /incidents            - Get all incidents (optional filter by status/type)
 * GET    /incidents/{id}       - Get a specific incident
 * PUT    /incidents/{id}/status - Update incident status
 * DELETE /incidents/{id}       - Delete an incident
 */
@RestController
@RequestMapping("/incidents")
@SuppressWarnings("null")
public class IncidentController {

    private final IncidentService incidentService;

    /**
     * Constructor injection.
     *
     * @param incidentService - service layer for incident logic
     */
    public IncidentController(final IncidentService incidentService) {
        this.incidentService = incidentService;
    }

    /**
     * Report a new emergency incident.
     *
     * @param request - incident data from client
     * @return 201 CREATED with the new incident
     */
    @PostMapping
    public ResponseEntity<IncidentResponse> createIncident(
            @RequestBody final IncidentRequest request) {
        final IncidentResponse response =
                incidentService.createIncident(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * Get all incidents, with optional filter by status or type.
     *
     * @param status - optional status filter
     * @param type   - optional type filter
     * @return list of incidents
     */
    @GetMapping
    public ResponseEntity<List<IncidentResponse>> getAllIncidents(
            @RequestParam(required = false) final IncidentStatus status,
            @RequestParam(required = false) final IncidentType type) {

        if (status != null) {
            return ResponseEntity.ok(incidentService.getByStatus(status));
        }
        if (type != null) {
            return ResponseEntity.ok(incidentService.getByType(type));
        }
        return ResponseEntity.ok(incidentService.getAllIncidents());
    }

    /**
     * Get a specific incident by ID.
     *
     * @param id - the incident ID
     * @return the incident or 404 if not found
     */
    @GetMapping("/{id}")
    public ResponseEntity<IncidentResponse> getIncidentById(
            @PathVariable final Long id) {
        try {
            return ResponseEntity.ok(incidentService.getIncidentById(id));
        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Update the status of an incident.
     *
     * @param id      - incident ID to update
     * @param request - new status
     * @return updated incident
     */
    @PutMapping("/{id}/status")
    public ResponseEntity<IncidentResponse> updateStatus(
            @PathVariable final Long id,
            @RequestBody final StatusUpdateRequest request) {
        try {
            return ResponseEntity.ok(
                    incidentService.updateStatus(id, request));
        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Delete an incident by ID.
     *
     * @param id - incident ID to delete
     * @return 204 NO CONTENT on success
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIncident(
            @PathVariable final Long id) {
        try {
            incidentService.deleteIncident(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }
    }
}
