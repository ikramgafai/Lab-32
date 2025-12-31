package com.igafai.vehicle.services;

import com.igafai.vehicle.entities.CustomerDataTransferObject;
import com.igafai.vehicle.entities.VehicleEntity;
import com.igafai.vehicle.models.VehicleResponseModel;
import com.igafai.vehicle.repositories.VehicleDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class for managing Vehicle business logic and operations.
 * 
 * <p>This service handles all vehicle-related business operations including retrieval,
 * data enrichment, and integration with the Customer Management Service. It acts as
 * the intermediary between the controller layer and the data access layer.</p>
 * 
 * <p>The service implements the business logic for combining vehicle data with customer
 * information, making it a key component in the microservices architecture for providing
 * comprehensive vehicle-customer relationship data.</p>
 * 
 * @author Ikram Gafai
 * @version 2.0
 * @since 2024
 * @see com.igafai.vehicle.repositories.VehicleDataRepository
 * @see com.igafai.vehicle.controllers.VehicleManagementController
 */
@Service
public class VehicleManagementService {

    /**
     * Repository for vehicle data access operations.
     */
    @Autowired
    private VehicleDataRepository vehicleDataRepository;

    /**
     * REST client for inter-service communication.
     */
    @Autowired
    private RestTemplate httpRestClient;

    /**
     * Base URL for the Customer Management Service API.
     * 
     * <p>This URL is configured via application properties and points to the
     * Customer Management Service through the API Gateway.</p>
     */
    private static final String CUSTOMER_SERVICE_BASE_URL = "http://localhost:8888/CUSTOMER-SERVICE";

    /**
     * Retrieves all vehicles with their associated customer information.
     * 
     * <p>This method fetches all vehicle records from the database and enriches each
     * vehicle with customer data retrieved from the Customer Management Service.
     * The method handles the aggregation of data from multiple microservices.</p>
     * 
     * <p>If customer data cannot be retrieved for a vehicle, the vehicle will still
     * be included in the response but with a null customer field.</p>
     * 
     * @return List of VehicleResponseModel objects containing complete vehicle and customer data
     * @throws RestClientException if communication with Customer Service fails
     */
    public List<VehicleResponseModel> retrieveAllVehiclesWithCustomerData() {
        List<VehicleEntity> vehicleEntities = vehicleDataRepository.findAll();
        
        ResponseEntity<CustomerDataTransferObject[]> customerServiceResponse = 
            httpRestClient.getForEntity(
                CUSTOMER_SERVICE_BASE_URL + "/api/customer",
                CustomerDataTransferObject[].class
            );
        
        CustomerDataTransferObject[] allCustomers = customerServiceResponse.getBody();
        
        return vehicleEntities.stream()
                .map(vehicle -> buildVehicleResponseWithCustomer(vehicle, allCustomers))
                .collect(Collectors.toList());
    }

    /**
     * Builds a VehicleResponseModel by combining vehicle data with customer information.
     * 
     * <p>This private method maps a VehicleEntity to a VehicleResponseModel and finds
     * the associated customer from the provided customer array. It implements the logic
     * for matching vehicles with their owners based on the customer identifier.</p>
     * 
     * @param vehicleEntity The vehicle entity to map
     * @param customerArray Array of all customers to search from
     * @return VehicleResponseModel object with vehicle and customer data
     */
    private VehicleResponseModel buildVehicleResponseWithCustomer(
            VehicleEntity vehicleEntity, 
            CustomerDataTransferObject[] customerArray) {
        
        CustomerDataTransferObject matchingCustomer = Arrays.stream(customerArray)
                .filter(customer -> customer.getCustomerIdentifier()
                        .equals(vehicleEntity.getOwnerCustomerIdentifier()))
                .findFirst()
                .orElse(null);

        return VehicleResponseModel.builder()
                .vehicleId(vehicleEntity.getVehicleIdentifier())
                .manufacturerBrand(vehicleEntity.getManufacturerBrand())
                .vehicleModel(vehicleEntity.getVehicleModel())
                .registrationPlateNumber(vehicleEntity.getRegistrationPlateNumber())
                .associatedCustomer(matchingCustomer)
                .build();
    }

    /**
     * Retrieves a specific vehicle by its identifier with associated customer information.
     * 
     * <p>This method fetches a single vehicle record from the database using the provided
     * identifier and enriches it with customer data from the Customer Management Service.
     * If the vehicle is not found, an exception is thrown.</p>
     * 
     * @param vehicleIdentifier The unique identifier of the vehicle to retrieve
     * @return VehicleResponseModel containing vehicle and customer data
     * @throws IllegalArgumentException if the vehicle identifier is invalid or not found
     * @throws RestClientException if communication with Customer Service fails
     */
    public VehicleResponseModel retrieveVehicleByIdWithCustomerData(Long vehicleIdentifier) 
            throws IllegalArgumentException {
        
        VehicleEntity vehicleEntity = vehicleDataRepository.findById(vehicleIdentifier)
                .orElseThrow(() -> new IllegalArgumentException(
                    "Vehicle not found with identifier: " + vehicleIdentifier));

        CustomerDataTransferObject customerData = httpRestClient.getForObject(
                CUSTOMER_SERVICE_BASE_URL + "/api/customer/" + vehicleEntity.getOwnerCustomerIdentifier(),
                CustomerDataTransferObject.class);

        return VehicleResponseModel.builder()
                .vehicleId(vehicleEntity.getVehicleIdentifier())
                .manufacturerBrand(vehicleEntity.getManufacturerBrand())
                .vehicleModel(vehicleEntity.getVehicleModel())
                .registrationPlateNumber(vehicleEntity.getRegistrationPlateNumber())
                .associatedCustomer(customerData)
                .build();
    }
}

