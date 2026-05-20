package com.ironhacklab5.jparelationshipinheritance.nurse.entity;

/**
 * Membership standing within a Division.
 *
 * ACTIVE  – dues are current and the member is in good standing.
 * LAPSED  – membership has expired or dues are overdue.
 */
public enum MemberStatus {
    ACTIVE,
    LAPSED
}