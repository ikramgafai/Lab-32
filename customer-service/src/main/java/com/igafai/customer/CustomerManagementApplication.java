package com.igafai.customer;

import com.igafai.customer.entities.CustomerEntity;
import com.igafai.customer.repositories.CustomerDataRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

/**
 * Main application class for the Customer Management Microservice.
 * 
 * <p>This Spring Boot application serves as the Customer Management Service in a
 * microservices architecture. It handles customer information management and provides
 * customer data to other microservices in the system through REST API endpoints.</p>
 * 
 * <p>The application is configured as a Eureka client to register with the service
 * discovery server, enabling dynamic service location and load balancing. It uses
 * Spring Data JPA for database operations and MySQL as the persistent data store.</p>
 * 
 * <p>Key features:
 * <ul>
 *   <li>Customer CRUD operations</li>
 *   <li>RESTful API endpoints for customer data access</li>
 *   <li>Service discovery via Eureka</li>
 *   <li>Database initialization with sample data</li>
 * </ul></p>
 * 
 * @author Ikram Gafai
 * @version 2.0
 * @since 2024
 * @see org.springframework.boot.autoconfigure.SpringBootApplication
 * @see org.springframework.cloud.netflix.eureka.EnableEurekaClient
 */
@SpringBootApplication
@EnableEurekaClient
public class CustomerManagementApplication {

    /**
     * Application bootstrap method that initializes and launches the Customer Management Service.
     * 
     * <p>This method serves as the main entry point for the customer management microservice,
     * initializing the Spring Boot application context and starting the embedded web server.
     * Upon startup, the application automatically registers with the Eureka service registry
     * and begins accepting HTTP requests on the configured port for customer management operations.</p>
     * 
     * @param args Command line arguments passed to the application during startup
     */
    public static void main(String[] args) {
        SpringApplication.run(CustomerManagementApplication.class, args);
    }

    /**
     * Configures application startup initialization with sample customer data.
     * 
     * <p>This Spring bean executes during application bootstrap to seed the
     * database with initial customer records for development and integration
     * testing scenarios. The initialization creates three sample customer
     * entities with varied demographic attributes.</p>
     * 
     * <p>Production deployments should disable this initialization mechanism
     * and utilize formal database migration tooling (e.g., Flyway, Liquibase)
     * for controlled data provisioning and version management.</p>
     * 
     * @param repo Repository instance for performing customer persistence operations
     * @return CommandLineRunner implementation that executes the data initialization logic
     */
    @Bean
    CommandLineRunner initializeDatabaseWithSampleData(CustomerDataRepository repo) {
        return args -> {
            repo.save(new CustomerEntity(1L, "Amine SAFI", 23.0f));
            repo.save(new CustomerEntity(2L, "Amal ALAOUI", 22.0f));
            repo.save(new CustomerEntity(3L, "Samir RAMI", 22.0f));
        };
    }
}

