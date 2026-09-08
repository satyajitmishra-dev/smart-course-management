# 🎓 Smart Course Management System

<div align="center">

![Java](https://img.shields.io/badge/Java-25-orange?style=for-the-badge&logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-4.0-green?style=for-the-badge&logo=springboot)
![MySQL](https://img.shields.io/badge/MySQL-8.0-blue?style=for-the-badge&logo=mysql)
![Hibernate](https://img.shields.io/badge/Hibernate-ORM-brown?style=for-the-badge&logo=hibernate)
![Docker](https://img.shields.io/badge/Docker-Containerized-2496ED?style=for-the-badge&logo=docker)
![Maven](https://img.shields.io/badge/Maven-Build-red?style=for-the-badge&logo=apachemaven)

### Enterprise-Level Course Management Backend

A production-ready **Spring Boot REST API** for managing Courses, Students, Instructors, and Enrollments with clean architecture, validation, exception handling, soft delete, Docker support, and MySQL integration.

🚀 Built for learning real-world backend development concepts.

</div>

---

# 📌 Project Overview

Smart Course Management System is a backend application that simulates how modern EdTech platforms manage:

- Students
- Courses
- Instructors
- Course Enrollment
- Capacity Management
- Status Tracking
- Soft Delete Operations

The project follows enterprise backend development practices including:

✅ Layered Architecture

✅ DTO Validation

✅ Global Exception Handling

✅ Repository Pattern

✅ JPA Relationships

✅ Docker Containerization

✅ RESTful API Design

✅ Clean Code Principles

---

# 🏗️ System Architecture

```text
Client
   │
   ▼
Controller Layer
   │
   ▼
Service Layer
   │
   ▼
Repository Layer
   │
   ▼
MySQL Database
```

---

# 🛠 Tech Stack

| Technology | Version |
|------------|----------|
| Java | 25 |
| Spring Boot | 4.0 |
| Spring Data JPA | Latest |
| Hibernate ORM | Latest |
| MySQL | 8.0 |
| Maven | Latest |
| Docker | Latest |
| Lombok | Latest |

---

# 📂 Project Structure

```text
src
└── main
    ├── java
    │   └── me.satyajit
    │       ├── controller
    │       ├── service
    │       ├── repository
    │       ├── entity
    │       ├── dto
    │       ├── exception
    │       └── config
    │
    └── resources
        └── application.properties
```

---

# ✨ Features

## Course Management

- Create Course
- Get All Courses
- Get Course By ID
- Update Course
- Soft Delete Course
- Assign Instructor

---

## Student Management

- Create Student
- Get All Students
- Get Student By ID
- Update Student
- Soft Delete Student

---

## Instructor Management

- Create Instructor
- Get All Instructors
- Get Instructor By ID
- Update Instructor
- Soft Delete Instructor

---

## Enrollment Management

- Enroll Student
- Prevent Duplicate Enrollment
- Course Capacity Validation
- Complete Enrollment
- Cancel Enrollment
- Enrollment Status Tracking

---

# 🗄️ Database Design

## Student

```java
Student
├── id
├── name
├── email
├── mobileNo
└── isDeleted
```

---

## Course

```java
Course
├── id
├── title
├── description
├── capacity
├── price
├── instructor
└── isDeleted
```

---

## Instructor

```java
Instructor
├── id
├── name
├── email
├── specialization
├── createdAt
└── isDeleted
```

---

## Enrollment

```java
Enrollment
├── id
├── student
├── course
├── enrollmentDate
└── enrollmentStatus
```

---

# 🔗 Entity Relationships

```text
Instructor
     │
     │ One-To-Many
     ▼
 Course

Student
     │
     │ Many-To-One
     ▼
Enrollment
     ▲
     │
     │ Many-To-One
     │
Course
```

---

# 🌐 API Base URL

```http
http://localhost:8080/api/v1
```

---

# 📚 API Documentation

---

# Course APIs

| Method | Endpoint |
|----------|-----------|
| POST | /courses |
| GET | /courses |
| GET | /courses/{id} |
| PUT | /courses/{id} |
| PATCH | /courses/{id} |
| PATCH | /courses/{courseId}/assign-instructor/{instructorId} |

---

## Create Course

### Request

```json
{
  "title": "Spring Boot Masterclass",
  "description": "Complete Spring Boot course",
  "capacity": 50,
  "price": 4999
}
```

### Response

```json
{
  "id": 1,
  "title": "Spring Boot Masterclass",
  "description": "Complete Spring Boot course",
  "capacity": 50,
  "price": 4999
}
```

---

# Student APIs

| Method | Endpoint |
|----------|-----------|
| POST | /students |
| GET | /students |
| GET | /students/{id} |
| PUT | /students/{id} |
| PATCH | /students/{id} |

---

## Create Student

```json
{
  "name": "Satyajit Mishra",
  "email": "satyajit@gmail.com",
  "mobileNo": "9123456789"
}
```

---

# Instructor APIs

| Method | Endpoint |
|----------|-----------|
| POST | /instructor |
| GET | /instructor |
| GET | /instructor/{id} |
| PUT | /instructor/{id} |
| PATCH | /instructor/{id} |

---

## Create Instructor

```json
{
  "name": "John Smith",
  "email": "john@example.com",
  "specialization": "Spring Boot"
}
```

---

# Enrollment APIs

| Method | Endpoint |
|----------|-----------|
| POST | /enrollments |
| GET | /enrollments |
| GET | /enrollments/{id} |
| PATCH | /enrollments/cancel?id={id} |
| PATCH | /enrollments/completed?id={id} |

---

## Create Enrollment

```json
{
  "studentId": 1,
  "courseId": 1
}
```

---

# ⚙️ Validation Rules

## Student

| Validation | Rule |
|------------|------|
| Name | Required |
| Email | Unique |
| Mobile Number | Required |

---

## Instructor

| Validation | Rule |
|------------|------|
| Email | Unique |
| Name | Required |
| Specialization | Required |

---

## Course

| Validation | Rule |
|------------|------|
| Title | Required |
| Capacity | Must Be Positive |
| Price | Must Be Positive |

---

## Enrollment

| Validation | Rule |
|------------|------|
| Student Exists | Required |
| Course Exists | Required |
| Capacity Check | Required |
| Duplicate Enrollment | Not Allowed |

---

# ❌ Exception Handling

The application includes centralized exception handling.

Examples:

```json
{
  "timestamp": "2026-09-08T10:00:00",
  "status": 404,
  "message": "Student Not Found"
}
```

---

# 🧪 Test Flow

---

## 1. Create Instructors

```http
POST /api/v1/instructor
```

---

## 2. Create Courses

```http
POST /api/v1/courses
```

---

## 3. Assign Instructor

```http
PATCH /api/v1/courses/1/assign-instructor/1
```

---

## 4. Create Students

```http
POST /api/v1/students
```

---

## 5. Create Enrollment

```http
POST /api/v1/enrollments
```

---

## 6. Verify Data

```http
GET /api/v1/courses
GET /api/v1/students
GET /api/v1/instructor
GET /api/v1/enrollments
```

---

## 7. Complete Enrollment

```http
PATCH /api/v1/enrollments/completed?id=1
```

---

## 8. Cancel Enrollment

```http
PATCH /api/v1/enrollments/cancel?id=2
```

---

# 🐳 Docker Support

## Build Application

```bash
mvn clean package
```

---

## Build Docker Image

```bash
docker build -t smart-course-management .
```

---

## Run Container

```bash
docker run -p 8080:8080 smart-course-management
```

---

## Docker Compose

```bash
docker compose up --build
```

---

# 🚀 Future Enhancements

- JWT Authentication
- Spring Security
- Role Based Access Control
- Swagger/OpenAPI Documentation
- Redis Caching
- Email Notifications
- Payment Integration
- File Upload Support
- AWS Deployment
- CI/CD Pipeline
- Monitoring with Prometheus & Grafana
- Kubernetes Deployment

---

# 📈 Learning Outcomes

By building this project, developers will learn:

- Spring Boot Fundamentals
- REST API Design
- JPA Relationships
- Hibernate ORM
- Validation Techniques
- Exception Handling
- Layered Architecture
- Docker Containerization
- MySQL Integration
- Enterprise Backend Development

---

# 🔗 GitHub Repository

**Repository:**

👉 https://github.com/satyajitmishra-dev/smart-course-management

---

# 👨‍💻 Author

## Satyajit Mishra

Java Backend Developer

- Java
- Spring Boot
- Spring Data JPA
- Hibernate
- REST APIs
- Docker
- MySQL

### Connect With Me

GitHub:

https://github.com/satyajitmishra-dev

---

# ⭐ Support

If you found this project useful:

⭐ Star the repository

🍴 Fork the project

📝 Share your feedback

🚀 Build something amazing with it

---

<div align="center">

### Built with Spring Boot ❤️ by Satyajit Mishra

</div>
