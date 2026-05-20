package com.ironhacklab5.jparelationshipinheritance.events.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * A person invited to attend an Event.
 *
 * Guest is a separate entity (not embedded) because it has its
 * own identity (id) and could participate in Many-to-Many
 * relationships in the future.
 */
@Entity
@Table(name = "guest")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Guest {

    /** Auto-generated primary key. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Full name of the guest. */
    private String name;

    /**
     * Current RSVP status, stored as a readable string.
     * EnumType.STRING prevents breakage if enum order ever changes.
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private GuestStatus status;
}
