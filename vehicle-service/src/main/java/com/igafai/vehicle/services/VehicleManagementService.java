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
 * Business service implementation for vehicle domain operations and data aggregation.
 * 
 * <p>This service class orchestrates all vehicle-related business logic including
 * entity retrieval, data enrichment through cross-service integration, and response
 * model construction. It functions as the primary abstraction layer between the
 * presentation controllers and data persistence repositories.</p>
 * 
 * <p>The service implements sophisticated data aggregation patterns by combining
 * vehicle information from the local database with customer data retrieved from
 * the customer management microservice, demonstrating inter-service collaboration
 * within a distributed system architecture.</p>
 * 
 * @author Ikram Gafai
 * @version 3.0
 * @since 2024
 * @see com.igafai.vehicle.repositories.VehicleDataRepository
 * @see com.igafai.vehicle.controllers.VehicleManagementController
 */
@Service
public class VehicleManagementService {

    /**
     * Base URL endpoint for customer management service API invocations.
     * 
     * <p>This constant defines the base URL for communicating with the customer
     * management microservice through the API gateway infrastructure. The URL
     * configuration should ideally be externalized to application properties
     * for improved deployment flexibility.</p>
     */
    private static final String CUSTOMER_SERVICE_BASE_URL = "http://localhost:8888/CUSTOMER-SERVICE";

    /**
     * Repository instance providing vehicle entity persistence operations.
     */
    @Autowired
    private VehicleDataRepository repository;

    /**
     * REST template client for executing HTTP requests to external microservices.
     */
    @Autowired
    private RestTemplate restClient;

    /**
     * Constructs a vehicle response model by enriching vehicle data with customer information.
     * 
     * <p>This private utility method transforms a vehicle entity into a response model
     * by locating and attaching the corresponding customer record from the provided
     * customer collection. The method performs identifier-based matching to establish
     * vehicle-owner relationships.</p>
     * 
     * @param vehicle The vehicle entity to transform
     * @param customers Array of customer DTOs to search for matching owner information
     * @return Fully populated vehicle response model with associated customer data
     */
    private VehicleResponseModel buildVehicleResponseWithCustomer(
            VehicleEntity vehicle, 
            CustomerDataTransferObject[] customers) {
        
        CustomerDataTransferObject owner = Arrays.stream(customers)
                .filter(c -> c.getCustomerIdentifier().equals(vehicle.getCustomerId()))
                .findFirst()
                .orElse(null);

        return VehicleResponseModel.builder()
                .vehicleId(vehicle.getId())
                .manufacturerBrand(vehicle.getBrand())
                .vehicleModel(vehicle.getModel())
                .registrationPlateNumber(vehicle.getRegistrationNumber())
                .associatedCustomer(owner)
                .build();
    }

    /**
     * Retrieves a single vehicle record with enriched customer information by identifier.
     * 
     * <p>This method performs a primary key lookup to locate a specific vehicle entity,
     * then enriches the result with customer details obtained through an inter-service
     * API call to the customer management service. The method throws an exception if
     * the vehicle cannot be located in the local database.</p>
     * 
     * @param id The unique numeric identifier of the target vehicle
     * @return Vehicle response model containing vehicle and associated customer data
     * @throws IllegalArgumentException if no vehicle exists with the specified identifier
     * @throws RestClientException if communication with the customer service fails
     */
    public VehicleResponseModel retrieveVehicleByIdWithCustomerData(Long id) 
            throws IllegalArgumentException {
        
        VehicleEntity vehicle = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(
                    "Vehicle not found with identifier: " + id));

        CustomerDataTransferObject customer = restClient.getForObject(
                CUSTOMER_SERVICE_BASE_URL + "/api/customer/" + vehicle.getCustomerId(),
                CustomerDataTransferObject.class);

        return VehicleResponseModel.builder()
                .vehicleId(vehicle.getId())
                .manufacturerBrand(vehicle.getBrand())
                .vehicleModel(vehicle.getModel())
                .registrationPlateNumber(vehicle.getRegistrationNumber())
                .associatedCustomer(customer)
                .build();
    }

    /**
     * Retrieves all vehicle records with enriched customer information from all microservices.
     * 
     * <p>This method performs a comprehensive data aggregation operation by fetching
     * all vehicle entities from the local database and enriching each record with
     * customer information retrieved from the customer management microservice. The
     * method demonstrates cross-service data integration patterns in distributed systems.</p>
     * 
     * <p>If customer data retrieval fails for any vehicle, the vehicle record is still
     * included in the response but with a null customer field, ensuring partial results
     * are returned even when external service communication encounters issues.</p>
     * 
     * @return Collection of vehicle response models containing complete vehicle and customer data
     * @throws RestClientException if communication with the customer service fails completely
     */
    public List<VehicleResponseModel> retrieveAllVehiclesWithCustomerData() {
        List<VehicleEntity> vehicles = repository.findAll();
        
        ResponseEntity<CustomerDataTransferObject[]> response = 
            restClient.getForEntity(
                CUSTOMER_SERVICE_BASE_URL + "/api/customer",
                CustomerDataTransferObject[].class
            );
        
        CustomerDataTransferObject[] allCustomers = response.getBody();
        
        return vehicles.stream()
                .map(v -> buildVehicleResponseWithCustomer(v, allCustomers))
                .collect(Collectors.toList());
    }
}

