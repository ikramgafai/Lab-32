package com.igafai.discovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Main application class for the Service Discovery and Registry Server.
 * 
 * <p>This Spring Boot application serves as the Eureka Server, providing service
 * registration and discovery capabilities for all microservices in the system.
 * It acts as the central registry where services register themselves and discover
 * other services dynamically.</p>
 * 
 * <p>The Eureka server enables:
 * <ul>
 *   <li>Automatic service registration when microservices start</li>
 *   <li>Service discovery for inter-service communication</li>
 *   <li>Load balancing across multiple instances of the same service</li>
 *   <li>Health monitoring and service status tracking</li>
 * </ul></p>
 * 
 * <p>The server runs on port 8761 by default and provides a web dashboard
 * for monitoring registered services and their health status.</p>
 * 
 * @author Ikram Gafai
 * @version 2.0
 * @since 2024
 * @see org.springframework.boot.autoconfigure.SpringBootApplication
 * @see org.springframework.cloud.netflix.eureka.server.EnableEurekaServer
 */
@SpringBootApplication
@EnableEurekaServer
public class ServiceRegistryApplication {

    /**
     * Main entry point for the Service Discovery and Registry application.
     * 
     * <p>This method initializes the Spring Boot application context and starts
     * the Eureka server. The server will begin accepting service registrations
     * and providing service discovery capabilities to client applications.</p>
     * 
     * <p>The Eureka dashboard will be available at http://localhost:8761
     * once the server is running.</p>
     * 
     * @param applicationArguments Command line arguments passed to the application
     */
    public static void main(String[] applicationArguments) {
        SpringApplication.run(ServiceRegistryApplication.class, applicationArguments);
    }
}

