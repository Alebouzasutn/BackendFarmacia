package com.alejandro.service;

import com.alejandro.entidad.Product;
import com.alejandro.repository.ProductRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public Product register(Product product) {
        product.setCreated(LocalDateTime.now());
        product.setUpdated(LocalDateTime.now());
        return repository.save(product);
    }

    public List<Product> list(String value) {
        return (value == null || value.trim().isEmpty())
                ? repository.findAll()
                : repository.findByNameContainingIgnoreCase(value);
    }

    public Optional<Product> getById(Integer id) {
        return repository.findById(id);
    }

    public Product update(Product product) {
        product.setUpdated(LocalDateTime.now());
        return repository.save(product);
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }

    public Product getByCode(Integer code) {
        return repository.findByCode(code);
    }

    public boolean updateStock(Integer id, Integer newQuantity) {
        Optional<Product> optional = repository.findById(id);
        if (optional.isPresent()) {
            Product product = optional.get();
            product.setProductQuantity(newQuantity);
            product.setUpdated(LocalDateTime.now());
            repository.save(product);
            return true;
        }
        return false;
    }
}
