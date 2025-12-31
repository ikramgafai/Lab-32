package com.igafai.vehicle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * Main application class for the Vehicle Management Microservice.
 * 
 * <p>This Spring Boot application serves as the Vehicle Management Service in a
 * microservices architecture. It handles vehicle information management and integrates
 * with the Customer Management Service to provide comprehensive vehicle-customer
 * relationship data.</p>
 * 
 * <p>The application is configured as a Eureka client to register with the service
 * discovery server, enabling dynamic service location and load balancing. It uses
 * Spring Data JPA for database operations and REST templates for inter-service
 * communication.</p>
 * 
 * <p>Key features:
 * <ul>
 *   <li>Vehicle CRUD operations</li>
 *   <li>Integration with Customer Management Service</li>
 *   <li>Service discovery via Eureka</li>
 *   <li>RESTful API endpoints</li>
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
public class VehicleManagementApplication {

    /**
     * Main entry point for the Vehicle Management Service application.
     * 
     * <p>This method initializes the Spring Boot application context and starts
     * the embedded web server. The application will register with Eureka and
     * begin accepting HTTP requests on the configured port.</p>
     * 
     * @param applicationArguments Command line arguments passed to the application
     */
    public static void main(String[] applicationArguments) {
        SpringApplication.run(VehicleManagementApplication.class, applicationArguments);
    }

    /**
     * Configures and provides a RestTemplate bean for HTTP communication.
     * 
     * <p>This bean is used for inter-service communication, particularly for
     * calling the Customer Management Service API. The RestTemplate is configured
     * with connection and read timeouts to ensure reliable service communication
     * and prevent indefinite blocking.</p>
     * 
     * <p>Timeout configuration:
     * <ul>
     *   <li>Connection timeout: 5000 milliseconds (5 seconds)</li>
     *   <li>Read timeout: 5000 milliseconds (5 seconds)</li>
     * </ul></p>
     * 
     * @return Configured RestTemplate instance with timeout settings
     */
    @Bean
    public RestTemplate restTemplate() {
        RestTemplate httpRestClient = new RestTemplate();
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        
        requestFactory.setConnectTimeout(5000);
        requestFactory.setReadTimeout(5000);
        httpRestClient.setRequestFactory(requestFactory);

        return httpRestClient;
    }
}

