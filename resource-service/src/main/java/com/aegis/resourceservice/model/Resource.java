package com.aegis.resourceservice.model;

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
 * Resource - JPA Entity representing an emergency resource.
 *
 * Fields:
 * - id          : Auto-generated unique ID
 * - name        : Name or identifier of the resource (e.g. "Ambulance-101")
 * - type        : Type of resource (AMBULANCE, FIRE_TRUCK, etc.)
 * - status      : Current availability (AVAILABLE, DEPLOYED, etc.)
 * - location    : Current stationed location
 * - contactInfo : Contact number or radio frequency
 * - createdAt   : When the resource was registered
 * - updatedAt   : When the resource was last updated
 */
@Entity
@Table(name = "resources")
@SuppressWarnings("null")
public class Resource {

    /** Auto-generated unique ID. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Name or identifier of the resource. */
    @Column(nullable = false)
    private String name;

    /** Type of resource. */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ResourceType type;

    /** Current availability status. */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ResourceStatus status;

    /** Current stationed location. */
    @Column(nullable = false)
    private String location;

    /** Contact number or radio frequency. */
    private String contactInfo;

    /** When the resource was registered. */
    @Column(nullable = false)
    private LocalDateTime createdAt;

    /** When the resource was last updated. */
    private LocalDateTime updatedAt;

    // =============================================
    // CONSTRUCTORS
    // =============================================

    /**
     * Default constructor required by JPA.
     */
    public Resource() {
    }

    /**
     * Full constructor for creating a new Resource.
     *
     * @param name        - name of the resource
     * @param type        - resource type
     * @param status      - current status
     * @param location    - stationed location
     * @param contactInfo - contact details
     * @param createdAt   - creation timestamp
     * @param updatedAt   - last update timestamp
     */
    public Resource(final String name, final ResourceType type,
                    final ResourceStatus status, final String location,
                    final String contactInfo,
                    final LocalDateTime createdAt,
                    final LocalDateTime updatedAt) {
        this.name = name;
        this.type = type;
        this.status = status;
        this.location = location;
        this.contactInfo = contactInfo;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // =============================================
    // GETTERS AND SETTERS
    // =============================================

    /**
     * Gets the resource ID.
     *
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the resource ID.
     *
     * @param id - the ID to set
     */
    public void setId(final Long id) {
        this.id = id;
    }

    /**
     * Gets the name.
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name.
     *
     * @param name - the name to set
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Gets the resource type.
     *
     * @return type
     */
    public ResourceType getType() {
        return type;
    }

    /**
     * Sets the resource type.
     *
     * @param type - the type to set
     */
    public void setType(final ResourceType type) {
        this.type = type;
    }

    /**
     * Gets the resource status.
     *
     * @return status
     */
    public ResourceStatus getStatus() {
        return status;
    }

    /**
     * Sets the resource status.
     *
     * @param status - the status to set
     */
    public void setStatus(final ResourceStatus status) {
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
     * Gets the contact info.
     *
     * @return contactInfo
     */
    public String getContactInfo() {
        return contactInfo;
    }

    /**
     * Sets the contact info.
     *
     * @param contactInfo - the contact info to set
     */
    public void setContactInfo(final String contactInfo) {
        this.contactInfo = contactInfo;
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
