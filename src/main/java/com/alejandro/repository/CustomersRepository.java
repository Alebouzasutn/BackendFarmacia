package com.alejandro.repository;


import com.alejandro.entidad.Customers;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


		@Repository
		public interface CustomersRepository extends JpaRepository<Customers, Integer> {

		  
		    
		    
		    List<Customers> findByFullNameContainingIgnoreCase(String name);

		    
		}




