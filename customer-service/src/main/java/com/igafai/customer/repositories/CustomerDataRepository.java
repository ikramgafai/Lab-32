package com.igafai.customer.repositories;

import com.igafai.customer.entities.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for Customer entity data access operations.
 * 
 * <p>This repository extends Spring Data JPA's JpaRepository to provide standard CRUD operations
 * and custom query methods for Customer entities. It abstracts the database access layer
 * and provides a clean interface for the service layer.</p>
 * 
 * <p>The repository follows Spring Data JPA conventions, allowing for automatic query
 * generation based on method names. Custom queries can be added using @Query annotations
 * for complex operations.</p>
 * 
 * @author Ikram Gafai
 * @version 2.0
 * @since 2024
 * @see org.springframework.data.jpa.repository.JpaRepository
 * @see com.igafai.customer.entities.CustomerEntity
 */
@Repository
public interface CustomerDataRepository extends JpaRepository<CustomerEntity, Long> {

    /**
     * Finds customers by their full name (case-insensitive partial match).
     * 
     * <p>This method performs a case-insensitive search for customers whose
     * full name contains the provided search term.</p>
     * 
     * @param nameSearchTerm The name or part of name to search for
     * @return List of CustomerEntity objects matching the search criteria
     */
    @Query("SELECT customer FROM customers customer WHERE LOWER(customer.customerFullName) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<CustomerEntity> findByFullNameContainingIgnoreCase(@Param("name") String nameSearchTerm);

    /**
     * Finds customers within a specific age range.
     * 
     * <p>This method retrieves all customers whose age falls within the specified
     * minimum and maximum values (inclusive).</p>
     * 
     * @param minimumAge The minimum age (inclusive)
     * @param maximumAge The maximum age (inclusive)
     * @return List of CustomerEntity objects within the age range
     */
    @Query("SELECT customer FROM customers customer WHERE customer.customerAge BETWEEN :minAge AND :maxAge")
    List<CustomerEntity> findByAgeBetween(@Param("minAge") Float minimumAge, @Param("maxAge") Float maximumAge);
}

