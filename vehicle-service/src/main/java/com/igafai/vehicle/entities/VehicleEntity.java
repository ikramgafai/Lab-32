package com.igafai.vehicle.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity class representing a Vehicle in the microservices architecture.
 * 
 * <p>This entity stores comprehensive vehicle information including manufacturer details,
 * model specifications, registration information, and the associated customer identifier.
 * The vehicle entity is managed by the Vehicle Management Service and maintains
 * a reference to the customer through the customer identifier.</p>
 * 
 * <p>The entity uses JPA annotations for persistence and Lombok annotations
 * for reducing boilerplate code. The primary key is auto-generated using
 * database identity strategy.</p>
 * 
 * @author Ikram Gafai
 * @version 2.0
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
     * Unique identifier for the vehicle record.
     * 
     * <p>This field serves as the primary key and is automatically generated
     * by the database using the IDENTITY strategy. Each vehicle instance
     * will have a unique identifier assigned upon persistence.</p>
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vehicle_id", nullable = false, unique = true)
    private Long vehicleIdentifier;

    /**
     * Brand or manufacturer name of the vehicle.
     * 
     * <p>Examples include: Toyota, BMW, Mercedes-Benz, Ford, etc.
     * This field represents the company that manufactured the vehicle.</p>
     */
    @Column(name = "brand", nullable = false, length = 100)
    private String manufacturerBrand;

    /**
     * Model name or designation of the vehicle.
     * 
     * <p>This field represents the specific model within the manufacturer's
     * product line. Examples: Camry, X5, C-Class, Mustang, etc.</p>
     */
    @Column(name = "model", nullable = false, length = 100)
    private String vehicleModel;

    /**
     * Registration number or license plate identifier.
     * 
     * <p>This unique identifier is assigned by the registration authority
     * and is used for legal identification of the vehicle. The format
     * may vary depending on the jurisdiction.</p>
     */
    @Column(name = "registration_number", nullable = false, unique = true, length = 50)
    private String registrationPlateNumber;

    /**
     * Foreign key reference to the customer who owns this vehicle.
     * 
     * <p>This identifier references the Customer entity managed by the
     * Customer Management Service. The relationship is maintained through
     * service-to-service communication rather than database foreign keys,
     * following microservices best practices.</p>
     * 
     * @see com.igafai.customer.entities.CustomerEntity
     */
    @Column(name = "customer_id", nullable = false)
    private Long ownerCustomerIdentifier;
}

