package com.ironhacklab5.jparelationshipinheritance.pr.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Embeddable value object representing a person's full name.
 *
 * <p>Using @Embeddable means these fields are stored in the same
 * table as the owning Contact entity — no separate join needed.
 * This is appropriate because a Name has no identity of its own;
 * it only makes sense as part of a Contact.</p>
 */
@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Name {

    /** Honorific prefix, e.g. Mr., Mrs., Dr., Prof. */
    private String salutation;

    /** Legal first (given) name. */
    private String firstName;

    /** Middle name or initial — optional. */
    private String middleName;

    /** Family / surname. */
    private String lastName;
}