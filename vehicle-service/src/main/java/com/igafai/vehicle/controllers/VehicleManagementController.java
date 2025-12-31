package com.igafai.vehicle.controllers;

import com.igafai.vehicle.models.VehicleResponseModel;
import com.igafai.vehicle.services.VehicleManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * REST Controller for Vehicle management API endpoints.
 * 
 * <p>This controller provides HTTP endpoints for retrieving vehicle information
 * with associated customer data. It follows RESTful principles and integrates
 * with the Vehicle Management Service to handle business logic.</p>
 * 
 * <p>All endpoints are prefixed with "/api/vehicle" and return JSON responses.
 * The controller handles HTTP requests and delegates business operations to the
 * service layer.</p>
 * 
 * @author Ikram Gafai
 * @version 2.0
 * @since 2024
 * @see com.igafai.vehicle.services.VehicleManagementService
 * @see org.springframework.web.bind.annotation.RestController
 */
@RestController
@RequestMapping("/api/vehicle")
public class VehicleManagementController {

    /**
     * Service for vehicle business logic operations.
     */
    @Autowired
    private VehicleManagementService vehicleManagementService;

    /**
     * Retrieves all vehicles with their associated customer information.
     * 
     * <p>This endpoint returns a complete list of all vehicles in the system,
     * each enriched with customer data from the Customer Management Service.
     * The response includes vehicle details (brand, model, registration) along
     * with owner information (name, age).</p>
     * 
     * <p>HTTP Method: GET
     * <br>Endpoint: /api/vehicle
     * <br>Response: 200 OK with list of vehicles</p>
     * 
     * @return ResponseEntity containing a list of VehicleResponseModel objects
     */
    @GetMapping
    public ResponseEntity<List<VehicleResponseModel>> getAllVehiclesWithCustomerData() {
        List<VehicleResponseModel> vehicleList = 
            vehicleManagementService.retrieveAllVehiclesWithCustomerData();
        return ResponseEntity.status(HttpStatus.OK).body(vehicleList);
    }

    /**
     * Retrieves a specific vehicle by its identifier with associated customer information.
     * 
     * <p>This endpoint returns a single vehicle record identified by the path variable.
     * The response includes complete vehicle details along with the associated customer
     * information retrieved from the Customer Management Service.</p>
     * 
     * <p>HTTP Method: GET
     * <br>Endpoint: /api/vehicle/{vehicleId}
     * <br>Response: 200 OK with vehicle data, or 400 Bad Request if vehicle not found</p>
     * 
     * @param vehicleId The unique identifier of the vehicle to retrieve
     * @return ResponseEntity containing the VehicleResponseModel object
     * @throws IllegalArgumentException if the vehicle identifier is invalid or not found
     */
    @GetMapping("/{vehicleId}")
    public ResponseEntity<VehicleResponseModel> getVehicleByIdWithCustomerData(
            @PathVariable("vehicleId") Long vehicleId) throws IllegalArgumentException {
        
        VehicleResponseModel vehicleResponse = 
            vehicleManagementService.retrieveVehicleByIdWithCustomerData(vehicleId);
        return ResponseEntity.status(HttpStatus.OK).body(vehicleResponse);
    }
}

