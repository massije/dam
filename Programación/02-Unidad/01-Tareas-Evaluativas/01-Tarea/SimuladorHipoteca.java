 /******************************************************************************************************************
 * 
 * Nombre: Jorge Martin Del Pino Contreras 
 * Fecha: 28/10/2023 
 * Modulo: Programación. 
 * UD1. 
 * Tarea: PROG02 - Tarea evaluativa 01: Programa que crea un simulador de hipotecas a plazo fijo.
 * Autoevaluación: https://docs.google.com/document/d/1W2EmE7Umk1ybRQmYxYO68I9cWeIVDZLJRAkOEzXLfJo/edit?usp=sharing
 *                 https://youtu.be/luXnMfq8sps
 * Descripción del programa: El programa imprime por consola una breve introducción de como funciona el simulador de hipoteca.
 *                           Luego nos pide que ingresemos el prestamo que deseamos solicitar y nos calcula en cuotas 
 *                           fijas lo que tendremos que pagar y nos lo muestra en forma de tabla. Y despues nos solicita el interes 
 *                           a aplicar al prestamo y los años en los que lo vamos a pagar para poder calcular las cuotas personalizadas
 *                           y nos lo muestra en forma de lista ordenada.
 *
 *******************************************************************************************************************/
 
// Librerias importadas para hacer uso de la clase Scanner y DecimalFormat
import java.util.Scanner;
import java.text.DecimalFormat;

 // Creamos la clase. El nombre debe coincidir con el del fichero .java
public class SimuladorHipoteca {

   static final int ANIOS_LIMITE_CUOTA_FIJA = 25;
   static final double INTERES_LIMITE_CUOTA_FIJA = 5.0;
   static final String TITULO_PRIMERA_OPCION = "PRIMERO";
   static final String TITULO_SEGUNDA_OPCION = "SEGUNDO";
   static final String SIMBOLO = "%";
   
    // Creamos método main. Siempre debe existir y siempre se define igual.
   public static void main(String[] args) {
      Scanner sc = new Scanner(System.in);
      calcularHipoteca(sc);
   }
   
    /* Método que calcula la hipoteca tanto por cuotas fijas como por cuotas personalizadas. 
     * @param sc objeto que usamos para leer valores del teclado
     */
   public static void calcularHipoteca(Scanner sc) {
      double cantidadSolicitada;
      mostrarMensajeIntroductorio();
      System.out.print("Introduce la cantidad solicitada para el préstamo: ");
      cantidadSolicitada = sc.nextDouble();
      primeraOpcion(cantidadSolicitada);
      segundaOpcion(sc, cantidadSolicitada);
   }
   
    // Método que muestra una introducción al programa.
   public static void mostrarMensajeIntroductorio() {
      String cadenaRepetida = "Solicita el monto del préstamo (euros)";
      System.out.println("Este es un simulador de hipoteca");
      System.out.println(TITULO_PRIMERA_OPCION);
      System.out.println(cadenaRepetida + " e imprime una tabla con diferentes opciones según el interés y plazo");
      System.out.println(TITULO_SEGUNDA_OPCION);
      System.out.println(cadenaRepetida +", la tasa de interés anual a pagar (" + SIMBOLO + ") y el plazo (años)");
      System.out.println("Calcula para cada año, el capital pendiente y la cuota a pagar, intereses y amortización\n");
   }
   
    /* Muestra las cuotas fijas a pagar.
     * 
     * @param cantidadSolicitada cantidad del prestamo solicitado  
     */
   public static void primeraOpcion(double cantidadSolicitada) {
      System.out.println("\n\n" + TITULO_PRIMERA_OPCION);
      calcularCuotasFijas(cantidadSolicitada);
   }
   
    /* Muestra las cuotas a pagar pero personalizado para cada caso.
     * 
     * @param sc objeto que usamos para leer valores del teclado
     * @param cantidad el prestamo solicitado 
     */
   public static void segundaOpcion(Scanner sc, double cantidad) {
      double interes;
      int anios;
      System.out.print("\n" + TITULO_SEGUNDA_OPCION);
      System.out.print("\nIntroduce el interés anual que se aplicará al préstamo en " + SIMBOLO + ": ");
      interes = sc.nextDouble();
      System.out.print("\nIntroduce el número de años que va a durar el préstamo: ");
      anios = sc.nextInt();
      System.out.println();
      calcularCuotasPersonalizadas(cantidad, interes, anios);
   }
   
