package com.alejandro.entidad;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "sales")
public class Sales {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "sale_date", nullable = false)
    private LocalDateTime created;
    @Column(nullable = false)
    private Double total;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SalesDetail> details;

    public Sales() {}

    public Integer getId() { 
        return id; 
    }
    public void setId(Integer id) { 
        this.id = id; 
    }

    public LocalDateTime getCreated(){
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

    public Customer getCustomer() {
        return customer;
    }
    public void setCustomer(Customer customer) {
        this.customer = customer; 
    }

    public Employee getEmployee() {
        return employee; 
    }
    public void setEmployee(Employee employee) { 
        this.employee = employee; 
    }

    public List<SalesDetail> getDetails() {
        return details; 
    }
    public void setDetails(List<SalesDetail> details) { 
        this.details = details;
    }
}

