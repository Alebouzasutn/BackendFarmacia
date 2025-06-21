package com.alejandro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.alejandro.entidad.Supplier;
import java.util.List;

public interface SupplierRepository extends JpaRepository<Supplier, Integer> {
    List<Supplier> findByNameContainingIgnoreCase(String name);
}
