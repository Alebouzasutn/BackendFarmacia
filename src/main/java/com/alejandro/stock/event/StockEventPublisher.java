package com.alejandro.stock.event;

import com.alejandro.entidad.Product;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class StockEventPublisher {

    private final ApplicationEventPublisher publisher;

    public StockEventPublisher(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    public void publicar(Product existente, int diferencia) {
        publisher.publishEvent(new StockModificadoEvent(existente, diferencia));
    }
}
