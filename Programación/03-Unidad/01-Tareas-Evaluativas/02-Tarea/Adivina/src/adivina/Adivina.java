package adivina;
 /******************************************************************************************************************
 * 
 * Nombre: Jorge Martin Del Pino Contreras 
 * Fecha: 29/11/2023 
 * Modulo: Programación. 
 * UD3. 
 * Tarea: PROG03 - Tarea evaluativa 02: Programa que permite generar un número al azar del 1 al 100 (entero) y que da pistas 
 *                                      para que el usuario o usuaria lo adivine.
 * Autoevaluación: https://docs.google.com/document/d/1xkVRlcg0wye72I8R50NGbhv0RFmhVOwKvQKIdGTw7WI/edit?usp=sharing
 *                 https://youtu.be/UAYlU56cfPo
 * Descripción del programa: Se imprime por consola una breve introducción para conocer el funcionamiento u objetivo global del programa.
 *                           El programa generara un número aleatorio y luego el usuario debe introducir un numero intentando adivinarlo.
 *                           Para ello tendremos un total de 10 intentos y por cada numero introducido que no sea el correcto, el programa
 *                           nos dara pistas diciendonos si el número a adivinar es mayor o menor y asi hasta acabar todos los intentos o 
 *                           adivinar el número.
 *                           Despues de acabar los intentos o adivinar el número, se imprimira por consola las estadisticas de las partidas
 *                           jugadas.
 *
 *******************************************************************************************************************/

// Libreria importada para hacer uso de la clase Scanner
import java.util.Scanner;
import java.util.Random;

// Creamos la clase en la cual el nombre debe coincidir con el nombre del fichero .java
public class Adivina {

    static int contadorPartidasGanadas = 0;
    static final int MAXIMO = 100;
    static final int LIMITE_INTENTOS = 10;

