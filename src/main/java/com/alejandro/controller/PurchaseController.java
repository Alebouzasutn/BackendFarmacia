package com.alejandro.controller;

import com.alejandro.dto.PurchaseResponseDTO;
import com.alejandro.entidad.Purchase;
import com.alejandro.service.PurchaseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/purchases")
@Tag(name = "Purchases", description = "Operaciones relacionadas con compras en el sistema")
public class PurchaseController {

    private final PurchaseService service;

    public PurchaseController(PurchaseService service) {
        this.service = service;
    }

    @Operation(summary = "Registrar una nueva compra")
    @ApiResponse(responseCode = "200", description = "Compra registrada exitosamente")
    @PostMapping
    public ResponseEntity<Purchase> register(@RequestBody Purchase purchase) {
        return ResponseEntity.ok(service.savePurchase(purchase));
    }

    @Operation(summary = "Obtener todas las compras registradas")
    @ApiResponse(responseCode = "200", description = "Listado de compras obtenido correctamente")
    @GetMapping
    public ResponseEntity<List<Purchase>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @Operation(summary = "Obtener una compra por ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Compra encontrada"),
        @ApiResponse(responseCode = "404", description = "Compra no encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Purchase> getById(@PathVariable Integer id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Actualizar una compra existente")
    @ApiResponse(responseCode = "200", description = "Compra actualizada exitosamente")
    @PutMapping("/{id}")
    public ResponseEntity<Purchase> update(@PathVariable Integer id, @RequestBody Purchase updated) {
        updated.setId(id);
        return ResponseEntity.ok(service.updatePurchase(updated));
    }

    @Operation(summary = "Eliminar una compra por ID")
    @ApiResponse(responseCode = "204", description = "Compra eliminada correctamente")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.deletePurchase(id);
        return ResponseEntity.noContent().build();
    }
    
    
    @Operation(summary = "Modificar parcialmente una compra existente")
    @PatchMapping("/{id}")
    public ResponseEntity<Purchase> patch(@PathVariable Integer id, @RequestBody Purchase partialPurchase) {
        partialPurchase.setId(id);
        return ResponseEntity.ok(service.patchPurchase(partialPurchase));
    }

    @Operation(summary = "Obtener detalles de una compra con productos y subtotales")
    @GetMapping("/{id}/details")
    public ResponseEntity<PurchaseResponseDTO> getFullDetails(@PathVariable Integer id) {
        return ResponseEntity.ok(service.getPurchaseDetails(id));
    }

    
}
