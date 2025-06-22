package com.alejandro.service;

import com.alejandro.dto.SalesItemDTO;
import com.alejandro.dto.SalesResponseDTO;
import com.alejandro.entidad.Customer;
import com.alejandro.entidad.Employee;
import com.alejandro.entidad.Product;
import com.alejandro.entidad.Sales;
import com.alejandro.repository.CustomerRepository;
import com.alejandro.repository.EmployeeRepository;
import com.alejandro.repository.SalesRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SalesService {

    @Autowired
    private SalesRepository salesRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    public Sales registerSale(Integer customerId, Integer employeeId, Double total) {
        Customer customer = customerRepository.findById(customerId)
            .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        Employee employee = employeeRepository.findById(employeeId)
            .orElseThrow(() -> new RuntimeException("Empleado no encontrado"));

        Sales sale = new Sales();
        sale.setCustomer(customer);
        sale.setEmployee(employee);
        sale.setTotal(total);
        sale.setCreated(LocalDateTime.now());

        return salesRepository.save(sale);
    }

    public List<Sales> getAllSales() {
        return salesRepository.findAll();
    }

    public Sales getSaleById(Integer id) {
        return salesRepository.findById(id).orElse(null);
    }

    public SalesResponseDTO getSaleDetails(Integer saleId) {
        Sales s = salesRepository.findById(saleId)
            .orElseThrow(() -> new RuntimeException("Venta no encontrada"));

        List<SalesItemDTO> items = s.getDetails().stream()
            .map(d -> {
                Product p = d.getProduct();
                return new SalesItemDTO(
                    p.getName(),
                    d.getUnitPrice(),
                    d.getQuantity(),
                    d.getSubtotal()
                );
            }).collect(Collectors.toList());

        return new SalesResponseDTO(
            s.getId(),
            s.getCustomer().getName(),
            s.getEmployee().getFullName(),
            s.getCreated(),
            s.getTotal(),
            items
        );
    }
}
