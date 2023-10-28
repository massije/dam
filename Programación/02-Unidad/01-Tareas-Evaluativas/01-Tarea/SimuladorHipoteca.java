/******************************************************************************************************************
 * 
 * Nombre: Jorge Martin Del Pino Contreras 
 * Fecha: 28/10/2023 
 * Modulo: Programaci�n. 
 * UD1. 
 * Tarea: PROG02 - Tarea evaluativa 01: Programa que crea un simulador de hipotecas a plazo fijo.
 * Autoevaluaci�n: https://docs.google.com/document/d/1KPFIshehQb2mBDWfngPgqkrnEbVzVSoQQSrSzSTrtfA/edit?usp=sharing
 *                 https://youtu.be/f1e1NBOfOgM
 * Descripci�n del programa: El programa imprime por consola una figura sim�trica que tiene forma de torre.
 *                           Y esta torre a su vez esta compuesta por otras figuras o simbolos independientes 
 *                           que de forma conjunta forman la torre.
 *
 *******************************************************************************************************************/
 
import java.util.Scanner;

public class SimuladorHipoteca {
   
   public static void main(String[] args) {
      Scanner sc = new Scanner(System.in);
      mostrarMensajeIntroductorio();
      primero(sc);     
   }
   
   public static void mostrarMensajeIntroductorio() {
      String cadenaRepetida = "Solicita el monto del pr�stamo (euros)";
      System.out.println("Este es un simulador de hipoteca");
      System.out.println("PRIMERO");
      System.out.println(cadenaRepetida + " e imprime una tabla con diferentes opciones seg�n el inter�s y plazo");
      System.out.println("SEGUNDO");
      System.out.println(cadenaRepetida +", la tasa de inter�s anual a pagar (%) y el plazo (a�os)");
      System.out.println("Calcula para cada a�o, el capital pendiente y la cuota a pagar, intereses y amortizaci�n\n");
   }
   
   public static void primero(Scanner sc) {
      System.out.print("Introduce la cantidad solicitada para el pr�stamo: ");
      int cantidadSolicitada = sc.nextInt();
      mostrarCuotasFijas(cantidadSolicitada);
      segundo(sc, cantidadSolicitada);
   }
   
   public static void segundo(Scanner sc, int cantidad) {
      System.out.print("\nIntroduce el inter�s anual que se aplicar� al pr�stamo en %: ");
      double interes = sc.nextDouble();
      System.out.print("Introduce el n�mero de a�os que va a durar el pr�stamo: ");
      int anios = sc.nextInt();
      mostrarCuotasPersonalizadas(cantidad, interes, anios);
   }
   
   public static void mostrarCuotasFijas(int cantidad) {
      System.out.println("\nPRIMERO");
      System.out.println("Estas son las cuotas a pagar para diferentes intereses y plazos");
      for (int anio = 20; anio <= 25; anio++) {
         System.out.print(anio + " A�os");
         for (double interes = 3.0; interes <= 5.0; interes += 0.5) {
            System.out.printf("  %.2f(%.1f%%)  ", cuotaAnual(cantidad, anio, interes), interes);
         }
         System.out.println();
      }
   }
   
   public static void mostrarCuotasPersonalizadas(int cantidad, double interes, int limiteAnio) {   
      double capitalPendiente = cantidad;
      for (int numAnio = 1; numAnio <= limiteAnio; numAnio++) {
         double cuota = cuotaAnual(cantidad, limiteAnio, interes);
         System.out.println("A�o: " + numAnio);
         System.out.printf("\tCapital Pendiente: %.2f%n", capitalPendiente);
         System.out.printf("\tCuota Anual: %.2f%n", cuota);
         System.out.printf("\tIntereses a pagar: %.2f%n", intereses(capitalPendiente, interes));
         System.out.printf("\tAmortizaci�n: %.2f%n", amortizacion(capitalPendiente, cuota, interes));
         capitalPendiente -= amortizacion(capitalPendiente, cuota, interes);
      }
   }
   
   public static double cuotaAnual(double prestamo, int duracion, double interes) {
      double i = interes / 100;
      return (prestamo * i)/(1 - Math.pow((1 + i),-duracion));
   }
   
   public static double intereses(double capitalPendiente, double interes) {
      return capitalPendiente * (interes / 100);
   }
   
   public static double amortizacion(double capitalPendiente, double cuotaAnual, double interes) {
      return cuotaAnual - intereses(capitalPendiente, interes);
   }
   
}