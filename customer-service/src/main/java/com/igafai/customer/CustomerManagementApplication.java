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
     * Main entry point for the Customer Management Service application.
     * 
     * <p>This method initializes the Spring Boot application context and starts
     * the embedded web server. The application will register with Eureka and
     * begin accepting HTTP requests on the configured port.</p>
     * 
     * @param applicationArguments Command line arguments passed to the application
     */
    public static void main(String[] applicationArguments) {
        SpringApplication.run(CustomerManagementApplication.class, applicationArguments);
    }

    /**
     * Initializes the database with sample customer data for development and testing.
     * 
     * <p>This bean runs at application startup to populate the database with initial
     * customer records. The sample data includes three customers with different names
     * and ages, providing a foundation for testing and development purposes.</p>
     * 
     * <p>Note: In production environments, this initialization should be disabled or
     * replaced with proper data migration scripts.</p>
     * 
     * @param customerDataRepository Repository for customer data access operations
     * @return CommandLineRunner that initializes sample customer data
     */
    @Bean
    CommandLineRunner initializeDatabaseWithSampleData(CustomerDataRepository customerDataRepository) {
        return applicationArguments -> {
            customerDataRepository.save(new CustomerEntity(1L, "Amine SAFI", 23.0f));
            customerDataRepository.save(new CustomerEntity(2L, "Amal ALAOUI", 22.0f));
            customerDataRepository.save(new CustomerEntity(3L, "Samir RAMI", 22.0f));
        };
    }
}

