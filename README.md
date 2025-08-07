# Blog Posts API

## Overview
The Blog Posts API is a Spring Boot application that provides CRUD operations for managing blog posts. It integrates with a relational database to persist posts, categories, and tags, and exposes a clean REST interface with built-in search and support for multiple response formats.

**Project page URL:** [https://roadmap.sh/projects/blogging-platform-api](https://roadmap.sh/projects/blogging-platform-api)

## ✨ Features
- Create, Read, Update, Delete (CRUD) for blog posts
- Search posts by title or content (via `searchTerm` query)
- **Dual format support**: JSON and XML responses via Content-Type negotiation
- Consistent `ApiResponse<T>` wrapper for all endpoints
- Global exception handling for validation, malformed UUIDs, missing entities, etc.
- Interactive Swagger/OpenAPI documentation

## 🛠️ Tech Stack
- **Java 21 & Spring Boot 3.x**
- **Spring Web** for REST endpoints
- **Spring Data JPA** + Hibernate for persistence
- **MySQL** as the database
- **Jackson XML** for XML serialization/deserialization
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

### Content-Type Negotiation
The API supports both JSON and XML formats. Use the `Accept` header to specify your preferred format:

**For JSON responses (default):**
```bash
curl -H "Accept: application/json" http://localhost:8080/api/v1/posts
```

**For XML responses:**
```bash
curl -H "Accept: application/xml" http://localhost:8080/api/v1/posts
```

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
Handles all REST endpoints for posts with support for both JSON and XML:

- `GET /api/v1/posts` → list (with optional searchTerm)
- `GET /api/v1/posts/{id}` → fetch by UUID
- `POST /api/v1/posts` → create
- `PUT /api/v1/posts/{id}` → update
- `DELETE /api/v1/posts/{id}` → delete

All endpoints automatically detect the requested format via the `Accept` header and return responses in the appropriate format (JSON or XML).

### GlobalExceptionHandler.java
Centralizes exception handling for:

- `MethodArgumentNotValidException` → 400 with validation errors
- `HttpMessageNotReadableException` → invalid JSON/XML/enums
- `IllegalArgumentException` → malformed UUIDs
- `EntityNotFoundException` → 404 for missing posts

## Response Format Examples

### JSON Response
```json
{
  "success": true,
  "message": "Post retrieved successfully",
  "data": {
    "id": "123e4567-e89b-12d3-a456-426614174000",
    "title": "Sample Post",
    "content": "This is a sample blog post content."
  }
}
```

### XML Response
```xml
<ApiResponse>
  <success>true</success>
  <message>Post retrieved successfully</message>
  <data>
    <id>123e4567-e89b-12d3-a456-426614174000</id>
    <title>Sample Post</title>
    <content>This is a sample blog post content.</content>
  </data>
</ApiResponse>
```

## License
This project is licensed under the MIT License. See the LICENSE file for details.
