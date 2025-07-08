package com.alejandro;

import com.alejandro.entidad.*;
import com.alejandro.repository.*;
import com.alejandro.service.PurchaseService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import java.util.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PurchaseServiceTest {

    @Mock
    private PurchaseRepository purchaseRepo;

    @Mock
    private PurchaseDetailRepository detailRepo;

    @Mock
    private ProductRepository productRepo;

    @InjectMocks
    private PurchaseService service;

    @Test
    void testGuardarCompraCalculaTotal() {
        Product producto = new Product();
        producto.setId(1);
        producto.setUnitPrice(50.0);

        PurchaseDetail detalle = new PurchaseDetail();
        detalle.setProduct(producto);
        detalle.setProductQuantity(2);

        Purchase compra = new Purchase();
        compra.setDetails(List.of(detalle));

        when(productRepo.findById(1)).thenReturn(Optional.of(producto));
        when(purchaseRepo.save(any())).thenAnswer(inv -> inv.getArgument(0));

        Purchase resultado = service.savePurchase(compra);

        assertEquals(100.0, resultado.getTotal());
        verify(purchaseRepo).save(any(Purchase.class));
    }
}
