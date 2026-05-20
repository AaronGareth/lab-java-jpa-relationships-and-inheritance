package com.ironhacklab5.jparelationshipinheritance.nurse.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * A regional sub-group within an {@link Association}.
 *
 * <p>Each Division has exactly one president (a {@link Member})
 * and a list of regular members.</p>
 *
 * <p>The member list uses LAZY loading — we don't want to fetch
 * every member row whenever we simply query for a division's
 * name or district.</p>
 */
@Entity
@Table(name = "division")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Division {

    /** Auto-generated primary key. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Human-readable name, e.g. "Eastern Division". */
    private String name;

    /** Geographic district this division covers. */
    private String district;

    /**
     * The member who presides over this division.
     * Modelled as a One-to-One relationship — one president per division.
     * CascadeType.ALL means saving a Division also persists its president.
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "president_id")
    private Member president;

    /**
     * All regular members in this division.
     *
     * <p>FetchType.LAZY — members are only loaded from the DB when
     * explicitly accessed, preventing unnecessary queries.</p>
     *
     * <p>CascadeType.ALL — saving/deleting a Division propagates
     * to its members.</p>
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "division_id")
    @Builder.Default
    private List<Member> members = new ArrayList<>();
}