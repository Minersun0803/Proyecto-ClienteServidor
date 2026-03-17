
package com.mycompany.proyecto;


public abstract class Inventario  {
    protected String detalles;
    protected int cantidad;
    protected int id_producto;
    protected String producto;

    public Inventario(String detalles, int cantidad, int id_producto, String producto) {
        this.detalles = detalles;
        this.cantidad = cantidad;
        this.id_producto = id_producto;
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

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }
    
    
}
