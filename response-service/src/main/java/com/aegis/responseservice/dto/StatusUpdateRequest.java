package com.aegis.responseservice.dto;

/**
 * StatusUpdateRequest - DTO for updating a dispatch status.
 */
public class StatusUpdateRequest {

    private String status;

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
    public StatusUpdateRequest(final String status) {
        this.status = status;
    }

    /**
     * Gets the status.
     *
     * @return status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the status.
     *
     * @param status - the status to set
     */
    public void setStatus(final String status) {
        this.status = status;
    }
}
