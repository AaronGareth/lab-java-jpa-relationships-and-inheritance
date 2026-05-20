package com.ironhacklab5.jparelationshipinheritance.nurse.repository;


import com.ironhacklab5.jparelationshipinheritance.nurse.entity.Association;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for Association — the aggregate root of Module 2.
 * Because cascades flow from Association downward, we only need to
 * save/find at this level for most operations.
 */
@Repository
public interface AssociationRepository extends JpaRepository<Association, Long> {
}
