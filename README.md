# World of Gamecraft Accounts Service

This is the Accounts Service for the World of Gamecraft project. The Accounts Service is responsible for user registration and authentication. It provides API endpoints for user registration and login, and it issues JWT tokens for authenticated users.

## Technologies Used

- Java
- Spring Boot
- SQL Server
- RabbitMQ
- Docker

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

- Java 11 or higher
- Maven
- Docker
- SQL Server

### Installation

1. Clone the repository
```bash
git clone https://github.com/your_username_/gamecraft-accounts-service.git
```
2. Navigate to the project directory
```bash
cd gamecraft-accounts-service
```
3. Build the project with Maven
```bash
mvn clean install
```

### Running the Application

You can run the application using either Spring Boot or Maven. But if for some reason container RabbitMQ doesn't start
please restart it. (on M1 Mac it doesn't start automatically sometimes)

```bash

#### Spring Boot

```bash
./mvnw spring-boot:run
```

#### Maven

```bash
mvn exec:java
```

## API Endpoints

The Accounts Service exposes the following endpoints:

- `POST /api/users/register`: Register a new user.
- `POST /api/users/login`: Authenticate a user and return a JWT token.

## Testing

To run the unit tests for the service, use the following command:

```bash
mvn test
```

## Docker

The service can be provisioned using Docker Compose. The `docker-compose.yml` file in the root directory defines the necessary services.

To start the services, use the following command:

```bash
docker-compose up
```

## License

This project is licensed under the MIT License - see the `LICENSE.md` file for details.