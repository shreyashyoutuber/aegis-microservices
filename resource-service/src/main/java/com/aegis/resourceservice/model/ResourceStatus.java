package com.aegis.resourceservice.model;

/**
 * ResourceStatus - Defines the availability status of a resource.
 *
 * Values:
 * - AVAILABLE   : Ready to be dispatched
 * - DEPLOYED    : Currently assigned to an incident
 * - MAINTENANCE : Under repair or servicing
 * - RETIRED     : No longer in active service
 */
@SuppressWarnings("null")
public enum ResourceStatus {

    /** Ready to be dispatched. */
    AVAILABLE,

    /** Currently assigned to an incident. */
    DEPLOYED,

    /** Under repair or servicing. */
    MAINTENANCE,

    /** No longer in active service. */
    RETIRED
}
