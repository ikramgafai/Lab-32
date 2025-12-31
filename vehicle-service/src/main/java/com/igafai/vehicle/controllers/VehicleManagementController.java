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
 * RESTful web service controller exposing vehicle management HTTP endpoints.
 * 
 * <p>This controller class defines the REST API interface for vehicle-related
 * operations within the vehicle management microservice. It adheres to REST
 * architectural principles and provides standardized HTTP method mappings for
 * vehicle resource retrieval with enriched customer information.</p>
 * 
 * <p>All API endpoints are namespace-scoped under the "/api/vehicle" base path
 * and produce JSON payloads. The controller coordinates between HTTP request
 * handling and business service layer operations, ensuring proper response
 * formatting and error handling.</p>
 * 
 * @author Ikram Gafai
 * @version 3.0
 * @since 2024
 * @see com.igafai.vehicle.services.VehicleManagementService
 * @see org.springframework.web.bind.annotation.RestController
 */
@RestController
@RequestMapping("/api/vehicle")
public class VehicleManagementController {

    /**
     * Business service component for vehicle domain operations.
     * 
     * <p>Dependency-injected service instance that encapsulates all business
     * logic, data aggregation, and cross-service integration for vehicle
     * management operations.</p>
     */
    @Autowired
    private VehicleManagementService service;

    /**
     * Handles HTTP GET requests to retrieve a specific vehicle with customer data by identifier.
     * 
     * <p>This endpoint performs a lookup operation for a single vehicle record
     * using the identifier provided as a path variable. The response includes
     * complete vehicle attributes along with associated customer information
     * obtained through inter-service communication.</p>
     * 
     * <p>Request Specification:
     * <ul>
     *   <li>Method: GET</li>
     *   <li>Path: /api/vehicle/{id}</li>
     *   <li>Path Variable: id (Long) - Vehicle unique identifier</li>
     * </ul></p>
     * 
     * <p>Response Specification:
     * <ul>
     *   <li>Status: 200 OK (success) or 400 Bad Request (not found)</li>
     *   <li>Body: VehicleResponseModel object with vehicle and customer data</li>
     * </ul></p>
     * 
     * @param id The unique numeric identifier of the vehicle to retrieve
     * @return HTTP response entity containing the vehicle response model
     * @throws IllegalArgumentException if the specified vehicle identifier is invalid
     */
    @GetMapping("/{id}")
    public ResponseEntity<VehicleResponseModel> getVehicleByIdWithCustomerData(
            @PathVariable("id") Long id) throws IllegalArgumentException {
        VehicleResponseModel result = service.retrieveVehicleByIdWithCustomerData(id);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    /**
     * Handles HTTP GET requests to retrieve all vehicles with enriched customer information.
     * 
     * <p>This endpoint returns a complete collection of all vehicle entities currently
     * stored in the system, with each vehicle enriched with customer data retrieved
     * from the customer management microservice. The response includes vehicle details
     * (brand, model, registration number) along with owner information (name, age).</p>
     * 
     * <p>Request Specification:
     * <ul>
     *   <li>Method: GET</li>
     *   <li>Path: /api/vehicle</li>
     * </ul></p>
     * 
     * <p>Response Specification:
     * <ul>
     *   <li>Status: 200 OK</li>
     *   <li>Body: Array of VehicleResponseModel objects</li>
     * </ul></p>
     * 
     * @return HTTP response entity containing a collection of vehicle response models
     */
    @GetMapping
    public ResponseEntity<List<VehicleResponseModel>> getAllVehiclesWithCustomerData() {
        List<VehicleResponseModel> vehicles = service.retrieveAllVehiclesWithCustomerData();
        return ResponseEntity.status(HttpStatus.OK).body(vehicles);
    }
}

