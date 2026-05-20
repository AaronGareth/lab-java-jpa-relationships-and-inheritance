package com.ironhacklab5.jparelationshipinheritance.pr.repository;


import com.ironhacklab5.jparelationshipinheritance.pr.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data repository for Contact entities.
 * Inherits standard CRUD operations from JpaRepository.
 */
@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
}