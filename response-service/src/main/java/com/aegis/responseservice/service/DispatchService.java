package com.aegis.responseservice.service;

import com.aegis.responseservice.dto.DispatchRequest;
import com.aegis.responseservice.dto.DispatchResponse;
import com.aegis.responseservice.dto.StatusUpdateRequest;
import com.aegis.responseservice.feign.IncidentFeignClient;
import com.aegis.responseservice.feign.ResourceFeignClient;
import com.aegis.responseservice.model.Dispatch;
import com.aegis.responseservice.model.DispatchStatus;
import com.aegis.responseservice.repository.DispatchRepository;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

/**
 * DispatchService - The brain of the AEGIS system.
 *
 * Handles the core dispatch logic:
 * 1. Fetches incident details from Incident Service via Feign
 * 2. Finds available resources from Resource Service via Feign
 * 3. Matches the best resource to the incident
 * 4. Updates the resource status to DEPLOYED
 * 5. Updates the incident status to IN_PROGRESS
 * 6. Saves the dispatch record
 */
@Service
@SuppressWarnings("null")
public class DispatchService {

    /** Feign client for Incident Service. */
    private final IncidentFeignClient incidentClient;

    /** Feign client for Resource Service. */
    private final ResourceFeignClient resourceClient;

    /** Repository for dispatch records. */
    private final DispatchRepository dispatchRepository;

    /**
     * Constructor injection.
     *
     * @param incidentClient     - Feign client for Incident Service
     * @param resourceClient     - Feign client for Resource Service
     * @param dispatchRepository - repository for dispatch records
     */
    public DispatchService(final IncidentFeignClient incidentClient,
                           final ResourceFeignClient resourceClient,
                           final DispatchRepository dispatchRepository) {
        this.incidentClient = incidentClient;
        this.resourceClient = resourceClient;
        this.dispatchRepository = dispatchRepository;
    }

    /**
     * Dispatch a resource to an incident.
     *
     * Steps:
     * 1. Fetch incident details from Incident Service
     * 2. If resourceId is provided, use that specific resource
     * 3. If not, find the first AVAILABLE resource that matches
     * 4. Update resource status to DEPLOYED
     * 5. Save the dispatch record
     *
     * @param request - dispatch request with incidentId and optional resourceId
     * @return dispatch response with full details
     */
    public DispatchResponse createDispatch(final DispatchRequest request) {

        // Step 1: Fetch incident details from Incident Service
        final Map<String, Object> incident =
                incidentClient.getIncidentById(request.getIncidentId());

        final String incidentTitle =
                getString(incident, "title");
        final String incidentType =
                getString(incident, "type");
        final String incidentLocation =
                getString(incident, "location");

        // Step 2: Find a resource
        Map<String, Object> selectedResource;

        if (request.getResourceId() != null) {
            // Use the specific resource requested
            selectedResource =
                    resourceClient.getResourceById(request.getResourceId());

            // Verify the resource is AVAILABLE
            final String resourceStatus =
                    getString(selectedResource, "status");
            if (!"AVAILABLE".equals(resourceStatus)) {
                throw new RuntimeException(
                        "Resource is not available. Current status: "
                                + resourceStatus);
            }
        } else {
            // Auto-select: find first AVAILABLE resource
            final List<Map<String, Object>> allResources =
                    resourceClient.getAllResources();

            selectedResource = allResources.stream()
                    .filter(r -> "AVAILABLE".equals(
                            getString(r, "status")))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException(
                            "No available resources found for dispatch."));
        }

        final Long resourceId =
                getLong(selectedResource, "id");
        final String resourceName =
                getString(selectedResource, "name");
        final String resourceType =
                getString(selectedResource, "type");

        // Step 3: Update resource status to DEPLOYED
        final Map<String, String> statusUpdate = new HashMap<>();
        statusUpdate.put("status", "DEPLOYED");
        resourceClient.updateResourceStatus(resourceId, statusUpdate);

        // Step 4: Save the dispatch record
        final Dispatch dispatch = new Dispatch();
        dispatch.setIncidentId(request.getIncidentId());
        dispatch.setIncidentTitle(incidentTitle);
        dispatch.setIncidentType(incidentType);
        dispatch.setIncidentLocation(incidentLocation);
        dispatch.setResourceId(resourceId);
        dispatch.setResourceName(resourceName);
        dispatch.setResourceType(resourceType);
        dispatch.setStatus(DispatchStatus.DISPATCHED);
        dispatch.setDispatchedAt(LocalDateTime.now());
        dispatch.setUpdatedAt(LocalDateTime.now());
        dispatch.setNotes(request.getNotes());

        final Dispatch saved = dispatchRepository.save(dispatch);

        final String alertMessage = "🚨 RED ALERT! 🚨\n"
                + "Emergency: " + incidentTitle + "\n"
                + "Location: " + incidentLocation + "\n"
                + "Action: " + resourceName + " has been dispatched to your location.";
        sendTelegramAlert(alertMessage);

