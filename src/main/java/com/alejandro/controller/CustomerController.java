package com.alejandro.controller;

import com.alejandro.service.CustomersService;
import com.alejandro.entidad.Customers;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import java.util.List;
import org.springframework.http.ResponseEntity; 
import org.springframework.web.bind.annotation.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;

@Tag(name = "Customers", description = "Operaciones relacionadas con clientes")
@RestController
@RequestMapping("/api/customers")
	
	public class CustomerController {

	    private final CustomersService service;

	    public CustomerController(CustomersService service) {
	        this.service = service;
	    }
	    @Operation(summary = "Registrar nuevo cliente", description = "Crea un cliente con nombre, contacto y dirección")
	    @PostMapping 
	    public ResponseEntity<Customers> register(@RequestBody Customers customer) {
	    	return ResponseEntity.ok(service.register(customer)); }
	    
	    @Operation(summary = "Listar clientes", description = "Devuelve una lista de todos los clientes registrados")
	    @GetMapping
	       public ResponseEntity<List<Customers>> list(@RequestParam(required = false) String search) {
	        return ResponseEntity.ok(service.list(search));
	    }
	    @Operation(summary = "Actualizar cliente", description = "Modifica por completo los datos del cliente con el ID dado")
	    @PutMapping("/{id}")
	    public ResponseEntity<Customers> update(@PathVariable Integer id, @RequestBody Customers updatedCustomer) {
	        updatedCustomer.setId(id);
	        return ResponseEntity.ok(service.update(updatedCustomer));
	    }
	    @Operation(summary = "Eliminar cliente", description = "Borra un cliente por su ID")
	    @DeleteMapping("/{id}")
	    public ResponseEntity<Void> delete(@PathVariable Integer id) {
	        service.delete(id);
	        return ResponseEntity.noContent().build();
	    }
	}


