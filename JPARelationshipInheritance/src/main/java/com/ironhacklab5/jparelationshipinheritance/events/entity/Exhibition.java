package com.ironhacklab5.jparelationshipinheritance.events.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * An exhibition event — a subtype of Event.
 *
 * No additional fields beyond what Event provides.
 * Modelled as a separate entity because it is a distinct domain
 * concept and may gain its own fields (e.g., exhibitor list) later.
 *
 * The JOINED table still exists in the schema so future
 * Exhibition-specific columns can be added without migration pain.
 */
@Entity
@Table(name = "exhibition")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@SuperBuilder
public class Exhibition extends Event {
}