# WEEK 1 — DAY 1

# Project Planning — Enterprise Task Management Backend

You are building a real-world backend system using:

* [Spring Boot](https://spring.io/projects/spring-boot?utm_source=chatgpt.com)
* [PostgreSQL](https://www.postgresql.org/?utm_source=chatgpt.com)
* [Spring Security](https://spring.io/projects/spring-security?utm_source=chatgpt.com)
* [MapStruct](https://mapstruct.org/?utm_source=chatgpt.com)
* [Redis](https://redis.io/?utm_source=chatgpt.com)
* [Flyway](https://flywaydb.org/?utm_source=chatgpt.com)

Architecture Style:

* Clean Architecture
* Modular Monolith
* Enterprise-grade backend design

---

# Day 1 Goal

By the end of today, you should clearly understand:

1. What the application does
2. Core business modules
3. Clean Architecture layers
4. High-level system design
5. Folder/package planning
6. Database planning mindset
7. API planning mindset

---

# 1. Understand The Project

## Project Name

Enterprise Task Management Backend

## Real-World Purpose

A company uses this system to:

* Manage employees
* Create projects
* Assign tasks
* Track task status
* Manage teams
* Handle authentication & authorization
* Generate activity logs
* Support notifications

Think of it like:

* Jira
* Trello
* Asana
* Monday.com

but built with enterprise backend practices.

---

# 2. Define Core Features

## Initial MVP Features

### Authentication Module

Features:

* Login
* Logout
* JWT Authentication
* Refresh Token
* Role-based access

---

### User Module

Features:

* Create user
* Update user
* Deactivate user
* View profile
* Assign roles

---

### Project Module

Features:

* Create project
* Update project
* Add members
* View projects

---

### Task Module

Features:

* Create task
* Assign task
* Update task status
* Priority management
* Comments

---

### Notification Module

Features:

* Email notification (later)
* In-app notification
* Redis event handling

---

### Audit Module

Features:

* Activity logs
* Who updated what
* Timestamp tracking

---

# 3. Define Architecture Style

# Clean Architecture Overview

You will structure the backend into layers.

---

## Layer 1 — Domain Layer

Contains:

* Entities
* Business rules
* Domain interfaces

Examples:

* User
* Task
* Project
* Role

This layer should NOT depend on Spring Boot.

---

## Layer 2 — Application Layer

Contains:

* Use cases
* DTOs
* Service interfaces
* Business workflows

Examples:

* CreateTaskUseCase
* AssignUserUseCase

This is the brain of the application.

---

## Layer 3 — Infrastructure Layer

Contains:

* Database implementations
* Security configs
* Redis configs
* JPA repositories

Examples:

* UserJpaRepository
* JwtService
* RedisPublisher

---

## Layer 4 — Presentation Layer

Contains:

* REST Controllers
* Request/Response models
* Exception handlers

Examples:

* AuthController
* TaskController

---

# 4. Modular Monolith Design

Instead of one giant codebase, divide features into modules.

# Planned Modules

```text
auth
users
projects
tasks
notifications
audit
common
```

Each module should contain:

```text
domain
application
infrastructure
presentation
```

---

# 5. High-Level Folder Structure

```text
src/main/java/com/company/taskmanagement

├── common
│
├── auth
│   ├── domain
│   ├── application
│   ├── infrastructure
│   └── presentation
│
├── users
│   ├── domain
│   ├── application
│   ├── infrastructure
│   └── presentation
│
├── projects
├── tasks
├── notifications
├── audit
│
└── config
```

This is VERY important.

Enterprise companies expect organized structure.

---

# 6. Define Main Entities

## User

Fields:

* id
* firstName
* lastName
* email
* password
* status
* createdAt

---

## Role

Fields:

* id
* roleName

Examples:

* ADMIN
* MANAGER
* EMPLOYEE

---

## Project

Fields:

* id
* projectName
* description
* createdBy

---

## Task

Fields:

* id
* title
* description
* status
* priority
* assignedTo
* dueDate

---

# 7. Define Task Workflow

Example workflow:

```text
ADMIN creates project
↓
MANAGER creates tasks
↓
EMPLOYEE works on tasks
↓
Task status updated
↓
Audit log generated
↓
Notification triggered
```

This helps you understand enterprise business flow.

---

# 8. API Planning (High-Level)

## Auth APIs

```text
POST /api/v1/auth/login
POST /api/v1/auth/refresh
POST /api/v1/auth/logout
```

---

## User APIs

```text
GET    /api/v1/users
POST   /api/v1/users
PUT    /api/v1/users/{id}
DELETE /api/v1/users/{id}
```

---

## Task APIs

```text
GET    /api/v1/tasks
POST   /api/v1/tasks
PUT    /api/v1/tasks/{id}
PATCH  /api/v1/tasks/{id}/status
```

---

# 9. Decide Technical Standards

## Naming Conventions

### Classes

```text
PascalCase
```

Example:

```text
CreateTaskUseCase
```

---

### Methods

```text
camelCase
```

Example:

```text
createTask()
```

---

### Packages

```text
lowercase
```

Example:

```text
application
infrastructure
```
---

# Task 2 — Architecture Flow Diagram

## Clean Architecture Flow

```text
┌─────────────────────────────┐
│     PRESENTATION LAYER      │
│-----------------------------│
│ AuthController              │
│ UserController              │
│ TaskController              │
│ ProjectController           │
└──────────────┬──────────────┘
               │
               ▼
┌─────────────────────────────┐
│     APPLICATION LAYER       │
│-----------------------------│
│ CreateTaskUseCase           │
│ AssignTaskUseCase           │
│ CreateUserUseCase           │
│ LoginUseCase                │
│ DTOs                        │
│ Service Interfaces          │
└──────────────┬──────────────┘
               │
               ▼
┌─────────────────────────────┐
│        DOMAIN LAYER         │
│-----------------------------│
│ User                        │
│ Role                        │
│ Task                        │
│ Project                     │
│ Business Rules              │
│ Repository Interfaces       │
└──────────────┬──────────────┘
               │
               ▼
┌─────────────────────────────┐
│    INFRASTRUCTURE LAYER     │
│-----------------------------│
│ JPA Repositories            │
│ Security Config             │
│ JWT Service                 │
│ Redis Config                │
│ Flyway Migration            │
│ PostgreSQL Adapters         │
└──────────────┬──────────────┘
               │
               ▼
┌─────────────────────────────┐
│        DATABASE LAYER       │
│-----------------------------│
│ PostgreSQL                  │
│ Redis Cache                 │
└─────────────────────────────┘
```

---

# Example Request Flow

## Create Task Flow

```text
Client Request
    ↓
TaskController
    ↓
CreateTaskUseCase
    ↓
Task Domain Entity
    ↓
TaskRepository Interface
    ↓
TaskRepositoryImpl
    ↓
PostgreSQL Database
```

---

# Important Rule

## Dependency Direction

```text
Presentation → Application → Domain

Infrastructure → Domain
```

Domain should NEVER depend on:

* Spring Boot
* Database
* Controllers
* Frameworks

This is the core rule of Clean Architecture.

---

# Task 3 — Initial Module Planning

# Planned Modules

## 1. Auth Module

Purpose:

* Authentication
* Authorization
* JWT handling
* Refresh token management

### Responsibilities

* Login
* Logout
* Token validation
* Password encryption

### Main Components

```text
AuthController
LoginUseCase
JwtService
SecurityConfig
AuthRepository
```

---

# 2. Users Module

Purpose:

* User management

### Responsibilities

* Create users
* Update users
* Assign roles
* Activate/deactivate users

### Main Components

```text
UserController
CreateUserUseCase
UpdateUserUseCase
User
Role
UserRepository
```

---

# 3. Projects Module

Purpose:

* Project management

### Responsibilities

* Create projects
* Add team members
* Update project details

### Main Components

```text
ProjectController
CreateProjectUseCase
Project
ProjectRepository
```

---

# 4. Tasks Module

Purpose:

* Task management

### Responsibilities

* Create tasks
* Assign tasks
* Update task status
* Set priorities
* Track deadlines

### Main Components

```text
TaskController
CreateTaskUseCase
AssignTaskUseCase
Task
TaskRepository
```

---

# 5. Notifications Module

Purpose:

* System notifications

### Responsibilities

* In-app notifications
* Redis event handling
* Future email support

### Main Components

```text
NotificationService
RedisPublisher
RedisSubscriber
```

---

# 6. Audit Module

Purpose:

* Activity tracking

### Responsibilities

* Log user activities
* Track updates
* Store timestamps

### Main Components

```text
AuditService
AuditLog
AuditRepository
```

---

# 7. Common Module

Purpose:

* Shared utilities

### Responsibilities

* Exception handling
* Constants
* Utility classes
* Common DTOs

### Main Components

```text
GlobalExceptionHandler
ApiResponse
Constants
DateUtils
```

---

# Final Modular Structure

```text
src/main/java/com/company/taskmanagement

├── auth
├── users
├── projects
├── tasks
├── notifications
├── audit
├── common
└── config
```

---

# Internal Structure Of Each Module

Example:

```text
users
│
├── domain
├── application
├── infrastructure
└── presentation
```

---

# Task 4 — Main Entities & Relationships

# Entity Relationship Design

```text
User
 └── has many Roles

User
 └── assigned many Tasks

Project
 └── contains many Tasks

Project
 └── has many Users

Task
 └── belongs to one Project

Task
 └── assigned to one User

Task
 └── created by one User

AuditLog
 └── belongs to one User
```

---

# Core Entities

# 1. User Entity

## Fields

```text
id
firstName
lastName
email
password
status
createdAt
updatedAt
```

## Relationships

```text
User → Roles (Many-to-Many)
User → Tasks (One-to-Many)
```

---

# 2. Role Entity

## Fields

```text
id
name
description
```

## Example Roles

```text
ADMIN
MANAGER
EMPLOYEE
```

## Relationships

```text
Role → Users (Many-to-Many)
```

---

# 3. Project Entity

## Fields

```text
id
name
description
startDate
endDate
status
createdBy
createdAt
```

## Relationships

```text
Project → Tasks (One-to-Many)
Project → Users (Many-to-Many)
```

---

# 4. Task Entity

## Fields

```text
id
title
description
status
priority
dueDate
createdAt
updatedAt
```

## Example Status

```text
TODO
IN_PROGRESS
COMPLETED
BLOCKED
```

## Example Priority

```text
LOW
MEDIUM
HIGH
CRITICAL
```

## Relationships

```text
Task → Project (Many-to-One)
Task → User (Many-to-One)
```

---

# 5. AuditLog Entity

## Fields

```text
id
action
entityName
entityId
performedBy
performedAt
```

## Example Actions

```text
CREATE_TASK
UPDATE_PROJECT
DELETE_USER
LOGIN
```

---

# High-Level ER Diagram

```text
+--------+       +--------+
| Users  |-------| Roles  |
+--------+       +--------+
    |
    |
    ▼
+--------+       +-----------+
| Tasks  |-------| Projects  |
+--------+       +-----------+
    |
    ▼
+-----------+
| AuditLogs |
+-----------+
```

---



# WEEK 1 — DAY 2

# Clean Architecture Deep Dive

Today you will deeply understand:

* Domain Layer
* Application Layer
* Infrastructure Layer
* Presentation Layer
* Dependency Rule
* Real request flow
* Enterprise backend design principles

This day is VERY important.

Most developers write Spring Boot code without understanding architecture boundaries.

Today you’ll learn how senior backend engineers structure enterprise systems.

---

# 1. What Is Clean Architecture?

Clean Architecture is a design approach where:

* Business logic stays independent
* Frameworks stay replaceable
* Code becomes maintainable
* Modules become testable
* Dependencies point inward

Created/popularized by:
Robert C. Martin

---

# Main Goal

```text id="0iqmsp"
Protect business logic from frameworks.
```

Your business rules should survive even if:

* Spring Boot changes
* Database changes
* Redis changes
* REST changes
* UI changes

---

# 2. The Four Main Layers

# Architecture Overview

```text id="v5f8gw"
Presentation Layer
        ↓
Application Layer
        ↓
Domain Layer
        ↑
Infrastructure Layer
```

IMPORTANT:

Infrastructure supports Domain.

Domain does NOT depend on Infrastructure.

---

# 3. DOMAIN LAYER (Core Business Layer)

# Purpose

Contains:

* Business rules
* Core entities
* Domain interfaces
* Enterprise logic

This is the HEART of the application.

---

# Domain Layer Rules

## Domain MUST NOT know about:

* Spring Boot
* JPA
* Controllers
* REST APIs
* PostgreSQL
* Redis

NO framework dependency.

---

# Example Domain Entity

## Task.java

```java
public class Task {

    private Long id;
    private String title;
    private TaskStatus status;
    private Priority priority;

    public void markCompleted() {
        this.status = TaskStatus.COMPLETED;
    }
}
```

Notice:

* No `@Entity`
* No Spring annotations
* Pure business object

---

# Domain Enums

## TaskStatus.java

```java
public enum TaskStatus {
    TODO,
    IN_PROGRESS,
    COMPLETED
}
```

---

# Domain Repository Interface

## TaskRepository.java

```java
public interface TaskRepository {

    Task save(Task task);

    Optional<Task> findById(Long id);
}
```

IMPORTANT:

* Interface only
* No database logic here

---

# Why This Matters

Tomorrow if you move from:

* PostgreSQL → MongoDB
* JPA → JDBC
* REST → GraphQL

Your Domain stays untouched.

That is enterprise-level flexibility.

---

# 4. APPLICATION LAYER (Use Case Layer)

# Purpose

Contains:

* Use cases
* Application workflows
* DTOs
* Business orchestration

This layer coordinates business operations.

---

# Application Layer Responsibilities

Example:

```text id="wzduzt"
Create Task
↓
Validate Request
↓
Call Domain Logic
↓
Save Task
↓
Return Response
```

---

# Example Structure

```text id="yks86w"
tasks/application

├── dto
├── usecase
├── service
└── mapper
```

---

# Example DTO

## CreateTaskRequest.java

```java
public class CreateTaskRequest {

    private String title;
    private String description;
}
```

---

# Example Use Case

## CreateTaskUseCase.java

```java
public interface CreateTaskUseCase {

    TaskResponse createTask(CreateTaskRequest request);
}
```

---

# Use Case Implementation

## CreateTaskService.java

```java
public class CreateTaskService
        implements CreateTaskUseCase {

    private final TaskRepository taskRepository;

    public CreateTaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public TaskResponse createTask(CreateTaskRequest request) {

        Task task = new Task();
        task.setTitle(request.getTitle());

        Task savedTask = taskRepository.save(task);

        return new TaskResponse(savedTask.getId());
    }
}
```

---

# Key Understanding

Application Layer:

* Depends on Domain
* Uses repository interfaces
* Does NOT know implementation details

---

# 5. INFRASTRUCTURE LAYER

# Purpose

Contains:

* Database logic
* External integrations
* Security
* Redis
* JPA
* Flyway
* JWT

This layer IMPLEMENTS interfaces defined in Domain.

---

# Example Structure

```text id="r69l8r"
tasks/infrastructure

├── persistence
├── security
├── redis
├── config
└── mapper
```

---

# JPA Entity Example

## TaskJpaEntity.java

```java
@Entity
@Table(name = "tasks")
public class TaskJpaEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String title;
}
```

Notice:

* JPA annotations exist ONLY here

---

# Repository Implementation

## TaskRepositoryImpl.java

```java
@Repository
public class TaskRepositoryImpl
        implements TaskRepository {

    private final SpringDataTaskRepository repository;

    @Override
    public Task save(Task task) {

        TaskJpaEntity entity = mapToEntity(task);

        TaskJpaEntity saved = repository.save(entity);

        return mapToDomain(saved);
    }
}
```

---

# Infrastructure Contains

## Database

* JPA
* Hibernate
* PostgreSQL

## Security

* JWT
* Spring Security

## Cache

* Redis

## External Services

* Email
* Kafka
* APIs

---

# 6. PRESENTATION LAYER

# Purpose

Handles:

* HTTP requests
* REST APIs
* Validation
* Response formatting

This is the OUTER layer.

---

# Example Structure

```text id="kkr06g"
tasks/presentation

├── controller
├── request
├── response
└── advice
```

---

# Example Controller

## TaskController.java

```java
@RestController
@RequestMapping("/api/v1/tasks")
public class TaskController {

    private final CreateTaskUseCase createTaskUseCase;

    public TaskController(
            CreateTaskUseCase createTaskUseCase) {

        this.createTaskUseCase = createTaskUseCase;
    }

    @PostMapping
    public ResponseEntity<TaskResponse> createTask(
            @RequestBody CreateTaskRequest request) {

        return ResponseEntity.ok(
                createTaskUseCase.createTask(request)
        );
    }
}
```

---

# Presentation Layer Responsibilities

## SHOULD DO

* Accept requests
* Validate input
* Return responses

## SHOULD NOT DO

* Business logic
* Database logic

---

# 7. Dependency Rule (MOST IMPORTANT)

# Core Rule

```text id="4y7qbo"
Dependencies always point inward.
```

---

# Correct Dependency Flow

```text id="2c5j7m"
Presentation
    ↓
Application
    ↓
Domain

Infrastructure
    ↓
Domain
```

---

# WRONG Architecture

```text id="jzv3m2"
Domain → Spring Boot
Domain → PostgreSQL
Domain → Controllers
```

Never do this.

---

# 8. Real Enterprise Request Flow

# Example: Create Task API

---

## Step 1 — HTTP Request

```http
POST /api/v1/tasks
```

JSON:

```json
{
  "title": "Complete Backend API"
}
```

---

## Step 2 — Controller

```text id="ynqoq0"
TaskController
```

Receives request.

---

## Step 3 — Application Layer

```text id="w7dkvw"
CreateTaskService
```

Executes use case.

---

## Step 4 — Domain Layer

```text id="2pjmzt"
Task entity
```

Business rules execute.

---

## Step 5 — Infrastructure Layer

```text id="z2tb6t"
TaskRepositoryImpl
```

Saves into PostgreSQL.

---

## Step 6 — Response Returned

```json
{
  "id": 1,
  "message": "Task created successfully"
}
```

---

# 9. Why Companies Use This Architecture

# Benefits

## Maintainability

Large systems stay manageable.

---

## Testability

Business logic can be tested independently.

---

## Flexibility

Easy to replace technologies.

---

## Scalability

Supports large enterprise teams.

---

## Separation of Concerns

Each layer has one responsibility.

---

# 10. Common Mistakes Beginners Make

# Mistake 1

```text id="vb3g7e"
Putting business logic inside controllers
```

Wrong.

---

# Mistake 2

```text id="bxfq9u"
Using JPA entities everywhere
```

Wrong.

---

# Mistake 3

```text id="hmlt3q"
Domain depending on Spring Boot
```

Wrong.

---

# Mistake 4

```text id="93v7s3"
Huge service classes doing everything
```

Wrong.

---

# 11. Enterprise Folder Structure

```text id="d2e3zt"
tasks

├── domain
│   ├── entity
│   ├── repository
│   └── enums
│
├── application
│   ├── dto
│   ├── usecase
│   └── service
│
├── infrastructure
│   ├── persistence
│   ├── config
│   └── security
│
└── presentation
    ├── controller
    ├── request
    └── response
```

---

---

# 13. Senior-Level Understanding

# Clean Architecture Is NOT About:

* Creating many folders
* Complicating projects

It is about:

```text id="3lxnj8"
Protecting business logic.
```

That is the REAL purpose.


---


# WEEK 1 — DAY 3

# Project Setup — Spring Boot + PostgreSQL + Enterprise Package Structure

Today you will build the REAL project foundation.

By end of today you should have:

* Spring Boot project setup
* PostgreSQL connected
* Flyway configured
* Enterprise package structure created
* First application running
* Clean Architecture foundation ready

---

# 1. Today’s Goal

You are NOT just creating a Spring Boot app.

You are creating:

```text id="7ov3d5"
Enterprise-grade backend foundation
```

This setup should support:

* Large features
* Team collaboration
* Scalable architecture
* Future microservices migration

---

# 2. Technologies Setup Today

# Main Stack

* [Spring Boot](https://spring.io/projects/spring-boot?utm_source=chatgpt.com)
* [PostgreSQL](https://www.postgresql.org/?utm_source=chatgpt.com)
* [Flyway](https://flywaydb.org/?utm_source=chatgpt.com)
* [MapStruct](https://mapstruct.org/?utm_source=chatgpt.com)
* [Redis](https://redis.io/?utm_source=chatgpt.com)
* [Spring Security](https://spring.io/projects/spring-security?utm_source=chatgpt.com)
* Maven

---

# 3. Install Required Software

# Install Java

Recommended:

* Java 21 LTS

Verify:

```bash id="6kmljd"
java -version
```

---

# Install Maven

Verify:

```bash id="ewitgm"
mvn -version
```

---

# Install PostgreSQL

Verify:

```bash id="09olfe"
psql --version
```

---

# Install IDE

Recommended:

* IntelliJ IDEA

---

# 4. Create Spring Boot Project

Use:

[Spring Initializr](https://start.spring.io/?utm_source=chatgpt.com)

---

# Project Configuration

## Project

```text id="3a7x2o"
Maven
```

---

## Language

```text id="1h3z8s"
Java
```

---

## Spring Boot Version

Use latest stable 3.x version.

---

## Project Metadata

```text id="h72pr4"
Group:
com.company

Artifact:
taskmanagement

Name:
taskmanagement
```

---

# Add Dependencies

Select:

```text id="i7jvcd"
Spring Web
Spring Data JPA
PostgreSQL Driver
Spring Security
Validation
Flyway Migration
Lombok
```

Later:

* Redis
* MapStruct

---

# 5. Open Project

Open in IntelliJ.

Wait for:

* Maven indexing
* Dependency downloads

---

# 6. Enterprise Package Structure

Create:

```text id="dyjgjs"
com.company.taskmanagement
```

---

# Create Main Modules

```text id="1eb4vn"
auth
users
tasks
projects
notifications
audit
common
config
```

---

# Create Internal Clean Architecture Structure

Example:

```text id="8kijlc"
users

├── domain
│   ├── entity
│   ├── repository
│   └── enums
│
├── application
│   ├── dto
│   ├── usecase
│   └── service
│
├── infrastructure
│   ├── persistence
│   ├── mapper
│   └── config
│
└── presentation
    ├── controller
    ├── request
    └── response
```

Repeat for:

* tasks
* projects
* auth

---

# 7. Configure PostgreSQL

# Create Database

Open PostgreSQL.

Run:

```sql id="v0yccv"
CREATE DATABASE task_management;
```

---

# Database Naming Convention

Use:

```text id="8h1h5q"
snake_case
```

Good:

```text id="jlwm9n"
task_management
user_roles
project_tasks
```

Bad:

```text id="dxg0ep"
TaskManagement
UserRoles
```

---

# 8. Configure application.yml

Location:

```text id="ynm5zz"
src/main/resources/application.yml
```

---

# Basic Configuration

```yaml id="k2qjbd"
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/task_management
    username: postgres
    password: postgres

  jpa:
    hibernate:
      ddl-auto: validate

    properties:
      hibernate:
        format_sql: true

    show-sql: true

  flyway:
    enabled: true
    locations: classpath:db/migration

server:
  port: 8080
```

---

# Important Understanding

## Why NOT use:

```yaml id="1k4p5v"
ddl-auto: update
```

in enterprise systems?

Because:

* Dangerous in production
* Uncontrolled schema changes
* Flyway should manage database changes

---

# 9. Setup Flyway

# Create Migration Folder

```text id="7uv2r4"
src/main/resources/db/migration
```

---

# Create First Migration

## File Name

```text id="anr4c5"
V1__create_users_table.sql
```

IMPORTANT:
Flyway naming matters.

---

# First SQL Migration

```sql id="q1pk1y"
CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    first_name VARCHAR(100),
    last_name VARCHAR(100),
    email VARCHAR(150) UNIQUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

---

# 10. Create Main Application Class

## TaskManagementApplication.java

```java id="8qdwna"
package com.company.taskmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TaskManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(
                TaskManagementApplication.class,
                args
        );
    }
}
```

---

# 11. Run Application

Run:

```bash id="qj6kqy"
mvn spring-boot:run
```

OR

Run from IntelliJ.

---

# Expected Result

You should see:

```text id="oqxx3k"
Started TaskManagementApplication
```

AND

Flyway logs:

```text id="p0m2wb"
Successfully applied migration
```

---

# 12. Verify Database Table

Open PostgreSQL:

```sql id="9aj9f2"
SELECT * FROM users;
```

You should see empty table created successfully.

---

# 13. Add Health Check API

# Create Controller

## HealthController.java

```java id="5xgy58"
package com.company.taskmanagement.common;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    @GetMapping("/health")
    public String health() {
        return "Application Running";
    }
}
```

---

# Test API

Open:

```text id="m3xjlwm"
http://localhost:8080/health
```

Expected:

```text id="r63dyl"
Application Running
```

---

# 14. Understand What Happened Today

Today you setup:

# Spring Boot

Application framework.

---

# PostgreSQL

Enterprise database.

---

# Flyway

Database version control.

---

# Modular Monolith Structure

Enterprise project organization.

---

# Clean Architecture Foundation

Layer separation.

---

# 15. Enterprise-Level Best Practices

# DO

## Use Flyway migrations

Correct enterprise practice.

---

## Use modular packages

Supports scalability.

---

## Separate layers

Supports maintainability.

---

## Keep Domain clean

Core enterprise principle.

---

# DO NOT

## Put everything in:

```text id="h6g7de"
service
controller
entity
repository
```

packages only.

That becomes messy fast.

---

## Use gigantic classes

Avoid:

```text id="fjlwmz"
UserService with 5000 lines
```

---

## Mix business logic inside controllers

Wrong architecture.

---


# WEEK 1 — DAY 4

# PostgreSQL Basics — Enterprise Database Design

Today you will learn:

* PostgreSQL fundamentals
* Database design
* Tables
* Relationships
* Primary keys
* Foreign keys
* Constraints
* Indexes
* Normalization
* Enterprise schema design mindset

This is one of the MOST important backend skills.

A strong backend engineer must know:

```text id="f88q6o"
How to design good databases.
```

---

# 1. Why PostgreSQL?

You are using:

PostgreSQL

because it is:

* Enterprise-grade
* Reliable
* ACID compliant
* Highly scalable
* Widely used in companies

Companies using PostgreSQL:

* Apple
* Instagram
* Spotify

---

# 2. Important Database Concepts

# Table

Stores data in rows and columns.

Example:

```text id="z7ecbe"
users
tasks
projects
roles
```

---

# Row

Single record.

Example:

```text id="sax67c"
1 | Vidya | sagar@gmail.com
```

---

# Column

Data field.

Example:

```text id="jclm8u"
first_name
email
created_at
```

---

# Schema

Logical grouping of tables.

Default schema:

```text id="yjlwmm"
public
```

Later enterprise systems may use:

```text id="ppjlwm"
auth
audit
reporting
```

---

# 3. Primary Key (PK)

# Purpose

Uniquely identifies each row.

Example:

```sql id="6rjlwm"
id BIGSERIAL PRIMARY KEY
```

---

# Why Important?

Without primary key:

* Duplicate rows possible
* Hard to identify records
* Relationships break

---

# Enterprise Standard

Usually:

```sql id="jlwmk8"
BIGSERIAL
```

OR

```sql id="7z8jlwm"
UUID
```

---

# Example

```sql id="jlwm4r"
CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    first_name VARCHAR(100)
);
```

---

# 4. Foreign Key (FK)

# Purpose

Creates relationships between tables.

---

# Example

A task belongs to a user.

```text id="0pjlwm"
tasks.user_id → users.id
```

---

# Example SQL

```sql id="jlwm5g"
CREATE TABLE tasks (
    id BIGSERIAL PRIMARY KEY,

    title VARCHAR(200),

    assigned_user_id BIGINT,

    CONSTRAINT fk_task_user
        FOREIGN KEY (assigned_user_id)
        REFERENCES users(id)
);
```

---

# Enterprise Understanding

Foreign keys:

* Maintain data integrity
* Prevent invalid references
* Protect relationships

---

# 5. One-to-One Relationship

# Example

```text id="fjlwmq"
User → UserProfile
```

One user has one profile.

---

# Example

```sql id="4jlwm6"
CREATE TABLE user_profiles (
    id BIGSERIAL PRIMARY KEY,

    user_id BIGINT UNIQUE,

    bio TEXT,

    CONSTRAINT fk_profile_user
        FOREIGN KEY (user_id)
        REFERENCES users(id)
);
```

---

# 6. One-to-Many Relationship

# MOST COMMON Relationship

Example:

```text id="jlwm2t"
One Project → Many Tasks
```

---

# Example

```sql id="jlwm7y"
CREATE TABLE projects (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(200)
);

CREATE TABLE tasks (
    id BIGSERIAL PRIMARY KEY,

    title VARCHAR(200),

    project_id BIGINT,

    CONSTRAINT fk_task_project
        FOREIGN KEY (project_id)
        REFERENCES projects(id)
);
```

---

# 7. Many-to-Many Relationship

# Example

```text id="rjlwm8"
Users ↔ Roles
```

One user can have many roles.
One role can belong to many users.

---

# Solution

Use junction table.

---

# Example

```sql id="jlwm9v"
CREATE TABLE roles (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100)
);

CREATE TABLE user_roles (

    user_id BIGINT,
    role_id BIGINT,

    PRIMARY KEY (user_id, role_id),

    CONSTRAINT fk_user_roles_user
        FOREIGN KEY (user_id)
        REFERENCES users(id),

    CONSTRAINT fk_user_roles_role
        FOREIGN KEY (role_id)
        REFERENCES roles(id)
);
```

---

# 8. Constraints

# Purpose

Protect database integrity.

---

# NOT NULL

```sql id="qjlwm1"
email VARCHAR(150) NOT NULL
```

Field cannot be empty.

---

# UNIQUE

```sql id="8jlwm2"
email VARCHAR(150) UNIQUE
```

Prevents duplicates.

---

# CHECK

```sql id="jlwm3m"
CHECK (priority IN ('LOW', 'MEDIUM', 'HIGH'))
```

Restricts allowed values.

---

# DEFAULT

```sql id="jlwm4w"
created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
```

Sets default value.

---

# Enterprise Rule

Use constraints aggressively.

Database should protect data.

---

# 9. Indexes

# Purpose

Improve query performance.

---

# Example

```sql id="4jlwmx"
CREATE INDEX idx_users_email
ON users(email);
```

---

# Why Needed?

Without indexes:

* Slow searches
* Full table scans
* Performance issues

---

# Common Indexed Fields

```text id="0jlwmc"
email
status
created_at
foreign keys
```

---

# Important

Indexes improve:

* SELECT

But slightly slow:

* INSERT
* UPDATE

---

# 10. Normalization

# Purpose

Reduce duplicated data.

---

# BAD DESIGN

```text id="xjlwm9"
tasks table:

assigned_user_name
assigned_user_email
assigned_user_phone
```

Duplicate user data everywhere.

---

# GOOD DESIGN

```text id="7jlwmq"
tasks.user_id
```

Reference users table.

---

# Benefits

* Cleaner design
* Easier updates
* Better integrity
* Smaller database size

---

# 11. Enterprise Schema Design

# Recommended Tables

---

# users

```sql id="jlwm0j"
id
first_name
last_name
email
password
status
created_at
updated_at
```

---

# roles

```sql id="jlwm1f"
id
name
description
```

---

# user_roles

```sql id="7jlwm5"
user_id
role_id
```

---

# projects

```sql id="0jlwmk"
id
name
description
status
created_by
created_at
```

---

# tasks

```sql id="4jlwm1"
id
title
description
status
priority
project_id
assigned_user_id
created_at
```

---

# audit_logs

```sql id="jlwm5z"
id
action
entity_name
entity_id
performed_by
performed_at
```

---

# 12. Naming Conventions

# Tables

Use:

```text id="8jlwmh"
snake_case
plural
```

Good:

```text id="ljlwmx"
users
task_comments
audit_logs
```

---

# Constraints

Good naming:

```text id="vjlwmv"
fk_task_user
uk_users_email
```

---

# Indexes

```text id="jlwm4n"
idx_users_email
idx_tasks_status
```

---

# 13. Create Real Enterprise Migrations

# V2__create_roles_table.sql

```sql id="yjlwm7"
CREATE TABLE roles (
    id BIGSERIAL PRIMARY KEY,

    name VARCHAR(100) NOT NULL UNIQUE,

    description VARCHAR(255)
);
```

---

# V3__create_user_roles_table.sql

```sql id="djlwm8"
CREATE TABLE user_roles (

    user_id BIGINT NOT NULL,

    role_id BIGINT NOT NULL,

    PRIMARY KEY (user_id, role_id),

    CONSTRAINT fk_user_roles_user
        FOREIGN KEY (user_id)
        REFERENCES users(id),

    CONSTRAINT fk_user_roles_role
        FOREIGN KEY (role_id)
        REFERENCES roles(id)
);
```

---

# V4__create_projects_table.sql

```sql id="1jlwmz"
CREATE TABLE projects (

    id BIGSERIAL PRIMARY KEY,

    name VARCHAR(200) NOT NULL,

    description TEXT,

    status VARCHAR(50),

    created_by BIGINT,

    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_project_creator
        FOREIGN KEY (created_by)
        REFERENCES users(id)
);
```

---

# V5__create_tasks_table.sql

```sql id="jlwm2m"
CREATE TABLE tasks (

    id BIGSERIAL PRIMARY KEY,

    title VARCHAR(200) NOT NULL,

    description TEXT,

    status VARCHAR(50),

    priority VARCHAR(50),

    project_id BIGINT,

    assigned_user_id BIGINT,

    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_task_project
        FOREIGN KEY (project_id)
        REFERENCES projects(id),

    CONSTRAINT fk_task_user
        FOREIGN KEY (assigned_user_id)
        REFERENCES users(id)
);
```

---

# 14. Run Application Again

Run:

```bash id="4jlwm7"
mvn spring-boot:run
```

Flyway should apply all migrations automatically.

---

# 15. Verify Tables

Run:

```sql id="5jlwm6"
SELECT * FROM flyway_schema_history;
```

You should see:

* V1
* V2
* V3
* V4
* V5

---

# Verify Tables Exist

```sql id="6jlwm2"
\dt
```

Expected:

```text id="7jlwm1"
users
roles
user_roles
projects
tasks
flyway_schema_history
```

---

# 16. Today's Practical Tasks

# Task 1

Create:

* roles table
* projects table
* tasks table

using Flyway migrations.

---

# Task 2

Add:

* Primary keys
* Foreign keys
* Constraints

---

# Task 3

Create:

* user_roles many-to-many table

---

# Task 4

Add indexes:

* users.email
* tasks.status

---

# Task 5

Practice SQL queries:

```sql id="9jlwm0"
SELECT *
INSERT
UPDATE
DELETE
JOIN
```

---

# 17. Important Enterprise Understanding

# Database Design Is Permanent

Bad schema design becomes expensive later.

Companies spend YEARS fixing poor database designs.

So always think carefully about:

* Relationships
* Constraints
* Naming
* Indexes
* Scalability

---

# 18. End Of Day Outcome

After today you should understand:

* Relationships
* Foreign keys
* Constraints
* Indexes
* Enterprise schema design
* Flyway migrations
* PostgreSQL fundamentals

---

# Tomorrow (Day 5)

You will deeply learn:

* Flyway advanced concepts
* Migration strategies
* Rollback mindset
* Versioning
* Seed data
* Production database practices
* Enterprise migration workflows


---

This is a VERY common and important Flyway learning moment.

Your application fails because:

```text id="0g6h8f"
You modified V1 migration AFTER it was already executed.
```

Flyway protects database consistency using checksums.

---

# Why This Happened

Initially your `V1__create_users_table.sql` looked like:

```sql id="jlwmf1"
CREATE TABLE users (
   id BIGSERIAL PRIMARY KEY,
   first_name VARCHAR(100),
   ...
);
```

Flyway:

* executed it
* stored checksum in:

```text id="jlwmf2"
flyway_schema_history
```

Then later you changed V1:

```sql id="jlwmf3"
password VARCHAR(255),
status VARCHAR(50),
updated_at TIMESTAMP
```

Now:

* database version ≠ local file version

Flyway detects mismatch and stops startup for safety.

---

# Best Practice Understanding

# NEVER modify executed migrations in real projects.

Instead:

* create new migration
* alter schema safely

Example:

```text id="jlwmf4"
V6__add_user_status_column.sql
```

This is enterprise practice.

---

# BUT — Since You Are Still Learning

And this is early local development,
you can safely RESET database.

This is the cleanest option now.

---

# Recommended Solution (Best For You)

# Step 1 — Drop Database

Open PostgreSQL:

```sql id="jlwmf5"
DROP DATABASE task_management;
```

---

# Step 2 — Recreate Database

```sql id="jlwmf6"
CREATE DATABASE task_management;
```

---

# Step 3 — Restart Application

Run:

```bash id="jlwmf7"
mvn spring-boot:run
```

Now Flyway will:

* execute V1
* V2
* V3
* V4
* V5
* V6

from scratch.

---

# Expected Result

You should see:

```text id="jlwmf8"
Successfully applied 6 migrations
```

---

# Verify

Run:

```sql id="jlwmf9"
SELECT * FROM flyway_schema_history;
```

You should see:

* V1
* V2
* V3
* V4
* V5
* V6

---

# IMPORTANT ENTERPRISE UNDERSTANDING

# In Real Production

You SHOULD NOT:

* drop database
* modify old migrations

Instead:

* create new migration files

Example:

```text id="jlwmg0"
V7__add_updated_at_to_users.sql
```

---

# Alternative Solution (NOT Recommended For Learning Yet)

You may see online:

```bash id="jlwmg1"
flyway repair
```

Avoid this for now.

You should first properly understand migration history discipline.

---

# Final Recommendation

For your current learning stage:

```text id="jlwmg2"
Drop DB
Recreate DB
Run app again
```

This is the cleanest and safest approach right now.



---


# WEEK 1 — DAY 5

# Flyway Advanced Concepts — Enterprise Database Migration Strategy

Today you will deeply learn:

* Migration versioning
* Migration lifecycle
* Migration discipline
* Schema evolution
* Roll-forward strategy
* Seed data
* Production migration mindset
* Flyway best practices

Yesterday’s checksum issue was actually a PERFECT introduction to today’s topic.

You already experienced a real-world Flyway problem.

That is excellent learning.

---

# 1. What Is Flyway Really?

Flyway is NOT just:

```text id="jlwmg3"
SQL file runner
```

It is:

```text id="jlwmg4"
Database version control system
```

Similar to:

* Git for code
* Flyway for database schema

---

# 2. Why Companies Use Flyway

Without migration tools:

Problems:

* Different databases in different environments
* Manual SQL execution
* Missing columns
* Deployment failures
* Production inconsistencies

Flyway solves this.

---

# 3. How Flyway Works Internally

# Migration Flow

```text id="jlwmg5"
Application Starts
        ↓
Flyway checks flyway_schema_history
        ↓
Compares executed versions
        ↓
Runs new migrations
        ↓
Stores checksums
        ↓
Application Starts Successfully
```

---

# 4. The Most Important Flyway Rule

# NEVER MODIFY EXECUTED MIGRATIONS

Once migration is executed:

```text id="jlwmg6"
DO NOT EDIT IT
```

---

# Why?

Flyway stores:

* Version
* Filename
* Checksum
* Execution timestamp

Inside:

```text id="jlwmg7"
flyway_schema_history
```

---

# What Happens If You Modify?

You already saw this:

```text id="jlwmg8"
Migration checksum mismatch
```

This is Flyway protecting database integrity.

---

# 5. Correct Enterprise Migration Strategy

# WRONG

```text id="jlwmg9"
Edit V1__create_users_table.sql
```

---

# CORRECT

Create new migration:

```text id="jlwmh0"
V7__add_status_to_users.sql
```

---

# Example

## V7__add_status_to_users.sql

```sql id="jlwmh1"
ALTER TABLE users
ADD COLUMN status VARCHAR(50);
```

This is enterprise database evolution.

---

# 6. Migration Naming Convention

# Correct Format

```text id="jlwmh2"
V1__create_users_table.sql
V2__create_roles_table.sql
V3__create_tasks_table.sql
```

Pattern:

```text id="jlwmh3"
V<version>__<description>.sql
```

---

# Common Mistakes

# WRONG

```text id="jlwmh4"
V1_create_users.sql
```

(single underscore)

---

# WRONG

```text id="jlwmh5"
create_users.sql
```

(no version)

---

# 7. Migration Types

# Schema Migration

Creates/changes tables.

Example:

```sql id="jlwmh6"
ALTER TABLE users
ADD COLUMN phone VARCHAR(20);
```

---

# Data Migration

Updates existing data.

Example:

```sql id="jlwmh7"
UPDATE users
SET status = 'ACTIVE';
```

---

# Seed Migration

Adds initial static data.

Example:

* roles
* permissions
* default admin

---

# 8. Seed Data Strategy

# Example

## V8__insert_roles.sql

```sql id="jlwmh8"
INSERT INTO roles(name, description)
VALUES
('ADMIN', 'System Administrator'),
('MANAGER', 'Project Manager'),
('EMPLOYEE', 'Regular Employee');
```

---

# Why Seed Data Matters

Enterprise systems need:

* default roles
* permissions
* lookup values
* configuration data

---

# 9. Roll-Forward Strategy

# Enterprise Rule

Usually:

```text id="jlwmh9"
Never rollback production migrations.
```

Instead:

```text id="jlwmi0"
Create corrective migration.
```

---

# Example

Wrong migration:

```sql id="jlwmi1"
DROP COLUMN email;
```

DON'T modify old file.

Create:

```text id="jlwmi2"
V9__restore_email_column.sql
```

---

# Why?

Because production databases:

* already executed migrations
* may contain live data

---

# 10. Flyway Schema History Table

# Table

```text id="jlwmi3"
flyway_schema_history
```

Tracks:

* installed_rank
* version
* description
* checksum
* execution time
* success/failure

---

# Verify

Run:

```sql id="jlwmi4"
SELECT * FROM flyway_schema_history;
```

---

# 11. Real Enterprise Migration Workflow

# Developer Workflow

```text id="jlwmi5"
Create migration
        ↓
Commit to Git
        ↓
Push code
        ↓
CI/CD deploys
        ↓
Flyway executes automatically
```

---

# Benefits

* Consistent databases
* Versioned schema
* Team synchronization
* Safe deployments

---

# 12. Advanced Flyway Commands

# Clean

Deletes all objects.

```text id="jlwmi6"
flyway clean
```

DANGEROUS in production.

---

# Repair

Fixes checksum history.

```text id="jlwmi7"
flyway repair
```

Use carefully.

---

# Validate

Checks migration consistency.

```text id="jlwmi8"
flyway validate
```

---

# Info

Shows migration state.

```text id="jlwmi9"
flyway info
```

---

# 13. Enterprise Migration Best Practices

# DO

## Keep migrations small

Good:

```text id="jlwmj0"
One logical change per migration
```

---

## Use clear naming

Good:

```text id="jlwmj1"
V10__add_priority_to_tasks.sql
```

---

## Use constraints

Protect database integrity.

---

## Test migrations locally first

Always.

---

# DO NOT

## Modify executed migrations

Very important.

---

## Put huge unrelated changes in one file

Avoid:

```text id="jlwmj2"
V20__everything.sql
```

---

## Use Flyway clean in production

Dangerous.

---

# 14. Real Enterprise Examples

# Add New Column

```sql id="jlwmj3"
ALTER TABLE tasks
ADD COLUMN due_date DATE;
```

---

# Add Constraint

```sql id="jlwmj4"
ALTER TABLE users
ADD CONSTRAINT uk_users_phone UNIQUE(phone);
```

---

# Rename Column

```sql id="jlwmj5"
ALTER TABLE projects
RENAME COLUMN name TO project_name;
```

---

# Add Index

```sql id="jlwmj6"
CREATE INDEX idx_tasks_priority
ON tasks(priority);
```

---

# 15. Important Production Mindset

# Database Changes Are Sensitive

Bad migration can:

* break production
* lose data
* cause downtime
* corrupt systems

That is why enterprise teams:

* review migrations carefully
* test them
* use Flyway discipline

---

# 16. Practical Tasks Today

# Task 1

Create seed migration:

## V7__insert_roles.sql

Insert:

* ADMIN
* MANAGER
* EMPLOYEE

---

# Task 2

Create migration:

## V8__add_due_date_to_tasks.sql

Add:

```text id="jlwmj7"
due_date DATE
```

---

# Task 3

Create migration:

## V9__add_task_priority_index.sql

Create index for:

```text id="jlwmj8"
tasks.priority
```

---

# Task 4

Run application again.

Verify:

* Flyway executes new migrations
* No checksum issues

---

# Task 5

Check migration history:

```sql id="jlwmj9"
SELECT * FROM flyway_schema_history;
```

Understand:

* version
* checksum
* execution order

---

# 17. Important Enterprise Understanding

# Flyway Encourages Discipline

It forces developers to:

* think carefully
* version database changes
* avoid dangerous modifications

This is why enterprise companies love Flyway.

---

# 18. End Of Day Outcome

After today you should understand:

* Migration lifecycle
* Versioning discipline
* Why checksum exists
* Roll-forward strategy
* Seed data strategy
* Production migration mindset

---

# WEEK 1 — DAY 6

# User Module Design — RBAC, Authentication & Enterprise User Management

Today you will learn:

* User module design
* RBAC (Role-Based Access Control)
* Authentication concepts
* Authorization concepts
* User entity modeling
* Role relationships
* Enterprise security mindset
* Spring Security foundations

This is one of the MOST important backend topics.

Almost every enterprise application has:

* users
* roles
* permissions
* authentication
* authorization

---

# 1. What Is Authentication?

# Authentication = Identity Verification

Question:

```text id="jlwml6"
Who are you?
```

Example:

* Login with email/password
* JWT token validation

---

# Real Example

```text id="jlwml7"
Email: vidya@gmail.com
Password: ********
```

System verifies:

* user exists
* password correct

Then:

```text id="jlwml8"
Authenticated
```

---

# 2. What Is Authorization?

# Authorization = Access Control

Question:

```text id="jlwml9"
What are you allowed to do?
```

Example:

| Role     | Permissions                |
| -------- | -------------------------- |
| ADMIN    | Manage everything          |
| MANAGER  | Manage projects/tasks      |
| EMPLOYEE | View/update assigned tasks |

---

# Authentication vs Authorization

| Authentication | Authorization    |
| -------------- | ---------------- |
| Who are you?   | What can you do? |
| Login          | Permissions      |
| Identity       | Access control   |

Very important distinction.

---

# 3. RBAC (Role-Based Access Control)

# Enterprise Standard Access Model

Users get:

```text id="jlwmma"
Roles
```

Roles contain:

```text id="jlwmmb"
Permissions
```

---

# Example

```text id="jlwmmc"
Vidya
   ↓
MANAGER role
   ↓
Can create tasks
Can assign tasks
Can view projects
```

---

# Current Roles In Your System

From your migration:

```text id="jlwmmd"
ADMIN
MANAGER
EMPLOYEE
```

Good enterprise starting point.

---

# 4. User Module Responsibilities

# User Module Should Handle

* User creation
* User update
* User activation/deactivation
* Role assignment
* User retrieval
* Password storage
* Authentication preparation

---

# 5. Enterprise User Entity Design

# Recommended User Fields

```text id="jlwmme"
id
first_name
last_name
email
password
status
created_at
updated_at
```

You already designed this correctly.

Excellent.

---

# Why Each Field Matters

# email

Used for:

* login
* unique identity

---

# password

Stores:

* encrypted password

NEVER store plain passwords.

---

# status

Controls:

* active users
* locked users
* disabled users

---

# created_at / updated_at

Used for:

* auditing
* tracking
* debugging

---

# 6. User Status Design

# Recommended Status Values

```text id="jlwmmf"
ACTIVE
INACTIVE
LOCKED
```

---

# Enterprise Meaning

## ACTIVE

Can use system.

---

## INACTIVE

Temporarily disabled.

---

## LOCKED

Security lock.

Example:

* too many failed logins

---

# 7. Role Entity Design

# roles Table

```text id="jlwmmg"
id
name
description
```

Good design.

---

# Example Roles

```text id="jlwmmh"
ADMIN
MANAGER
EMPLOYEE
```

---

# 8. User ↔ Role Relationship

# Many-to-Many

Why?

One user can have:

* ADMIN
* MANAGER

One role can belong to:

* many users

---

# Your Existing Design

```text id="jlwmmi"
users
roles
user_roles
```

This is correct enterprise design.

---

# Relationship Flow

```text id="jlwmmj"
users
   ↓
user_roles
   ↓
roles
```

---

# 9. Enterprise Authorization Examples

# ADMIN

Can:

* manage users
* delete projects
* assign roles

---

# MANAGER

Can:

* create tasks
* assign tasks
* manage projects

---

# EMPLOYEE

Can:

* view assigned tasks
* update own task status

---

# 10. Password Security (VERY IMPORTANT)

# NEVER Store Plain Passwords

WRONG:

```text id="jlwmmk"
password = admin123
```

---

# ALWAYS Encrypt Passwords

Use:

```text id="jlwmml"
BCrypt
```

Example encrypted password:

```text id="jlwmmm"
$2a$10$...
```

---

# Why?

If database leaks:

* plain passwords become disaster

Encrypted passwords protect users.

---

# 11. Spring Security Authentication Flow

# Login Flow

```text id="jlwmmn"
User sends email/password
        ↓
Spring Security validates user
        ↓
Password checked
        ↓
JWT token generated
        ↓
User authenticated
```

---

# 12. Future JWT Flow

Later you will build:

```text id="jlwmmo"
POST /api/v1/auth/login
```

Returns:

```json id="jlwmmp"
{
  "accessToken": "jwt-token"
}
```

Then frontend sends:

```http id="jlwmmq"
Authorization: Bearer jwt-token
```

---

# 13. Enterprise User Module Structure

# Recommended Structure

```text id="jlwmmr"
users

├── domain
│   ├── entity
│   ├── repository
│   └── enums
│
├── application
│   ├── dto
│   ├── usecase
│   └── service
│
├── infrastructure
│   ├── persistence
│   ├── mapper
│   └── security
│
└── presentation
    ├── controller
    ├── request
    └── response
```

---

# 14. User Domain Entity

# User.java

```java id="jlwmms"
public class User {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private UserStatus status;
}
```

---

# UserStatus Enum

## UserStatus.java

```java id="jlwmmt"
public enum UserStatus {
    ACTIVE,
    INACTIVE,
    LOCKED
}
```

---

# 15. Role Domain Entity

## Role.java

```java id="jlwmmu"
public class Role {

    private Long id;

    private String name;

    private String description;
}
```

---

# 16. User Repository Design

## UserRepository.java

```java id="jlwmmv"
public interface UserRepository {

    User save(User user);

    Optional<User> findById(Long id);

    Optional<User> findByEmail(String email);

    List<User> findAll();
}
```

---

# Why findByEmail Is Important?

Authentication uses:

```text id="jlwmmw"
email lookup
```

frequently.

That’s why:

```text id="jlwmmx"
users.email
```

was indexed/unique.

---

# 17. Create User Use Case

# Responsibilities

```text id="jlwmmy"
Validate request
Check email uniqueness
Encrypt password
Save user
Assign default role
Return response
```

---

# Example Flow

```text id="jlwmmz"
CreateUserController
        ↓
CreateUserUseCase
        ↓
Validate email
        ↓
Encrypt password
        ↓
Save user
        ↓
Assign EMPLOYEE role
```

---

# 18. Enterprise Security Best Practices

# DO

## Encrypt passwords

Always.

---

## Use RBAC

Enterprise standard.

---

## Validate user status

Locked users should not login.

---

## Use unique emails

Correct.

---

## Use JWT tokens

Modern stateless authentication.

---

# DO NOT

## Store plain passwords

Never.

---

## Hardcode credentials

Dangerous.

---

## Put security logic in controllers

Wrong layer.

---

# 19. Practical Tasks Today

# Task 1

Create:

* User domain entity
* Role domain entity
* UserStatus enum

---

# Task 2

Create:

* UserRepository interface

---

# Task 3

Design:

* CreateUserUseCase flow

Write steps clearly.

---

# Task 4

Draw RBAC flow:

```text id="jlwmn0"
User
  ↓
Roles
  ↓
Permissions
```

---

# Task 5

Understand authentication flow:

```text id="jlwmn1"
Login
↓
Validate credentials
↓
Generate JWT
↓
Authorize requests
```

---

# 20. Important Enterprise Understanding

# Security Is NOT Just Login

Enterprise security includes:

* authentication
* authorization
* encryption
* token management
* access control
* audit tracking

---

# 21. End Of Day Outcome

After today you should understand:

* RBAC
* Authentication vs authorization
* Enterprise user modeling
* Password security
* JWT flow
* User-role relationships
* Security architecture mindset

---

Your Task 3 design is very good overall.

You are already thinking in:

* DTO flow
* mapper layer
* repository usage
* validation flow
* service separation

Those are strong enterprise backend habits.

Small correction:

In controller you mentioned:

```text id="jlwmn2"
@GetMapping("api/v1/users")
```

For create user, it should be:

```java id="jlwmn3"
@PostMapping
```

because creating resource = POST.

---

# Task 4 — RBAC Flow Solution

# RBAC = Role-Based Access Control

# High-Level Flow

```text id="jlwmn4"
User
  ↓
Roles
  ↓
Permissions
```

---

# Enterprise RBAC Flow

```text id="jlwmn5"
Vidya Sagar
     ↓
MANAGER Role
     ↓
Permissions:
- CREATE_TASK
- ASSIGN_TASK
- UPDATE_PROJECT
```

---

# Real System Flow

```text id="jlwmn6"
User logs in
      ↓
System loads user
      ↓
System loads roles
      ↓
System checks permissions
      ↓
Allow or deny request
```

---

# Example Flow

# ADMIN

```text id="jlwmn7"
ADMIN
   ↓
Can:
- Create users
- Delete users
- Manage roles
- Access all APIs
```

---

# MANAGER

```text id="jlwmn8"
MANAGER
   ↓
Can:
- Create tasks
- Assign tasks
- Manage projects
```

---

# EMPLOYEE

```text id="jlwmn9"
EMPLOYEE
   ↓
Can:
- View assigned tasks
- Update own task status
```

---

# Database Relationship Flow

```text id="jlwmoa"
users
   ↓
user_roles
   ↓
roles
```

---

# Future Enterprise Permission Model

Later systems may add:

```text id="jlwmob"
roles
   ↓
role_permissions
   ↓
permissions
```

Example:

```text id="jlwmoc"
CREATE_TASK
DELETE_TASK
VIEW_REPORTS
MANAGE_USERS
```

This becomes:

```text id="jlwmod"
User
 ↓
Role
 ↓
Permission
 ↓
API Access
```

---

# Spring Security Authorization Flow

Later you’ll implement:

```java id="jlwmoe"
@PreAuthorize("hasRole('ADMIN')")
```

Example:

```java id="jlwmof"
@PreAuthorize("hasRole('MANAGER')")
@PostMapping
public TaskResponse createTask() {
}
```

---

# Enterprise Understanding

RBAC helps:

* centralized access control
* security management
* scalable permissions
* enterprise governance

Almost every large application uses RBAC.

---

# Task 5 — Authentication Flow Solution

# Authentication Flow

```text id="jlwmog"
Login
↓
Validate Credentials
↓
Generate JWT Token
↓
Return Token
↓
Frontend Stores Token
↓
Frontend Sends Token
↓
Backend Validates Token
↓
Authorize Request
```

---

# Detailed Enterprise Authentication Flow

# Step 1 — Login Request

Client sends:

```http id="jlwmoh"
POST /api/v1/auth/login
```

Request:

```json id="jlwmoi"
{
  "email": "vidya@gmail.com",
  "password": "password123"
}
```

---

# Step 2 — Backend Validates User

System:

* finds user by email
* checks encrypted password
* checks user status

---

# Step 3 — Password Verification

Using:

```text id="jlwmoj"
BCryptPasswordEncoder
```

Example:

```java id="jlwmok"
passwordEncoder.matches(
    rawPassword,
    encryptedPassword
);
```

---

# Step 4 — Generate JWT Token

Backend generates:

```text id="jlwmol"
JWT Access Token
```

Example:

```text id="jlwmom"
eyJhbGciOiJIUzI1Ni...
```

---

# Step 5 — Return Token

Response:

```json id="jlwmon"
{
  "accessToken": "jwt-token"
}
```

---

# Step 6 — Frontend Stores Token

Usually:

* localStorage
* sessionStorage
* secure cookie

---

# Step 7 — Frontend Sends Token

Every request:

```http id="jlwmoo"
Authorization: Bearer jwt-token
```

---

# Step 8 — Spring Security Filter Validates JWT

Backend:

* validates token signature
* checks expiration
* extracts user info
* loads roles

---

# Step 9 — Authorization Happens

Example:

```text id="jlwmop"
MANAGER can create tasks
EMPLOYEE cannot delete users
```

---

# Final Enterprise Authentication Architecture

```text id="jlwmoq"
Client
  ↓
AuthController
  ↓
AuthenticationService
  ↓
UserRepository
  ↓
Password Validation
  ↓
JWT Generation
  ↓
Spring Security Context
  ↓
Authorized APIs
```

---

# Important Enterprise Security Concepts

# Authentication

```text id="jlwmor"
Who are you?
```

---

# Authorization

```text id="jlwmos"
What can you do?
```

---

# JWT

```text id="jlwmot"
Stateless authentication token
```

---

# BCrypt

```text id="jlwmou"
Password encryption
```

---

# Security Context

Stores:

* authenticated user
* roles
* authorities

during request lifecycle.

---

# Future APIs You’ll Build

## Auth APIs

```http id="jlwmov"
POST /api/v1/auth/login
POST /api/v1/auth/refresh
POST /api/v1/auth/logout
```

---

# Your Current Understanding Is Strong

You are already thinking in:

* DTOs
* layers
* validation
* repositories
* RBAC
* JWT flow
* service orchestration

That is excellent backend engineering progression for Week 1.
---

# Review + Refactor + Enterprise Cleanup

| beginners                 | Senior engineers regularly                |
|---------------------------| -------------------------- |
| only build features       | review architecture|
| never refactor	           | clean code |
| ignore architecture quality | improve naming |
|                           | reduce technical debt |
|                           | validate structure |

You are doing well and your Week 1 foundation is strong enough to continue.

You now understand:

* Clean Architecture
* Modular Monolith
* Flyway discipline
* PostgreSQL relationships
* RBAC basics
* User module design
* Migration strategy
* Enterprise backend structure

So yes — let’s start Day 7.

---

# WEEK 1 — DAY 7

# Review + Refactor + Enterprise Cleanup

Today is extremely important.

Most beginners:

* only build features
* never refactor
* ignore architecture quality

Senior engineers regularly:

* review architecture
* clean code
* improve naming
* reduce technical debt
* validate structure

Today you will learn that mindset.

---

# 1. Day 7 Goal

Today is NOT about adding features.

It is about:

```text id="jlwmov"
Making your foundation clean and maintainable.
```

---

# 2. What Is Refactoring?

# Refactoring

Means:

```text id="jlwmow"
Improving code structure
WITHOUT changing behavior.
```

---

# Examples

## Better naming

```text id="jlwmox"
usrSrv
```

→

```text id="jlwmoy"
UserService
```

---

## Better package organization

Move misplaced classes.

---

## Smaller methods

Break huge methods into readable methods.

---

## Remove duplicate code

Very important.

---

# 3. Enterprise Review Areas Today

You will review:

* package structure
* naming conventions
* DTO naming
* migration naming
* API naming
* layer responsibilities
* validation placement
* repository design
* service responsibilities

---

# 4. Package Structure Review

# Expected Structure

```text id="jlwmoz"
users

├── domain
│   ├── entity
│   ├── repository
│   └── enums
│
├── application
│   ├── dto
│   ├── usecase
│   ├── service
│   └── mapper
│
├── infrastructure
│   ├── persistence
│   ├── mapper
│   └── config
│
└── presentation
    ├── controller
    ├── request
    └── response
```

---

# Review Questions

Ask yourself:

## Is every class in correct layer?

Example:

* controller logic inside service?
* database logic inside controller?

---

## Is Domain clean?

Should NOT contain:

* Spring annotations
* JPA annotations
* REST logic

---

# 5. Naming Convention Review

# Classes

Use:

```text id="jlwmp0"
PascalCase
```

Examples:

```text id="jlwmp1"
UserService
CreateTaskUseCase
TaskController
```

---

# Methods

Use:

```text id="jlwmp2"
camelCase
```

Examples:

```text id="jlwmp3"
createUser()
findByEmail()
assignTask()
```

---

# Packages

Use:

```text id="jlwmp4"
lowercase
```

Examples:

```text id="jlwmp5"
controller
service
repository
```

---

# Variables

Use meaningful names.

BAD:

```java id="jlwmp6"
String s;
```

GOOD:

```java id="jlwmp7"
String email;
```

---

# 6. API Design Review

# REST API Naming

GOOD:

```http id="jlwmp8"
GET /api/v1/users
POST /api/v1/users
GET /api/v1/tasks
```

---

# BAD

```http id="jlwmp9"
/getUsers
/createTask
```

Avoid verb-based URLs.

REST uses:

* HTTP methods
* resource names

---

# 7. DTO Review

# Request DTO

Should contain:

* client input
* validation annotations

Example:

```java id="jlwmpa"
@NotBlank
private String email;
```

---

# Response DTO

Should:

* hide sensitive fields
* expose only needed data

---

# NEVER Return Password

BAD:

```json id="jlwmpb"
{
  "password": "$2a$10$..."
}
```

Never expose passwords.

---

# 8. Service Layer Review

# Services SHOULD

* orchestrate business logic
* coordinate repositories
* validate workflows

---

# Services SHOULD NOT

* contain SQL
* handle HTTP
* build huge god methods

---

# BAD

```java id="jlwmpc"
public void createUser() {
   // 500 lines
}
```

---

# GOOD

```java id="jlwmpd"
validateEmail();
validateRole();
saveUser();
mapResponse();
```

---

# 9. Repository Review

# Repository Responsibilities

Only:

* database access
* persistence operations

---

# BAD

```java id="jlwmpe"
sendEmail();
generateJWT();
```

inside repository.

Wrong responsibility.

---

# GOOD

```java id="jlwmpf"
findByEmail();
save();
findAll();
```

---

# 10. Validation Review

# Input Validation

Should happen:

* request layer
* service layer

---

# Example

```java id="jlwmpg"
@NotBlank
@Email
private String email;
```

---

# Business Validation

Inside service.

Example:

```text id="jlwmph"
email already exists
role does not exist
user locked
```

---

# 11. Exception Handling Review

# Recommended Future Structure

```text id="jlwmpi"
common

└── exception
    ├── GlobalExceptionHandler
    ├── ResourceNotFoundException
    ├── DuplicateResourceException
    └── UnauthorizedException
```

---

# Enterprise Goal

Consistent API errors.

---

# Example Error Response

```json id="jlwmpj"
{
  "timestamp": "2026-06-02T10:00:00",
  "message": "Email already exists",
  "status": 400
}
```

---

# 12. Migration Review

# Verify

* correct ordering
* proper naming
* no modified migrations
* constraints exist
* indexes exist

---

# Your Current Migration Sequence

Good:

```text id="jlwmpk"
V1 → users
V2 → roles
V3 → user_roles
V4 → projects
V5 → tasks
V6 → indexes
V7 → seed roles
V8 → due_date
V9 → priority index
```

Excellent progression.

---

# 13. Security Review

# Current Security

You already:

* enabled Spring Security
* allowed `/health`
* protected remaining APIs

Good foundation.

---

# Future Security Improvements

Later:

* JWT
* BCrypt
* UserDetailsService
* authentication filter
* authorization rules

---

# 14. Technical Debt Awareness

# Technical Debt

Means:

```text id="jlwmpl"
Short-term shortcuts
that create long-term problems.
```

---

# Examples

* giant service classes
* duplicate logic
* bad naming
* missing validation
* mixed responsibilities

---

# Enterprise Engineers Regularly Refactor

Because:

* codebases grow
* teams grow
* complexity grows

---

# 15. Week 1 Final Architecture

# Current Enterprise Foundation

```text id="jlwmpm"
Spring Boot
    ↓
Modular Monolith
    ↓
Clean Architecture
    ↓
PostgreSQL
    ↓
Flyway
    ↓
RBAC Design
    ↓
Spring Security Foundation
```

This is already strong beginner-to-intermediate backend architecture.

---

# 16. Practical Tasks Today

# Task 1

Review all package names.

Ensure:

* lowercase
* clean structure
* no misplaced classes

---

# Task 2

Review DTOs.

Ensure:

* validation annotations
* no password exposure
* meaningful names

---

# Task 3

Review service methods.

Refactor:

* large methods
* duplicated logic
* unclear naming

---

# Task 4

Review migrations.

Verify:

* constraints
* indexes
* naming consistency

---

# Task 5

Review API naming.

Ensure RESTful style.

---

# Task 6

Create Week 1 Architecture Diagram

Example:

```text id="jlwmpn"
Controller
   ↓
Service / Use Case
   ↓
Repository Interface
   ↓
Infrastructure Repository
   ↓
PostgreSQL
```

---

# 17. Week 1 Achievements

You completed:

# Architecture

* Clean Architecture
* Modular Monolith

---

# Database

* PostgreSQL
* Relationships
* Constraints
* Indexes

---

# Migration Management

* Flyway
* Versioning
* Schema evolution

---

# Security Foundations

* RBAC
* Authentication flow
* Authorization flow

---

# Enterprise Design

* DTOs
* repositories
* services
* layered architecture

---

# 18. End Of Week 1 Outcome

After Week 1 you now understand:

* enterprise backend structure
* migration discipline
* database design
* RBAC architecture
* Clean Architecture fundamentals
* modular monolith design

This is a strong foundation for Spring Boot backend engineering.

---
