package com.igafai.customer.controllers;

import com.igafai.customer.entities.CustomerEntity;
import com.igafai.customer.services.CustomerManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller for Customer management API endpoints.
 * 
 * <p>This controller provides HTTP endpoints for managing customer information.
 * It follows RESTful principles and integrates with the Customer Management Service
 * to handle business logic and data operations.</p>
 * 
 * <p>All endpoints are prefixed with "/api/customer" and return JSON responses.
 * The controller handles HTTP requests, validates input, and delegates business
 * operations to the service layer.</p>
 * 
 * @author Ikram Gafai
 * @version 2.0
 * @since 2024
 * @see com.igafai.customer.services.CustomerManagementService
 * @see org.springframework.web.bind.annotation.RestController
 */
@RestController
@RequestMapping("/api/customer")
public class CustomerManagementController {

    /**
     * Service for customer business logic operations.
     */
    @Autowired
    private CustomerManagementService customerManagementService;

    /**
     * Retrieves all customers from the database.
     * 
     * <p>This endpoint returns a complete list of all customers in the system.
     * The response includes customer identifier, full name, and age for each customer.</p>
     * 
     * <p>HTTP Method: GET
     * <br>Endpoint: /api/customer
     * <br>Response: 200 OK with list of customers</p>
     * 
     * @return ResponseEntity containing a list of CustomerEntity objects
     */
    @GetMapping
    public ResponseEntity<List<CustomerEntity>> getAllCustomers() {
        List<CustomerEntity> customerList = customerManagementService.retrieveAllCustomers();
        return ResponseEntity.status(HttpStatus.OK).body(customerList);
    }

    /**
     * Retrieves a specific customer by their unique identifier.
     * 
     * <p>This endpoint returns a single customer record identified by the path variable.
     * The response includes complete customer details including identifier, full name, and age.</p>
     * 
     * <p>HTTP Method: GET
     * <br>Endpoint: /api/customer/{customerId}
     * <br>Response: 200 OK with customer data, or 400 Bad Request if customer not found</p>
     * 
     * @param customerId The unique identifier of the customer to retrieve
     * @return ResponseEntity containing the CustomerEntity object
     * @throws IllegalArgumentException if the customer identifier is invalid or not found
     */
    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerEntity> getCustomerById(
            @PathVariable("customerId") Long customerId) throws IllegalArgumentException {
        
        CustomerEntity customerEntity = customerManagementService.retrieveCustomerById(customerId);
        return ResponseEntity.status(HttpStatus.OK).body(customerEntity);
    }

    /**
     * Creates a new customer in the database.
     * 
     * <p>This endpoint accepts a customer object in the request body and persists it
     * to the database. The customer identifier will be automatically generated if not
     * provided in the request.</p>
     * 
     * <p>HTTP Method: POST
     * <br>Endpoint: /api/customer
     * <br>Request Body: CustomerEntity object (JSON)
     * <br>Response: 201 Created with the saved customer data</p>
     * 
     * @param customerEntity The customer entity to be created
     * @return ResponseEntity containing the created CustomerEntity with generated identifier
     */
    @PostMapping
    public ResponseEntity<CustomerEntity> createCustomer(@RequestBody CustomerEntity customerEntity) {
        CustomerEntity savedCustomer = customerManagementService.createNewCustomer(customerEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCustomer);
    }
}

