package com.alejandro.dto;

import java.time.LocalDateTime;
import java.util.List;
import io.swagger.v3.oas.annotations.media.Schema;


@Schema(description = "Representación de una compra con sus productos, proveedor, empleado y total")
public class PurchaseResponseDTO {

    @Schema(description = "ID único de la compra", example = "12")
    private Integer id;

    @Schema(description = "Nombre del proveedor asociado a la compra", example = "Laboratorio Suipacha")
    private String supplierName;

    @Schema(description = "Nombre del empleado que registró la compra", example = "Mariano Gómez")
    private String employeeName;

    @Schema(description = "Fecha y hora de registro de la compra", example = "2025-06-24T14:20:00")
    private LocalDateTime created;

    @Schema(description = "Total general de la compra en pesos", example = "3120.0")
    private Double total;

    @Schema(description = "Listado de productos comprados con cantidades y subtotales")
    private List<PurchaseItemDTO> items;
	
    public PurchaseResponseDTO(Integer id, String supplierName, String employeeName, LocalDateTime created,
			Double total, List<PurchaseItemDTO> items) {
		
    	
		this.id = id;
		this.supplierName = supplierName;
		this.employeeName = employeeName;
		this.created = created;
		this.total = total;
		this.items = items;
	}
    
    

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public LocalDateTime getCreated() {
		return created;
	}
	public void setCreated(LocalDateTime created) {
		this.created = created;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	public List<PurchaseItemDTO> getItems() {
		return items;
	}
	public void setItems(List<PurchaseItemDTO> items) {
		this.items = items;
	}
	

   
}

