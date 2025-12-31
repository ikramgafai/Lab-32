# Microservices Architecture - Vehicle and Customer Management System

A professional microservices-based application built with Spring Boot and Spring Cloud, featuring service discovery, API gateway, and distributed architecture.

## Author
**Ikram Gafai** - Full Stack Developer

## Architecture Overview

This project implements a microservices architecture with the following components:

### Services

1. **Service Registry** (Port: 8761)
   - Eureka Server for service registration and discovery
   - Enables dynamic service location and load balancing
   - Provides web dashboard for monitoring registered services

2. **API Gateway Service** (Port: 8888)
   - Central entry point for all client requests
   - Routes requests to appropriate microservices
   - Integrates with Eureka for dynamic routing
   - Built on Spring Cloud Gateway for reactive, non-blocking request handling

3. **Customer Management Service** (Port: 8081)
   - Manages customer information and profiles
   - Provides REST API for customer operations
   - MySQL database: `customerservicedb`
   - Handles customer CRUD operations

4. **Vehicle Management Service** (Port: 8082)
   - Manages vehicle information
   - Associates vehicles with customers
   - Communicates with Customer Management Service for complete data
   - MySQL database: `vehicleservicedb`
   - Provides enriched vehicle data with customer information

### Infrastructure

- **MySQL Database**: Persistent data storage for both services
- **Eureka Service Registry**: Service discovery and registration
- **Spring Cloud Gateway**: API Gateway for request routing

## Technology Stack

- **Framework**: Spring Boot 3.2.0
- **Language**: Java 17
- **Cloud**: Spring Cloud 2023.0.0
- **Database**: MySQL 8.0+
- **Service Discovery**: Netflix Eureka
- **API Gateway**: Spring Cloud Gateway
- **Build Tool**: Maven
- **Containerization**: Docker & Docker Compose
- **Testing**: JUnit 5, Spring Boot Test

## Project Structure

```
├── service-registry/          # Eureka server for service discovery
├── api-gateway-service/       # API Gateway for request routing
├── customer-management-service/ # Customer management microservice
├── vehicle-management-service/ # Vehicle management microservice
└── deployment/                # Docker Compose configuration
```

## Getting Started

### Prerequisites

- Java 17 or higher
- Maven 3.6+
- MySQL 8.0+
- Docker & Docker Compose (for containerized deployment)

### Local Development

1. **Start MySQL Database**
   ```bash
   # Ensure MySQL is running on localhost:3306
   # Create databases: customerservicedb and vehicleservicedb
   ```

2. **Start Service Registry**
   ```bash
   cd service-registry
   mvn spring-boot:run
   ```
   Access Eureka Dashboard at: `http://localhost:8761`

3. **Start API Gateway Service**
   ```bash
   cd api-gateway-service
   mvn spring-boot:run
   ```

4. **Start Customer Management Service**
   ```bash
   cd customer-management-service
   mvn spring-boot:run
   ```

5. **Start Vehicle Management Service**
   ```bash
   cd vehicle-management-service
   mvn spring-boot:run
   ```

### Docker Deployment

```bash
cd deployment
docker-compose up --build
```

## API Endpoints

### Customer Management Service
- `GET /api/customer` - Get all customers
- `GET /api/customer/{customerId}` - Get customer by ID
- `POST /api/customer` - Create new customer

### Vehicle Management Service
- `GET /api/vehicle` - Get all vehicles with customer information
- `GET /api/vehicle/{vehicleId}` - Get vehicle by ID with customer information

### Access via API Gateway
All services are accessible through the API Gateway at `http://localhost:8888/{SERVICE-NAME}/{endpoint}`

Example:
- `http://localhost:8888/CUSTOMER-SERVICE/api/customer`
- `http://localhost:8888/VEHICLE-SERVICE/api/vehicle`

## Service Discovery

Access the Eureka Dashboard at: `http://localhost:8761`

The dashboard shows all registered services and their health status.

## Database Management

The services use MySQL databases:
- Customer Service: `customerservicedb`
- Vehicle Service: `vehicleservicedb`

## Key Features

- **Microservices Architecture**: Independent, scalable services
- **Service Discovery**: Automatic service registration and discovery via Eureka
- **API Gateway**: Centralized routing and load balancing
- **RESTful APIs**: Clean, well-documented REST endpoints
- **Database Integration**: MySQL with JPA/Hibernate
- **Comprehensive Documentation**: JavaDoc comments throughout
- **Professional Code Quality**: Clean code with proper naming conventions
- **Docker Support**: Easy containerized deployment
- **Reactive Gateway**: Non-blocking request handling

## Development Practices

- Comprehensive JavaDoc documentation
- Professional naming conventions
- Clean code architecture
- Separation of concerns
- RESTful API design
- Microservices best practices
- Service-to-service communication
- Error handling and validation

## Code Quality

- All classes include comprehensive JavaDoc comments
- Variables and methods use descriptive names
- Code follows SOLID principles
- Proper exception handling
- Integration tests for all services

## License

This project is developed for educational and professional purposes.

## Contact

**Ikram Gafai**
- GitHub: [@ikramgafai](https://github.com/ikramgafai)

---

*Built with Spring Boot and Spring Cloud*
