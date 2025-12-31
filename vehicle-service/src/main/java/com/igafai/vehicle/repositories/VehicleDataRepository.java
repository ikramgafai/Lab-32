package com.igafai.vehicle.repositories;

import com.igafai.vehicle.entities.VehicleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Data access repository interface for vehicle entity persistence operations.
 * 
 * <p>This repository interface extends Spring Data JPA's JpaRepository to inherit
 * standard CRUD operations and transaction management capabilities for vehicle entities.
 * It provides a clean abstraction layer that isolates business logic from database
 * implementation specifics and query execution details.</p>
 * 
 * <p>The repository adheres to Spring Data JPA naming conventions for automatic
 * query method generation while also supporting custom JPQL queries via @Query
 * annotations for specialized data retrieval requirements.</p>
 * 
 * @author Ikram Gafai
 * @version 3.0
 * @since 2024
 * @see org.springframework.data.jpa.repository.JpaRepository
 * @see com.igafai.vehicle.entities.VehicleEntity
 */
@Repository
public interface VehicleDataRepository extends JpaRepository<VehicleEntity, Long> {

    /**
     * Locates all vehicle records linked to a specific customer identifier.
     * 
     * <p>This query method retrieves every vehicle entity where the customer
     * identifier matches the provided parameter, enabling the identification
     * of all vehicles owned by a particular customer within the system.</p>
     * 
     * @param id The unique numeric identifier of the target customer
     * @return Collection of vehicle entities associated with the specified customer
     */
    @Query("SELECT v FROM vehicles v WHERE v.customerId = :id")
    List<VehicleEntity> findByCustomerIdentifier(@Param("id") Long id);

    /**
     * Performs vehicle lookup using registration number as the search key.
     * 
     * <p>This method provides an alternative vehicle retrieval mechanism that
     * utilizes the unique registration number instead of the primary key identifier.
     * The method returns an Optional to handle cases where no matching vehicle
     * exists for the provided registration number.</p>
     * 
     * @param registrationNumber The registration plate number to search for
     * @return Optional wrapper containing the VehicleEntity if a match is found, empty otherwise
     */
    Optional<VehicleEntity> findByRegistrationNumber(String registrationNumber);
}

