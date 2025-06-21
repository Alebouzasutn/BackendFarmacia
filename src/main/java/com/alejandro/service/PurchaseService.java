package com.alejandro.service;

import com.alejandro.entidad.Product;
import com.alejandro.entidad.Purchase;
import com.alejandro.entidad.PurchaseDetail;
import com.alejandro.repository.PurchaseRepository;
import com.alejandro.repository.PurchaseDetailRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
public class PurchaseService {

    private final PurchaseRepository purchaseRepo;
    private final PurchaseDetailRepository detailRepo;

    public PurchaseService(PurchaseRepository purchaseRepo, PurchaseDetailRepository detailRepo) {
        this.purchaseRepo = purchaseRepo;
        this.detailRepo = detailRepo;
    }

    public Purchase savePurchase(Purchase purchase) {
        purchase.setCreated(LocalDateTime.now());
        for (PurchaseDetail d : purchase.getDetails()) {
            d.setPurchase(purchase); 
        }
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
}