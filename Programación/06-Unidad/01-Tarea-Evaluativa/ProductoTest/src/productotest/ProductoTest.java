
package productotest;
 /******************************************************************************************************************
 * 
 * Nombre: Jorge Martin Del Pino Contreras 
 * Fecha: 09/02/2024 
 * Modulo: Programación. 
 * UD6. 
 * Tarea: PROG06 - Tarea evaluativa 01: Esta tarea se centra en el proceso de creación y testeo de la clase producto.
 * Autoevaluación: https://docs.google.com/document/d/1sPwNUlWH9gecebeEdFLO31Ac_gh2pdmwlEf4yGB36eE/edit?usp=sharing
 *                 https://youtu.be/ondngqaPme4
 * Descripción del programa: El programa empezara creando un objeto con un tipo de constructor para luego imprimirlo por pantalla.
 *                           Luego creara otro objeto pero con otro constructor y hara lo mismo pero esta vez usaremos los metodos del
 *                           objeto para modificar sus campos(atributos) y asi hasta que hayamos probado todos los constructores.
 *                           Luego seguiremmos testeado la clase "Producto" para que al final hagamos un array de productos y 
 *                           obtengamos el total de todos los productos(como si de una compra real se tratara).
 * 
 *******************************************************************************************************************/

import java.util.*;

public class ProductoTest {
    
    // Método principal
    public static void main(String[] args) {
        
        // Creamos un producto con el primer constructor
        Producto productoUno = new Producto("Naranjas", 1.6, 5);
        
        // Lo mostramos formateado con el método toString
        System.out.println(productoUno.toString());
        System.out.println();
        
        // Creamos un producto con el segundo constructor y lo mostramos
        // Producto: "Chocolate", Precio: 2.35
        Producto productoDos = new Producto("Chocolate", 2.35);
        System.out.println(productoDos.toString());
        
        // Actualizamos la cantidad del producto anterior a 2 y lo mostramos
        productoDos.setCantidad(2);
        System.out.println(productoDos.toString());
        
        // Creamos un producto con el tercer constructor a partir del producto 1 y lo mostramos
        Producto productoTres = new Producto(productoUno);
        System.out.println(productoTres.toString());
        
        // Actualiza la cantidad del producto anterior a 10 y lo mostramos
        productoTres.setCantidad(10);
        System.out.println(productoTres.toString());
        
        // Mostramos la siguiente información del producto 2
        // Nombre
        // Precio
        // Cantidad
        // Precio Total
        //String nombreProducto = productoDos.getNombre();
        //System.out.println("NOMBRE: " + nombreProducto);
        System.out.println();
        System.out.println("NOMBRE: " + productoDos.getNombre());
        System.out.println("PRECIO: " + productoDos.getPrecio());
        System.out.println("CANTIDAD: " + productoDos.getCantidad());
        System.out.println("PRECIO TOTAL: " +   productoDos.getPrecioTotal());
        
        // Pedimos datos por teclado, creamos un producto y lo mostramos
        System.out.println();
        Scanner leerDatos = new Scanner(System.in);
        Producto otroProducto = pedirProducto(leerDatos);
        System.out.println(otroProducto);
        
        
        // Creamos un array de 3 Productos, pedimos los datos y los mostramos
        System.out.println();
        Producto[] arrayProductos = new Producto[3];
        for (int i = 0; i < arrayProductos.length; i++) {
            arrayProductos[i] = pedirProducto(leerDatos);
        }
        System.out.println(Arrays.toString(arrayProductos));
        
        // Mostramos el precio del elemento 1 de arrayProductos
        System.out.println();
        System.out.println("Precio del elemento 1: " + arrayProductos[1].getPrecio());
        
        // Mostramos el nombre del elemento 0 de arrayProductos
        System.out.println("Nombre del elemento 0: " + arrayProductos[0].getNombre());
        
        // Mostramos la cantidad del elemento 2 de arrayProductos
        System.out.println("Cantidad del elemento 2: " + arrayProductos[1].getCantidad());
        // Mostramos el precio total del elemento 1 de arrayProductos
        System.out.println("Precio total del elemento 1: " + arrayProductos[1].getPrecioTotal());
        
        // Muestra la suma del precio total de todos los productos del arrayProductos
        System.out.println();
        double suma = precioTotal(arrayProductos);
        System.out.printf("Suma total: %.2f\n", suma);
    }
    
    // Pedimos datos por teclado y creamos un producto
    public static Producto pedirProducto(Scanner leerDatos){
        String nombre;
        double precio;
        int cantidad;
        System.out.println("Nombre: ");
        nombre = leerDatos.nextLine();
        System.out.println("Precio: ");
        precio = leerDatos.nextDouble();
        System.out.println("Cantidad: ");
        cantidad = leerDatos.nextInt();
        leerDatos.nextLine();
        return new Producto(nombre, precio, cantidad);
    }
    
    // Mostramos el precio total de cada uno de los productos del arrayde Productos
    // y devuelvemos la suma de todos ellos
    public static double precioTotal(Producto[] arrayProductos){
        double preciosTotales = 0;
        double precioTotal;
        for(int i = 0; i < arrayProductos.length; i++){
            precioTotal = arrayProductos[i].getPrecioTotal();
            System.out.printf("Precio total del producto " + (i + 1) + ": %.2f\n", precioTotal);
            preciosTotales += precioTotal;
        }
        return preciosTotales;
    }
    
}
