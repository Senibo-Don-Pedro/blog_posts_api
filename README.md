# Blog Posts API

## Overview
The Blog Posts API is a Spring Boot application that provides CRUD operations for managing blog posts. It integrates with a relational database to persist posts, categories, and tags, and exposes a clean REST interface with built-in search and pagination.

## ✨ Features
- Create, Read, Update, Delete (CRUD) for blog posts
- Search posts by title or content (via `searchTerm` query)
- Consistent `ApiResponse<T>` wrapper for all endpoints
- Global exception handling for validation, malformed UUIDs, missing entities, etc.
- Interactive Swagger/OpenAPI documentation

## 🛠️ Tech Stack
- **Java 21 & Spring Boot 3.x**
- **Spring Web** for REST endpoints
- **Spring Data JPA** + Hibernate for persistence
- **MySQL** as the database
- **Lombok** to reduce boilerplate
- **springdoc-openapi** for Swagger UI

## Prerequisites
- **Java Development Kit (JDK)** 17 or later
- **Apache Maven** 3.8 or later
- **Git**
- **MySQL** (or use H2 in-memory)
- **IDE** (IntelliJ IDEA, Eclipse, VS Code, etc.)

## Setup Instructions

### 1. Clone the repository
```bash
git clone https://github.com/Senibo-Don-Pedro/blog_posts_api.git
cd blog_posts_api
```

### 2. Configure the database
Create a Mysql database (e.g., `blog_posts_db`) and update `src/main/resources/application.properties`:

```properties
# DataSource
spring.datasource.url=jdbc:mysql://localhost:3306/blog
spring.datasource.username=YOUR_DB_USERNAME
spring.datasource.password=YOUR_DB_PASSWORD

# JPA
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

# Swagger/OpenAPI
springdoc.api-docs.path=/v3/api-docs
springdoc.swagger-ui.path=/swagger-ui.html
```

### 3. Build the project
```bash
./mvnw clean compile
./mvnw test
./mvnw clean package
```

### 4. Run the application
```bash
# Option A: Maven plugin
./mvnw spring-boot:run

# Option B: Executable JAR
java -jar target/blog_posts_api-0.0.1-SNAPSHOT.jar
```
You should see Spring Boot start-up logs indicating the application is listening on port 8080.

## API Usage

### Swagger UI
After running, open Swagger UI to explore all endpoints and models:

```
http://localhost:8080/swagger-ui/index.html
```

## Project Structure
```
blog_posts_api/
├── src/
│   ├── main/
│   │   ├── java/com/senibo/blogApi/
│   │   │   ├── controller/
│   │   │   │   └── PostController.java
│   │   │   ├── service/
│   │   │   │   ├── PostService.java
│   │   │   │   └── serviceImpl/PostServiceImpl.java
│   │   │   ├── repository/
│   │   │   │   └── PostRepository.java
│   │   │   ├── dto/
│   │   │   │   ├── PostRequest.java
│   │   │   │   ├── PostResponse.java
│   │   │   │   └── ApiResponse.java
│   │   │   ├── exception/
│   │   │   │   └── GlobalExceptionHandler.java
│   │   │   └── model/
│   │   │       ├── Post.java
│   │   │       ├── Category.java
│   │   │       └── Tag.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
│       └── java/com/senibo/blogApi/…
├── .mvn/
├── mvnw, mvnw.cmd, pom.xml
└── README.md
```

## Code Explanation

### PostController.java
Handles all REST endpoints for posts:

- `GET /api/v1/posts` → list (with optional searchTerm)
- `GET /api/v1/posts/{id}` → fetch by UUID
- `POST /api/v1/posts` → create
- `PUT /api/v1/posts/{id}` → update
- `DELETE /api/v1/posts/{id}` → delete

### GlobalExceptionHandler.java
Centralizes exception handling for:

- `MethodArgumentNotValidException` → 400 with validation errors
- `HttpMessageNotReadableException` → invalid JSON/enums
- `IllegalArgumentException` → malformed UUIDs
- `EntityNotFoundException` → 404 for missing posts

## License
This project is licensed under the MIT License. See the LICENSE file for details.
