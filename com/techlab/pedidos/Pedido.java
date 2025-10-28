package com.techlab.pedidos;

import java.util.Map;

import com.techlab.productos.Producto;
import com.techlab.sistema.Sistema;

public class Pedido {

    private int id;
    private String cliente;
    private Map<Integer, Integer> cantidadPorId;
    private boolean activo;
    private Sistema sistema;


    public Pedido(int id, String cliente, Map<Integer, Integer> cantidadPorId, Sistema sistema) {
        this.id = id;
        this.cliente = cliente;
        this.cantidadPorId = cantidadPorId;
        this.activo = true;
        this.sistema = sistema;
    }

    public float calcularTotal() {
        float total = 0;
        for(Map.Entry<Integer, Integer> entry : cantidadPorId.entrySet()) {
            int idProducto = entry.getKey();
            int cantidad = entry.getValue();
            Producto producto = sistema.buscarProductoPorId(idProducto);    

            total += producto.getPrecio() * cantidad;
        }
        return total;
    }


    public boolean fueEliminado() {
        return !activo;
    }

    public void eliminar() {
        this.activo = false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[ Pedido ID: ").append(id).append("\n");
        sb.append("Cliente: ").append(cliente).append("\n");
        sb.append("Productos:\n");
        sb.append(getLineasProductos());
        sb.append("Total: ").append(calcularTotal()).append("]\n");
        return sb.toString();
    }

    private String getLineasProductos() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Integer, Integer> entry : cantidadPorId.entrySet()) {
            int idProducto = entry.getKey();
            int cantidad = entry.getValue();
            Producto producto = sistema.buscarProductoPorId(idProducto);
            sb.append(" - ").append(producto.getNombre())
                            .append(": ")
                            .append(producto.getPrecio())
                            .append(" x ")
                            .append(cantidad)
                            .append("\n");
        }
        return sb.toString();
    }

}
