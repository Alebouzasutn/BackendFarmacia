
package com.alejandro.controller;

import com.alejandro.entidad.Supplier;
import com.alejandro.service.SupplierService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/suppliers")
@Tag(name = "Suppliers", description = "Gestión de proveedores")
public class SupplierController {

    private final SupplierService supplierService;

    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @Operation(summary = "Crear un nuevo proveedor")
    @PostMapping
    public Supplier create(@RequestBody Supplier supplier) {
        return supplierService.createSupplier(supplier);
    }

    @Operation(summary = "Actualizar un proveedor existente")
    @PutMapping("/{id}")
    public Supplier update(@PathVariable Integer id, @RequestBody Supplier supplier) {
        return supplierService.updateSupplier(id, supplier);
    }

    @Operation(summary = "Eliminar un proveedor por ID")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        supplierService.deleteSupplier(id);
    }

    @Operation(summary = "Listar proveedores (filtrado opcional por nombre)")
    @GetMapping
    public List<Supplier> getAll(@RequestParam(required = false) String name) {
        return supplierService.listAllSuppliers(name);
    }
}
