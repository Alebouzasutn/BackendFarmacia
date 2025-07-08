package com.alejandro.stock.event;

import com.alejandro.entidad.Product;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
@Component
public class StockEventListener {

	 @EventListener
	    public void manejarEvento(StockModificadoEvent evento) {
	        Product producto = evento.getProducto();
	        int cantidad = evento.getCantidad();

	        if (producto.getProductQuantity() < 5) {
	            System.out.println("Evento: stock bajo para " + producto.getName());
	        }

	        System.out.println("Evento: stock actualizado → " + producto.getProductQuantity());
	    }
	}

