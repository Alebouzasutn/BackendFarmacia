package com.alejandro.entidad;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.ArrayList;

@Entity
@Table(name = "purchases")

public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    private Double total;
    @CreationTimestamp
    private LocalDateTime created;

    @OneToMany(mappedBy = "purchase", cascade = CascadeType.ALL)
    private List<PurchaseDetail> details = new ArrayList<>();
    @UpdateTimestamp
    private LocalDateTime updated;
    
    
    
	public Purchase() {
		super();
	}

	public Purchase(Integer id, Supplier supplier, Employee employee, Double total, LocalDateTime created, LocalDateTime updated) {
			
		
		this.id = id;
		this.supplier = supplier;
		this.employee = employee;
		this.total = total;
		this.created = created;
		this.updated = updated;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
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
	
	public void setUpdated(LocalDateTime now) {
       this.updated= updated;	
	
	}
	
	public List<PurchaseDetail> getDetails() {
		return details;
	}

	public void setDetails(List<PurchaseDetail> details) {
		this.details = details;
	}

	
		
	}

	

	
    


    
    

