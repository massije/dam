/******************************************************************************************************************
 * 
 * Nombre: Jorge Martin Del Pino Contreras 
 * Fecha: 15/10/2023 
 * Modulo: Programaci�n. 
 * UD1. 
 * Tarea: PROG01 - Tarea evaluativa 02: Programa que crea una figura sim�trica 
 * Autoevaluaci�n: https://docs.google.com/document/d/1KPFIshehQb2mBDWfngPgqkrnEbVzVSoQQSrSzSTrtfA/edit?usp=sharing
 *                 https://youtu.be/f1e1NBOfOgM
 * Descripci�n del programa: El programa imprime por consola una figura sim�trica que tiene forma de torre.
 *                           Y esta torre a su vez esta compuesta por otras figuras o simbolos independientes 
 *                           que de forma conjunta forman la torre.
 *
 *******************************************************************************************************************/
 
// Creamos la clase. El nombre debe coincidir con el del fichero .java
public class Torre {
   /* Todas estas constantes son creadas para formar la figura Torre
      La inicial F significa FIGURA */
   static final char ESPACIO = ' ';
   static final String F_TUBO = "||";
   static final String F_LADO_IZQUIERDO = "_/";
   static final String F_LADO_DERECHO = "\\_";
   static final String F_UVE_INVERTIDA = "/\\";
   static final char F_DOS_PUNTOS = ':';
   static final char F_BARRA_BAJA = '_';
   static final char F_BARRA_VERTICAL = '|';
   static final char F_COMILLA_DOBLE = '"';
   // Creamos m�todo main. Siempre debe existir y siempre se define igual.
   public static void main(String[] args) {
   // Llamada al m�todo dibujarTorre()
      dibujarTorre();
   }
   
   // M�todo que crea la Torre
   public static void dibujarTorre() {
   // Llamda al m�todo dibujarCuatroTubos()
      dibujarCuatroTubos();
      // Llamda al m�todo
      dibujarPiramide();
      // Llamda al m�todo
      dibujarPiramideInvertida();
      // Llamda al m�todo
      dibujarDoceTubos();
      // Llamda al m�todo
      dibujarPiramide();
   }
   
   // M�todo que crea doce espacios Figura " "
   public static void dibujarDoceEspacios() {
      for (int count = 1; count <= 12; count++) {
         System.out.print(ESPACIO);
      }
   }
   
   // M�todo que crea cuatro tubos Figura ||
   public static void dibujarCuatroTubos() {
      for (int linea = 1; linea <= 4; linea++){
         dibujarDoceEspacios();
         System.out.println(F_TUBO);
      }
   }
   
   //M�todo que crea doce tubos Figura ||
   public static void dibujarDoceTubos() {
      for (int grupo = 1; grupo <= 3; grupo++) {
         dibujarCuatroTubos();
      }
   }
   
   //M�todo que crea una pir�mide invertida
   public static void dibujarPiramideInvertida() {
      int multiplo = 2;
      for (int linea = 0; linea < 4; linea++) {
         for (int count = 1; count <= (multiplo * linea); count++) {
            System.out.print(ESPACIO);
         }
         System.out.print(F_LADO_DERECHO);
         for (int count = 1; count <= (11 - (multiplo * linea)); count++) {
            System.out.print(F_UVE_INVERTIDA);
         }
         System.out.println(F_LADO_IZQUIERDO);
      }
   }
   
   //M�todo que crea una pir�mide
   public static void dibujarPiramide() {
      int multiplo = 3;
      for (int linea = 0; linea < 4; linea++) { 
         for (int count = 1; count <= (9 - (multiplo * linea)); count++) {
            System.out.print(ESPACIO);
         }
         // figura __/ = F_BARRA_BAJA + F_LADO_IZQUIERDO
         System.out.print(F_BARRA_BAJA + F_LADO_IZQUIERDO);
         for (int count = 1; count <= (multiplo * linea); count++) {
            System.out.print(F_DOS_PUNTOS);
         }
         System.out.print(F_TUBO);
         for (int count = 1; count <= (multiplo * linea); count++) {
            System.out.print(F_DOS_PUNTOS);
         }
         // figura \__ = F_LADO_DERECHO + F_BARRA_BAJA
         System.out.println(F_LADO_DERECHO + F_BARRA_BAJA);
      }
      dibujarComillas();
   }
   
   //M�todo que crea conjunto de comillas |"""""""""""""""|
   public static void dibujarComillas() { 
      System.out.print(F_BARRA_VERTICAL);
      for (int count = 1; count <= 24; count++) {
         System.out.print(F_COMILLA_DOBLE);
      }
      System.out.println(F_BARRA_VERTICAL);
   }
}