package com.aegis.resourceservice.model;

/**
 * ResourceType - Defines the types of emergency resources.
 *
 * Values:
 * - AMBULANCE     : Medical emergency vehicle
 * - FIRE_TRUCK    : Fire fighting vehicle
 * - POLICE_CAR    : Law enforcement vehicle
 * - RESCUE_BOAT   : Flood/water rescue boat
 * - HELICOPTER    : Aerial rescue and transport
 * - MEDICAL_TEAM  : Group of medical personnel
 */
@SuppressWarnings("null")
public enum ResourceType {

    /** Medical emergency vehicle. */
    AMBULANCE,

    /** Fire fighting vehicle. */
    FIRE_TRUCK,

    /** Law enforcement vehicle. */
    POLICE_CAR,

    /** Flood and water rescue boat. */
    RESCUE_BOAT,

    /** Aerial rescue and transport. */
    HELICOPTER,

    /** Group of medical personnel. */
    MEDICAL_TEAM
}
