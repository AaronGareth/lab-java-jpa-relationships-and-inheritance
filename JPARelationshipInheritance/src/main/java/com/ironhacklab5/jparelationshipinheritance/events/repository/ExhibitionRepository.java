package com.ironhacklab5.jparelationshipinheritance.events.repository;

import com.ironhacklab5.jparelationshipinheritance.events.entity.Exhibition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExhibitionRepository extends JpaRepository<Exhibition, Long> {
}
