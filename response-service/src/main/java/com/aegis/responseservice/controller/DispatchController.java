package com.aegis.responseservice.controller;

import com.aegis.responseservice.dto.DispatchRequest;
import com.aegis.responseservice.dto.DispatchResponse;
import com.aegis.responseservice.dto.StatusUpdateRequest;
import com.aegis.responseservice.model.DispatchStatus;
import com.aegis.responseservice.service.DispatchService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;

/**
 * DispatchController - REST API endpoints for Dispatch Management.
 *
 * Endpoints:
 * POST   /dispatch              - Dispatch a resource to an incident
 * GET    /dispatch              - Get all dispatch records
 * GET    /dispatch/{id}         - Get a specific dispatch
 * GET    /dispatch?incidentId=X - Get dispatches for an incident
 * PUT    /dispatch/{id}/status  - Update dispatch status
 * GET    /dispatch/health       - Health check
 */
@RestController
@RequestMapping("/dispatch")
@SuppressWarnings("null")
public class DispatchController {

    /** Service layer for dispatch logic. */
    private final DispatchService dispatchService;

    /**
     * Constructor injection.
     *
     * @param dispatchService - service layer for dispatch logic
     */
    public DispatchController(
            final DispatchService dispatchService) {
        this.dispatchService = dispatchService;
    }

    /**
     * Dispatch a resource to an incident.
     *
     * @param request - dispatch request with incidentId
     * @return 201 CREATED with dispatch details
     */
    @PostMapping
    public ResponseEntity<Object> createDispatch(
            @RequestBody final DispatchRequest request) {
        try {
            final DispatchResponse response =
                    dispatchService.createDispatch(request);
            return ResponseEntity
                    .status(HttpStatus.CREATED).body(response);
        } catch (RuntimeException ex) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", ex.getMessage()));
        }
    }

    /**
     * Get all dispatches, with optional filter by incidentId or status.
     *
     * @param incidentId - optional incident ID filter
     * @param status     - optional status filter
     * @return list of dispatches
     */
    @GetMapping
    public ResponseEntity<List<DispatchResponse>> getAllDispatches(
            @RequestParam(required = false) final Long incidentId,
            @RequestParam(required = false) final DispatchStatus status) {

        if (incidentId != null) {
            return ResponseEntity.ok(
                    dispatchService.getByIncidentId(incidentId));
        }
        if (status != null) {
            return ResponseEntity.ok(
                    dispatchService.getByStatus(status));
        }
        return ResponseEntity.ok(
                dispatchService.getAllDispatches());
    }

    /**
     * Get a specific dispatch by ID.
     *
     * @param id - the dispatch ID
     * @return the dispatch or 404 if not found
     */
    @GetMapping("/{id}")
    public ResponseEntity<Object> getDispatchById(
            @PathVariable final Long id) {
        try {
            return ResponseEntity.ok(
                    dispatchService.getDispatchById(id));
        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Update the status of a dispatch.
     *
     * @param id      - dispatch ID to update
     * @param request - new status
     * @return updated dispatch
     */
    @PutMapping("/{id}/status")
    public ResponseEntity<Object> updateStatus(
            @PathVariable final Long id,
            @RequestBody final StatusUpdateRequest request) {
        try {
            return ResponseEntity.ok(
                    dispatchService.updateStatus(id, request));
        } catch (RuntimeException ex) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", ex.getMessage()));
        }
    }

    /**
     * Health check endpoint.
     *
     * @return health status
     */
    @GetMapping("/health")
    public ResponseEntity<Map<String, String>> health() {
        return ResponseEntity.ok(
                Map.of("service", "response-service",
                       "status", "UP",
                       "port", "8083"));
    }
}
