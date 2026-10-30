package com.aegis.responseservice.model;

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
 * Dispatch - JPA Entity representing a dispatch operation.
 *
 * Records which resource was sent to which incident and the current
 * status of the dispatch operation.
 *
 * Fields:
 * - id              : Auto-generated unique ID
 * - incidentId      : ID of the incident being responded to
 * - incidentTitle   : Title of the incident (cached for quick reference)
 * - incidentType    : Type of incident (FLOOD, FIRE, etc.)
 * - incidentLocation: Location of the incident
 * - resourceId      : ID of the resource dispatched
 * - resourceName    : Name of the resource (cached for quick reference)
 * - resourceType    : Type of resource (RESCUE_BOAT, AMBULANCE, etc.)
 * - status          : Current dispatch status
 * - dispatchedAt    : When the dispatch was initiated
 * - updatedAt       : When the dispatch was last updated
 * - notes           : Additional notes or instructions
 */
@Entity
@Table(name = "dispatches")
@SuppressWarnings("null")
public class Dispatch {

    /** Auto-generated unique ID. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** ID of the incident being responded to. */
    @Column(nullable = false)
    private Long incidentId;

    /** Title of the incident. */
    @Column(nullable = false)
    private String incidentTitle;

    /** Type of incident (FLOOD, FIRE, etc.). */
    @Column(nullable = false)
    private String incidentType;

    /** Location of the incident. */
    @Column(nullable = false)
    private String incidentLocation;

    /** ID of the resource dispatched. */
    @Column(nullable = false)
    private Long resourceId;

    /** Name of the resource dispatched. */
    @Column(nullable = false)
    private String resourceName;

    /** Type of resource (RESCUE_BOAT, AMBULANCE, etc.). */
    @Column(nullable = false)
    private String resourceType;

    /** Current dispatch status. */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DispatchStatus status;

    /** When the dispatch was initiated. */
    @Column(nullable = false)
    private LocalDateTime dispatchedAt;

    /** When the dispatch was last updated. */
    private LocalDateTime updatedAt;

    /** Additional notes or instructions. */
    @Column(length = 1000)
    private String notes;

    // =============================================
    // CONSTRUCTORS
    // =============================================

    /**
     * Default constructor required by JPA.
     */
    public Dispatch() {
    }

    /**
     * Full constructor for creating a new Dispatch.
     *
     * @param incidentId       - ID of the incident
     * @param incidentTitle    - title of the incident
     * @param incidentType     - type of incident
     * @param incidentLocation - location of the incident
     * @param resourceId       - ID of the resource
     * @param resourceName     - name of the resource
     * @param resourceType     - type of resource
     * @param status           - dispatch status
     * @param dispatchedAt     - when dispatched
     * @param updatedAt        - last update time
     * @param notes            - additional notes
     */
    public Dispatch(final Long incidentId, final String incidentTitle,
                    final String incidentType, final String incidentLocation,
                    final Long resourceId, final String resourceName,
                    final String resourceType, final DispatchStatus status,
                    final LocalDateTime dispatchedAt,
                    final LocalDateTime updatedAt,
                    final String notes) {
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
     * @param id - the ID to set
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
     * @param incidentId - the incident ID to set
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
     * @param incidentTitle - the title to set
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
     * @param incidentType - the type to set
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
     * @param incidentLocation - the location to set
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
     * @param resourceId - the resource ID to set
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
     * @param resourceName - the name to set
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
     * @param resourceType - the type to set
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
     * @param status - the status to set
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
     * @param dispatchedAt - the timestamp to set
     */
    public void setDispatchedAt(final LocalDateTime dispatchedAt) {
        this.dispatchedAt = dispatchedAt;
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
