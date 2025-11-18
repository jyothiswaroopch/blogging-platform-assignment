# Blogging Platform: Monolith to Microservices 🚀

This project demonstrates the architectural evolution of a blogging application from a traditional **Monolithic** structure to a distributed **Microservices** system.

It was designed to showcase Spring Boot, REST APIs, JPA relationships, and containerization using Docker & Kubernetes principles.

## 📂 Project Structure

The repository is organized as a **Monorepo** containing two distinct phases of development:

```text
blogging-platform-assignment/
├── monolith/             # Phase 1: The Monolithic Application (H2 Database)
├── user-service/         # Phase 2: Microservice for User Management
├── post-service/         # Phase 2: Microservice for Blog Posts
├── comment-service/      # Phase 2: Microservice for Comments
├── docker-compose.yml    # Orchestration for Phase 2 (7 Containers)
└── README.md             # Documentation
````

-----

## 🛠 Technologies Used

  * **Java 17** (Amazon Corretto)
  * **Spring Boot 3.x** (Web, Data JPA, Actuator)
  * **Databases:** H2 (Monolith), PostgreSQL (Microservices)
  * **DevOps:** Docker, Docker Compose
  * **Communication:** Synchronous HTTP (RestTemplate)
  * **Tools:** Postman, PGAdmin, Maven

-----

## 🏛️ Phase 1: The Monolith

In the first phase, the entire application logic (Users, Posts, Comments) resides in a single deployable unit.

  * **Database:** In-memory H2 database.
  * **Port:** 8080.
  * **Features:** Full CRUD operations with JPA relationships (`@OneToMany`, `@ManyToOne`).

### How to Run the Monolith

1.  Navigate to the folder: `cd monolith`
2.  Run the app: `mvn spring-boot:run` (or use your IDE).
3.  **API URL:** `http://localhost:8080`
4.  **H2 Console:** `http://localhost:8080/h2-console` (User: `sa`, Pass: `password`)

-----

## 🐳 Phase 2: The Microservices (Current State)

The application was refactored into three independent services, each managing its own domain and database. This architecture ensures loose coupling and independent scalability.

[Image of microservices architecture diagram]

### Architecture

| Service | Port | Database (PostgreSQL) | Description |
| :--- | :--- | :--- | :--- |
| **User Service** | `8081` | `user_db` | Manages user registration and retrieval. |
| **Post Service** | `8082` | `post_db` | Manages blog posts. Validates authors via User Service. |
| **Comment Service** | `8083` | `comment_db` | Manages comments. Validates User and Post existence via HTTP calls. |

### Key Features

  * **Database per Service:** Each microservice connects to its own isolated PostgreSQL container.
  * **Inter-Service Communication:** Services communicate via `RestTemplate` to validate data (e.g., Comment Service calls User Service to ensure a user exists before saving a comment).
  * **Containerization:** Each service has a custom `Dockerfile`.
  * **Orchestration:** `docker-compose.yml` manages the entire fleet of **7 containers** (3 Apps, 3 Databases, 1 PGAdmin).

### 🚀 How to Run the Microservices

You need **Docker Desktop** installed and running.

1.  Navigate to the root project folder.
2.  Build and start the system:
    ```bash
    docker-compose up --build
    ```
    *(This may take a few minutes the first time to download base images).*
3.  Wait until you see "Started [ServiceName]Application" in the logs.

### 🧪 Testing Endpoints (Postman)

Use Postman to simulate a real workflow across the distributed system:

**1. Create a User (User Service)**

  * **POST** `http://localhost:8081/users`
  * **Body (JSON):**
    ```json
    {
      "username": "dev_user", 
      "email": "dev@test.com", 
      "bio": "Java Dev"
    }
    ```

**2. Create a Post (Post Service)**

  * **POST** `http://localhost:8082/posts`
  * **Body (JSON):**
    ```json
    {
      "title": "Microservices", 
      "content": "Docker is cool", 
      "authorId": 1
    }
    ```
    *(Internal Logic: Post Service calls User Service to verify ID 1 exists)*

**3. Add a Comment (Comment Service)**

  * **POST** `http://localhost:8083/comments`
  * **Body (JSON):**
    ```json
    {
      "text": "Great post!", 
      "postId": 1, 
      "userId": 1
    }
    ```
    *(Internal Logic: Comment Service calls both Post and User Services)*

### 📊 Database Verification (PGAdmin)

You can visualize the data using the PGAdmin container included in the setup.

1.  Go to: `http://localhost:8888`
2.  **Login:**
      * Email: `admin@admin.com`
      * Password: `admin`
3.  **Register Server:**
      * **Host name/address:** `user-db` (or `post-db`, `comment-db`)
          * *Note: We use the container name, not localhost\!*
      * **Port:** `5432`
      * **Username:** `user`
      * **Password:** `password`
      * **Maintenance DB:** `user_db` (or `post_db`, `comment_db`)

-----

## 🛡️ Monitoring

Each service includes **Spring Boot Actuator** for health monitoring to ensure the container is running correctly.

  * User Service Health: `http://localhost:8081/actuator/health`
  * Post Service Health: `http://localhost:8082/actuator/health`
  * Comment Service Health: `http://localhost:8083/actuator/health`

<!-- end list -->

```
```
