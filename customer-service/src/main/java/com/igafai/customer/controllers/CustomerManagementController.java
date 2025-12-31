package com.igafai.customer.controllers;

import com.igafai.customer.entities.CustomerEntity;
import com.igafai.customer.services.CustomerManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * RESTful web service controller exposing customer management HTTP endpoints.
 * 
 * <p>This controller class defines the REST API interface for customer-related
 * operations within the customer management microservice. It adheres to REST
 * architectural principles and provides standardized HTTP method mappings for
 * customer resource manipulation.</p>
 * 
 * <p>All API endpoints are namespace-scoped under the "/api/customer" base path
 * and produce/consume JSON payloads. The controller coordinates between HTTP
 * request handling and business service layer operations, ensuring proper
 * request validation and response formatting.</p>
 * 
 * @author Ikram Gafai
 * @version 3.0
 * @since 2024
 * @see com.igafai.customer.services.CustomerManagementService
 * @see org.springframework.web.bind.annotation.RestController
 */
@RestController
@RequestMapping("/api/customer")
public class CustomerManagementController {

    /**
     * Business service component for customer domain operations.
     * 
     * <p>Dependency-injected service instance that encapsulates all business
     * logic and data access coordination for customer management operations.</p>
     */
    @Autowired
    private CustomerManagementService service;

    /**
     * Handles HTTP POST requests to create new customer resources.
     * 
     * <p>This endpoint processes customer creation requests by accepting a
     * JSON-serialized customer entity in the request body. The system
     * automatically generates a unique identifier if not provided, then
     * persists the entity and returns the complete persisted record.</p>
     * 
     * <p>Request Specification:
     * <ul>
     *   <li>Method: POST</li>
     *   <li>Path: /api/customer</li>
     *   <li>Content-Type: application/json</li>
     *   <li>Request Body: CustomerEntity JSON representation</li>
     * </ul></p>
     * 
     * <p>Response Specification:
     * <ul>
     *   <li>Status: 201 Created</li>
     *   <li>Body: Complete CustomerEntity with generated identifier</li>
     * </ul></p>
     * 
     * @param entity The customer entity to persist, deserialized from request body
     * @return HTTP response entity containing the created customer record
     */
    @PostMapping
    public ResponseEntity<CustomerEntity> createCustomer(@RequestBody CustomerEntity entity) {
        CustomerEntity created = service.createNewCustomer(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Handles HTTP GET requests to retrieve a specific customer by identifier.
     * 
     * <p>This endpoint performs a lookup operation for a single customer record
     * using the identifier provided as a path variable. The endpoint returns
     * the complete customer details including all associated attributes.</p>
     * 
     * <p>Request Specification:
     * <ul>
     *   <li>Method: GET</li>
     *   <li>Path: /api/customer/{id}</li>
     *   <li>Path Variable: id (Long) - Customer unique identifier</li>
     * </ul></p>
     * 
     * <p>Response Specification:
     * <ul>
     *   <li>Status: 200 OK (success) or 400 Bad Request (not found)</li>
     *   <li>Body: CustomerEntity object or error details</li>
     * </ul></p>
     * 
     * @param id The unique numeric identifier of the customer to retrieve
     * @return HTTP response entity containing the customer record
     * @throws IllegalArgumentException if the specified customer identifier is invalid
     */
    @GetMapping("/{id}")
    public ResponseEntity<CustomerEntity> getCustomerById(@PathVariable("id") Long id) 
            throws IllegalArgumentException {
        CustomerEntity result = service.retrieveCustomerById(id);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    /**
     * Handles HTTP GET requests to retrieve all customer resources.
     * 
     * <p>This endpoint returns a complete collection of all customer entities
     * currently stored in the system. The response includes all customer
     * attributes for each record: identifier, name, and age.</p>
     * 
     * <p>Request Specification:
     * <ul>
     *   <li>Method: GET</li>
     *   <li>Path: /api/customer</li>
     * </ul></p>
     * 
     * <p>Response Specification:
     * <ul>
     *   <li>Status: 200 OK</li>
     *   <li>Body: Array of CustomerEntity objects</li>
     * </ul></p>
     * 
     * @return HTTP response entity containing a collection of all customer entities
     */
    @GetMapping
    public ResponseEntity<List<CustomerEntity>> getAllCustomers() {
        List<CustomerEntity> customers = service.retrieveAllCustomers();
        return ResponseEntity.status(HttpStatus.OK).body(customers);
    }
}

