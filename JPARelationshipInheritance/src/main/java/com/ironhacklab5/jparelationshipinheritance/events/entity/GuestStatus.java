package com.ironhacklab5.jparelationshipinheritance.events.entity;


/**
 * RSVP status for a Guest at an Event.
 *
 * ATTENDING     – confirmed attendance.
 * NOT_ATTENDING – explicitly declined.
 * NO_RESPONSE   – invitation sent but no reply received yet.
 */
public enum GuestStatus {
    ATTENDING,
    NOT_ATTENDING,
    NO_RESPONSE
}
