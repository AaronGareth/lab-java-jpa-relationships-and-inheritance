package com.ironhacklab5.jparelationshipinheritance.events.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * A presenter at a Conference.
 *
 * Speaker is Many-to-Many with Conference:
 * one speaker can present at multiple conferences, and one
 * conference can have multiple speakers. A join table
 * (conference_speaker) handles this.
 */
@Entity
@Table(name = "speaker")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Speaker {

    /** Auto-generated primary key. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Full name of the speaker. */
    private String name;

    /** How long (in minutes) this speaker's presentation runs. */
    private Integer presentationDuration;
}