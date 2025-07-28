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

```
## 📑 API Endpoints Overview

These are the main API endpoints exposed by the **Library Management System**.

| Method | Endpoint                             | Description                      | Access Role     |
|--------|--------------------------------------|----------------------------------|------------------|
| `POST` | `/auth/registernormaluser`           | Register a normal user           | Public           |
| `POST` | `/auth/login`                        | Login (Admin/User)               | Public           |
| `POST` | `/admin/registeradminuser`           | Create new admin user            | Admin            |
| `POST` | `/books/addbook`                     | Add a new book                   | Admin            |
| `GET`  | `/books/getallbooks`                 | Retrieve all books               | Authenticated    |
| `GET`  | `/books/getbookbyid/{id}`            | Get book details by ID           | Authenticated    |
| `POST` | `/issuerecords/issuethebook/{id}`    | Issue a book by ID               | Authenticated    |
| `POST` | `/issuerecords/returnthebook/{id}`   | Return a book by ID              | Authenticated    |

---



