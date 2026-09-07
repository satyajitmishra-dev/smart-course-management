# 🎓 Smart Course Management System

A production-ready Spring Boot REST API for managing **Students, Courses, Instructors, and Enrollments** with proper validation, exception handling, soft delete, DTO mapping, service-layer business rules, and enterprise-grade architecture.

---

# 📌 Project Overview

The Smart Course Management System helps educational organizations manage:

- Student registration
- Course management
- Instructor management
- Course enrollments
- Instructor-course assignment
- Enrollment status tracking

The project follows a clean layered architecture used in enterprise Spring Boot applications.

---

# 🚀 Tech Stack

| Technology | Version |
|------------|----------|
| Java | 25 |
| Spring Boot | 4.x |
| Spring Data JPA | Latest |
| Hibernate | Latest |
| MySQL | 8+ |
| Maven | Latest |
| Lombok | Latest |
| Jakarta Validation | Latest |

---

# 📂 Project Structure

```text
src/main/java
│
├── controller
│   ├── StudentController
│   ├── CourseController
│   ├── InstructorController
│   └── EnrollmentController
│
├── service
│   ├── StudentService
│   ├── CourseService
│   ├── InstructorService
│   └── EnrollmentService
│
├── repository
│   ├── StudentRepository
│   ├── CourseRepository
│   ├── InstructorRepository
│   └── EnrollmentRepository
│
├── dto
│   ├── request
│   └── response
│
├── mapper
│
├── entity
│
├── exception
│
└── config
```

---

# 🗄️ Database Design

## Student

```java
id
name
email
mobileNo
createdAt
updatedAt
deleted
```

---

## Course

```java
id
title
description
capacity
price
createdAt
updatedAt
deleted
instructor_id
```

---

## Instructor

```java
id
name
email
specialization
createdAt
updatedAt
deleted
```

---

## Enrollment

```java
id
student_id
course_id
enrollmentDate
status
```

---

# Enrollment Status

```java
ACTIVE
COMPLETED
CANCELLED
```

---

# 🔗 Entity Relationships

## Student ↔ Enrollment

```text
One Student
      ↓
Many Enrollments
```

---

## Course ↔ Enrollment

```text
One Course
      ↓
Many Enrollments
```

---

## Instructor ↔ Course

```text
One Instructor
      ↓
Many Courses
```

---

# 📚 Student APIs

Base URL

```http
/api/v1/students
```

---

## Create Student

### POST

```http
/api/v1/students
```

### Request

```json
{
  "name": "Satyajit Mishra",
  "email": "satyajit@gmail.com",
  "mobileNo": "9876543210"
}
```

### Response

```json
{
  "id": 1,
  "name": "Satyajit Mishra",
  "email": "satyajit@gmail.com",
  "mobileNo": "9876543210",
  "createdAt": "2026-09-07T12:30:00"
}
```

---

## Get Student

```http
GET /api/v1/students/1
```

---

## Get All Students

```http
GET /api/v1/students
```

---

## Update Student

```http
PUT /api/v1/students/1
```

---

## Delete Student

```http
PATCH /api/v1/students/1
```

Soft delete enabled.

---

# 📚 Course APIs

Base URL

```http
/api/v1/courses
```

---

## Create Course

```http
POST /api/v1/courses
```

### Request

```json
{
  "title": "Spring Boot Masterclass",
  "description": "Complete Spring Boot Training",
  "capacity": 50,
  "price": 4999.0
}
```

---

## Get Course

```http
GET /api/v1/courses/1
```

---

## Get All Courses

```http
GET /api/v1/courses
```

---

## Update Course

```http
PUT /api/v1/courses/1
```

---

## Delete Course

```http
PATCH /api/v1/courses/1
```

---

# 👨‍🏫 Instructor APIs

Base URL

```http
/api/v1/instructor
```

---

## Create Instructor

```http
POST /api/v1/instructor
```

### Request

```json
{
  "name": "John Smith",
  "email": "john@example.com",
  "specialization": "Spring Boot"
}
```

---

## Get Instructor

```http
GET /api/v1/instructor/1
```

---

## Get All Instructors

```http
GET /api/v1/instructor
```

---

## Update Instructor

