package com.igafai.vehicle.models;

import com.igafai.vehicle.entities.CustomerDataTransferObject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Response model for Vehicle data with associated Customer information.
 * 
 * <p>This model combines vehicle details with customer data to provide a comprehensive
 * response to API consumers. It serves as the primary data structure returned by
 * the Vehicle Management Service endpoints.</p>
 * 
 * <p>The model uses the Builder pattern (via Lombok) to facilitate easy construction
 * of response objects with various combinations of vehicle and customer data.</p>
 * 
 * @author Ikram Gafai
 * @version 2.0
 * @since 2024
 * @see com.igafai.vehicle.entities.VehicleEntity
 * @see com.igafai.vehicle.entities.CustomerDataTransferObject
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleResponseModel {

    /**
     * Unique identifier of the vehicle.
     * 
     * <p>This identifier matches the vehicle's primary key in the database
     * and can be used for subsequent API calls to retrieve or update vehicle information.</p>
     */
    private Long vehicleId;

    /**
     * Brand or manufacturer name of the vehicle.
     * 
     * <p>Represents the company that manufactured the vehicle (e.g., Toyota, BMW, Mercedes).</p>
     */
    private String manufacturerBrand;

    /**
     * Model name or designation of the vehicle.
     * 
     * <p>Represents the specific model within the manufacturer's product line.</p>
     */
    private String vehicleModel;

    /**
     * Registration number or license plate identifier.
     * 
     * <p>Unique identifier assigned by the registration authority for legal identification.</p>
     */
    private String registrationPlateNumber;

    /**
     * Complete customer information associated with this vehicle.
     * 
     * <p>This object contains the customer details retrieved from the Customer Management Service.
     * It includes customer identifier, full name, and age. This field may be null if the
     * associated customer cannot be found or retrieved.</p>
     * 
     * @see com.igafai.vehicle.entities.CustomerDataTransferObject
     */
    private CustomerDataTransferObject associatedCustomer;
}

