
package com.mycompany.proyecto;

//Prueba
public abstract class Inventario  {
    protected String detalles;
    protected int cantidad;
    protected String producto;

    public Inventario(String detalles, int cantidad,String producto) {
        this.detalles = detalles;
        this.cantidad = cantidad;
        this.producto = producto;
    }

    public String getDetalles() {
        return detalles;
    }

    public void setDetalles(String detalles) {
        this.detalles = detalles;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }



    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }
    
    
}
