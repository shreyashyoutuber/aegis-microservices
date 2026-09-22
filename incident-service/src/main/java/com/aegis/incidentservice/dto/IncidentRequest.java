package com.aegis.incidentservice.dto;

import com.aegis.incidentservice.model.IncidentType;

/**
 * IncidentRequest - DTO for creating a new incident.
 *
 * Fields sent by the client when reporting an emergency.
 */
public class IncidentRequest {

    private String title;
    private String description;
    private IncidentType type;
    private String location;
    private String reportedBy;

    // =============================================
    // CONSTRUCTORS
    // =============================================

    /**
     * Default constructor.
     */
    public IncidentRequest() {
    }

    /**
     * Full constructor.
     *
     * @param title       - short title
     * @param description - full description
     * @param type        - type of incident
     * @param location    - where it occurred
     * @param reportedBy  - who is reporting
     */
    public IncidentRequest(final String title, final String description,
                           final IncidentType type, final String location,
                           final String reportedBy) {
        this.title = title;
        this.description = description;
        this.type = type;
        this.location = location;
        this.reportedBy = reportedBy;
    }

    // =============================================
    // GETTERS AND SETTERS
    // =============================================

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
}
