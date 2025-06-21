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
    private Integer purchaseAmount;
    private Double purchaseSubtotal;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "purchase_id")
    private Purchase purchase;

	public PurchaseDetail(Integer id, Double purchasePrice, Integer purchaseAmount, Double purchaseSubtotal,
			Product product, Purchase purchase) {
		super();
		this.id = id;
		this.purchasePrice = purchasePrice;
		this.purchaseAmount = purchaseAmount;
		this.purchaseSubtotal = purchaseSubtotal;
		this.product = product;
		this.purchase = purchase;
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

	public Integer getPurchaseAmount() {
		return purchaseAmount;
	}

	public void setPurchaseAmount(Integer purchaseAmount) {
		this.purchaseAmount = purchaseAmount;
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
        
}
