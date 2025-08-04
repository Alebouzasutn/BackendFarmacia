# Sistema de Compras y Ventas – Backend

## 📌 Descripción

Este proyecto es una API RESTful desarrollada en **Java con Spring**, que implementa un sistema de gestión de compras y ventas. Permite realizar operaciones CRUD sobre entidades como productos, empleados, proveedores y compras.

Incluye:

- Gestión automática de stock al registrar una compra (**Spring Events**).
- Documentación de endpoints con **Swagger UI**.
- Persistencia con **MySQL** mediante **Spring Data JPA**.
- Pruebas unitarias del módulo de compras con **JUnit**.

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
   ```

2. Crear la base de datos en MySQL:

   ```sql
   CREATE DATABASE pharmacy_database;
   ```

3. Configurar el archivo `application.properties`:

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

4. Ejecutar la aplicación:

   Desde tu IDE (IntelliJ, Eclipse, etc.)  

   O desde terminal con Maven:

   ```bash
   ./mvnw spring-boot:run
   ```

5. Acceso a Swagger:

   Una vez que la aplicación esté corriendo, podés acceder a la documentación de la API desde:  

   [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

---

## ?? Despliegue en AWS EC2 con Docker (Podman)

### 1. Requisitos previos

- Cuenta en [AWS](https://aws.amazon.com/)
- Instancia EC2 (Ubuntu)
- Podman instalado en EC2
- Proyecto Java empaquetado (`.jar`)
- Contenedor de MySQL configurado

---

### 2. Configuraci�n de MySQL en Podman


  ```podman run --name mysql-farma -e MYSQL_ROOT_PASSWORD=rootpass \
  -e MYSQL_DATABASE=farmakend -p 3307:3306 -d mysql:8
  ```

---


### 3. Configuraci�n de la aplicaci�n Java
Compilaci�n del .jar:


 ```mvn clean package
Transferencia del .jar a EC2:


scp -i /path/to/key.pem target/farma.backend-1-0.0.1-SNAPSHOT.jar \
  ubuntu@<EC2_PUBLIC_IP>:~/farmakend/
 ```
 ---

###  4. Ejecuci�n del contenedor Spring Boot

 ```podman run --name farma-backend -p 8081:8080 \
  -e SPRING_DATASOURCE_URL=jdbc:mysql://<EC2_PRIVATE_IP>:3306/farmakend \
  -e SPRING_DATASOURCE_USERNAME=root \
  -e SPRING_DATASOURCE_PASSWORD=rootpass \
  -v ~/farmakend/farma.backend-1-0.0.1-SNAPSHOT.jar:/app.jar:Z \
  --network host \
  docker.io/eclipse-temurin:17 java -jar /app.jar
 ```
  Asegurarse de ajustar los valores del datasource si MySQL est� en otro contenedor o m�quina.

 ---
  
### 5. Verificaci�n
Acceso a Swagger o API en:
http://<EC2_PUBLIC_IP>:8081/

## 📬 Contacto

- **Email:** alej.bouzas@gmail.com  
- **LinkedIn:** [https://linkedin.com/in/alejandro-manuel-b-a64133a4/](https://linkedin.com/in/alejandro-manuel-b-a64133a4/)
