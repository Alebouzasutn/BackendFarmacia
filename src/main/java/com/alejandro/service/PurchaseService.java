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
import com.alejandro.stock.event.StockEventPublisher;
import com.alejandro.repository.ProductRepository;
@Service
public class PurchaseService {

    private final PurchaseRepository purchaseRepo;
    private final PurchaseDetailRepository detailRepo;
    private final ProductRepository productRepo;
    private final StockEventPublisher stockeventpublisher;

   
    public PurchaseService(PurchaseRepository purchaseRepo, PurchaseDetailRepository detailRepo, ProductRepository productRepo, StockEventPublisher stockeventpublisher) {
        this.purchaseRepo = purchaseRepo;
        this.detailRepo = detailRepo;
        this.productRepo = productRepo;
        this.stockeventpublisher = stockeventpublisher;
    }

  
 
    public Purchase savePurchase(Purchase purchase) {
        purchase.setCreated(LocalDateTime.now());
        double total = 0.0;

        for (PurchaseDetail d : purchase.getDetails()) {
            d.setPurchase(purchase);

            // Obtener producto real desde la base
            Product producto = productRepo.findById(d.getProduct().getId())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

            int stockActual = producto.getProductQuantity(); // stock actual lo saco de la bd, columna product quantity
            int cantidadComprada = d.getProductQuantity(); //lo saca del detalle de la compra d...

            // Verificar stock suficiente
            if (stockActual < cantidadComprada) {
                throw new RuntimeException("Stock insuficiente para el producto: " + producto.getName());
            }

            // Restar stock
            producto.setProductQuantity(stockActual - cantidadComprada);
            productRepo.save(producto);

            // Publicar evento de stock
            stockeventpublisher.publicar(producto, -cantidadComprada);

            // Asociar el producto actualizado al detalle
            d.setProduct(producto);

            // Calcular subtotal
            total += producto.getUnitPrice() * cantidadComprada;
        }

        // Asignar total a la compra 
        purchase.setTotal(total);

        // Guardar compra con sus detalles
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
                Product producto = productRepo.findById(d.getProduct().getId())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

                int cantidad = d.getProductQuantity();

                producto.setProductQuantity(producto.getProductQuantity() + cantidad);
                productRepo.save(producto);

                stockeventpublisher.publicar(producto, cantidad); 

                d.setProduct(producto); // actualiza el detalle con el producto real

                total += producto.getUnitPrice() * cantidad;
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

