package com.aegis.responseservice.dto;

import com.aegis.responseservice.model.DispatchStatus;
import java.time.LocalDateTime;

/**
 * DispatchResponse - DTO returned to the client after a dispatch operation.
 */
public class DispatchResponse {

    private Long id;
    private Long incidentId;
    private String incidentTitle;
    private String incidentType;
    private String incidentLocation;
    private Long resourceId;
    private String resourceName;
    private String resourceType;
    private DispatchStatus status;
    private LocalDateTime dispatchedAt;
    private LocalDateTime updatedAt;
    private String notes;
    private String message;

    // =============================================
    // CONSTRUCTORS
    // =============================================

    /**
     * Default constructor.
     */
    public DispatchResponse() {
    }

    /**
     * Full constructor.
     *
     * @param id               - dispatch ID
     * @param incidentId       - incident ID
     * @param incidentTitle    - incident title
     * @param incidentType     - incident type
     * @param incidentLocation - incident location
     * @param resourceId       - resource ID
     * @param resourceName     - resource name
     * @param resourceType     - resource type
     * @param status           - dispatch status
     * @param dispatchedAt     - dispatch time
     * @param updatedAt        - last update time
     * @param notes            - additional notes
     * @param message          - human-readable summary
     */
    public DispatchResponse(final Long id, final Long incidentId,
                            final String incidentTitle,
                            final String incidentType,
                            final String incidentLocation,
                            final Long resourceId,
                            final String resourceName,
                            final String resourceType,
                            final DispatchStatus status,
                            final LocalDateTime dispatchedAt,
                            final LocalDateTime updatedAt,
                            final String notes,
                            final String message) {
        this.id = id;
        this.incidentId = incidentId;
        this.incidentTitle = incidentTitle;
        this.incidentType = incidentType;
        this.incidentLocation = incidentLocation;
        this.resourceId = resourceId;
        this.resourceName = resourceName;
        this.resourceType = resourceType;
        this.status = status;
        this.dispatchedAt = dispatchedAt;
        this.updatedAt = updatedAt;
        this.notes = notes;
        this.message = message;
    }

    // =============================================
    // GETTERS AND SETTERS
    // =============================================

    /**
     * Gets the dispatch ID.
     *
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the dispatch ID.
     *
     * @param id - id to set
     */
    public void setId(final Long id) {
        this.id = id;
    }

    /**
     * Gets the incident ID.
     *
     * @return incidentId
     */
    public Long getIncidentId() {
        return incidentId;
    }

    /**
     * Sets the incident ID.
     *
     * @param incidentId - incident ID to set
     */
    public void setIncidentId(final Long incidentId) {
        this.incidentId = incidentId;
    }

    /**
     * Gets the incident title.
     *
     * @return incidentTitle
     */
    public String getIncidentTitle() {
        return incidentTitle;
    }

    /**
     * Sets the incident title.
     *
     * @param incidentTitle - title to set
     */
    public void setIncidentTitle(final String incidentTitle) {
        this.incidentTitle = incidentTitle;
    }

    /**
     * Gets the incident type.
     *
     * @return incidentType
     */
    public String getIncidentType() {
        return incidentType;
    }

    /**
     * Sets the incident type.
     *
     * @param incidentType - type to set
     */
    public void setIncidentType(final String incidentType) {
        this.incidentType = incidentType;
    }

    /**
     * Gets the incident location.
     *
     * @return incidentLocation
     */
    public String getIncidentLocation() {
        return incidentLocation;
    }

    /**
     * Sets the incident location.
     *
     * @param incidentLocation - location to set
     */
    public void setIncidentLocation(final String incidentLocation) {
        this.incidentLocation = incidentLocation;
    }

    /**
     * Gets the resource ID.
     *
     * @return resourceId
     */
    public Long getResourceId() {
        return resourceId;
    }

    /**
     * Sets the resource ID.
     *
     * @param resourceId - resource ID to set
     */
    public void setResourceId(final Long resourceId) {
        this.resourceId = resourceId;
    }

    /**
     * Gets the resource name.
     *
     * @return resourceName
     */
    public String getResourceName() {
        return resourceName;
    }

    /**
     * Sets the resource name.
     *
     * @param resourceName - name to set
     */
    public void setResourceName(final String resourceName) {
        this.resourceName = resourceName;
    }

    /**
     * Gets the resource type.
     *
     * @return resourceType
     */
    public String getResourceType() {
        return resourceType;
    }

    /**
     * Sets the resource type.
     *
     * @param resourceType - type to set
     */
    public void setResourceType(final String resourceType) {
        this.resourceType = resourceType;
    }

    /**
     * Gets the dispatch status.
     *
     * @return status
     */
    public DispatchStatus getStatus() {
        return status;
    }

    /**
     * Sets the dispatch status.
     *
     * @param status - status to set
     */
    public void setStatus(final DispatchStatus status) {
        this.status = status;
    }

    /**
     * Gets the dispatch timestamp.
     *
     * @return dispatchedAt
     */
    public LocalDateTime getDispatchedAt() {
        return dispatchedAt;
    }

    /**
     * Sets the dispatch timestamp.
     *
     * @param dispatchedAt - time to set
     */
    public void setDispatchedAt(final LocalDateTime dispatchedAt) {
        this.dispatchedAt = dispatchedAt;
    }

    /**
     * Gets the last update time.
     *
     * @return updatedAt
     */
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    /**
     * Sets the last update time.
     *
     * @param updatedAt - time to set
     */
    public void setUpdatedAt(final LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     * Gets the notes.
     *
     * @return notes
     */
    public String getNotes() {
        return notes;
    }

    /**
     * Sets the notes.
     *
     * @param notes - notes to set
     */
    public void setNotes(final String notes) {
        this.notes = notes;
    }

    /**
     * Gets the human-readable message.
     *
     * @return message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the human-readable message.
     *
     * @param message - message to set
     */
    public void setMessage(final String message) {
        this.message = message;
    }
}
