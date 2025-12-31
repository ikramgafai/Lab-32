package com.igafai.vehicle.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data transfer object for customer information received from external microservices.
 * 
 * <p>This DTO class facilitates the deserialization of customer data received through
 * REST API invocations targeting the customer management service. Unlike customer
 * entities within the customer service domain, this class is not a JPA entity because
 * customer data ownership resides in a distinct microservice, respecting service
 * boundary principles in distributed system architectures.</p>
 * 
 * <p>The DTO pattern enables the vehicle service to integrate customer information
 * into its responses without violating microservice boundaries or creating direct
 * database dependencies, thereby preserving service independence and deployability.</p>
 * 
 * @author Ikram Gafai
 * @version 3.0
 * @since 2024
 * @see com.igafai.customer.entities.CustomerEntity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDataTransferObject {

    /**
     * Unique numeric identifier representing the customer in the customer service domain.
     * 
     * <p>This identifier aligns with the primary key value maintained in the customer
     * management service and facilitates the establishment of vehicle-owner relationships
     * through identifier matching rather than database joins.</p>
     */
    private Long customerIdentifier;

    /**
     * Complete legal name of the customer as maintained in the customer management system.
     * 
     * <p>This attribute contains the full customer name which may incorporate given names,
     * middle names, and family names in a single string field, accommodating diverse
     * international naming conventions.</p>
     */
    private String customerFullName;

    /**
     * Customer age value expressed in whole years.
     * 
     * <p>This numeric field captures the customer's age at the time of record creation
     * and supports demographic analysis, business rule validation, and regulatory
     * compliance verification requirements.</p>
     */
    private Integer customerAge;
}

