package com.ironhacklab5.jparelationshipinheritance.events.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Abstract base entity for all event types.
 *
 * <h3>Inheritance Strategy: JOINED</h3>
 * <p>We use {@code InheritanceType.JOINED} here because:</p>
 * <ul>
 *   <li>Each subtype ({@link Conference}, {@link Exhibition}) has its own
 *       distinct fields. JOINED avoids NULL columns that SINGLE_TABLE
 *       would create for subtype-specific data.</li>
 *   <li>The tables are normalised — {@code event} holds the common columns,
 *       {@code conference} and {@code exhibition} hold only their extras.</li>
 *   <li>TABLE_PER_CLASS would duplicate the base columns in every table,
 *       making cross-type queries inefficient.</li>
 * </ul>
 *
 * <p>Trade-off: JOINED requires a JOIN when loading a subtype, but for
 * an event management system that is an acceptable cost for clean schema design.</p>
 */
@Entity
@Table(name = "event")
@Inheritance(strategy = InheritanceType.JOINED)
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public abstract class Event {

    /** Auto-generated primary key. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Descriptive title of the event. */
    private String title;

    /** Calendar date on which the event takes place. */
    private LocalDate date;

    /** How long the event runs, in minutes. */
    private Integer duration;

    /** Venue or address where the event is held. */
    private String location;

    /**
     * All guests invited to this event.
     *
     * <p>One Event → Many Guests. A guest record belongs to
     * one event (the foreign key is on the guest table).
     * LAZY load — we don't need all guests every time we
     * read basic event info.</p>
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id")
    private List<Guest> guests = new ArrayList<>();
}