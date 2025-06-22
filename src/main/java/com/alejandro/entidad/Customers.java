package com.alejandro.entidad;

import jakarta.persistence.*;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;



	@Entity
	@Table(name = "customers")
	public class Customers {
		@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Integer id;

	    @Column(name = "full_name", nullable = false)
	    private String fullName;

	    private String address;
	    private String telephone;
	    private String email;

	    @CreationTimestamp
	    private LocalDateTime created;
	    @UpdateTimestamp
	    private LocalDateTime updated;

	    public Customers() {}

	    public Customers(Integer id, String fullName, String address, String telephone, String email, LocalDateTime created, LocalDateTime updated) {
	        this.id = id;
	        this.fullName = fullName;
	        this.address = address;
	        this.telephone = telephone;
	        this.email = email;
	        this.created = created;
	        this.updated = updated;
	    }

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public String getFullName() {
			return fullName;
		}

		public void setFullName(String fullName) {
			this.fullName = fullName;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public String getTelephone() {
			return telephone;
		}

		public void setTelephone(String telephone) {
			this.telephone = telephone;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
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


