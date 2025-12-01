# 🔐 AuthService — Spring Boot Authentication & Authorization

---

Sebuah service autentikasi dan otorisasi sederhana berbasis **Java 21**, **Spring Boot 3**, **PostgreSQL**, dan **JWT**.  
Proyek ini dibuat sebagai template reusable untuk aplikasi lainnya.

AuthService mendukung:

✅ Registrasi & Login  
✅ JWT Authentication  
✅ Role-Based Authorization (RBAC)  
✅ Admin-Only Endpoints  
✅ Auto-create tables via JPA

---

## ⚙️ **Tech Stack**

-   Java **21**
-   Spring Boot **3.4.12**
-   Spring Web
-   Spring Security
-   Spring Data JPA (Hibernate)
-   PostgreSQL
-   Lombok
-   JJWT (JSON Web Token)

---

## 🚀 **Fitur**

### **1. Register**

-   Pengguna baru otomatis mendapatkan role `ROLE_USER`

### **2. Login**

-   Mengembalikan JWT token

### **3. JWT Middleware**

-   Memvalidasi token
-   Menyuntikkan user ke `SecurityContext`

### **4. Authorization**

-   `/api/user/**` → USER atau ADMIN
-   `/api/admin/**` → ADMIN Only

### **5. Admin Creation**

-   Admin tidak bisa register
-   Admin harus dibuat manual via SQL (best practice)

---

## 📦 **Instalasi & Setup**

### **1. Clone Repository**

```bash
git clone https://github.com/<username>/authservice.git
cd authservice
```

### **2. Konfigurasi Database**

Buat database PostgreSQL:

```bash
CREATE DATABASE db_auth;
```

### **3. Isi file application.yaml**

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

## 🔑 **Inisialisasi Role (WAJIB)**

Tabel role harus diisi manual pertama kali.

```bash
INSERT INTO roles (name) VALUES ('ROLE_USER');
INSERT INTO roles (name) VALUES ('ROLE_ADMIN');
```

## 🙋‍♂️ **Registrasi User**

POST /api/auth/register

```bash
Body:
{
  "name": "Dimas",
  "email": "dimas@mail.com",
  "password": "123456"
}

Response:
{ "token": "<jwt_token>" }
```

Catatan: user selalu mendapatkan ROLE_USER.

## 🔐 **Login**

POST /api/auth/login

```bash
{
  "email": "dimas@mail.com",
  "password": "123456"
}

Response:

{ "token": "<jwt_token>" }
```

## 👤 **User Endpoint**

GET /api/user/profile

```bash
Header:
Authorization: Bearer <token>
```

Roles:

```bash
USER
ADMIN
```

## 👑 **Membuat Admin (Manual SQL)**

```bash
UPDATE users
SET role_id = (SELECT id FROM roles WHERE name='ROLE_ADMIN')
WHERE email = 'admin@mail.com';
```

Setelah itu login → dapat token admin → bisa akses endpoint admin.

## 🛠 **Admin Endpoint**

GET /api/admin/dashboard

```bash
Header:
Authorization: Bearer <token_admin>
```

Hanya role ROLE_ADMIN.

## 📁 **Struktur Proyek (Simplified)**

```bash
src/main/java/com/example/authservice/
│
├── config/
│   └── SecurityConfig.java
│
├── controller/
│   ├── AuthController.java
│   ├── UserController.java
│   └── AdminController.java
│
├── service/
│   ├── AuthService.java
│   └── JwtService.java
│
├── entity/
│   ├── User.java
│   └── Role.java
│
├── repository/
│   ├── UserRepository.java
│   └── RoleRepository.java
│
└── dto/
    ├── RegisterRequest.java
    ├── LoginRequest.java
    └── AuthResponse.java
```

## 🧪 **Cara Test dengan Postman / Thunder Client**

1. Register user → dapat token
2. Login user → dapat token
3. Coba /api/user/profile → berhasil
4. Update user jadi admin via SQL
5. Login admin → ambil token admin
6. Coba /api/admin/dashboard → berhasil

## 🎯 **Tujuan Proyek**

Proyek ini bertujuan untuk:

-   Belajar Spring Security modern
-   Menyiapkan reusable authentication service
-   Portofolio profesional Spring Boot
-   Fondasi untuk project tingkat lanjut
