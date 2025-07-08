package com.alejandro.controller;

import com.alejandro.entidad.Product;
import com.alejandro.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import java.util.Optional;

@Tag(name = "Products", description = "Operaciones relacionadas con productos")
@RestController
@RequestMapping("/api/products")

public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }
    
    @Operation(summary = "Registrar nuevo producto", description = "Crea un producto")
    @PostMapping
    public ResponseEntity<Product> register(@RequestBody Product product) {
        return ResponseEntity.ok(service.register(product));
    }

    
    @Operation(summary = "Listar productos", description = "Devuelve una lista de todos los productos registrados")
    @GetMapping
    public ResponseEntity<List<Product>> list(@RequestParam(required = false) String search) {
        return ResponseEntity.ok(service.list(search));
    }

    @Operation(summary = "Listar productos por id", description = "Devuelve un producto segun id buscado")
    @GetMapping("/{id}")
    public ResponseEntity<Product> getById(@PathVariable Integer id) {
        return service.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Actualizar producto", description = "Modifica los datos del producto con el ID dado")
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Integer id, @RequestBody Product incoming) {    	  
    	Product actualizado = service.actualizarProductoConStock(id, incoming);
           return ResponseEntity.ok(actualizado);
    }

    @Operation(summary = "Eliminar producto", description = "Borra un producto por su ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/code/{code}")
    public ResponseEntity<Product> getByCode(@PathVariable Integer code) {
        Product product = service.getByCode(code);
        return product != null ? ResponseEntity.ok(product) : ResponseEntity.notFound().build();
    }
   
    @Operation(summary = "Agregar stock", description = "Agrega stock por id")
    @PutMapping("/{id}/stock")
    public ResponseEntity<Void> updateStock(@PathVariable Integer id, @RequestBody Integer quantity) {
        boolean updated = service.updateStock(id, quantity);
        return updated ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}
