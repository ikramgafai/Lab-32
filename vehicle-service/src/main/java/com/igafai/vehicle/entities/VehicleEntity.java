package com.igafai.vehicle.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * JPA entity representing a vehicle within the vehicle management domain.
 * 
 * <p>This persistence entity encapsulates vehicle information including
 * manufacturer branding, model designations, registration identifiers, and
 * ownership associations. The entity is managed exclusively by the vehicle
 * management microservice and serves as the authoritative data source for
 * vehicle records in the distributed system architecture.</p>
 * 
 * <p>The entity employs Jakarta Persistence API annotations for database
 * mapping and Lombok for reducing repetitive boilerplate code. Primary key
 * generation follows the IDENTITY strategy for efficient database operations.</p>
 * 
 * @author Ikram Gafai
 * @version 3.0
 * @since 2024
 * @see jakarta.persistence.Entity
 * @see com.igafai.vehicle.repositories.VehicleDataRepository
 */
@Entity(name = "vehicles")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleEntity {

    /**
     * Primary key uniquely identifying each vehicle record in the system.
     * 
     * <p>This numeric identifier is automatically assigned by the database
     * engine using the IDENTITY generation strategy. The identifier ensures
     * uniqueness and provides a stable reference for vehicle records across
     * service boundaries and over time.</p>
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vehicle_id", nullable = false, unique = true)
    private Long id;

    /**
     * Brand or manufacturer name associated with the vehicle.
     * 
     * <p>This field stores the corporate brand name of the vehicle manufacturer.
     * Common examples include established automotive brands such as Toyota,
     * BMW, Mercedes-Benz, Ford, and similar manufacturers. The brand serves
     * as a primary categorization mechanism for vehicle identification.</p>
     */
    @Column(name = "brand", nullable = false, length = 100)
    private String brand;

    /**
     * Model designation identifying the specific vehicle variant.
     * 
     * <p>This attribute specifies the particular model within the manufacturer's
     * product portfolio. Examples include model names like Camry, X5, C-Class,
     * Mustang, and other manufacturer-specific designations that distinguish
     * between different vehicle variants produced by the same brand.</p>
     */
    @Column(name = "model", nullable = false, length = 100)
    private String model;

    /**
     * Official registration or license plate number assigned to the vehicle.
     * 
     * <p>This unique alphanumeric identifier is officially assigned by the
     * relevant governmental registration authority. The registration number
     * serves as the legal identification marker for the vehicle and is used
     * for regulatory compliance, law enforcement, and administrative purposes.
     * The format and structure may vary significantly across different
     * jurisdictions and regulatory frameworks.</p>
     */
    @Column(name = "registration_number", nullable = false, unique = true, length = 50)
    private String registrationNumber;

    /**
     * Reference identifier linking this vehicle to its owner in the customer service.
     * 
     * <p>This numeric identifier establishes the ownership relationship between
     * a vehicle and a customer. The reference points to a customer entity managed
     * by the customer management microservice, maintaining loose coupling between
     * services. The relationship is resolved through inter-service API communication
     * rather than database foreign key constraints, preserving microservice autonomy.</p>
     * 
     * @see com.igafai.customer.entities.CustomerEntity
     */
    @Column(name = "customer_id", nullable = false)
    private Long customerId;
}

