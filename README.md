# Wishlist Application

## Overview

This project is a Wishlist application divided into three modules: `domain`, `application`, and `infrastructure`. The
separation of the system into these modules follows the principles of Clean Architecture, ensuring a clear separation of
concerns and making the system more maintainable and testable.

### Modules

- **Domain**: Contains the core business logic and domain entities.
- **Application**: Contains the use cases and application services.
- **Infrastructure**: Contains the implementation details, such as controllers, repositories, and configuration.

## Running the Application

### Prerequisites

- Java 21
- Docker
- Docker Compose
- Gradle

### Running with Docker

- **Start the application using Docker Compose**:
    ```sh
    docker-compose up
    ```

### Running Locally

1. **Build the project**:
    ```sh
    ./gradlew :infrastructure:build
    ```

2. **Run the application**:
    ```sh
    ./gradlew :infrastructure:bootRun
    ```

## Running Tests

### Unit and Integration Tests

To run the unit and integration tests, use the following command:

```sh
./gradlew test
```

### Cucumber Tests

To run the Cucumber tests, use the following command:

```sh
./gradlew cucumberCli
```

## Accessing Swagger UI

After running the application, you can access the Swagger UI to explore and test the API endpoints.

- **Swagger UI**: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
