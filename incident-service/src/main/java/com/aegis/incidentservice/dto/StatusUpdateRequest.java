package com.aegis.incidentservice.dto;

import com.aegis.incidentservice.model.IncidentStatus;

/**
 * StatusUpdateRequest - DTO for updating the status of an incident.
 */
public class StatusUpdateRequest {

    private IncidentStatus status;

    /**
     * Default constructor.
     */
    public StatusUpdateRequest() {
    }

    /**
     * Constructor with status.
     *
     * @param status - new status to set
     */
    public StatusUpdateRequest(final IncidentStatus status) {
        this.status = status;
    }

    /**
     * Gets the status.
     *
     * @return status
     */
    public IncidentStatus getStatus() {
        return status;
    }

    /**
     * Sets the status.
     *
     * @param status - status to set
     */
    public void setStatus(final IncidentStatus status) {
        this.status = status;
    }
}
