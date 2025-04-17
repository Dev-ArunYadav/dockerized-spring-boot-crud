# 🚀 Spring Boot CRUD Application with MySQL using Docker

This project demonstrates how to build a **Spring Boot CRUD application** that connects to a **MySQL database** using **Docker containers**. It supports two setups:
- **Development Setup**: Spring Boot runs locally through (intellij, ide, terminal), MySQL runs in Docker.
- **Dockerized Setup**: Both Spring Boot and MySQL run in Docker containers. it is mostly used when you want to run the app in production.

---

## 🛠️ Tech Stack

| Technology     | Purpose                            |
|----------------|------------------------------------|
| Java 17        | Programming language               |
| Spring Boot    | Backend framework for RESTful APIs |
| Maven          | Build and dependency management    |
| MySQL          | Relational database                |
| Docker         | Containerization                   |
| Docker Compose | Multi-container orchestration      |

---

## 📁 Project Structure

```plaintext
spring-boot-crud-mysql-docker/
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── crud
│   │   │           ├── config
│   │   │           ├── controller
│   │   │           ├── model
│   │   │           ├── repository
|   │   │           ├── security
│   │   │           ├── service
│   │   │           └── SpringBootCrudApplication.java
│   │   └── resources
│   │       ├── application.properties
│   │       ├── application-dev.properties
│   │       └── application-prod.properties
│   └── test
│       └── java
│           └── com
│               └── crud
│                   └── SpringBootCrudApplicationTests.java
├── Dockerfile
├── docker-compose.yml
├── pom.xml
└── README.md
```
## 📦 Dependencies
- Spring Web
- Spring Data JPA
- Spring Security
- MySQL Driver
- Lombok
- JWT
- Spring Boot DevTools (for development)
- Spring Boot Starter Test (for testing)
- Spring Boot Starter Validation (for input validation)



## 📦 Dockerfile (it will be used to build the image for the Spring Boot application)
```dockerfile
# Use the official OpenJDK image as a base
FROM openjdk:17-jdk-alpine
# Set the working directory in the container
WORKDIR /app
# Copy the Maven build artifact into the container
COPY target/*.jar app.jar
# Expose the application port
EXPOSE 8080
# Run the application when the container starts
ENTRYPOINT ["java", "-Dspring.profiles.active=prod", "-jar", "/app.jar"]
```

# 🐳 Docker Compose File (Only MySQL Container)
## 📦 docker-compose.yml
```yaml
services:
  mysql:
    image: mysql:latest
    container_name: guide-mysql
    environment:
      MYSQL_ROOT_PASSWORD: root
      MYSQL_DATABASE: mydb
      MYSQL_USER: user
      MYSQL_PASSWORD: verysecret
    ports:
      - "3306:3306"
```
# 🐳 Docker Compose File (App + Other Services)
## 📦 docker-compose.yml
```yaml 
version: '3.8'
services:
  mysql:
    image: mysql:latest
    container_name: guide-mysql
    environment:
      MYSQL_ROOT_PASSWORD: verysecret
      MYSQL_DATABASE: mydb
#      MYSQL_USER: user
#      MYSQL_PASSWORD: verysecret
    ports:
      - "3306:3306"
    volumes:
      - mysql_data:/var/lib/mysql

  app:
    build: .
    container_name: dockerized-spring-boot-crud
    ports:
      - "8080:8080"
    depends_on:
      - mysql
    environment:
      # if you already configured in application-prod.properties file, then you can comment this but it will override the properties file default 
      SPRING_PROFILES_ACTIVE: prod
      SPRING_DATASOURCE_URL: jdbc:mysql://mysql:3306/mydb
      SPRING_DATASOURCE_USERNAME: root
      SPRING_DATASOURCE_PASSWORD: verysecret
      SPRING_JPA_HIBERNATE_DDL_AUTO: update
      SPRING_JPA_SHOW_SQL: true
    networks:
      - crud_network
    volumes:
      - ./src/main/resources:/app/src/main/resources
      - ./target:/app/target
volumes:
  mysql_data:
    driver: local
networks:
  crud_network:
    driver: bridge
```

## 🛠️ Build and Run
### 1. Build the Application
```bash
mvn clean package
```
### 2. Run Docker Compose
```bash
# For Development Setup (MySQL only)
docker-compose up -d mysql 
# OR
docker-compose up -d 
# For Dockerized Setup (App + MySQL) it mostly used when you want to run the app in production
docker-compose up --build 
```
### 3. Access the Application
- **Localhost**: [http://localhost:8080](http://localhost:8080)
- **MySQL**: [http://localhost:3306](http://localhost:3306)
- **MySQL Workbench**: Use the credentials defined in `docker-compose.yml` to connect to the MySQL database.
```bash
# Host: localhost or IP address of the docker container
# Port: 3306
# Username: root
# Password: verysecret
```

## 📜 API Endpoints
| Method | Endpoint               | Description              |
|--------|------------------------|--------------------------|
| POST   | /api/auth/register     | Post request for sign up |
| POST   | /api/auth/authenticate | Post request for sign in |
| GET    | /api/admin/dashboard   | Get Admin Dashboard      |
| GET    | /api/admin/track       | Get All Users            |
| GET    | /api/user1/dashboard   | Get User1 Dashboard      |
| GET    | /api/user1/profile     | Get User1 Profile        |
| GET    | /api/user1/track       | Get All User2            |
| GET    | /api/user2/dashboard   | Get User2 Dashboard      |
| GET    | /api/user2/profile     | Get User2 Profile        |

---
## 📜 License
This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

## 📜 Contributing
Contributions are welcome! Please feel free to submit a pull request or open an issue for any suggestions or improvements.

## 👨‍💻 Author
**Arun Yadav**  
📧 ydv.arun182@gmail.com  
🔗 [LinkedIn](https://www.linkedin.com/in/arun-yadav-java/)  
