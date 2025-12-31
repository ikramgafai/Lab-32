package com.igafai.customer.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a customer entity in the distributed microservices system.
 * 
 * <p>This JPA entity class defines the customer data model that persists
 * customer information within the customer management domain. It encapsulates
 * customer identity details and serves as the authoritative data source
 * for customer records in the enterprise microservices ecosystem.</p>
 * 
 * <p>The entity leverages Jakarta Persistence API for database mapping
 * and utilizes Lombok annotations to minimize boilerplate code generation.
 * Primary key generation follows the IDENTITY strategy for optimal
 * database performance and consistency.</p>
 * 
 * <p>Cross-service references utilize inter-service API communication
 * patterns rather than direct database joins, preserving microservice
 * autonomy and enabling independent service deployment cycles.</p>
 * 
 * @author Ikram Gafai
 * @version 3.0
 * @since 2024
 * @see jakarta.persistence.Entity
 * @see com.igafai.customer.repositories.CustomerDataRepository
 */
@Entity(name = "customers")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerEntity {

    /**
     * Primary key identifier uniquely identifying each customer record.
     * 
     * <p>This numeric identifier is automatically assigned by the database
     * engine upon entity persistence. The IDENTITY generation strategy ensures
     * uniqueness and sequential allocation without requiring additional
     * database queries or synchronization mechanisms.</p>
     * 
     * <p>The identifier serves as the foreign key reference point for
     * dependent entities in other bounded contexts within the microservices
     * architecture.</p>
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id", nullable = false, unique = true)
    private Long id;

    /**
     * Full legal name of the customer as per official documentation.
     * 
     * <p>This attribute stores the complete name string, potentially
     * encompassing given names, middle names, and family names in a
     * single field to accommodate diverse international naming conventions
     * and cultural naming patterns.</p>
     * 
     * <p>The field is mandatory and subject to length constraints
     * to ensure data quality and storage optimization.</p>
     */
    @Column(name = "full_name", nullable = false, length = 255)
    private String name;

    /**
     * Current age of the customer measured in years as a decimal value.
     * 
     * <p>The floating-point representation enables precise age tracking
     * including fractional years when necessary. This value typically
     * represents age at the point of record creation and can be used
     * for demographic analysis, business rule enforcement, and
     * regulatory compliance verification.</p>
     */
    @Column(name = "age", nullable = false)
    private Float age;
}

