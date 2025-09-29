# Sistema de Compras y Ventas – Backend
🛒 Purchase System with Spring Framework

Description:
A complete purchase and sales management system built with a monolithic architecture, covering core business entities such as clients, employees, purchases, sales, and products.

Architecture:

Monolithic application following layered architecture (Controller, Service, Repository).

Integrated Swagger UI for API documentation.

Containerized MySQL database with Podman and deployed on AWS EC2.

Technologies:

Java, Spring Boot, JPA, Swagger, DTOs, MySQL, Podman, AWS EC2

Features:

CRUD operations for Clients, Employees, Products, Sales, and Purchases.

DTO mapping for clean API responses.

API testing with Postman and Swagger UI.

AWS EC2 deployment.

🔗 View on GitHub | 🔗 Swagger UI
## Descripción

Este proyecto es un monolitico + API RESTful desarrollada en Java con Spring Boot, que implementa un sistema de gestión de compras y ventas. Permite realizar operaciones CRUD sobre entidades como productos, empleados, proveedores y compras.

Incluye:

- Gestión automática de stock al registrar una compra (Spring Events).
- Documentación de endpoints con Swagger UI.
- Persistencia con MySQL mediante Spring Data JPA.
- Pruebas unitarias del módulo de compras con JUnit.

## Tecnologías utilizadas

- Java 8
- Spring Boot
- Spring Web
- Spring Data JPA
- Spring Events
- MySQL
- Swagger (Springdoc OpenAPI)
- JUnit 5

## Funcionalidades principales

- CRUD de:
  - Productos
  - Compras
  - Detalle de compras
  - Proveedores
  - Empleados
- Actualización automática de stock al registrar una compra
- Validaciones básicas
- Documentación Swagger para probar los endpoints
- Pruebas unitarias con JUnit del flujo de compras

## Configuración del proyecto

### 1. Clonar el repositorio

```bash
git clone https://github.com/Alebouzasutn/BackendFarmacia.git
cd BackendFarmacia
```

### 2. Crear la base de datos en MySQL

```sql
CREATE DATABASE pharmacy_database;
```

### 3. Configurar `application.properties`

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/pharmacy_database
spring.datasource.username=${DB_USER}
spring.datasource.password=${DB_PASSWORD}
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect

spring.datasource.hikari.connection-init-sql=SET NAMES utf8mb4

springdoc.api-docs.path=/api-docs
springdoc.swagger-ui.path=/swagger-ui.html
```

### 4. Ejecutar la aplicación

Desde tu IDE (IntelliJ, Eclipse, etc.) o desde la terminal con Maven:

```bash
./mvnw spring-boot:run
```

### 5. Acceder a Swagger

Una vez corriendo la aplicación:

[http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

---

## Despliegue en AWS EC2 con Podman (Docker alternativo)

### 1. Requisitos previos

- Cuenta en AWS
- Instancia EC2 (Ubuntu)
- Podman instalado
- Contenedor MySQL corriendo
- Proyecto Java empaquetado en `.jar`

### 2. Configurar MySQL en Podman

```bash
podman run --name mysql-container \
  -e MYSQL_ROOT_PASSWORD=rootpass \
  -e MYSQL_DATABASE=pharmacy_database \
  -p 3306:3306 \
  -d mysql:8
```

### 3. Transferir `.jar` al servidor EC2

```bash
scp -i /ruta/a/clave.pem target/farma.backend-1-0.0.1-SNAPSHOT.jar \
ubuntu@<3.21.171.119.>:~/pharmacy_backend/
```

### 4. Ejecutar el contenedor Java con Podman

```bash
podman run --rm \
  -e SPRING_DATASOURCE_URL=jdbc:mysql://<EC2_PRIVATE_IP>:3306/pharmacy_database \
  -e SPRING_DATASOURCE_USERNAME=root \
  -e SPRING_DATASOURCE_PASSWORD=rootpass \
  -v ~/pharmacy_backend/farma.backend-1-0.0.1-SNAPSHOT.jar:/app.jar:Z \
  --network host \
  docker.io/eclipse-temurin:17 java -jar /app.jar
```

### 5. Verificación

API pública desplegada (si configuraste el puerto 8081 en tu grupo de seguridad):

[http://<3.21.171.119>:8081/](http://3.21.171.119:8081/)
