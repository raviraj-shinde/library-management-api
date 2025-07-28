# 📚 Library Management System

A robust **Library Management System** built using **Spring Boot**, featuring secure user authentication with **JWT**, **role-based authorization**, and dynamic role assignments for Admins and Users.

---

## 🛠️ Tech Stack

- **Spring Boot**
- **Spring Security** (JWT-based Authentication)
- **MySQL**
- **Spring Data JPA**
- **RESTful APIs**
- **Maven**

---

## 🔐 Authentication & Roles

- 🧑‍🎓 **Users** can register themselves.
- 🛡️ **Admins** are inserted manually (initially) and can create more Admins.
- 🎭 Roles: `ROLE_USER`, `ROLE_ADMIN`

---

## 📥 Initial Admin Setup (Manual SQL)

After running the application and database `library` is ready, insert your first Admin manually:
(Here password is - admin) you can generate any form  [BCrypt online tool](https://bcrypt-generator.com/) 
```sql
CREATE DATABASE library;
USE library;

-- Manually insert admin user (password is BCrypt hashed )
INSERT INTO users (username, email, password) 
VALUES ('admin', 'admin@gmail.com', '$2y$10$7qxJYHHDYENm2K1YMVW5iu5H2s5nBl0A4BQfYu5.ood/2CLuYF7Xm');

-- Assign roles to the user (replace 1 with correct user_id if needed)
INSERT INTO user_roles (user_id, role) 
VALUES (1, 'ROLE_USER'), (1, 'ROLE_ADMIN');
