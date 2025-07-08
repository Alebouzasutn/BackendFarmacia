package com.alejandro.stock.event;

import com.alejandro.entidad.Product;

public class StockModificadoEvent {

	private final Product producto;
    private final int cantidad;

    public StockModificadoEvent(Product producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
    }

    public Product getProducto() {
    	return producto; 
    	}
    
    public int getCantidad() { 
    	return cantidad; 
    	}
}

