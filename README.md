# CWM-spring-boot-store

A Spring Boot RESTful backend for an e-commerce store, built to practice and demonstrate core Java backend engineering skills: domain modeling with JPA, relational database management with MySQL, schema versioning with Flyway, and clean entity design with Lombok.

## Tech Stack

| Layer | Technology |
|---|---|
| Language | Java 25 |
| Framework | Spring Boot 4.0.5 |
| Persistence | Spring Data JPA + Hibernate |
| Database | MySQL |
| Migrations | Flyway 11.14.1 |
| Utilities | Lombok |
| Build | Maven |

## Domain Model

The application models a product store with the following core entities:

- **User** — registered customer with profile and address information
- **Address** — shipping/billing address linked to a User
- **Profile** — extended user details (one-to-one with User)
- **Tag** — labels for categorizing products
- **Category** — product groupings with cascade delete behavior
- **Product** — store catalog items with category and tag associations

## Features Demonstrated

- JPA entity relationships (one-to-one, one-to-many, many-to-many)
- Cascade delete strategies across domain relationships
- Spring Data JPA repositories (`CategoryRepository`, `ProductRepository`)
- Flyway database migration pipeline with MySQL
- Clean entity design using Lombok (`@Data`, `@Entity`, etc.)
- RESTful web layer via Spring Web

## Getting Started

### Prerequisites

- Java 25
- MySQL running locally on port 3306
- Maven

### Setup

1. Clone the repository:
```bash
git clone https://github.com/cperezba/CWM-spring-boot-store.git
cd CWM-spring-boot-store
```

2. Configure your database credentials in `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/store?createDatabaseIfNotExist=true
spring.datasource.username=your_username
spring.datasource.password=your_password
```

3. Run the application:
```bash
./mvnw spring-boot:run
```

Flyway will automatically apply migrations and create the schema on first run.

## Project Status

Actively in development. Current focus: expanding the REST API layer with controllers and service classes for product and category management.

## Author

Carlos Perez — [LinkedIn](https://www.linkedin.com/in/cperezbarr) | [Portfolio](https://react-portfolio-nu-five.vercel.app/)