    // Creamos el método main ya que siempre debe existir y siempre se define igual
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        mostrarInstrucciones();
        iniciarJuego(sc);
        sc.close();
    }

    // Muestra una introducción de como funciona el programa
    public static void mostrarInstrucciones() {
        System.out.println("El programa genera un número aleatorio del 1 al " + MAXIMO);
        System.out.println("El usuario o usuaria debe adivinarlo en máximo " + LIMITE_INTENTOS + " intentos.");
        System.out.println("El programa le dará pistas cada vez que falle");
    }
    
    /* Permite generar un número aleatorio usando una semilla.
     * 
     * @param limite número hasta donde se podran generar números 
     * @return el número generado desde 1 hasta el limite introducido
     */
    public static int generarNumeroAleatorio(int limite) {
        Random r = new Random(20);
        return r.nextInt(limite) + 1;
    }

    /* Permite pedir un número
     * 
     * @param sc objeto que usamos para leer datos desde el teclado
     * @return el número introducido
     */
    public static int pedirNumero(Scanner sc) {
        System.out.print("Adivina el número: ");
        return sc.nextInt();
    }

    /* Permite dar pistas a los usuarios
     * 
     * @param numeroIntroducido el número introducido por el usuario o usuaria
     * @param numeroGenerado el número generado aleatoriamente
     */
    public static void darPistas(int numeroIntroducido, int numeroGenerado) {
        if (numeroIntroducido > numeroGenerado) {
            System.out.println("\nEl número es menor.");
        } else if (numeroIntroducido < numeroGenerado) {
            System.out.println("\nEl número es mayor.");
        }
    }

    /* Permite calcular la media de intentos entre todas las partidas jugadas
     * 
     * @param partidasJugadas número total de partidas jugadas
     * @param intentosTotalesRealizados número total de intentos realizados entre todas las partidas
     * @return la medía de intentos
     */
    public static double calcularMediaIntentosPorPartida(int partidasJugadas, int intentosTotalesRealizados) {
        return intentosTotalesRealizados / (double) partidasJugadas;
    }

    /* Permite establecer los intentos de la mejor partida y obtenerlos.
     * 
     * @param contadorIntentosMejorPartida número de intentos totales de la mejor partida
     * @param contaodrIntentosRealizadosUltimaPartida número de intentos totales de la última partida
     * @return el número total de intentos de la mejor partida
     */
    public static int obtenerIntentosMejorPartida(int contadorIntentosMejorPartida, int contadorIntentosRealizadosUltimaPartida) {
        if (contadorIntentosMejorPartida >= contadorIntentosRealizadosUltimaPartida) {
            contadorIntentosMejorPartida = contadorIntentosRealizadosUltimaPartida;
        }
        return contadorIntentosMejorPartida;
    }

    /* Permite mostrar las estadiscticas finales del juego
     * 
     * @param partidasJugadas número total de partidas jugadas
     * @param intentosTotalesRealizados número de intentos totales realizados durante todo el juego
     * @param contadorIntentosMejorPartida número de intentos de la mejor partida
     */
    public static void mostrarEstadisticas(int partidasJugadas, int intentosTotalesRealizados, int contadorIntentosMejorPartida) {
        System.out.println("\n\nResultados del juego:");
        System.out.println("Partidas jugadas: " + partidasJugadas);
        System.out.println("Intentos realizados: " + intentosTotalesRealizados);
        System.out.println("Partidas ganadas: " + contadorPartidasGanadas);
        System.out.printf("Intentos por partida: %.1f%n", calcularMediaIntentosPorPartida(partidasJugadas, intentosTotalesRealizados));
        System.out.println("Mejor partida: " + contadorIntentosMejorPartida);
    }

    /* Permite jugar una partida
     * 
     * @param sc objeto que usamos para leer datos desde el teclado
     * @return el número total de intentos realizados en la partida
     */
    public static int jugarPartida(Scanner sc) {
        int contadorIntentosRealizadosPorPartida;
        int numberToGuess;
        int introducedNumber;
        boolean seguirJugando;
        boolean tienesIntentos;
        boolean sonIguales;
        contadorIntentosRealizadosPorPartida = 0;
        numberToGuess = generarNumeroAleatorio(MAXIMO);
        System.out.println("\nAdivina un número del 1 al " + MAXIMO);
        do {
            introducedNumber = pedirNumero(sc);
            darPistas(introducedNumber, numberToGuess);
            sonIguales = introducedNumber == numberToGuess;
            contadorIntentosRealizadosPorPartida++;
            if (sonIguales) {
                System.out.println("\nHas adivinado en " + contadorIntentosRealizadosPorPartida + " intentos.");
                contadorPartidasGanadas++;
                break;
            }
            tienesIntentos = contadorIntentosRealizadosPorPartida != LIMITE_INTENTOS;
            if (!tienesIntentos) {
                System.out.println("No has adivinado el número secreto: " + numberToGuess);
            }
            seguirJugando = tienesIntentos && !sonIguales;
        } while (seguirJugando);
        return contadorIntentosRealizadosPorPartida;
    }

    /* Permite comprobar la respuesta introducida por el usuario o usuaria
     * 
     * @param sc objeto que usamos para leer datos desde el teclado
     * @return true o false
     */
    public static boolean comprobarRespuesta(Scanner sc) {
        System.out.print("¿Quieres jugar otra vez (S/N)? ");
        return sc.next().toLowerCase().charAt(0) == 's';
    }

    /* Permite iniciar el juego para jugar todas las partidas que queramos
     * 
     * @param sc objeto que usamos para leer datos desde el teclado
     */
    public static void iniciarJuego(Scanner sc) {
        int partidasJugadas = 0;
        int contadorIntentosTotales = 0;
        int contadorIntentosMejorPartida = LIMITE_INTENTOS;
        int contadorIntentosRealizadosUltimaPartida;
        boolean jugarOtraPartida;
        do {
            partidasJugadas++;
            contadorIntentosRealizadosUltimaPartida = jugarPartida(sc);
            contadorIntentosMejorPartida = obtenerIntentosMejorPartida(contadorIntentosMejorPartida, contadorIntentosRealizadosUltimaPartida);
            contadorIntentosTotales += contadorIntentosRealizadosUltimaPartida;
            jugarOtraPartida = comprobarRespuesta(sc);
        } while (jugarOtraPartida);
        mostrarEstadisticas(partidasJugadas, contadorIntentosTotales, contadorIntentosMejorPartida);
    }
}
