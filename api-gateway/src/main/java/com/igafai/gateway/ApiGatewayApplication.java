package com.igafai.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.ReactiveDiscoveryClient;
import org.springframework.cloud.gateway.discovery.DiscoveryClientRouteDefinitionLocator;
import org.springframework.cloud.gateway.discovery.DiscoveryLocatorProperties;
import org.springframework.context.annotation.Bean;

/**
 * Main application class for the API Gateway service.
 * 
 * <p>This Spring Boot application serves as the API Gateway in the microservices
 * architecture, acting as the single entry point for all client requests. It routes
 * incoming requests to appropriate microservices using service discovery and provides
 * dynamic routing capabilities.</p>
 * 
 * <p>The gateway provides:
 * <ul>
 *   <li>Centralized request routing to backend microservices</li>
 *   <li>Dynamic service discovery integration with Eureka</li>
 *   <li>Load balancing across service instances</li>
 *   <li>Request/response transformation capabilities</li>
 *   <li>Cross-cutting concerns (logging, monitoring, security)</li>
 * </ul></p>
 * 
 * <p>The gateway uses Spring Cloud Gateway, which is built on Spring WebFlux and
 * provides reactive, non-blocking request handling for high performance.</p>
 * 
 * @author Ikram Gafai
 * @version 2.0
 * @since 2024
 * @see org.springframework.boot.autoconfigure.SpringBootApplication
 * @see org.springframework.cloud.gateway.discovery.DiscoveryClientRouteDefinitionLocator
 */
@SpringBootApplication
public class ApiGatewayApplication {

    /**
     * Main entry point for the API Gateway application.
     * 
     * <p>This method initializes the Spring Boot application context and starts
     * the gateway server. The gateway will register with Eureka and begin routing
     * requests to registered microservices based on the service discovery configuration.</p>
     * 
     * <p>The gateway runs on port 8888 by default and routes requests to services
     * registered with the Eureka discovery server.</p>
     * 
     * @param applicationArguments Command line arguments passed to the application
     */
    public static void main(String[] applicationArguments) {
        SpringApplication.run(ApiGatewayApplication.class, applicationArguments);
    }

    /**
     * Configures dynamic route discovery using Eureka service registry.
     * 
     * <p>This bean enables automatic route creation for services registered with Eureka,
     * eliminating the need for manual route configuration. Routes are dynamically created
     * based on service names registered in the Eureka server.</p>
     * 
     * <p>The locator automatically:
     * <ul>
     *   <li>Discovers services from Eureka registry</li>
     *   <li>Creates routes for each discovered service</li>
     *   <li>Updates routes when services are added or removed</li>
     *   <li>Handles service instance selection and load balancing</li>
     * </ul></p>
     * 
     * @param reactiveDiscoveryClient Reactive client for service discovery operations
     * @param discoveryLocatorProperties Configuration properties for the discovery locator
     * @return Configured DiscoveryClientRouteDefinitionLocator for dynamic routing
     */
    @Bean
    DiscoveryClientRouteDefinitionLocator dynamicRouteLocator(
            ReactiveDiscoveryClient reactiveDiscoveryClient,
            DiscoveryLocatorProperties discoveryLocatorProperties) {
        return new DiscoveryClientRouteDefinitionLocator(reactiveDiscoveryClient, discoveryLocatorProperties);
    }
}

