package com.aegis.resourceservice.dto;

import com.aegis.resourceservice.model.ResourceType;

/**
 * ResourceRequest - DTO for creating or updating a resource.
 *
 * Fields sent by the client:
 * - name        : Name of the resource (e.g. "Ambulance-101")
 * - type        : Type of resource (AMBULANCE, FIRE_TRUCK, etc.)
 * - location    : Where the resource is stationed
 * - contactInfo : Contact number or radio frequency
 */
@SuppressWarnings("null")
public class ResourceRequest {

    /** Name of the resource. */
    private String name;

    /** Type of resource. */
    private ResourceType type;

    /** Current stationed location. */
    private String location;

    /** Contact number or radio frequency. */
    private String contactInfo;

    /**
     * Default constructor.
     */
    public ResourceRequest() {
    }

    /**
     * Full constructor.
     *
     * @param name        - resource name
     * @param type        - resource type
     * @param location    - stationed location
     * @param contactInfo - contact details
     */
    public ResourceRequest(final String name, final ResourceType type,
                           final String location, final String contactInfo) {
        this.name = name;
        this.type = type;
        this.location = location;
        this.contactInfo = contactInfo;
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
}
