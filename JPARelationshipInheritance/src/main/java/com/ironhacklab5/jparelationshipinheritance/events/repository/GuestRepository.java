package com.ironhacklab5.jparelationshipinheritance.events.repository;

import com.ironhacklab5.jparelationshipinheritance.events.entity.Guest;
import com.ironhacklab5.jparelationshipinheritance.events.entity.GuestStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Repository for Guest entities.
 * Provides status-based lookup in addition to standard CRUD.
 */
@Repository
public interface GuestRepository extends JpaRepository<Guest, Long> {

    /**
     * Returns all guests with the given RSVP status.
     * Derived query: SELECT * FROM guest WHERE status = ?
     */
    List<Guest> findByStatus(GuestStatus status);
}