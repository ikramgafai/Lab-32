package com.igafai.vehicle.models;

import com.igafai.vehicle.entities.CustomerDataTransferObject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Response model aggregating vehicle and customer data for API responses.
 * 
 * <p>This model class combines vehicle-specific attributes with associated
 * customer information to deliver comprehensive response payloads to API consumers.
 * It functions as the primary response structure for all vehicle management
 * service endpoints that require enriched vehicle data.</p>
 * 
 * <p>The class leverages the Builder design pattern through Lombok annotations
 * to enable flexible object construction with various attribute combinations,
 * supporting complex response scenarios while maintaining code clarity.</p>
 * 
 * @author Ikram Gafai
 * @version 3.0
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
     * Primary key identifier uniquely identifying the vehicle record.
     * 
     * <p>This identifier corresponds directly to the vehicle entity's primary key
     * in the persistence layer and can be utilized in subsequent API invocations
     * to perform vehicle-specific operations such as retrieval, updates, or deletion.</p>
     */
    private Long vehicleId;

    /**
     * Manufacturing brand name associated with the vehicle.
     * 
     * <p>This attribute represents the corporate brand responsible for vehicle
     * production. Common examples include global automotive manufacturers such
     * as Toyota, BMW, Mercedes-Benz, and similar established brands.</p>
     */
    private String manufacturerBrand;

    /**
     * Product model designation identifying the vehicle variant.
     * 
     * <p>This field specifies the particular model name or designation within
     * the manufacturer's product portfolio, enabling distinction between different
     * vehicle variants produced under the same brand umbrella.</p>
     */
    private String vehicleModel;

    /**
     * Official registration or license plate number assigned to the vehicle.
     * 
     * <p>This unique identifier is issued by governmental registration authorities
     * and serves as the legal identification marker for vehicle registration,
     * regulatory compliance, and administrative tracking purposes.</p>
     */
    private String registrationPlateNumber;

    /**
     * Customer entity information linked to this vehicle's ownership.
     * 
     * <p>This nested object encapsulates customer details obtained through
     * inter-service communication with the customer management microservice.
     * The object includes customer identifier, full name, and age attributes.
     * This field may be null if the associated customer record cannot be
     * located or retrieved due to service unavailability or data inconsistencies.</p>
     * 
     * @see com.igafai.vehicle.entities.CustomerDataTransferObject
     */
    private CustomerDataTransferObject associatedCustomer;
}

