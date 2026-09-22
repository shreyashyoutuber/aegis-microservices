package com.aegis.incidentservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

/**
 * Incident - JPA Entity representing an emergency incident.
 *
 * Fields:
 * - id          : Auto-generated unique ID
 * - title       : Short description of the incident
 * - description : Full details of the incident
 * - type        : Type of emergency (FIRE, FLOOD, MEDICAL, etc.)
 * - status      : Current status (REPORTED, IN_PROGRESS, RESOLVED, CLOSED)
 * - location    : Where the incident occurred
 * - reportedBy  : Username of the person who reported it
 * - createdAt   : When the incident was first reported
 * - updatedAt   : When the incident was last updated
 */
@Entity
@Table(name = "incidents")
public class Incident {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(length = 1000)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private IncidentType type;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private IncidentStatus status;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private String reportedBy;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    // =============================================
    // CONSTRUCTORS
    // =============================================

    /**
     * Default constructor required by JPA.
     */
    public Incident() {
    }

    /**
     * Full constructor for creating a new Incident.
     *
     * @param title       - short title of the incident
     * @param description - full description
     * @param type        - incident type
     * @param status      - current status
     * @param location    - where it happened
     * @param reportedBy  - who reported it
     * @param createdAt   - when it was created
     * @param updatedAt   - when it was last updated
     */
    public Incident(final String title, final String description,
                    final IncidentType type, final IncidentStatus status,
                    final String location, final String reportedBy,
                    final LocalDateTime createdAt, final LocalDateTime updatedAt) {
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
     * Gets the incident ID.
     *
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the incident ID.
     *
     * @param id - the ID to set
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
     * @param title - the title to set
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
     * @param description - the description to set
     */
    public void setDescription(final String description) {
        this.description = description;
    }

    /**
     * Gets the incident type.
     *
     * @return type
     */
    public IncidentType getType() {
        return type;
    }

    /**
     * Sets the incident type.
     *
     * @param type - the type to set
     */
    public void setType(final IncidentType type) {
        this.type = type;
    }

    /**
     * Gets the incident status.
     *
     * @return status
     */
    public IncidentStatus getStatus() {
        return status;
    }

    /**
     * Sets the incident status.
     *
     * @param status - the status to set
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
     * @param location - the location to set
     */
    public void setLocation(final String location) {
        this.location = location;
    }

    /**
     * Gets the reporter's username.
     *
     * @return reportedBy
     */
    public String getReportedBy() {
        return reportedBy;
    }

    /**
     * Sets the reporter's username.
     *
     * @param reportedBy - the username to set
     */
    public void setReportedBy(final String reportedBy) {
        this.reportedBy = reportedBy;
    }

    /**
     * Gets the creation timestamp.
     *
     * @return createdAt
     */
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    /**
     * Sets the creation timestamp.
     *
     * @param createdAt - the timestamp to set
     */
    public void setCreatedAt(final LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * Gets the last update timestamp.
     *
     * @return updatedAt
     */
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    /**
     * Sets the last update timestamp.
     *
     * @param updatedAt - the timestamp to set
     */
    public void setUpdatedAt(final LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
