package com.ironhacklab5.jparelationshipinheritance.nurse.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * A nurse who belongs to a {@link Division}.
 *
 * <p>Members have a status tracked as a string enum so that
 * the stored value is human-readable in the database
 * (e.g., "ACTIVE" rather than ordinal "0").</p>
 */
@Entity
@Table(name = "member")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member {

    /** Auto-generated primary key. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Full name of the nurse member. */
    private String name;

    /**
     * Current membership standing.
     * Stored as a String ("ACTIVE" / "LAPSED") for readability.
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MemberStatus status;

    /**
     * The date on which this member's subscription must next be renewed.
     * Stored as a SQL DATE column via LocalDate.
     */
    private LocalDate renewalDate;
}