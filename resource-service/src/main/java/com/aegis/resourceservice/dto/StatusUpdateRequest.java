package com.aegis.resourceservice.dto;

import com.aegis.resourceservice.model.ResourceStatus;

/**
 * StatusUpdateRequest - DTO for updating a resource's status.
 *
 * Used when dispatching or recalling a resource.
 */
public class StatusUpdateRequest {

    /** The new status to apply. */
    private ResourceStatus status;

    /**
     * Default constructor.
     */
    public StatusUpdateRequest() {
    }

    /**
     * Constructor with status.
     *
     * @param status - the new status
     */
    public StatusUpdateRequest(final ResourceStatus status) {
        this.status = status;
    }

    /**
     * Gets the status.
     *
     * @return status
     */
    public ResourceStatus getStatus() {
        return status;
    }

    /**
     * Sets the status.
     *
     * @param status - the status to set
     */
    public void setStatus(final ResourceStatus status) {
        this.status = status;
    }
}
