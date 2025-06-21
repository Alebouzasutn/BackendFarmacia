package com.alejandro.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.alejandro.service.SalesService;
import com.alejandro.entidad.Sales;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("/api/sales")
public class SalesController {

    private final SalesService salesService;

    public SalesController(SalesService salesService) {
        this.salesService = salesService;
    }

    @PostMapping
    public ResponseEntity<Sales> createSale(@RequestParam Integer customerId,
                                            @RequestParam Integer employeeId,
                                            @RequestParam Double total) {
    	return ResponseEntity.ok(salesService.registerSale(customerId, employeeId, total));    }
    

    @GetMapping
    public List<Sales> getAllSales() {
        return salesService.getAllSales();
    }

    @GetMapping("/{id}")
    public Sales getSaleById(@PathVariable Integer id) {
        return salesService.getSaleById(id);
    }
}
