package com.alejandro.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.alejandro.entidad.Purchase;

public interface PurchaseRepository extends JpaRepository<Purchase, Integer> {

	List<Purchase> findAll();

	Optional<Purchase> findById(Integer id);}


