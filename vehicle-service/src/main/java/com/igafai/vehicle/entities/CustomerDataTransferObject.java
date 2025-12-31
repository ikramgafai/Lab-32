package com.igafai.vehicle.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object (DTO) representing Customer information received from external service.
 * 
 * <p>This class is used to deserialize customer data received from the Customer Management Service
 * via REST API calls. Unlike the Customer entity in the customer service, this is not a JPA entity
 * as customer data is managed by a separate microservice following the microservices architecture pattern.</p>
 * 
 * <p>This DTO enables the Vehicle Service to work with customer information without directly
 * accessing the customer database, maintaining service boundaries and independence.</p>
 * 
 * @author Ikram Gafai
 * @version 2.0
 * @since 2024
 * @see com.igafai.customer.entities.CustomerEntity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDataTransferObject {

    /**
     * Unique identifier of the customer.
     * 
     * <p>This identifier corresponds to the primary key in the Customer Management Service
     * and is used to establish the relationship between vehicles and their owners.</p>
     */
    private Long customerIdentifier;

    /**
     * Complete legal name of the customer.
     * 
     * <p>This field contains the full name as registered in the customer management system.
     * The format may include first name, middle name(s), and last name.</p>
     */
    private String customerFullName;

    /**
     * Age of the customer in years.
     * 
     * <p>This field represents the customer's age at the time of record creation.
     * It is used for demographic analysis and business logic validation.</p>
     */
    private Integer customerAge;
}

