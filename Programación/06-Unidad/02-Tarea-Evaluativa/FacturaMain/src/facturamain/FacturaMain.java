package facturamain;
 /******************************************************************************************************************
 * 
 * Nombre: Jorge Martin Del Pino Contreras 
 * Fecha: 23/02/2024 
 * Modulo: Programación. 
 * UD6. 
 * Tarea: PROG06 - Tarea evaluativa 02: Esta tarea se centra en el proceso de creación y testeo de la clase producto.
 * Autoevaluación: https://docs.google.com/document/d/15cQC8fM5MUEUmkNGZmLJuWtRTULR09kUpIbXA3zxyls/edit?usp=sharing
 *                 https://youtu.be/DVeEwlPVGNw
 *     archivo .ZIP: https://drive.google.com/file/d/1z3yfuZX2gxyla1hXzuenlehISL1oNvLW/view?usp=drive_link
 * Descripción del programa: El programa consiste en poder acceder a las facturas de los clientes para luego poder editarlo
 *                           ya sea para añadir mas productos a su factura o eliminarlos.
 * 
 *                           El funcionamiento es el siguiente: Se muestra una breve descripción de lo que va a hacer el programa
 *                           y seguido se nos pide el dni del cliente. Si este existe podremos abrir el archivo que simula ser una factura
 *                           para luego editarlo, pero si no existe se creara un nuevo archivo para ese cliente.
 *                          
 *                           Luego se nos mostrara un menu con 3 opciones que nos diran lo que podemos hacer con dicha factura y luego al finalizar 
 *                           el programa, la factura o archivo sera actualizado.
 * 
 *******************************************************************************************************************/
import java.util.*;     // Para utilizar el objeto Scanner
import java.io.*;       // Para utilizar los ficheros

public class FacturaMain {

    // Constantes
    public static final int FIN = 1;
    public static final int COMPRAR = 2;
    public static final int DEVOLVER = 3;

    // Método principal
    public static void main(String[] args) throws FileNotFoundException {
        Scanner leerTeclado = new Scanner(System.in);
        presentacion();
        System.out.print("DNI del cliente o la clienta: ");
        String dniCliente = leerTeclado.nextLine().toLowerCase();
        System.out.println();
        Factura facturaCliente = new Factura(dniCliente);
        System.out.println(facturaCliente);

        int opcion = elegirOpcion(leerTeclado);
        while (opcion != FIN) {
            switch (opcion) {
                case COMPRAR:
                    comprar(leerTeclado, facturaCliente);
                    break;
                case DEVOLVER:
                    devolver(leerTeclado, facturaCliente);
                    break;
                default:
                    System.out.println("Opción incorrecta");
                    break;
            }
            System.out.println(facturaCliente);
            opcion = elegirOpcion(leerTeclado);
        }
        System.out.println(facturaCliente);
        actualizar(dniCliente, facturaCliente);
        leerTeclado.close();
    }

    /*
      Presentación
     */
    public static void presentacion() {
        System.out.println("Este programa muestra la factura de la compra realizada");
        System.out.println("por un cliente o una clienta");
        System.out.println("Lee la lista de la compra inicial de un fichero, si es que existe");
        System.out.println("Mediante un menú permite comprar más productos o devolverlos");
        System.out.println("Cuando se elige finalizar, muestra la factura definitiva");
        System.out.println();
    }

    /*
      Elegir opcion:  muestra el menú, lee la opción elegida y la devuelve
      Parámetros: 
         Scanner consola para leer datos por teclado
      return: un int con la opción elegida
     */
    public static int elegirOpcion(Scanner consola) {
        System.out.print("\n******* MENU *******\n"
                + "1. Imprimir factura\n"
                + "2. Comprar un producto\n"
                + "3. Devolver un producto\n");
        System.out.print("Elige tu opción: ");
        int opcion = consola.nextInt();
        System.out.println("\n");
        return opcion;
    }

    /*
      Pide los datos del producto. Si existe modifica la cantidad, si no lo añade a la lista.
      Parámetros: 
         Scanner consola: para leer datos por teclado
         Factura unaFactura: para añadir o modificar los productos de la factura
      No devuelve nada
     */
    public static void comprar(Scanner consola, Factura unaFactura) {
        System.out.print("Nombre del producto: ");
        String nombre = consola.next();
        System.out.print("Cantidad: ");
        int cantidad = consola.nextInt();
        //System.out.println("\n");
        int indice = unaFactura.buscaProducto(nombre);
        if (indice == -1) {
            System.out.print("Precio: ");
            double precio = consola.nextDouble();
            unaFactura.addCompra(nombre, precio, cantidad);
        } else {
            unaFactura.modificarCompra(indice, cantidad);
        }
    }

    /*
      Pide los datos del producto. Si existe modifica la cantidad, si no avisa que el producto no está en la lista.
      Parámetros: 
         Scanner consola: para leer datos por teclado
         Factura unaFactura: para modificar los productos de la factura
      No devuelve nada
     */
    public static void devolver(Scanner consola, Factura unaFactura) {
        System.out.print("Nombre del producto: ");
        String nombre = consola.next();
        int indice = unaFactura.buscaProducto(nombre);
        if (indice != -1) {
            System.out.print("\nCantidad: ");
            int cantidad = consola.nextInt();
            unaFactura.modificarCompra(indice, cantidad * -1);
        }else{
            System.out.println("\nEse producto no está en la lista de la compra");
        }
    }

    public static void actualizar(String flName, Factura unaFactura) {
        try {
            String namePath = flName + ".txt";
            File ficheroSalida = new File(namePath);
            PrintStream escribirFichero = new PrintStream(ficheroSalida);
            String lista = unaFactura.getLista();
            escribirFichero.print(lista);
            escribirFichero.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error");
        }
    }

}

