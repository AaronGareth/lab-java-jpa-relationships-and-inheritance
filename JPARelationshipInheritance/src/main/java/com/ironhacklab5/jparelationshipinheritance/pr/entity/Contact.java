package com.ironhacklab5.jparelationshipinheritance.pr.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a PR company contact person.
 *
 * <p>The {@link Name} object is embedded directly into this table
 * rather than stored in a separate table, since a name is a
 * value object — it belongs entirely to this contact.</p>
 *
 * <p>Table columns will include: id, company, title,
 * salutation, first_name, middle_name, last_name.</p>
 */
@Entity
@Table(name = "contact")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Contact {

    /** Auto-generated primary key. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** The company this contact belongs to. */
    private String company;

    /** Job title within the company (e.g., "Head of Communications"). */
    private String title;

    /**
     * The contact's full name, stored as embedded columns
     * in this same table row.
     */
    @Embedded
    private Name name;
}
