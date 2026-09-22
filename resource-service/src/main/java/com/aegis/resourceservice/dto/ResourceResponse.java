package com.aegis.resourceservice.dto;

import com.aegis.resourceservice.model.ResourceStatus;
import com.aegis.resourceservice.model.ResourceType;
import java.time.LocalDateTime;

/**
 * ResourceResponse - DTO returned to the client.
 *
 * Contains all fields of a resource including:
 * - id, name, type, status, location, contactInfo, createdAt, updatedAt
 */
public class ResourceResponse {

    /** Unique ID. */
    private Long id;

    /** Name of the resource. */
    private String name;

    /** Type of resource. */
    private ResourceType type;

    /** Current status. */
    private ResourceStatus status;

    /** Current stationed location. */
    private String location;

    /** Contact number or radio frequency. */
    private String contactInfo;

    /** When the resource was registered. */
    private LocalDateTime createdAt;

    /** When the resource was last updated. */
    private LocalDateTime updatedAt;

    /**
     * Default constructor.
     */
    public ResourceResponse() {
    }

    /**
     * Full constructor.
     *
     * @param id          - resource ID
     * @param name        - resource name
     * @param type        - resource type
     * @param status      - current status
     * @param location    - stationed location
     * @param contactInfo - contact details
     * @param createdAt   - creation timestamp
     * @param updatedAt   - last update timestamp
     */
    public ResourceResponse(final Long id, final String name,
                            final ResourceType type,
                            final ResourceStatus status,
                            final String location,
                            final String contactInfo,
                            final LocalDateTime createdAt,
                            final LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.status = status;
        this.location = location;
        this.contactInfo = contactInfo;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

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
     * Gets the type.
     *
     * @return type
     */
    public ResourceType getType() {
        return type;
    }

    /**
     * Sets the type.
     *
     * @param type - the type to set
     */
    public void setType(final ResourceType type) {
        this.type = type;
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
