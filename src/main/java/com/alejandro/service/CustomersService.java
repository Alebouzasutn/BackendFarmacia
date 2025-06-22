package com.alejandro.service;

import com.alejandro.entidad.Customers;
import com.alejandro.repository.CustomersRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CustomersService {

    private final CustomersRepository repository;

    public CustomersService(CustomersRepository repository) {
        this.repository = repository;
    }
    
    public Customers register(Customers customer) {
        customer.setCreated(LocalDateTime.now());
        customer.setUpdated(LocalDateTime.now());
        return repository.save(customer);
    }

    public List<Customers> list(String value) {
        if (value == null || value.trim().isEmpty()) {
            return repository.findAll();
        } else {
            return repository.findByFullNameContainingIgnoreCase(value);
        }
    }
        

    public Optional<Customers> searchById(Integer id) {
        return repository.findById(id);
    }

    public Customers update(Customers customer) {
        customer.setUpdated(LocalDateTime.now());
        return repository.save(customer);
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }
    
    public Customers patch(Integer id, Map<String, Object> updates) {
        Customers customer = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

       
        if (updates.containsKey("name")) {
            customer.setFullName((String) updates.get("name"));
        }

        if (updates.containsKey("email")) {
            customer.setEmail((String) updates.get("email"));
        }

   

        return repository.save(customer);
    }
    
    
}