        // Step 5: Build response
        return toResponse(saved,
                resourceName + " dispatched to " + incidentTitle
                        + " at " + incidentLocation + " successfully!");
    }

    /**
     * Sends an instant Telegram alert to the victim's phone.
     */
    private void sendTelegramAlert(final String message) {
        try {
            final String token = "8934879324:AAF87m_hbRSQRH4YILNHjOjTeAD-Ffu1LLY";
            final String chatId = "8127012517";
            final String urlString = "https://api.telegram.org/bot{token}/sendMessage?chat_id={chatId}&text={text}";
            
            final org.springframework.web.client.RestTemplate restTemplate = 
                    new org.springframework.web.client.RestTemplate();
            restTemplate.getForObject(urlString, String.class, token, chatId, message);
            System.out.println("✅ Telegram Alert Sent Successfully!");
        } catch (Exception e) {
            System.err.println("❌ Failed to send Telegram alert: " + e.getMessage());
        }
    }

    /**
     * Get all dispatch records.
     *
     * @return list of all dispatches
     */
    public List<DispatchResponse> getAllDispatches() {
        return dispatchRepository.findAll()
                .stream()
                .map(d -> toResponse(d, null))
                .collect(Collectors.toList());
    }

    /**
     * Get a specific dispatch by ID.
     *
     * @param id - dispatch ID
     * @return dispatch response
     */
    public DispatchResponse getDispatchById(final Long id) {
        final Dispatch dispatch = dispatchRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Dispatch not found with ID: " + id));
        return toResponse(dispatch, null);
    }

    /**
     * Get all dispatches for a specific incident.
     *
     * @param incidentId - incident ID
     * @return list of dispatches
     */
    public List<DispatchResponse> getByIncidentId(final Long incidentId) {
        return dispatchRepository.findByIncidentId(incidentId)
                .stream()
                .map(d -> toResponse(d, null))
                .collect(Collectors.toList());
    }

    /**
     * Get all dispatches by status.
     *
     * @param status - dispatch status
     * @return list of dispatches
     */
    public List<DispatchResponse> getByStatus(final DispatchStatus status) {
        return dispatchRepository.findByStatus(status)
                .stream()
                .map(d -> toResponse(d, null))
                .collect(Collectors.toList());
    }

    /**
     * Update the status of a dispatch.
     * When status changes to COMPLETED, also release the resource
     * back to AVAILABLE.
     *
     * @param id      - dispatch ID
     * @param request - new status
     * @return updated dispatch
     */
    public DispatchResponse updateStatus(final Long id,
                                         final StatusUpdateRequest request) {
        final Dispatch dispatch = dispatchRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Dispatch not found with ID: " + id));

        final DispatchStatus newStatus =
                DispatchStatus.valueOf(request.getStatus());
        dispatch.setStatus(newStatus);
        dispatch.setUpdatedAt(LocalDateTime.now());

        // If completed or cancelled, release the resource back to AVAILABLE
        if (newStatus == DispatchStatus.COMPLETED
                || newStatus == DispatchStatus.CANCELLED) {
            final Map<String, String> statusUpdate = new HashMap<>();
            statusUpdate.put("status", "AVAILABLE");
            resourceClient.updateResourceStatus(
                    dispatch.getResourceId(), statusUpdate);
        }

        final Dispatch saved = dispatchRepository.save(dispatch);
        return toResponse(saved,
                "Dispatch status updated to " + newStatus);
    }

    // =============================================
    // HELPER METHODS
    // =============================================

    /**
     * Convert a Dispatch entity to a DispatchResponse DTO.
     *
     * @param dispatch - the dispatch entity
     * @param message  - optional message
     * @return DispatchResponse DTO
     */
    private DispatchResponse toResponse(final Dispatch dispatch,
                                        final String message) {
        final DispatchResponse response = new DispatchResponse();
        response.setId(dispatch.getId());
        response.setIncidentId(dispatch.getIncidentId());
        response.setIncidentTitle(dispatch.getIncidentTitle());
        response.setIncidentType(dispatch.getIncidentType());
        response.setIncidentLocation(dispatch.getIncidentLocation());
        response.setResourceId(dispatch.getResourceId());
        response.setResourceName(dispatch.getResourceName());
        response.setResourceType(dispatch.getResourceType());
        response.setStatus(dispatch.getStatus());
        response.setDispatchedAt(dispatch.getDispatchedAt());
        response.setUpdatedAt(dispatch.getUpdatedAt());
        response.setNotes(dispatch.getNotes());
        response.setMessage(message);
        return response;
    }

    /**
     * Safely get a String value from a Map.
     *
     * @param map - the map
     * @param key - the key
     * @return the string value or "UNKNOWN"
     */
    private String getString(final Map<String, Object> map,
                             final String key) {
        final Object value = map.get(key);
        return value != null ? value.toString() : "UNKNOWN";
    }

    /**
     * Safely get a Long value from a Map.
     *
     * @param map - the map
     * @param key - the key
     * @return the long value
     */
    private Long getLong(final Map<String, Object> map,
                         final String key) {
        final Object value = map.get(key);
        if (value instanceof Number) {
            return ((Number) value).longValue();
        }
        return Long.parseLong(value.toString());
    }
}
