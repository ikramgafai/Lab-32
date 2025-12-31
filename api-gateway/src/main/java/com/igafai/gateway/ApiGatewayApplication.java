package com.igafai.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.ReactiveDiscoveryClient;
import org.springframework.cloud.gateway.discovery.DiscoveryClientRouteDefinitionLocator;
import org.springframework.cloud.gateway.discovery.DiscoveryLocatorProperties;
import org.springframework.context.annotation.Bean;

/**
 * Main application entry point for the API Gateway microservice.
 * 
 * <p>This Spring Boot application implements the API Gateway pattern within the
 * microservices architecture, functioning as the centralized entry point for all
 * external client requests. The gateway dynamically routes incoming requests to
 * appropriate backend microservices using service discovery mechanisms and provides
 * sophisticated routing capabilities with load balancing support.</p>
 * 
 * <p>The gateway implementation provides comprehensive capabilities:
 * <ul>
 *   <li>Unified request routing to distributed microservices</li>
 *   <li>Automatic service discovery integration with Eureka server</li>
 *   <li>Intelligent load balancing across multiple service instances</li>
 *   <li>Request and response transformation capabilities</li>
 *   <li>Centralized cross-cutting concerns including logging, monitoring, and security</li>
 * </ul></p>
 * 
 * <p>The gateway is built on Spring Cloud Gateway technology, which leverages
 * Spring WebFlux for reactive, non-blocking request processing, enabling high
 * throughput and efficient resource utilization under concurrent load conditions.</p>
 * 
 * @author Ikram Gafai
 * @version 3.0
 * @since 2024
 * @see org.springframework.boot.autoconfigure.SpringBootApplication
 * @see org.springframework.cloud.gateway.discovery.DiscoveryClientRouteDefinitionLocator
 */
@SpringBootApplication
public class ApiGatewayApplication {

    /**
     * Application bootstrap method that initializes and launches the API Gateway.
     * 
     * <p>This method serves as the main entry point for the gateway application,
     * initializing the Spring Boot application context and starting the embedded
     * web server. Upon startup, the gateway automatically registers with the Eureka
     * discovery server and begins routing incoming requests to registered microservices
     * based on the configured service discovery settings.</p>
     * 
     * <p>The gateway server runs on port 8888 by default and maintains active
     * connections with services registered in the Eureka service registry, enabling
     * dynamic request routing without manual configuration updates.</p>
     * 
     * @param args Command line arguments passed to the application during startup
     */
    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
    }

    /**
     * Configures and registers a bean for dynamic route discovery via Eureka service registry.
     * 
     * <p>This Spring bean configuration enables automatic route generation for all services
     * registered with the Eureka discovery server, eliminating the necessity for manual
     * route configuration. Routes are dynamically created and maintained based on service
     * names discovered in the Eureka service registry, ensuring that the gateway can route
     * to all available microservices without requiring code changes or restarts.</p>
     * 
     * <p>The route locator performs the following operations automatically:
     * <ul>
     *   <li>Discovers all available services from the Eureka service registry</li>
     *   <li>Creates HTTP routes for each discovered service instance</li>
     *   <li>Dynamically updates routes when services are registered or deregistered</li>
     *   <li>Performs intelligent service instance selection and load balancing</li>
     * </ul></p>
     * 
     * @param discoveryClient Reactive discovery client for service registry interactions
     * @param locatorProps Configuration properties defining discovery locator behavior
     * @return Fully configured DiscoveryClientRouteDefinitionLocator instance for dynamic routing
     */
    @Bean
    DiscoveryClientRouteDefinitionLocator dynamicRouteLocator(
            ReactiveDiscoveryClient discoveryClient,
            DiscoveryLocatorProperties locatorProps) {
        return new DiscoveryClientRouteDefinitionLocator(discoveryClient, locatorProps);
    }
}

