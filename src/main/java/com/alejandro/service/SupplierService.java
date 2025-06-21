package com.alejandro.service;

import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


import com.alejandro.entidad.Supplier;
import com.alejandro.repository.SupplierRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class SupplierService {

    private final SupplierRepository supplierRepository;

    public SupplierService(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    public Supplier createSupplier(Supplier supplier) {
        LocalDateTime now = LocalDateTime.now();
        supplier.setCreated(now);
        supplier.setUpdated(now);
        return supplierRepository.save(supplier);
    }

    public Supplier updateSupplier(Integer id, Supplier updated) {
        Optional<Supplier> existing = supplierRepository.findById(id);
        if (existing.isPresent()) {
            Supplier supplier = existing.get();
            supplier.setName(updated.getName());
            supplier.setDescription(updated.getDescription());
            supplier.setAddress(updated.getAddress());
            supplier.setTelephone(updated.getTelephone());
            supplier.setEmail(updated.getEmail());
            supplier.setCity(updated.getCity());
            supplier.setUpdated(LocalDateTime.now());
            return supplierRepository.save(supplier);
        }
        return null;
    }

    public boolean deleteSupplier(Integer id) {
        if (supplierRepository.existsById(id)) {
            supplierRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Supplier> listAllSuppliers(String nameFilter) {
        if (nameFilter == null || nameFilter.isEmpty()) {
            return supplierRepository.findAll();
        }
        return supplierRepository.findByNameContainingIgnoreCase(nameFilter);
    }
}
