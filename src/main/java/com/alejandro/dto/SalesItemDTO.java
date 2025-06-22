package com.alejandro.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Detalle de un producto dentro de una venta")
public class SalesItemDTO {

    @Schema(description = "Nombre del producto vendido", example = "Ibuprofeno 400mg")
    private String productName;

    @Schema(description = "Precio unitario al momento de la venta", example = "480.0")
    private Double unitPrice;

    @Schema(description = "Cantidad vendida del producto", example = "2")
    private Integer quantity;

    @Schema(description = "Subtotal del producto (precio × cantidad)", example = "960.0")
    private Double subtotal;

	public SalesItemDTO(String productName, Double unitPrice, Integer quantity, Double subtotal) {
		super();
		this.productName = productName;
		this.unitPrice = unitPrice;
		this.quantity = quantity;
		this.subtotal = subtotal;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(Double subtotal) {
		this.subtotal = subtotal;
	}

    
}
