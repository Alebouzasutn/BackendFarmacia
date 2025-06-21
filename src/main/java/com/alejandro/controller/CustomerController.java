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

@RestController
@RequestMapping("/api/customers")
	
	public class CustomerController {

	    private final CustomersService service;

	    public CustomerController(CustomersService service) {
	        this.service = service;
	    }

	    @PostMapping 
	    public ResponseEntity<Customers> register(@RequestBody Customers customer) {
	    	return ResponseEntity.ok(service.register(customer)); }
	    

	    @GetMapping
	    public ResponseEntity<List<Customers>> list(@RequestParam(required = false) String search) {
	        return ResponseEntity.ok(service.list(search));
	    }

	    @PutMapping("/{id}")
	    public ResponseEntity<Customers> update(@PathVariable Integer id, @RequestBody Customers updatedCustomer) {
	        updatedCustomer.setId(id);
	        return ResponseEntity.ok(service.update(updatedCustomer));
	    }

	    @DeleteMapping("/{id}")
	    public ResponseEntity<Void> delete(@PathVariable Integer id) {
	        service.delete(id);
	        return ResponseEntity.noContent().build();
	    }
	}


