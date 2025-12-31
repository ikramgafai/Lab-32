package com.igafai.vehicle.repositories;

import com.igafai.vehicle.entities.VehicleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for Vehicle entity data access operations.
 * 
 * <p>This repository extends Spring Data JPA's JpaRepository to provide standard CRUD operations
 * and custom query methods for Vehicle entities. It abstracts the database access layer
 * and provides a clean interface for the service layer.</p>
 * 
 * <p>The repository follows Spring Data JPA conventions, allowing for automatic query
 * generation based on method names. Custom queries can be added using @Query annotations.</p>
 * 
 * @author Ikram Gafai
 * @version 2.0
 * @since 2024
 * @see org.springframework.data.jpa.repository.JpaRepository
 * @see com.igafai.vehicle.entities.VehicleEntity
 */
@Repository
public interface VehicleDataRepository extends JpaRepository<VehicleEntity, Long> {

    /**
     * Finds all vehicles associated with a specific customer.
     * 
     * <p>This method retrieves all vehicle records that belong to the customer
     * identified by the provided customer identifier.</p>
     * 
     * @param customerIdentifier The unique identifier of the customer
     * @return List of VehicleEntity objects associated with the customer
     */
    @Query("SELECT vehicle FROM vehicles vehicle WHERE vehicle.ownerCustomerIdentifier = :customerId")
    List<VehicleEntity> findByCustomerIdentifier(@Param("customerId") Long customerIdentifier);

    /**
     * Finds a vehicle by its registration plate number.
     * 
     * <p>This method provides an alternative lookup mechanism using the unique
     * registration number instead of the primary key.</p>
     * 
     * @param registrationNumber The registration plate number to search for
     * @return Optional containing the VehicleEntity if found, empty otherwise
     */
    Optional<VehicleEntity> findByRegistrationPlateNumber(String registrationNumber);
}

