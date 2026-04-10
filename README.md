---

# 🛒 E-Commerce Platform (Learning Project)

> A fullstack e-commerce system built from **core web fundamentals (Servlet + Ajax)** and gradually evolved into **Spring Boot + React/Angular architecture**.

This project is designed as a **learning journey** to deeply understand how modern web applications work **from the lowest level (HTTP + Servlet)** to **enterprise-level architecture (Spring ecosystem)**.

The main goal is to build a **production-like e-commerce system** that demonstrates:

* Fullstack development capability
* System architecture thinking
* Clean API design
* Security awareness
* Scalable backend architecture

This repository is structured as a **professional software project**, including:

* system documentation
* architecture diagrams
* API specification
* database design
* logging standards

---

# 🎯 PROJECT GOALS

This project is built to demonstrate the following competencies required for **Java Fullstack Intern positions**:

### Backend Skills

* Java Servlet fundamentals
* REST API design
* JDBC data access
* Authentication & Authorization
* Session & Token management
* Web security (XSS, CSRF, SQL Injection)

### Frontend Skills

* HTML/CSS layout
* Vanilla JavaScript
* AJAX / Fetch API
* REST API consumption
* State management on client side

### Database Skills

* Relational database design
* Normalization
* Foreign key constraints
* Transaction management

### Software Engineering Skills

* Clean architecture
* API documentation
* Logging and monitoring
* Project documentation
* Git workflow

---

# 🧠 SYSTEM FEATURES

The system will implement **core features of a real e-commerce platform**.

## 👤 Authentication & Authorization

* User registration
* Login / logout
* Password hashing
* Session management
* Role-based access control

Roles:

* **Customer**
* **Admin**

---

## 🛍 Product Catalog

Users can browse and search products.

Features:

* Product listing
* Product detail page
* Product categories
* Search products
* Pagination
* Sorting (price, popularity, newest)

---

## 🛒 Shopping Cart

Each user has a personal cart.

Features:

* Add product to cart
* Update quantity
* Remove product
* Calculate total price
* Persist cart in database

---

## 💳 Checkout System

Users can place orders.

Features:

* Order summary
* Shipping information
* Order creation
* Order status tracking

Order statuses:

* Pending
* Paid
* Shipped
* Completed
* Cancelled

---

## 📦 Order Management

Users can view their purchase history.

Features:

* Order list
* Order detail
* Order status tracking

---

## 🛠 Admin Management Panel

Admin users can manage the platform.

Features:

* Manage products
* Manage categories
* Manage users
* Manage orders

Admin operations:

* Create product
* Update product
* Delete product
* Manage inventory

---

# 🏗 SYSTEM ARCHITECTURE

The project follows a **layered architecture** similar to enterprise Java applications.

```
Browser (HTML + JS)
        │
        ▼
Controller Layer (Servlet)
        │
        ▼
Service Layer (Business Logic)
        │
        ▼
Repository Layer (JDBC / DAO)
        │
        ▼
Database (MySQL)
```

Responsibilities:

| Layer      | Responsibility       |
| ---------- | -------------------- |
| Controller | Handle HTTP requests |
| Service    | Business logic       |
| Repository | Database access      |
| Database   | Persistent storage   |

---

# 📂 PROJECT STRUCTURE

```
/ecommerce-project
│
├── /docs
│
│   ├── /business-logic
│   │   ├── authentication-requirements.md
│   │   ├── product-management.md
│   │   ├── cart-workflow.md
│   │   └── order-processing.md
│   │
│   ├── /architecture
│   │   ├── system-architecture.mermaid
│   │   ├── login-sequence-diagram.mermaid
│   │   ├── add-to-cart-sequence.mermaid
│   │   └── checkout-sequence.mermaid
│   │
│   ├── /api
│   │   ├── auth-api.yaml
│   │   ├── product-api.yaml
│   │   ├── cart-api.yaml
│   │   └── order-api.yaml
│   │
│   ├── /database
│   │   ├── database-design.md
│   │   └── schema.sql
│   │
│   └── /logging
│       └── logging-standard.md
│── /logs
│── /docker
├── /src/main/java/org/phuchoang2005/ecommerce
│             |                      ├── /api
│             |                      ├── /controller
│             |                      ├── /dao
│             |                      ├── /dto
│             |                      ├── /enums
│             |                      ├── /exceptions
│             |                      ├── /filter
│             |                      ├── /repository
│             |                      ├── /service
│             |                      └── /util
│             ├── /resource
│             ├── /webapp
└── README.md
```

---

# 🗄 DATABASE DESIGN

Core entities:

```
USERS
PROFILES
CATEGORIES
PRODUCTS
ORDERS
ORDERS_DETAILS
```

---

# 🔐 SECURITY PRACTICES

The system will implement basic **web security mechanisms**.

### Password Security

* Password hashing (BCrypt)
* No plaintext password storage

### SQL Injection Prevention

* PreparedStatement

### XSS Prevention

* Output encoding

### CSRF Protection

* CSRF token

### Authentication

* Session-based authentication

---

# 📜 API DESIGN

The backend exposes **REST-style APIs**.

Data format: JSON

---

# 🧭 DOCUMENTATION READING PATH

To understand the system completely, read the documentation in the following order:

1️⃣ **Business Logic**

```
/docs/business-logic
```

Understand the functional requirements.

---

2️⃣ **Database Design**

```
/docs/database/database-design.md
```

Understand how the data model supports the business logic.

---

3️⃣ **Architecture**

```
/docs/architecture
```

Understand system structure and request flow.

---

4️⃣ **API Specification**

```
/docs/api
```

Understand how frontend communicates with backend.

---

5️⃣ **Logging Standards**

```
/docs/logging/logging-standard.md
```

Understand system observability.

---

# 🛠 TECHNOLOGY STACK

### Backend

* Java Servlet
* JDBC
* MySQL

### Frontend

* HTML
* CSS
* JavaScript
* Fetch API / Ajax

### Documentation

* Markdown
* OpenAPI 3.0
* Mermaid.js

---

# 🚀 FUTURE IMPROVEMENTS (SPRING BOOT MIGRATION)

After mastering the **Servlet implementation**, the system will be migrated to a modern stack:

### Backend

* Spring Boot
* Spring MVC
* Spring Data JPA
* Spring Security
* JWT authentication

### Frontend

* React or Angular
* REST API integration

### Infrastructure

* Docker
* CI/CD
* Cloud deployment

---

# 🧪 TESTING PLAN

Planned testing:

* Unit testing
* API testing
* Integration testing

Tools:

* JUnit
* Postman
* Mock data

---

# 💡 HOW TO UPDATE DOCUMENTATION

### Diagrams

Edit using:

Mermaid Live Editor

```
https://mermaid.live
```

---

### API Specification

Edit using:

Swagger Editor

```
https://editor.swagger.io
```

---

### Markdown

All documents follow **standard Markdown formatting**.

---

# 📌 IMPORTANT NOTE

This documentation is a **Living Document**.

Any change in:

* business logic
* database structure
* API contract
* system architecture

**must be reflected in the documentation.**

---

# 📈 PROJECT ROADMAP

```
Phase 1
Core Web Fundamentals
Servlet + JDBC + Ajax

Phase 2
Complete E-commerce Features

Phase 3
Security Improvements

Phase 4
Refactor to Spring Boot

Phase 5
React / Angular Frontend
```

---

# 👨‍💻 AUTHOR

Learning project for **Java Fullstack Developer Internship** preparation.

Focus:

* System thinking
* Backend architecture
* Clean API design
* Professional documentation

---