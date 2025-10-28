package com.techlab.productos;

public class Producto {

    private String nombre;
    private float precio;
    private int stock;
    private int id;
    private boolean presente;


        public Producto(String nombre, float precio, int stock, int id) {
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        this.id = id;
        presente = true;
    }

    public void agregarStock(int cantidad) {
        if (cantidad < 0) {
            throw new IllegalArgumentException("La cantidad a agregar no puede ser negativa");
        }
        this.stock += cantidad;
    }

    public void reducirStock(int cantidad) {
        if (cantidad < 0) {
            throw new IllegalArgumentException("La cantidad a reducir no puede ser negativa");
        }
        if (cantidad > this.stock) {
            throw new IllegalArgumentException("No hay suficiente stock para reducir");
        }
        this.stock -= cantidad;
    }

    public boolean tieneNombre(String nombre) {
        return this.nombre.equalsIgnoreCase(nombre);
    }


    @Override
    public String toString() {
        return "Producto [id=" + id + ", nombre=" + nombre + ", precio=" + precio + ", stock=" + stock + "]";
    }




    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public boolean tieneId(int id) {
        return this.id == id;
    }

    public void setPrecio(float nuevoPrecio) {
        precio = nuevoPrecio;
    }

    public float getPrecio() {
        return precio;
    }

    public void setNombre(String nuevoNombre) {
        nombre = nuevoNombre;
    }

    public void eliminar() {
        this.stock = 0;
        this.nombre = "";
        this.presente = false;

    }

    public boolean fueEliminado() {
        return !presente;
    }

    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

}
