package com.ironhacklab5.jparelationshipinheritance.tasks.repository;


import com.ironhacklab5.jparelationshipinheritance.tasks.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for Task and its subtypes.
 * Querying via the base Task type returns all subtypes (polymorphic query).
 */
@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
}
