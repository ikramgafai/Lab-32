package com.igafai.customer.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity class representing a Customer in the microservices architecture.
 * 
 * <p>This entity stores customer information including personal details such as
 * full name and age. The customer entity is managed by the Customer Management
 * Service and serves as the source of truth for customer data across the system.</p>
 * 
 * <p>The entity uses JPA annotations for persistence and Lombok annotations
 * for reducing boilerplate code. The primary key is auto-generated using
 * database identity strategy.</p>
 * 
 * <p>This entity is referenced by other microservices (such as Vehicle Management)
 * through service-to-service communication rather than direct database access,
 * maintaining service boundaries in the microservices architecture.</p>
 * 
 * @author Ikram Gafai
 * @version 2.0
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
     * Unique identifier for the customer record.
     * 
     * <p>This field serves as the primary key and is automatically generated
     * by the database using the IDENTITY strategy. Each customer instance
     * will have a unique identifier assigned upon persistence.</p>
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id", nullable = false, unique = true)
    private Long customerIdentifier;

    /**
     * Complete legal name of the customer.
     * 
     * <p>This field contains the full name as registered in the system.
     * The format may include first name, middle name(s), and last name.
     * The name is stored as a single string to accommodate various naming conventions.</p>
     */
    @Column(name = "full_name", nullable = false, length = 255)
    private String customerFullName;

    /**
     * Age of the customer in years.
     * 
     * <p>This field represents the customer's age at the time of record creation.
     * It is stored as a floating-point number to allow for precise age calculations
     * and demographic analysis. The age is used for business logic validation
     * and reporting purposes.</p>
     */
    @Column(name = "age", nullable = false)
    private Float customerAge;
}

