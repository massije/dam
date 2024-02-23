package facturamain;
// Clase Factura

import java.io.*;
import java.util.*;
// La lista de productos será una lista vacía de NUM productos
// Si existe un fichero TXT para el comprador o la compradora
// leerá los productos y los guardará en el array hasta llenarlo
// numProductos llevará la cuenta de productos
// Utilizará la sentencia try-catch para gestionar las excepciones

public class Factura {
// Atributos

    private String dniComprador;
    private Producto[] listaCompra;
    private int numProductos;
    private final int NUM = 10;

    public Factura(String dni) {
        String pathFile = dni + ".txt";
        File fl = new File(pathFile);
        try {
            if (fl.exists()) {
                try {
                    Scanner scFile = new Scanner(fl).useLocale(Locale.US);
                    listaCompra = new Producto[NUM];
                    while (scFile.hasNext()) {
                        String nombre = scFile.next();
                        double precio = scFile.nextDouble();
                        int cantidad = scFile.nextInt();
                        listaCompra[numProductos] = new Producto(nombre, precio, cantidad);
                        numProductos++;
                    }
                    scFile.close();
                } catch (FileNotFoundException e) {
                    System.out.println("Error al abrir el archivo");
                }
            }
            dniComprador = dni;
            PrintStream nuevoArchivo = new PrintStream(pathFile);
            nuevoArchivo.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error");
        }
    }

// Si todavía hay sitio en el array añadirá la compra de un producto a la factura.
// Si está completa informará de ello con el siguiente mensaje: "Tu lista de la compra está completa"
    public void addCompra(String nombre, double precio, int cantidad) {
        if (this.numProductos != NUM) {
            listaCompra[numProductos] = new Producto(nombre, precio, cantidad);
            numProductos++;
        } else {
            System.out.println("Tu lista de la compra está completa");
        }
    }

    @Override
    public String toString() {
        String formatToSend = "";
        formatToSend += String.format("\n%34s\n\n%s%s\n\n%-30s%8s%8s%8s\n", "F A C T U R A", "DNI: ", dniComprador.toUpperCase(), "Descripcion", "Precio", "Cant.", "Total");
        for (int i = 0; i < numProductos; i++) {
            formatToSend += listaCompra[i].toString() + "\n";
        }
        formatToSend += String.format("\n%s%8.2f%s", "TOTAL: ", getTotalFactura(), " Euros");
        return formatToSend;
    }

// Calcula y devuelve el total de la factura.
    private double getTotalFactura() {
        double suma = 0;
        for (int i = 0; i < numProductos; i++) {
            double precioTotal = listaCompra[i].getPrecioTotal();
            suma = suma + precioTotal;
        }
        return suma;
    }

// Busca un producto en la lista a partir de su nombre
// Devuelve el índice del producto en el array, -1 si no está.
    public int buscaProducto(String nombre) {
        for (int i = 0; i < numProductos; i++) {
            if (listaCompra[i].getNombre().equalsIgnoreCase(nombre)) {
                return i;
            }
        }
        return -1;
    }

// Modifica la cantidad del producto con el índice indicado
// Si la cantidad final es menor o igual que 0 el producto se elimina de la lista   
    public void modificarCompra(int indice, int cantidad) {
        int nuevaCantidad = listaCompra[indice].getCantidad() + cantidad;
        listaCompra[indice].setCantidad(nuevaCantidad);
        if (listaCompra[indice].getCantidad() <= 0) {
            if (indice == numProductos - 1) {
                listaCompra[indice] = null;
            } else {
                for (int i = indice; i < numProductos - 1; i++) {
                    String name = listaCompra[i + 1].getNombre();
                    double price = listaCompra[i + 1].getPrecio();
                    int amount = listaCompra[i + 1].getCantidad();
                    listaCompra[i] = new Producto(name, price, amount);
                }
            }
            numProductos--;
        }
    }
    
    public String getLista(){
        String toSend = "";
        for(int i = 0; i < numProductos; i++){
            String oneProduct = "";
            oneProduct += String.format("%s %.2f %d\n", listaCompra[i].getNombre(), listaCompra[i].getPrecio(), listaCompra[i].getCantidad());
            toSend += oneProduct;
        }
        return toSend;
    }
}

