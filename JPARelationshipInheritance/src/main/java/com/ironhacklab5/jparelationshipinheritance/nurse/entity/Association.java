package com.ironhacklab5.jparelationshipinheritance.nurse.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * The top-level organisation that groups many {@link Division}s.
 *
 * <p>Example: "Nurse Association of Spain" contains 7 regional divisions.</p>
 *
 * <p>This is the aggregate root — saving an Association cascades
 * down to all of its Divisions (and their Members).</p>
 */
@Entity
@Table(name = "association")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Association {

    /** Auto-generated primary key. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Official name of the association. */
    private String name;

    /**
     * All divisions belonging to this association.
     *
     * <p>LAZY fetch — we rarely need all divisions when just
     * reading the association name or id.</p>
     *
     * <p>mappedBy = "association" means the foreign key column
     * lives on the Division table, not here.</p>
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "association_id")
    @Builder.Default
    private List<Division> divisions = new ArrayList<>();
}