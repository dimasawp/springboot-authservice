# 🔐 SpringBoot AuthService — JWT-Based Authentication & Authorization

## 🚀 Description

A Spring Boot backend project for secure user authentication and role-based authorization using JWT.  
Designed to serve as a foundation for building secure REST APIs in Java applications.

**Portfolio Highlights:**

-   Implemented JWT authentication & role-based authorization
-   Clean project structure following Spring Boot best practices
-   Integrated PostgreSQL using Spring Data JPA

---

## ✨ Features

-   **Authentication**

    -   → Register new users with default `ROLE_USER` and login with JWT

-   **Authorization**
    -   → `/api/user/**` accessible by USER or ADMIN, `/api/admin/**` accessible by ADMIN only
    -   → Admin roles created via SQL

---

## 🛠 Tech Stack

-   **Runtime:** Java 21
-   **Framework:** Spring Boot 3.4.12
-   **Language:** Java
-   **Database:** PostgreSQL
-   **ORM:** Spring Data JPA (Hibernate)
-   **Authentication:** JJWT (JSON Web Token)
-   **API Style:** REST
-   **Validation:** Lombok
-   **Other Tools:** Spring Web, Spring Security

---

## ⚡ Quickstart

### **1. Clone Repository**

```bash
git clone https://github.com/<username>/authservice.git
cd authservice
```

### **2. Configure Database **

Create a PostgreSQL database:

```bash
CREATE DATABASE db_auth;
```

### **3. Update application.yaml**

```bash
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/db_auth
    username: postgres
    password: your_password

  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true

security:
  jwt:
    secret-key: your_secret_key
    expiration: 3600000
```

### 4. Initialize Roles (MANDATORY)

Roles must be inserted manually on the first setup:

```bash
INSERT INTO roles (name) VALUES ('ROLE_USER');
INSERT INTO roles (name) VALUES ('ROLE_ADMIN');
```

### 5. Register a User

```bash
POST /api/auth/register

Body:
{
  "name": "Dimas",
  "email": "dimas@mail.com",
  "password": "123456"
}

Response:
{ "token": "<jwt_token>" }
```

Note: Users are automatically assigned the ROLE_USER.

### 6. Login

```bash
POST /api/auth/login

Body:
{
  "email": "dimas@mail.com",
  "password": "123456"
}

Response:
{ "token": "<jwt_token>" }
```

### 7. Access User Endpoint

```bash
GET /api/user/profile

Header:
Authorization: Bearer <token>
```

Roles allowed: USER, ADMIN

### 8. Create Admin Manually (SQL)

```bash
UPDATE users
SET role_id = (SELECT id FROM roles WHERE name='ROLE_ADMIN')
WHERE email = 'admin@mail.com';
```

After updating, login → get admin JWT → access admin endpoints.

### 9. Access Admin Endpoint

```bash
GET /api/admin/dashboard

Header:
Authorization: Bearer <token_admin>
```

Accessible only by ROLE_ADMIN.

---

## 🧪 Testing

Use Postman or ThunderClient to test all available endpoints. Ensure the environment and configuration are correctly set up.

---

## 📁 Project Structure

```none
src/main/java/com/example/authservice/
│
├── config/
│   └── SecurityConfig.java       # Spring Security configuration
│
├── controller/
│   ├── AuthController.java       # Handles registration & login endpoints
│   ├── UserController.java       # Endpoints accessible by USER or ADMIN
│   └── AdminController.java      # Endpoints accessible by ADMIN only
│
├── service/
│   ├── AuthService.java          # Authentication business logic
│   └── JwtService.java           # JWT generation & validation
│
├── entity/
│   ├── User.java                 # User entity
│   └── Role.java                 # Role entity
│
├── repository/
│   ├── UserRepository.java       # User database operations
│   └── RoleRepository.java       # Role database operations
│
└── dto/
    ├── RegisterRequest.java      # Payload for registration
    ├── LoginRequest.java         # Payload for login
    └── AuthResponse.java         # Response containing JWT
```

Explanation:

-   Controller: Handles HTTP requests and responses
-   Service: Business logic for authentication and JWT handling
-   Repository: Database access layer for User and Role entities
-   Entity: Database models representing tables
-   DTO: Request and response payloads
-   Config: Security setup and configuration

---

## 🎯 Goals

-   Learn and implement JWT-based authentication in Spring Boot
-   Gain experience with role-based authorization
-   Build a clean, maintainable backend project for portfolio
-   Integrate PostgreSQL with Spring Data JPA
-   Follow standard controller-service-repository architecture
