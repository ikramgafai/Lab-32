package com.igafai.customer.services;

import com.igafai.customer.entities.CustomerEntity;
import com.igafai.customer.repositories.CustomerDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Business service layer implementation for customer domain operations.
 * 
 * <p>This service class encapsulates all business logic and orchestration
 * related to customer management within the customer microservice. It serves
 * as the primary abstraction between the presentation layer (controllers)
 * and the data persistence layer (repositories), enforcing business rules
 * and maintaining transactional boundaries.</p>
 * 
 * <p>The service implements operations for customer lifecycle management
 * including query operations, entity creation, and domain-specific business
 * validations. It ensures data integrity and consistency across customer
 * operations while providing a clean API for the controller layer.</p>
 * 
 * @author Ikram Gafai
 * @version 3.0
 * @since 2024
 * @see com.igafai.customer.repositories.CustomerDataRepository
 * @see com.igafai.customer.controllers.CustomerManagementController
 */
@Service
public class CustomerManagementService {

    /**
     * Data repository for customer entity persistence operations.
     * 
     * <p>Injected dependency providing database access capabilities
     * for customer entities. This repository handles all CRUD operations
     * and custom query executions.</p>
     */
    @Autowired
    private CustomerDataRepository repository;

    /**
     * Creates and persists a new customer record in the system.
     * 
     * <p>This method accepts a customer entity and persists it to the database.
     * The system automatically generates a unique identifier if the entity
     * doesn't already contain one. The method includes basic persistence logic
     * and returns the fully populated entity including any generated fields.</p>
     * 
     * <p>Future enhancements should include comprehensive validation rules
     * such as age constraints, name format validation, and duplicate detection
     * to ensure data quality and business rule compliance.</p>
     * 
     * @param entity The customer entity instance to persist
     * @return The persisted entity with all generated and computed fields populated
     */
    public CustomerEntity createNewCustomer(CustomerEntity entity) {
        return repository.save(entity);
    }

    /**
     * Retrieves all customer records from the persistence store.
     * 
     * <p>This operation fetches every customer entity currently stored in
     * the database without applying any filtering, sorting, or pagination.
     * In production environments with large datasets, this method should
     * be enhanced with pagination support to prevent performance degradation
     * and excessive memory consumption.</p>
     * 
     * @return Complete collection of all customer entities in the system
     */
    public List<CustomerEntity> retrieveAllCustomers() {
        return repository.findAll();
    }

    /**
     * Locates and retrieves a single customer entity by its unique identifier.
     * 
     * <p>This method performs a primary key lookup to find a specific customer
     * record. If no matching record exists for the provided identifier, the
     * method throws an exception to signal the lookup failure, enabling the
     * calling layer to handle the error condition appropriately.</p>
     * 
     * @param id The unique numeric identifier of the target customer
     * @return The customer entity matching the provided identifier
     * @throws IllegalArgumentException if no customer exists with the specified identifier
     */
    public CustomerEntity retrieveCustomerById(Long id) throws IllegalArgumentException {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(
                    "Customer not found with identifier: " + id));
    }
}

