# Pharmacy Management System - Backend

## 📌 Overview
Pharmacy Management System Backend is a Spring Boot application designed to manage pharmacy operations, focusing on user authentication, role-based access control, and secure token management. The system ensures scalability, maintainability, and security by integrating modern technologies such as Redis, Flyway, and JPA.

## ✅ Features
### User & Role Management
- Registration & authentication
- Role-based access control (multi-role per user)

### Token Management
- Secure JWT authentication
- Refresh token storage & revocation using Redis

### Auditing & Soft Deletion
- Track user activities & entity changes
- Hibernate-based soft deletion for entities

### Database Migration
- Versioned schema management with Flyway

## 🛠 Tech Stack
- **Java**: 21
- **Spring Boot**: 3.5.3
- **MySQL**: Relational database
- **Redis**: Token storage & caching
- **Flyway**: Database migration
- **MapStruct**: DTO ↔ Entity mapping
- **BCrypt**: Password hashing
- **Maven**: Dependency management

## 📂 Project Structure
```bash
src/
 ├── main/
 │   ├── java/com/mt/pharmacy_be/
 │   │   ├── config/          # Application & security configurations
 │   │   ├── controller/      # REST controllers
 │   │   ├── dto/             # Data Transfer Objects
 │   │   ├── entity/          # JPA entities
 │   │   ├── enums/           # Enum definitions
 │   │   ├── exception/       # Custom exception handling
 │   │   ├── repository/      # JPA repositories
 │   │   ├── service/         # Business logic layer
 │   │   └── util/            # Utility classes
 │   └── resources/
 │       ├── db/migration/    # Flyway migration scripts
 │       └── application.yml  # Application configuration
 └── test/                    # Unit & integration tests
```
## ⚙ Installation & Setup
### Prerequisites
- Java 21
- Maven 3.9+
- MySQL
- Redis

### Steps
1. Clone the repository
```bash
git clone https://github.com/phhtruc/pharmacy_be.git
cd pharmacy_be
```
Configure the database & Redis

yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/pharmacy_db
    username: root
    password: your_password
  redis:
    host: localhost
    port: 6379
Run database migrations

bash
mvn flyway:migrate
Build & run the application

bash
mvn clean install
mvn spring-boot:run
📌 API Documentation
Access Swagger UI at: http://localhost:8000/swagger-ui.html

Example Endpoints
Method	Endpoint	Description
POST	/api/v1/auth/register	Register a new user
POST	/api/v1/auth/login	Authenticate & get tokens
POST	/api/v1/auth/logout	Logout & revoke token
GET	/api/v1/users	Get all users
🗄 Database Migration
Migration scripts location: src/main/resources/db/migration

Example Script
sql
CREATE TABLE app_user (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    is_deleted BOOLEAN DEFAULT FALSE,
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
🤝 Contributing
Fork the repository

Create a feature branch

bash
git checkout -b feature/your-feature
Commit & push changes

bash
git commit -m "Add your feature"
git push origin feature/your-feature
Create a Pull Request

📄 License
MIT License
