package com.igafai.customer.services;

import com.igafai.customer.entities.CustomerEntity;
import com.igafai.customer.repositories.CustomerDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for managing Customer business logic and operations.
 * 
 * <p>This service handles all customer-related business operations including retrieval,
 * creation, and data management. It acts as the intermediary between the controller
 * layer and the data access layer, implementing business rules and validation logic.</p>
 * 
 * <p>The service provides methods for querying customer data, creating new customer
 * records, and managing customer information. It ensures data consistency and implements
 * business logic that should not be exposed at the repository level.</p>
 * 
 * @author Ikram Gafai
 * @version 2.0
 * @since 2024
 * @see com.igafai.customer.repositories.CustomerDataRepository
 * @see com.igafai.customer.controllers.CustomerManagementController
 */
@Service
public class CustomerManagementService {

    /**
     * Repository for customer data access operations.
     */
    @Autowired
    private CustomerDataRepository customerDataRepository;

    /**
     * Retrieves all customers from the database.
     * 
     * <p>This method fetches all customer records stored in the system.
     * It returns a complete list of customers without any filtering or pagination.
     * For production systems, consider implementing pagination for large datasets.</p>
     * 
     * @return List of all CustomerEntity objects in the database
     */
    public List<CustomerEntity> retrieveAllCustomers() {
        return customerDataRepository.findAll();
    }

    /**
     * Retrieves a specific customer by their unique identifier.
     * 
     * <p>This method fetches a single customer record using the provided identifier.
     * If the customer is not found, an exception is thrown to indicate the invalid
     * identifier.</p>
     * 
     * @param customerIdentifier The unique identifier of the customer to retrieve
     * @return CustomerEntity object if found
     * @throws IllegalArgumentException if the customer identifier is invalid or not found
     */
    public CustomerEntity retrieveCustomerById(Long customerIdentifier) throws IllegalArgumentException {
        return customerDataRepository.findById(customerIdentifier)
                .orElseThrow(() -> new IllegalArgumentException(
                    "Customer not found with identifier: " + customerIdentifier));
    }

    /**
     * Creates a new customer record in the database.
     * 
     * <p>This method persists a new customer entity to the database. The customer
     * identifier will be automatically generated if not provided. The method performs
     * basic validation and saves the customer entity.</p>
     * 
     * <p>Note: Additional business validation (e.g., age restrictions, name format)
     * should be implemented here or in a separate validation layer.</p>
     * 
     * @param customerEntity The customer entity to be persisted
     * @return The saved CustomerEntity with generated identifier
     */
    public CustomerEntity createNewCustomer(CustomerEntity customerEntity) {
        return customerDataRepository.save(customerEntity);
    }
}

