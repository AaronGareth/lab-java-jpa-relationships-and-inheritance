package com.ironhacklab5.jparelationshipinheritance.tasks.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

/**
 * Abstract base entity for all task types.
 *
 * <h3>Inheritance Strategy: SINGLE_TABLE</h3>
 * <p>We use {@code InheritanceType.SINGLE_TABLE} here because:</p>
 * <ul>
 *   <li>{@link BillableTask} adds only one extra field ({@code hourlyRate});
 *       {@link InternalTask} adds none. The schema overhead of JOINED
 *       (extra tables + joins) is not justified for such minor differences.</li>
 *   <li>SINGLE_TABLE gives the best query performance — no joins needed.</li>
 *   <li>The only cost is nullable columns for subtype-specific data, which
 *       is acceptable here given the tiny number of extra fields.</li>
 * </ul>
 *
 * <p>A {@code dtype} discriminator column is added automatically by JPA
 * to distinguish rows by subtype ("BillableTask", "InternalTask").</p>
 */
@Entity
@Table(name = "task")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "task_type", discriminatorType = DiscriminatorType.STRING)
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public abstract class Task {

    /** Auto-generated primary key. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Short descriptive title of the task. */
    private String title;

    /** Date by which the task must be completed. */
    private LocalDate dueDate;

    /** Whether the task has been finished. */
    private boolean completed;
}