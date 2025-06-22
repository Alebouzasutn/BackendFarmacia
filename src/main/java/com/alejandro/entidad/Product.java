package com.alejandro.entidad;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "products")
public class Product {

	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer code;
    private String name;
    private String description;

    @Column(name = "unit_price")
    private Double unitPrice;

    @Column(name = "product_quantity")
    private Integer productQuantity;

    @CreationTimestamp
    private LocalDateTime created;
    @UpdateTimestamp
    private LocalDateTime updated;
	

    
    

	public Product(Integer id, Integer code, String name, String description, Double unitPrice, Integer productQuantity,
			LocalDateTime created, LocalDateTime updated) {
		super();
		this.id = id;
		this.code = code;
		this.name = name;
		this.description = description;
		this.unitPrice = unitPrice;
		this.productQuantity = productQuantity;
		this.created = created;
		this.updated = updated;
		
	}

	public Product() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Integer getProductQuantity() {
		return productQuantity;
	}

	public void setProductQuantity(Integer productQuantity) {
		this.productQuantity = productQuantity;
	}

	public LocalDateTime getCreated() {
		return created;
	}

	public void setCreated(LocalDateTime created) {
		this.created = created;
	}

	public LocalDateTime getUpdated() {
		return updated;
	}

	public void setUpdated(LocalDateTime updated) {
		this.updated = updated;
	}

		
	}

		
	

    

