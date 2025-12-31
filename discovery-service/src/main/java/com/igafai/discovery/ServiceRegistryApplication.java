package com.igafai.discovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Main application entry point for the Service Discovery and Registry microservice.
 * 
 * <p>This Spring Boot application implements the Eureka Server pattern, functioning
 * as the central service registry and discovery mechanism for all microservices
 * within the distributed system. The server maintains a dynamic registry where
 * microservices register their availability and discover other services for
 * inter-service communication.</p>
 * 
 * <p>The Eureka service registry provides the following core capabilities:
 * <ul>
 *   <li>Automatic service registration when microservices bootstrap and start</li>
 *   <li>Dynamic service discovery enabling runtime service location resolution</li>
 *   <li>Client-side load balancing across multiple instances of the same service</li>
 *   <li>Service health monitoring and automatic removal of unhealthy instances</li>
 *   <li>Real-time service status tracking and availability reporting</li>
 * </ul></p>
 * 
 * <p>The server operates on port 8761 by default and exposes a comprehensive web
 * dashboard for monitoring registered services, viewing service health status,
 * and managing service registry operations.</p>
 * 
 * @author Ikram Gafai
 * @version 3.0
 * @since 2024
 * @see org.springframework.boot.autoconfigure.SpringBootApplication
 * @see org.springframework.cloud.netflix.eureka.server.EnableEurekaServer
 */
@SpringBootApplication
@EnableEurekaServer
public class ServiceRegistryApplication {

    /**
     * Application bootstrap method that initializes and launches the Eureka Server.
     * 
     * <p>This method serves as the main entry point for the service registry application,
     * initializing the Spring Boot application context and starting the Eureka server
     * instance. Upon startup, the server begins accepting service registration requests
     * from client microservices and providing service discovery capabilities through
     * the Eureka service registry API.</p>
     * 
     * <p>Once the server is running, the Eureka management dashboard becomes accessible
     * at http://localhost:8761, providing administrators and developers with a web-based
     * interface for monitoring the health and availability of all registered microservices.</p>
     * 
     * @param args Command line arguments passed to the application during startup
     */
    public static void main(String[] args) {
        SpringApplication.run(ServiceRegistryApplication.class, args);
    }
}

