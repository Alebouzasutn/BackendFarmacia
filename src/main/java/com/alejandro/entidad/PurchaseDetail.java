package com.alejandro.entidad;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "purchase_details")

public class PurchaseDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Double purchasePrice;
    
   private Double purchaseSubtotal;
    private Integer productQuantity;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "purchase_id")
    private Purchase purchase;

	public PurchaseDetail(Integer id, Double purchasePrice, Double purchaseSubtotal,
			Product product, Purchase purchase, Integer productQuantity) {
		super();
		this.id = id;
		this.purchasePrice = purchasePrice;
		
		this.purchaseSubtotal = purchaseSubtotal;
		this.product = product;
		this.purchase = purchase;
		this.productQuantity = productQuantity;
	}

	public PurchaseDetail() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(Double purchasePrice) {
		this.purchasePrice = purchasePrice;
	}



	public Double getPurchaseSubtotal() {
		return purchaseSubtotal;
	}

	public void setPurchaseSubtotal(Double purchaseSubtotal) {
		this.purchaseSubtotal = purchaseSubtotal;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Purchase getPurchase() {
		return purchase;
	}

	public void setPurchase(Purchase purchase) {
		this.purchase = purchase;
	}
	public Integer getProductQuantity() {
		return productQuantity;
    }
	public void setProductQuantity( Integer productQuantity) {
		this.productQuantity = productQuantity;
	}
	
}
