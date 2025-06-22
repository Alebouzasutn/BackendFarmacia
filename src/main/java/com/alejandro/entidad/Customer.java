package com.alejandro.entidad;

import jakarta.persistence.*;
import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Table(name = "customers")
@Schema(description = "Entidad que representa a un cliente registrado")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID del cliente", example = "4")
    private Integer id;

    @Schema(description = "Nombre completo del cliente", example = "Carlos Peralta")
    private String name;

    @Schema(description = "Correo del cliente", example = "carlos.peralta@example.com")
    private String email;

    
    public Customer() {}

    public Customer(Integer id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    
    public Integer getId() { 
    	return id; }
    
    public void setId(Integer id) { 
    	this.id = id; }

    public String getName() { 
    	return name; }
   
    public void setName(String name) { 
    	this.name = name; }

    public String getEmail() { 
    	return email; }
    
    public void setEmail(String email) { 
    	this.email = email; }
}
