package com.alejandro.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import java.util.List;

@Schema(description = "Representación completa de una venta con productos, cliente, empleado y total")
public class SalesResponseDTO {

    @Schema(description = "ID de la venta", example = "5")
    private Integer id;

    @Schema(description = "Nombre del cliente", example = "Carlos Peralta")
    private String customerName;

    @Schema(description = "Nombre del empleado que registró la venta", example = "Valeria Romero")
    private String employeeName;

    @Schema(description = "Fecha de la venta", example = "2025-06-26T11:30:00")
    private LocalDateTime created;

    @Schema(description = "Monto total de la venta", example = "1420.0")
    private Double total;

    @Schema(description = "Listado de productos vendidos")
    private List<SalesItemDTO> items;

	public SalesResponseDTO(Integer id, String customerName, String employeeName, LocalDateTime created, Double total,
			List<SalesItemDTO> items) {
		super();
		this.id = id;
		this.customerName = customerName;
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

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
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

	public List<SalesItemDTO> getItems() {
		return items;
	}

	public void setItems(List<SalesItemDTO> items) {
		this.items = items;
	}

   
}
