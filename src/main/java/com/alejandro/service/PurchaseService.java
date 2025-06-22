package com.alejandro.service;


import com.alejandro.entidad.Product;
import com.alejandro.entidad.Purchase;
import com.alejandro.entidad.PurchaseDetail;
import com.alejandro.repository.PurchaseRepository;
import com.alejandro.repository.ProductRepository;
import com.alejandro.repository.PurchaseDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import com.alejandro.dto.PurchaseItemDTO;
import com.alejandro.dto.PurchaseResponseDTO;

@Service
public class PurchaseService {

    private final PurchaseRepository purchaseRepo;
    private final PurchaseDetailRepository detailRepo;

    public PurchaseService(PurchaseRepository purchaseRepo, PurchaseDetailRepository detailRepo) {
        this.purchaseRepo = purchaseRepo;
        this.detailRepo = detailRepo;
    }

    @Autowired
    private ProductRepository productRepo;

    public Purchase savePurchase(Purchase purchase) {
        purchase.setCreated(LocalDateTime.now());

        double total = 0.0;

        for (PurchaseDetail d : purchase.getDetails()) {
            d.setPurchase(purchase);

            // 🔁 Cargar el producto completo desde la base
            Product producto = productRepo.findById(d.getProduct().getId())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

            d.setProduct(producto); // actualiza el detalle con el producto real
            int cantidad = d.getProductQuantity();

            total += producto.getUnitPrice() * cantidad;
        }

        purchase.setTotal(total);
        return purchaseRepo.save(purchase);
    }

    public List<Purchase> findAll() {
        return purchaseRepo.findAll();
    }

    public Optional<Purchase> findById(Integer id) {
        return purchaseRepo.findById(id);
    }
    
    public void deletePurchase(Integer id) {
        purchaseRepo.deleteById(id);
}
    
    public Purchase updatePurchase(Purchase updated) {
        return purchaseRepo.save(updated);
    	 
    }
    
    public Purchase patchPurchase(Purchase patch) {
        Purchase existing = purchaseRepo.findById(patch.getId())
            .orElseThrow(() -> new RuntimeException("Compra no encontrada con ID: " + patch.getId()));

        if (patch.getEmployee() != null) {
            existing.setEmployee(patch.getEmployee());
        }

        if (patch.getSupplier() != null) {
            existing.setSupplier(patch.getSupplier());
        }

        if (patch.getDetails() != null && !patch.getDetails().isEmpty()) {
            for (PurchaseDetail d : patch.getDetails()) {
                d.setPurchase(existing);
            }
            existing.setDetails(patch.getDetails());

            
            double total = 0.0;
            for (PurchaseDetail d : patch.getDetails()) {
                double price = d.getProduct().getUnitPrice();
                int quantity = d.getProductQuantity();
                total += price * quantity;
            }
            existing.setTotal(total);
        }

        existing.setUpdated(LocalDateTime.now());
        return purchaseRepo.save(existing);
}
    
    
    public PurchaseResponseDTO getPurchaseDetails(Integer id) {
        Purchase p = purchaseRepo.findById(id)
            .orElseThrow(() -> new RuntimeException("Compra no encontrada"));

        List<PurchaseItemDTO> items = p.getDetails().stream()
            .map(d -> {
                Product prod = d.getProduct();
                double subtotal = prod.getUnitPrice() * d.getProductQuantity();
                return new PurchaseItemDTO(prod.getName(), prod.getUnitPrice(), d.getProductQuantity(), subtotal);
            })
            .collect(Collectors.toList());

        return new PurchaseResponseDTO(
            p.getId(),
            p.getSupplier().getName(),
            p.getEmployee().getFullName(),
            p.getCreated(),
            p.getTotal(),
            items
        );
    }

}