```http
PUT /api/v1/instructor/1
```

---

## Delete Instructor

```http
PATCH /api/v1/instructor/1
```

---

# 🎯 Instructor Assignment APIs

Assign instructor to course.

---

## Assign Instructor

```http
PATCH /api/v1/courses/1/instructor/1
```

Meaning:

```text
Course ID = 1
Instructor ID = 1
```

### Success Response

```json
{
  "message": "Instructor assigned successfully"
}
```

---

# 🎓 Enrollment APIs

Base URL

```http
/api/v1/enrollments
```

---

## Create Enrollment

```http
POST /api/v1/enrollments
```

### Request

```json
{
  "studentId": 1,
  "courseId": 1
}
```

### Success Response

```json
{
  "id": 1,
  "studentName": "Satyajit Mishra",
  "courseTitle": "Spring Boot Masterclass",
  "enrollmentDate": "2026-09-07T13:08:24",
  "enrollmentStatus": "ACTIVE"
}
```

---

## Get Enrollment

```http
GET /api/v1/enrollments/1
```

---

## Get All Enrollments

```http
GET /api/v1/enrollments
```

---

## Cancel Enrollment

```http
PATCH /api/v1/enrollments/cancel?id=1
```

### Response

```json
{
  "message": "Enrollment CANCELED"
}
```

---

## Complete Enrollment

```http
PATCH /api/v1/enrollments/completed?id=1
```

### Response

```json
{
  "message": "Enrollment COMPLETED"
}
```

---

# 🛡️ Validations

## Student Validation

```java
@NotBlank
@Size(min = 3,max = 50)

@Email

@Pattern(regexp = "^[0-9]{10}$")
```

---

## Course Validation

```java
@NotBlank
@Size(min = 3,max = 100)

@NotNull
@Min(1)

@NotNull
@DecimalMin("0.0")
```

---

## Instructor Validation

```java
@NotBlank
@Size(min = 3)

@Email

@NotBlank
@Size(min = 3)
```

---

# ❌ Business Rule Exceptions

## Student

- Email already exists
- Student not found
- Duplicate update request

---

## Course

- Course not found
- Title already exists
- Duplicate update request

---

## Instructor

- Instructor not found
- Email already exists
- Duplicate update request

---

## Enrollment

- Student not found
- Course not found
- Already enrolled
- Course capacity reached
- Enrollment not found
- Enrollment already cancelled
- Enrollment already completed

---

# 📌 Sample Error Response

```json
{
  "timestamp": "2026-09-07T13:00:00",
  "status": 400,
  "error": "Bad Request",
  "message": "Validation Failed",
  "path": "/api/v1/students",
  "fieldErrors": {
    "name": "Name cannot be blank",
    "email": "Enter a valid email",
    "mobileNo": "Enter valid mobile number"
  }
}
```

---

# 🧪 Complete Testing Flow

## Step 1

Create Instructor

```http
POST /api/v1/instructor
```

---

## Step 2

Create Course

```http
POST /api/v1/courses
```

---

## Step 3

Assign Instructor To Course

```http
PATCH /api/v1/courses/1/instructor/1
```

---

## Step 4

Create Student

```http
POST /api/v1/students
```

---

## Step 5

Create Enrollment

```http
POST /api/v1/enrollments
```

---

## Step 6

Get Enrollment

```http
GET /api/v1/enrollments/1
```

---

## Step 7

Complete Enrollment

```http
PATCH /api/v1/enrollments/completed?id=1
```

---

## Step 8

Verify Enrollment

```http
GET /api/v1/enrollments/1
```

---

# 🔥 Enterprise Features Implemented

✅ DTO Pattern

✅ Service Layer Validation

✅ Custom Exceptions

✅ Global Exception Handler

✅ Soft Delete

✅ Repository Layer Abstraction

✅ Mapper Layer

✅ Entity Relationships

✅ Business Rule Validation

✅ Enrollment Workflow

✅ Instructor Assignment

✅ Clean Architecture

✅ RESTful API Design

---

# 👨‍💻 Author

**Satyajit Mishra**

Backend Developer | Java | Spring Boot | JPA | Hibernate

Building scalable enterprise applications with clean architecture and industry-standard practices.
