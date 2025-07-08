# 🛒 Sistema de Compras y Ventas – Backend

## 📌 Descripción

Este proyecto es una API RESTful desarrollada en **Java con Spring **, que implementa un sistema de gestión de **compras y ventas**. 
Permite realizar operaciones CRUD sobre entidades como productos, empleados, proveedores y compras.

Incluye:
- Gestión automática de stock al registrar una compra (**Spring Events**).
- Documentación de endpoints con **Swagger UI**.
- Persistencia con **MySQL** mediante **Spring Data JPA**.
- Pruebas unitarias del módulo de compras con **JUnit **.

---

## ⚙️ Tecnologías utilizadas

- Java 8
- Spring Boot
- Spring Web
- Spring Data JPA
- Spring Events
- MySQL
- Swagger (Springdoc OpenAPI)
- JUnit 5

---

## 🚀 Funcionalidades principales

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

---

## 🛠️ Configuración del proyecto

1. Clonar el repositorio:
   ```bash
   git clone https://github.com/Alebouzasutn/BackendFarmacia.git
   cd BackendFarmacia

2. Crear la base de datos en MySQL:


CREATE DATABASE sistema_ventas;

3. Configurar el archivo application.properties:

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


4.Ejecutar la aplicación:
Desde tu IDE (IntelliJ, Eclipse, etc.)

O desde terminal con Maven:

./mvnw spring-boot:run

5.Acceso a Swagger
Una vez que la aplicación esté corriendo, podés acceder a la documentación de la API desde:

http://localhost:8080/swagger-ui.html

6.📬 Contacto
Email: alej.bouzas@gmail.com

LinkedIn: kedin.com/in/alejandro-manuel-b-a64133a4/