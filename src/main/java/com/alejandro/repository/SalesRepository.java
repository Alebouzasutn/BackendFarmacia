package com.alejandro.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.alejandro.entidad.Sales;

public interface SalesRepository extends JpaRepository<Sales, Integer> {
    // Podés agregar consultas personalizadas si más adelante las necesitas
}