    /* Calcula las cuotas fijas a pagar.
     * 
     * @param cantidad el prestamo solicitado 
     */
   public static void calcularCuotasFijas(double cantidad) {
      System.out.println("Estas son las cuotas a pagar para diferentes intereses y plazos");
      String resultado = "";
      for (int anio = 20; anio <= ANIOS_LIMITE_CUOTA_FIJA; anio++) {
         System.out.print(anio + " Años\t");
         for (double interes = 3.0; interes <= INTERES_LIMITE_CUOTA_FIJA; interes += 0.5) {
            resultado = roundToTwoDecimals(calcularCuotaAnual(cantidad, anio, interes));
            System.out.print("\t" + resultado + "(" + interes + SIMBOLO +")");
         }
         System.out.println();
      }
   }
   
    /* Calcula las cuotas personalizadas a pagar para cada caso.
     * 
     * @param cantidad el prestamo solicitado
     * @param interes el interes aplicado al prestamo
     * @param limiteAnio el año limite para terminar de pagar el prestamo
     */
   public static void calcularCuotasPersonalizadas(double cantidad, double interes, int limiteAnio) {   
      double capitalPendiente = cantidad;
      double cuota = calcularCuotaAnual(cantidad, limiteAnio, interes);
      double amortizacionActual;
      double intereses;
      for (int numAnio = 1; numAnio <= limiteAnio; numAnio++) { 
         amortizacionActual = calcularAmortizacion(capitalPendiente, cuota, interes);
         intereses = calcularIntereses(capitalPendiente, interes);
         imprimirDatosCuotasPersonalizadas(numAnio, capitalPendiente, cuota, intereses, amortizacionActual);
         capitalPendiente -= amortizacionActual;
      }
   }
   
    /* Imprime los datos de las cuotas personalizadas de forma ordenada.
     * 
     * @param numAnio el año en el que se aplican ciertas cuotas, interes, etc
     * @param capitalPendiente la cantidad de capital que falta por pagar en determinado año
     * @param cuota la cuota a pagar por el año 
     * @param intereses el interes a pagar determinado año
     * @param amortizaciónAcutal la amortización a calcular para determinado año.
     */
   public static void imprimirDatosCuotasPersonalizadas(int numAnio, double capitalPendiente, double cuota, double intereses, double amortizacionActual) {
      System.out.println("Año: " + numAnio);
      System.out.println("\tCapital Pendiente: " + roundToTwoDecimals(capitalPendiente));
      System.out.println("\tCuota Anual: " + roundToTwoDecimals(cuota));
      System.out.println("\tIntereses a pagar: " + roundToTwoDecimals(intereses));
      System.out.println("\tAmortización: " + roundToTwoDecimals(amortizacionActual));
   }
   
    /* Calcula la cuota anual para las cuotas personalizadas y luego retorna ese valor.
     *
     * @param prestamo la cantidad de dinero solicita
     * @param duración el tiempo limite para terminar de pagar el prestamo
     * @param interes el interes aplicado al prestamo solicitado
     * @return cuota Anual calculada apartir de los parametros
     */
   public static double calcularCuotaAnual(double prestamo, int duracion, double interes) {
      double i;
      i = dividir(interes);
      return (prestamo * i)/(1 - Math.pow((1 + i),-duracion));
   }
   
    /* Calcula los intereses para cada año de las cuotas personalizadas y luego retorno ese valor.
     *
     * @param capitalPendiente el dinero que falta por pagar un año determinado
     * @param interes el interes que se aplica al capital pendiente de determinado año
     * @return el interes calculado con los parametros usados.
     */
   public static double calcularIntereses(double capitalPendiente, double interes) {
      double i;
      i = dividir(interes);
      return capitalPendiente * i;
   }
   
    /* Calcula la amortización para las cuotas personalizas y luego retorna ese valor.
     * 
     * @param capitalPendiente el dinero que falta por pagar un año determinado
     * @param cuotaAnual el dinero a pagar por cada año de prestamo
     * @param interes el interes aplicado al prestamo
     * @return la amortización calculada apartir de todos los parámetros anteriores
     */
   public static double calcularAmortizacion(double capitalPendiente, double cuotaAnual, double interes) {
      return cuotaAnual - calcularIntereses(capitalPendiente, interes);
   } 
   
    /* Permite redondear cualquier valor a dos decimales y luego retorna ese valor.
     * 
     * @param cantidad la cantidad que queremos formaterar a 2 decimales
     * @return la cantidad recibidad formateada a 2 decimales
     */
   public static String roundToTwoDecimals(double cantidad) {
      DecimalFormat df = new DecimalFormat("0.00");
      return df.format(cantidad).replace(".",",");
   }
   
   /* Calula la division del interes entre 100.
    * 
    * @param interes el interes aplicado al prestamo.
    * @return la division entre el interes y 100.
    */
   public static double dividir(double interes) {
      return interes/100;
   }
}