package com.ironhacklab5.jparelationshipinheritance.events.entity;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;
/**
 * A professional conference — a subtype of Event.
 *
 * Speaker relationship is Many-to-Many:
 * - One conference can have many speakers.
 * - One speaker can present at multiple conferences.
 *
 * IMPORTANT: No CascadeType on @ManyToMany here.
 * Speakers are saved independently via SpeakerRepository first,
 * then linked to conferences. Adding CascadeType.PERSIST would
 * cause a "detached entity" error when the same speaker is reused
 * across multiple conferences in separate transactions.
 */
@Entity
@Table(name = "conference")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Conference extends Event {

    /**
     * Speakers presenting at this conference.
     * No cascade — speakers have their own lifecycle and are
     * saved/managed independently before being linked here.
     */
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "conference_speaker",
            joinColumns = @JoinColumn(name = "conference_id"),
            inverseJoinColumns = @JoinColumn(name = "speaker_id")
    )
    private List<Speaker> speakers = new ArrayList<>();
}