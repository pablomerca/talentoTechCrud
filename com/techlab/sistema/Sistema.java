package com.techlab.sistema;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.techlab.pedidos.Pedido;
import com.techlab.productos.Producto;

public class Sistema {

    private List<Producto> productos;
    private List<Pedido> pedidos;
    private Map<Producto, Integer> idProductos;
    private Map<Integer, Integer> stockConId;

    public Sistema() {
        this.productos = new ArrayList<>();
        this.idProductos = new HashMap<>();
        this.stockConId = new HashMap<>();
        this.pedidos = new ArrayList<>();
    }

    public boolean productoEnSistema(Producto p){
        return productos.contains(p) && !p.fueEliminado();
    }

    public boolean hayProductoConNombre(String nombre){
        return productos.stream().anyMatch(prod -> prod.tieneNombre(nombre) && !prod.fueEliminado());
    }


    public void agregarProducto(String nombre, float precio, int stock){

        if(stock < 0){
            throw new IllegalArgumentException("El stock no puede ser negativo");
        }
        if(precio < 0){
            throw new IllegalArgumentException("El precio no puede ser negativo");
        }
        if(nombre == null || nombre.trim().isEmpty()){
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        }
        if(hayProductoConNombre(nombre)){
            throw new IllegalArgumentException("Ya existe un producto con ese nombre");
        }

        int id = productos.size();

        Producto prod = new Producto(nombre, precio, stock, id);
        productos.add(prod);

        idProductos.put(prod, id);
        stockConId.put(id, stock);

        System.out.println("Producto agregado con ID: " + id);
    }

    public Producto buscarProductoPorId(int id) {

        for (Producto prod : productos) {
            if (prod.tieneId(id)) {
                return prod;
            }
        }
        throw new IllegalArgumentException("No se encontró un producto con ese ID");
    }

    public Producto buscarProductoPorNombre(String nombre) {
        for (Producto prod : productos) {
            if (prod.tieneNombre(nombre)) {
                return prod;
            }
        }
        throw new IllegalArgumentException("No se encontró un producto con ese nombre");
    }

    public void listarProductos() {
        for (Producto prod : productos) {
            if(!prod.fueEliminado())
                System.out.println(prod);
        }
    }

    public void crearPedido(String cliente, Map<Integer, Integer> cantidadPorId) {

        Pedido pedido = new Pedido(productos.size(), cliente, cantidadPorId, this);
        pedidos.add(pedido);
    }

    public void procesarListadoDePedidos() {
        // Listar todos los pedidos
        for (Pedido pedido : pedidos) {
            if (!pedido.fueEliminado()) {
                System.out.println(pedido);
            }
        }
    }

    public float obtenerPrecioPorId(int idProducto) {
        Producto prod = buscarProductoPorId(idProducto);
        return prod.getPrecio();
    }



}


