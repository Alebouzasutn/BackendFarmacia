package com.alejandro.controller;

import com.alejandro.entidad.Purchase;
import com.alejandro.service.PurchaseService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/purchases")
public class PurchaseController {

    private final PurchaseService service;

    public PurchaseController(PurchaseService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Purchase> register(@RequestBody Purchase purchase) {
        return ResponseEntity.ok(service.savePurchase(purchase));
    }

    @GetMapping
    public ResponseEntity<List<Purchase>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Purchase> getById(@PathVariable Integer id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Purchase> update(@PathVariable Integer id, @RequestBody Purchase updated) {
        updated.setId(id); 
        return ResponseEntity.ok(service.updatePurchase(updated));
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.deletePurchase(id);
        return ResponseEntity.noContent().build();
    }
}
