package com.alejandro.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Detalle de un producto dentro de una compra")
public class PurchaseItemDTO {

    @Schema(description = "Nombre del producto", example = "Paracetamol 500mg")
    private String productName;

    @Schema(description = "Precio unitario del producto", example = "520.0")
    private Double unitPrice;

    @Schema(description = "Cantidad de unidades compradas", example = "3")
    private Integer quantity;

    @Schema(description = "Subtotal del producto (precio x cantidad)", example = "1560.0")
    private Double subtotal;
	    
    public PurchaseItemDTO(String productName, Double unitPrice, Integer quantity, Double subtotal) {
			super();
			this.productName = productName;
			this.unitPrice = unitPrice;
			this.quantity = quantity;
			this.subtotal = subtotal;
		}
		
	  
		public PurchaseItemDTO() {
			super();
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


