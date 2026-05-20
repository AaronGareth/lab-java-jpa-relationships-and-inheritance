package com.ironhacklab5.jparelationshipinheritance.nurse.repository;


import com.ironhacklab5.jparelationshipinheritance.nurse.entity.Division;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Repository for Division entities.
 * Provides district-based lookup in addition to standard CRUD.
 */
@Repository
public interface DivisionRepository extends JpaRepository<Division, Long> {

    /**
     * Returns all divisions located in the given district.
     *
     * Spring Data derives the SQL automatically from the method name:
     * SELECT * FROM division WHERE district = ?
     *
     * @param district the district name to filter by
     * @return list of matching divisions (empty list if none found)
     */
    List<Division> findByDistrict(String district);
}