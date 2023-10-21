/******************************************************************************************************************
 * 
 * Nombre: Jorge Martin Del Pino Contreras 
 * Fecha: 27/09/2023 
 * Modulo: Programación. 
 * UD1. 
 * Tarea: PROG01 - Tarea evaluativa 01: Programa que crea una cancion acumulativa 
 * Autoevaluación: https://docs.google.com/document/d/1DBTdnwSXswXfPFMnVhDx_p97KwrZACyxzzyGTcJJCzc/edit?usp=sharing
 * Descripción del programa: El programa imprime un primer parrafo de 4 líneas, al cual se le va añadiendo 1 lineas más
 *                           en la siguiente impresion o salida por pantalla y de esa forma se va construyendo nuestra 
 *                           canción acumulativa, hasta llegar al último parrafo con un total de 8 líneas impresas.
 *
 *******************************************************************************************************************/

// creamos la clase. El nombre debe coincidir con el del fichero .java
public class RinconSombra {
   // Creamos método main. Siempre debe existir y siempre se define igual.
	public static void main(String[] args) {
      
      // llamada al método imprimirPrimerParrafo()
      imprimirPrimerParrafo();
      
      // llamada al método imprimirSegundoParrafo()
      imprimirSegundoParrafo();
      
      // llamada al método imprimirTercerParrafo()
      imprimirTercerParrafo();
      
      // llamada al método imprimirCuartoParrafo()
      imprimirCuartoParrafo();
      
      // llamada al método imprimirQuintoParrafo()
      imprimirUltimoParrafo(); 
      
   } 
   
   // método que imprime un parrafo con 4 líneas
   public static void imprimirPrimerParrafo(){
      System.out.println("Se encontraba el sauce en su rincon,");
      System.out.println("Vino el roble y le hizo sombra,");
      // llamada al método obtenerDosUltimasLineas()
      obtenerDosUltimasLineas();
   }
   
   // método que imprime un parrafo con 5 líneas
   public static void imprimirSegundoParrafo(){
      System.out.println("Se encontraba el roble en su rincon,");
      System.out.println("Vino el abedul y le hizo sombra,");
      // llamada al método obtenerTresUltimasLineas()
      obtenerTresUltimasLineas();
   }
   
   // método que imprime un parrafo con 6 líneas
   public static void imprimirTercerParrafo(){
      System.out.println("Se encontraba el abedul en su rincon,");
      System.out.println("Vino el pino y le hizo sombra,");
      // llamada al método obtenerCuatroUltimasLineas()
      obtenerCuatroUltimasLineas();
   }
   
   // método que imprime un parrafo con 7 líneas
   public static void imprimirCuartoParrafo(){
      System.out.println("Se encontraba el pino en su rincon,");
      System.out.println("Vino el cedro y le hizo sombra,");
      // llamada al método obtenerCincoUltimasLineas()
      obtenerCincoUltimasLineas();
   }
   
   // método que imprime un parrafo con 8 líneas
   public static void imprimirUltimoParrafo(){
      System.out.println("Se encontraba el cedro en su rincon,");
      System.out.println("Vino el haya y le hizo sombra,");
      System.out.println("Sombreo el haya al cedro,");
      // llamada al método obtenerCincoUltimasLineas()
      obtenerCincoUltimasLineas();
   }
   
   // método que almacena 2 lineas
   public static void obtenerDosUltimasLineas(){
      System.out.println("Sombreo el roble al sauce,");
      System.out.println("El sauce a la sombra se quedo.\n");
   }
   // método que almacena 3 lineas
   public static void obtenerTresUltimasLineas(){
      System.out.println("sombreo el abedul al roble,");
      // llamada al método obtenerDosUltimasLineas() 
      obtenerDosUltimasLineas();
   }
   
   // método que almacena 4 lineas
   public static void obtenerCuatroUltimasLineas(){
      System.out.println("sombreo el pino al abedul,");
      // llamada al método obtenerTresUltimasLineas()
      obtenerTresUltimasLineas();
   }
   
   // método que almacena 5 lineas
   public static void obtenerCincoUltimasLineas(){
      System.out.println("sombreo el cedro al pino,");
      // llamada al método obtenerCuatroUltimasLineas()
      obtenerCuatroUltimasLineas();
   }

}