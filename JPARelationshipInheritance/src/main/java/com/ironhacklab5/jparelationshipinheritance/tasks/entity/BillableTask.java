package com.ironhacklab5.jparelationshipinheritance.tasks.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import java.math.BigDecimal;

/**
 * A task that is charged to a client at an hourly rate.
 *
 * Stored in the same "task" table (SINGLE_TABLE strategy).
 * The task_type discriminator column holds "BillableTask" for these rows.
 *
 * BigDecimal is used instead of double/float to avoid
 * floating-point rounding errors in financial calculations.
 */
@Entity
@DiscriminatorValue("BillableTask")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class BillableTask extends Task {

    /** Amount charged per hour. Uses DECIMAL(10,2) precision. */
    @Column(precision = 10, scale = 2)
    private BigDecimal hourlyRate;
}
