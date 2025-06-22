package com.alejandro.controller;

import com.alejandro.dto.SalesResponseDTO;
import com.alejandro.entidad.Sales;
import com.alejandro.service.SalesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/sales")
@Tag(name = "Sales", description = "Operaciones relacionadas con ventas en el sistema")
public class SalesController {

    private final SalesService salesService;

    public SalesController(SalesService salesService) {
        this.salesService = salesService;
    }

    @Operation(
        summary = "Registrar una nueva venta",
        description = "Crea una venta indicando el cliente, el empleado y el total de la compra"
    )
    @ApiResponse(responseCode = "200", description = "Venta creada correctamente")
    @PostMapping
    public ResponseEntity<Sales> createSale(
            @Parameter(description = "ID del cliente", in = ParameterIn.QUERY) @RequestParam Integer customerId,
            @Parameter(description = "ID del empleado", in = ParameterIn.QUERY) @RequestParam Integer employeeId,
            @Parameter(description = "Monto total de la venta", in = ParameterIn.QUERY) @RequestParam Double total) {
        return ResponseEntity.ok(salesService.registerSale(customerId, employeeId, total));
    }

    @Operation(summary = "Obtener todas las ventas registradas")
    @ApiResponse(responseCode = "200", description = "Lista de ventas obtenida correctamente")
    @GetMapping
    public List<Sales> getAllSales() {
        return salesService.getAllSales();
    }

    @Operation(summary = "Buscar una venta por ID")
    @ApiResponse(responseCode = "200", description = "Venta encontrada")
    @GetMapping("/{id}")
    public Sales getSaleById(@Parameter(description = "ID de la venta") @PathVariable Integer id) {
        return salesService.getSaleById(id);
    }
    
    @Operation(summary = "Ver detalle completo de una venta")
    @GetMapping("/{id}/detalle")
    public ResponseEntity<SalesResponseDTO> getSaleDetails(@PathVariable Integer id) {
        return ResponseEntity.ok(salesService.getSaleDetails(id));
    }

}
