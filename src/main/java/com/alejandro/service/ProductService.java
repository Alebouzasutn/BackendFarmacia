package com.alejandro.service;

import com.alejandro.entidad.Product;
import com.alejandro.repository.ProductRepository;
import com.alejandro.stock.event.StockEventPublisher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

	@Autowired
	private StockEventPublisher stockEventPublisher;
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

   /* public Product update(Product product) {
        product.setUpdated(LocalDateTime.now());
        return repository.save(product);
    }*/
    
    public Product actualizarProductoConStock(Integer id, Product incoming) {
        Product existente = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        int anterior = existente.getProductQuantity(); 
        existente.setName(incoming.getName());
        existente.setProductQuantity(incoming.getProductQuantity());
        // otros setters...

        repository.save(existente);

        int diferencia = incoming.getProductQuantity() - anterior; //  calculo diferencia
        stockEventPublisher.publicar(existente, diferencia);       //  invoco correctamente

        return existente;
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
