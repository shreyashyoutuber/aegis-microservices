package com.aegis.responseservice.dto;

/**
 * DispatchRequest - DTO for creating a new dispatch.
 * The client sends the incident ID and optionally a specific resource ID.
 * If no resource ID is provided, the system auto-selects the best match.
 */
public class DispatchRequest {

    private Long incidentId;
    private Long resourceId;
    private String notes;

    // =============================================
    // CONSTRUCTORS
    // =============================================

    /**
     * Default constructor.
     */
    public DispatchRequest() {
    }

    /**
     * Full constructor.
     *
     * @param incidentId - ID of the incident to respond to
     * @param resourceId - ID of the resource to dispatch (optional)
     * @param notes      - additional instructions
     */
    public DispatchRequest(final Long incidentId, final Long resourceId,
                           final String notes) {
        this.incidentId = incidentId;
        this.resourceId = resourceId;
        this.notes = notes;
    }

    // =============================================
    // GETTERS AND SETTERS
    // =============================================

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
     * @param incidentId - the incident ID to set
     */
    public void setIncidentId(final Long incidentId) {
        this.incidentId = incidentId;
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
     * @param resourceId - the resource ID to set
     */
    public void setResourceId(final Long resourceId) {
        this.resourceId = resourceId;
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
     * @param notes - the notes to set
     */
    public void setNotes(final String notes) {
        this.notes = notes;
    }
}
