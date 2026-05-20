package com.ironhacklab5.jparelationshipinheritance.tasks.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * An internal task with no client billing.
 *
 * No additional fields beyond what Task provides.
 * The task_type discriminator column holds "InternalTask" for these rows.
 */
@Entity
@DiscriminatorValue("InternalTask")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@SuperBuilder
public class InternalTask extends Task {
}