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
