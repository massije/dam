/******************************************************************************************************************
 * 
 * Nombre: Jorge Martin Del Pino Contreras 
 * Fecha: 27/09/2023 
 * Modulo: Programaci�n. 
 * UD1. 
 * Tarea: PROG01 - Tarea evaluativa 01: Programa que crea una cancion acumulativa 
 * Autoevaluaci�n: https://docs.google.com/document/d/1DBTdnwSXswXfPFMnVhDx_p97KwrZACyxzzyGTcJJCzc/edit?usp=sharing
 * Descripci�n del programa: El programa imprime un primer parrafo de 4 l�neas, al cual se le va a�adiendo 1 lineas m�s
 *                           en la siguiente impresion o salida por pantalla y de esa forma se va construyendo nuestra 
 *                           canci�n acumulativa, hasta llegar al �ltimo parrafo con un total de 8 l�neas impresas.
 *
 *******************************************************************************************************************/

// creamos la clase. El nombre debe coincidir con el del fichero .java
public class RinconSombra {
   // Creamos m�todo main. Siempre debe existir y siempre se define igual.
	public static void main(String[] args) {
      
      // llamada al m�todo imprimirPrimerParrafo()
      imprimirPrimerParrafo();
      
      // llamada al m�todo imprimirSegundoParrafo()
      imprimirSegundoParrafo();
      
      // llamada al m�todo imprimirTercerParrafo()
      imprimirTercerParrafo();
      
      // llamada al m�todo imprimirCuartoParrafo()
      imprimirCuartoParrafo();
      
      // llamada al m�todo imprimirQuintoParrafo()
      imprimirUltimoParrafo(); 
      
   } 
   
   // m�todo que imprime un parrafo con 4 l�neas
   public static void imprimirPrimerParrafo(){
      System.out.println("Se encontraba el sauce en su rincon,");
      System.out.println("Vino el roble y le hizo sombra,");
      // llamada al m�todo obtenerDosUltimasLineas()
      obtenerDosUltimasLineas();
   }
   
   // m�todo que imprime un parrafo con 5 l�neas
   public static void imprimirSegundoParrafo(){
      System.out.println("Se encontraba el roble en su rincon,");
      System.out.println("Vino el abedul y le hizo sombra,");
      // llamada al m�todo obtenerTresUltimasLineas()
      obtenerTresUltimasLineas();
   }
   
   // m�todo que imprime un parrafo con 6 l�neas
   public static void imprimirTercerParrafo(){
      System.out.println("Se encontraba el abedul en su rincon,");
      System.out.println("Vino el pino y le hizo sombra,");
      // llamada al m�todo obtenerCuatroUltimasLineas()
      obtenerCuatroUltimasLineas();
   }
   
   // m�todo que imprime un parrafo con 7 l�neas
   public static void imprimirCuartoParrafo(){
      System.out.println("Se encontraba el pino en su rincon,");
      System.out.println("Vino el cedro y le hizo sombra,");
      // llamada al m�todo obtenerCincoUltimasLineas()
      obtenerCincoUltimasLineas();
   }
   
   // m�todo que imprime un parrafo con 8 l�neas
   public static void imprimirUltimoParrafo(){
      System.out.println("Se encontraba el cedro en su rincon,");
      System.out.println("Vino el haya y le hizo sombra,");
      System.out.println("Sombreo el haya al cedro,");
      // llamada al m�todo obtenerCincoUltimasLineas()
      obtenerCincoUltimasLineas();
   }
   
   // m�todo que almacena 2 lineas
   public static void obtenerDosUltimasLineas(){
      System.out.println("Sombreo el roble al sauce,");
      System.out.println("El sauce a la sombra se quedo.\n");
   }
   // m�todo que almacena 3 lineas
   public static void obtenerTresUltimasLineas(){
      System.out.println("sombreo el abedul al roble,");
      // llamada al m�todo obtenerDosUltimasLineas() 
      obtenerDosUltimasLineas();
   }
   
   // m�todo que almacena 4 lineas
   public static void obtenerCuatroUltimasLineas(){
      System.out.println("sombreo el pino al abedul,");
      // llamada al m�todo obtenerTresUltimasLineas()
      obtenerTresUltimasLineas();
   }
   
   // m�todo que almacena 5 lineas
   public static void obtenerCincoUltimasLineas(){
      System.out.println("sombreo el cedro al pino,");
      // llamada al m�todo obtenerCuatroUltimasLineas()
      obtenerCuatroUltimasLineas();
   }

}