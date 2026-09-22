package com.aegis.incidentservice.dto;

import com.aegis.incidentservice.model.IncidentStatus;
import com.aegis.incidentservice.model.IncidentType;
import java.time.LocalDateTime;

/**
 * IncidentResponse - DTO returned to the client after any incident operation.
 */
public class IncidentResponse {

    private Long id;
    private String title;
    private String description;
    private IncidentType type;
    private IncidentStatus status;
    private String location;
    private String reportedBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // =============================================
    // CONSTRUCTORS
    // =============================================

    /**
     * Default constructor.
     */
    public IncidentResponse() {
    }

    /**
     * Full constructor.
     *
     * @param id          - incident ID
     * @param title       - title
     * @param description - description
     * @param type        - incident type
     * @param status      - incident status
     * @param location    - location
     * @param reportedBy  - who reported it
     * @param createdAt   - creation time
     * @param updatedAt   - last update time
     */
    public IncidentResponse(final Long id, final String title,
                            final String description, final IncidentType type,
                            final IncidentStatus status, final String location,
                            final String reportedBy,
                            final LocalDateTime createdAt,
                            final LocalDateTime updatedAt) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.type = type;
        this.status = status;
        this.location = location;
        this.reportedBy = reportedBy;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // =============================================
    // GETTERS AND SETTERS
    // =============================================

    /**
     * Gets the ID.
     *
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the ID.
     *
     * @param id - id to set
     */
    public void setId(final Long id) {
        this.id = id;
    }

    /**
     * Gets the title.
     *
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title.
     *
     * @param title - title to set
     */
    public void setTitle(final String title) {
        this.title = title;
    }

    /**
     * Gets the description.
     *
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description.
     *
     * @param description - description to set
     */
    public void setDescription(final String description) {
        this.description = description;
    }

    /**
     * Gets the type.
     *
     * @return type
     */
    public IncidentType getType() {
        return type;
    }

    /**
     * Sets the type.
     *
     * @param type - type to set
     */
    public void setType(final IncidentType type) {
        this.type = type;
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

    /**
     * Gets the location.
     *
     * @return location
     */
    public String getLocation() {
        return location;
    }

    /**
     * Sets the location.
     *
     * @param location - location to set
     */
    public void setLocation(final String location) {
        this.location = location;
    }

    /**
     * Gets the reporter username.
     *
     * @return reportedBy
     */
    public String getReportedBy() {
        return reportedBy;
    }

    /**
     * Sets the reporter username.
     *
     * @param reportedBy - username to set
     */
    public void setReportedBy(final String reportedBy) {
        this.reportedBy = reportedBy;
    }

    /**
     * Gets the creation time.
     *
     * @return createdAt
     */
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    /**
     * Sets the creation time.
     *
     * @param createdAt - time to set
     */
    public void setCreatedAt(final LocalDateTime createdAt) {
        this.createdAt = createdAt;
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
}
