package com.techlab.app;

import java.util.Map;
import java.util.Scanner;

import com.techlab.productos.Producto;
import com.techlab.sistema.Sistema;

public class App {

    public static void printMenu() {
        System.out.println("\n===== Menú =====");
        System.out.println("1. Agregar producto");
        System.out.println("2. Listar productos");
        System.out.println("3. Buscar producto");
        System.out.println("4. Actualizar producto");
        System.out.println("5. Eliminar producto");
        System.out.println("6. Crear pedido");
        System.out.println("7. Listar pedidos");
        System.out.println("8. Salir");
        System.out.print("Elige una opción: ");
    }


    public void run() {
        Scanner in = new Scanner(System.in);
        Sistema sistema = new Sistema();

        while(true){

            printMenu();

            int choice = in.nextInt();
            in.nextLine(); 
            System.out.println();

            switch(choice){
                case 1:
                    System.out.println("Ingrese Nombre, Precio y Cantidad del producto:");
                    String nombre = in.next();
                    float precio = in.nextFloat();
                    int cantidad = in.nextInt();
                    sistema.agregarProducto(nombre, precio, cantidad);
                    break;
                case 2:
                    procesarListadoDeProductos(sistema);
                    break;
                case 3:
                    procesarBusqueda(in, sistema);
                    break;
                case 4:
                    procesarActualizacion(in, sistema);
                    break;
                case 5:
                    procesarEliminacion(in, sistema);
                    break;
                case 6:
                    procesarCreacionPedido(in, sistema);
                    break;
                case 7:
                    procesarListadoDePedidos(sistema);
                    break;
                case 8:
                    System.out.println("Saliendo...");
                    in.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void procesarListadoDePedidos(Sistema sistema) {
        sistema.procesarListadoDePedidos();
    }

    private void procesarCreacionPedido(Scanner in, Sistema sistema) {

        System.out.println("Ingrese el nombre del cliente: ");
        String cliente = in.nextLine();
        System.out.println("Ingrese la cantidad de productos a pedir: ");
        int cantidadProductos = in.nextInt();
        // in.nextLine();

        Map<Integer, Integer> cantidadPorId = new java.util.HashMap<>();

        for (int i = 0; i < cantidadProductos; i++) {
            System.out.println("Ingrese el nombre del producto y la cantidad deseada:");
            String nombre = in.next();
            int cantidad = in.nextInt();
            // in.nextLine();

            Producto prod = sistema.buscarProductoPorNombre(nombre);
            cantidadPorId.put(prod.getId(), cantidad);
        }

        sistema.crearPedido(cliente, cantidadPorId);
    }

    

    private void procesarEliminacion(Scanner in, Sistema sistema) {

        System.out.println("Seleccione el producto a eliminar:");
        Producto prod = procesarBusqueda(in, sistema);
        prod.eliminar();
    }

    private void procesarActualizacion(Scanner in, Sistema sistema) {
        System.out.println("Seleccione el producto a actualizar:");
        Producto prod = procesarBusqueda(in, sistema);
        
        System.out.println("1. Actualizar Nombre");
        System.out.println("2. Actualizar Precio");
        System.out.println("3. Actualizar Stock");
        System.out.print("Elige una opción: ");

        int opcion = in.nextInt();

        switch (opcion) {
            case 1:
                System.out.print("Ingrese el nuevo nombre: ");
                String nuevoNombre = in.next();
                prod.setNombre(nuevoNombre);
                break;
            case 2:
                System.out.print("Ingrese el nuevo precio: ");
                float nuevoPrecio = in.nextFloat();
                prod.setPrecio(nuevoPrecio);
                break;
            case 3:
                System.out.print("Ingrese el nuevo stock: ");
                int nuevoStock = in.nextInt();
                prod.setStock(nuevoStock);
                break;
            default:
                System.out.println("Opción inválida.");
        }

    }

    private void procesarListadoDeProductos(Sistema sistema) {
        System.out.println("Listando Productos...");
        sistema.listarProductos();
    } 


    private static Producto procesarBusqueda(Scanner in, Sistema sistema) {
        System.out.println("1. Buscar por ID");
        System.out.println("2. Buscar por Nombre");
        int eleccion = in.nextInt();
        in.nextLine();

        Producto prod = null;

        switch (eleccion) {
            case 1:
                System.out.print("Ingrese el ID del producto: ");
                int id = in.nextInt();
                prod = sistema.buscarProductoPorId(id);
                System.out.println(prod);
                break;
            case 2:
                System.out.print("Ingrese el nombre del producto: ");
                String nombre = in.nextLine();
                prod = sistema.buscarProductoPorNombre(nombre);
                System.out.println(prod);
                break;
            default:
                System.out.println("Opción inválida.");
        }
        return prod;
    }

}
