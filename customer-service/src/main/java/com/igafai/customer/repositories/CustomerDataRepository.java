package com.igafai.customer.repositories;

import com.igafai.customer.entities.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Data access repository interface for customer persistence operations.
 * 
 * <p>This repository interface extends Spring Data JPA's JpaRepository
 * to inherit standard CRUD operations and transaction management capabilities.
 * It provides a data access abstraction layer that decouples business logic
 * from database implementation details.</p>
 * 
 * <p>The repository leverages Spring Data JPA's method name conventions
 * for automatic query generation, while also supporting custom JPQL queries
 * via @Query annotations for complex data retrieval scenarios.</p>
 * 
 * @author Ikram Gafai
 * @version 3.0
 * @since 2024
 * @see org.springframework.data.jpa.repository.JpaRepository
 * @see com.igafai.customer.entities.CustomerEntity
 */
@Repository
public interface CustomerDataRepository extends JpaRepository<CustomerEntity, Long> {

    /**
     * Performs case-insensitive partial matching search on customer names.
     * 
     * <p>This query method searches for customer records where the name field
     * contains the specified search string, regardless of character case.
     * The search pattern matches any occurrence of the search term within
     * the customer name field.</p>
     * 
     * @param searchTerm The text fragment to search for within customer names
     * @return Collection of customer entities matching the name criteria
     */
    @Query("SELECT c FROM customers c WHERE LOWER(c.name) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
    List<CustomerEntity> findByFullNameContainingIgnoreCase(@Param("searchTerm") String searchTerm);

    /**
     * Retrieves customers whose age falls within a specified numeric range.
     * 
     * <p>This method performs an inclusive range query to find all customers
     * whose age value is greater than or equal to the minimum parameter and
     * less than or equal to the maximum parameter.</p>
     * 
     * @param lowerBound The minimum age threshold (inclusive)
     * @param upperBound The maximum age threshold (inclusive)
     * @return Collection of customer entities within the specified age range
     */
    @Query("SELECT c FROM customers c WHERE c.age BETWEEN :lowerBound AND :upperBound")
    List<CustomerEntity> findByAgeBetween(@Param("lowerBound") Float lowerBound, @Param("upperBound") Float upperBound);
}

