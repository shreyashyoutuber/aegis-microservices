package com.aegis.responseservice.model;

/**
 * DispatchStatus - Defines the current state of a dispatch operation.
 *
 * Values:
 * - DISPATCHED  : Resource has been assigned and sent
 * - EN_ROUTE    : Resource is on the way to the incident
 * - ARRIVED     : Resource has arrived at the incident location
 * - COMPLETED   : Rescue operation completed successfully
 * - CANCELLED   : Dispatch was cancelled
 */
@SuppressWarnings("null")
public enum DispatchStatus {

    /** Resource has been assigned and sent. */
    DISPATCHED,

    /** Resource is on the way to the incident. */
    EN_ROUTE,

    /** Resource has arrived at the incident location. */
    ARRIVED,

    /** Rescue operation completed successfully. */
    COMPLETED,

    /** Dispatch was cancelled. */
    CANCELLED
}
