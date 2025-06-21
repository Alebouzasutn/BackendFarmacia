package com.alejandro.service;

import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

import com.alejandro.entidad.Sales;
import com.alejandro.repository.SalesRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;



@Service
public class SalesService {

    private final SalesRepository salesRepository;

    public SalesService(SalesRepository salesRepository) {
        this.salesRepository = salesRepository;
    }

    public Sales registerSale(Integer customerId, Integer employeeId, Double total) {
        Sales sale = new Sales();
        sale.setCustomerId(customerId);
        sale.setEmployeeId(employeeId);
        sale.setTotal(total);
        sale.setSaleDate(LocalDateTime.now());
        return salesRepository.save(sale); 
    }


    public List<Sales> getAllSales() {
        return salesRepository.findAll();
    }

    public Sales getSaleById(Integer id) {
        return (Sales) salesRepository.findById(id).orElse(null);
    }
}
