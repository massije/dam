/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package notamedia;
 /******************************************************************************************************************
 * 
 * Nombre: Jorge Martin Del Pino Contreras 
 * Fecha: 16/11/2023 
 * Modulo: Programación. 
 * UD3. 
 * Tarea: PROG03 - Tarea evaluativa 01: Programa que permite calcular la nota media de un estudiante a partir de la calificación
 *                                      del examen parcial, el examen final y las Unidades didácticas realizadas durante el curso.
 * Autoevaluación: https://docs.google.com/document/d/1BfT4n4gCrXNkWSlcNTcxVpSokZjRU-UXfpxpXsllKcQ/edit?usp=sharing
 *                 https://youtu.be/UAYlU56cfPo
 * Descripción del programa: El programa imprime por consola una breve introducción sobre que pasos hay que seguir para obtener o calcular
 *                           la nota final de un alumno en una asignatura.Primero obtendremos o calcularemos la nota de un examen parcial,
 *                           luego la nota de un examen final siguiendo los mismos pasos para ambas, estos pasos son: primero se nos pedira la nota obtenidad
 *                           en el examen y despues nos preguntaran si hemos obtenido o no puntos extra, de esta forma calcularemos la nota del examen.
 *                           Así haremos con el otro examen para despues ponderarlas segun el porcentaje que valgan cada una y sumarlas para obtener la nota 
 *                           total de los examenes. 
 * 
 *                           Despues calcularemos la nota total de las unidades, para ello solicitaremos el número de unidades estudiadas en la asignatura 
 *                           y la nota correspondiente a cada una de ellas como tambien pediremos el peso que tiene dicha unidad sobre la nota(en porcentaje)
 *                           de tal forma que podremos obtener la nota total de las unidades. 
 * 
 *                           Al final sumaremos la nota total de los examenes con la nota total de la unidades y obtendremos la nota final real que luego 
 *                           mostraremos acompañado con un mensaje según la nota obtenida.                          
 *
 *******************************************************************************************************************/

// Libreria importada para hacer uso la clase Scanner
import java.util.Scanner;

// Creamos la clase en la cual el nombre debe coincidir con el nombre del fichero .java
public class NotaMedia {

    static final int PUNTUACION_MAXIMA = 100;
    static final int PORCENTAJE_EXAMEN_PARCIAL = 40;
    static final int PORCENTAJE_EXAMEN_FINAL = 60;
    static final int PORCENTAJE_TOTAL_EXAMENES_UNIDADES = 50;
    static final int PUNTUACION_EXCELENTE_TRABAJO = 100;
    static final int PUNTUACION_MINIMA_MUY_BUEN_TRABAJO = 85;
    static final int PUNTUACION_MINIMA_BUEN_TRABAJO = 75;
    static final int PUNTUACION_MINIMA_TRABAJO_POR_HACER = 60;

    // Creamos el método main ya que siempre debe existir y siempre se define igual.
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        mostrarIntroduccionPrograma();
        double notaAlumno1 = obtenerNotaFinalAlumno(sc);
        //double notaAlumno2 = obtenerNotaFinalAlumno(sc);
        //compararNotasAlumnos(notaAlumno1, notaAlumno2);
        sc.close();
    }

    /* Permite comparar la nota de dos alumnos y muestra la nota del alumno con mayor nota.
     * 
     * @param notaAlumno1 la nota correspondiente a un alumno
     * @param notaAlumno2 la nota correspondiente a otro alumno
     */
    public static void compararNotasAlumnos(double notaAlumno1, double notaAlumno2) {
        double notaMayor;
        System.out.println("\nNota primer alumno: " + notaAlumno1);
        System.out.println("Nota segundo alumno: " + notaAlumno2);
        if (notaAlumno1 == notaAlumno2) {
            System.out.println("\nAmbas notas son iguales");
        } else {
            if (notaAlumno1 > notaAlumno2) {
                notaMayor = notaAlumno1;
            } else {
                notaMayor = notaAlumno2;
            }
            System.out.println("\nLa nota mayor es: " + notaMayor);
        }
    }

    /* Permite obtener la nota final de un alumno en una asignatura.
     * 
     * @param sc objeto que usamos para leer datos desde el teclado
     * @return la nota final del alumno.
     */
    public static double obtenerNotaFinalAlumno(Scanner sc) {
        double notaExamenParcial = pedirNota(sc, "PARCIAL", PORCENTAJE_EXAMEN_PARCIAL);
        double notaExamenFinal = pedirNota(sc, "FINAL", PORCENTAJE_EXAMEN_FINAL);
        double notaTotalExamenes = obtenerNotaTotalExamenes(notaExamenParcial, notaExamenFinal);
        double notaTotalUnidades = pedirNotasUnidades(sc);
        double notaFinal = obtenerNotaFinal(notaTotalExamenes, notaTotalUnidades);
        mostrarEscala(notaFinal);
        return notaFinal;
    }

    /* Muestra una introducción de como funciona el programa */
    public static void mostrarIntroduccionPrograma() {
        System.out.println("\nEste programa lee las calificaciones de exámenes (parcial y final) y Unidades Didácticas"
                + "\npara calcular la nota final del módulo."
                + "\nTambién podría hacerlo para 2 estudiantes y comparar sus notas. Funcionaría sin problemas.");
    }

    /* Permite pedir la nota de un examen para luego devolver el valor ponderado según el peso(en porcentaje).
     * 
     * @param sc objeto que usamos para leer datos desde el teclado
     * @param tipoExamen título para saber de que examen estamos pidiendo la nota
     * @param tipoPorcentaje valor que tiene el examen(en porcentaje)
     * @return la nota del examen ponderada
     */
    public static double pedirNota(Scanner sc, String tipoExamen, int tipoPorcentaje) {
        int nota;
        double notaPonderada;
        System.out.println("\n" + tipoExamen + ":");
        nota = calcularNota(sc);
        notaPonderada = obtenerNotaPonderada(tipoPorcentaje, nota);
        mostrarNotas(nota, notaPonderada, tipoPorcentaje);
        return notaPonderada;
    }

    /* Muestra las nota final sin ponderar y ponderada de un alumno. 
     * 
     * @param nota la nota de un alumno sin ponderar
     * @param notaPonderada la nota ponderada de un alumno
     * @param tipoPorcentaje el peso de la asignatura
     */
    public static void mostrarNotas(int nota, double notaPonderada, int tipoPorcentaje) {
        System.out.println("\nNota final = " + nota + " / " + PUNTUACION_MAXIMA);
        System.out.printf("Nota final ponderada = %.1f / %d%n", notaPonderada, tipoPorcentaje);
    }

    /* Permite obtener la nota total(es decir la suma de sus examenes) y retorna dicho valor.
     * 
     * @param examenParcial nota del examen parcial
     * @param examenFinal nota del examen final
     * @return la nota total de los examenes
     */
    public static double obtenerNotaTotalExamenes(double examenParcial, double examenFinal) {
        double resultado;
        System.out.println("\n\nTOTAL EXAMENES");
        resultado = examenParcial + examenFinal;
        System.out.println("Nota final ponderada de los exámenes (sobre " + PUNTUACION_MAXIMA + "): " + resultado);
        return resultado;
    }

    /* Permite calcular la nota de un examen individualmente
     * 
     * @param sc objeto que usamos para leer datos desde el teclado
     * @return la nota del examen
     */
    public static int calcularNota(Scanner sc) {
        int calificacionExamen;
        int hayPuntosExtra;
        int notaFinal = 0;
        System.out.print("Introduce la calificación del examen (0-100): ");
        calificacionExamen = sc.nextInt();
        notaFinal += calificacionExamen;
        System.out.print("\n¿Has obtenido puntos extra (1=Si, 2=No)? ");
        hayPuntosExtra = sc.nextInt();
        if (hayPuntosExtra == 1) {
            notaFinal = pedirPuntosExtra(sc, notaFinal);
        }
        return notaFinal;
    }

    /* Permite pedir puntos extra y sumarlos a la nota de un examen
     * 
     * @param sc objeto que usamos para leer datos desde el teclado
     * @param nota nota de un examen
     * @return la suma de la nota original mas los puntos extra.
     */
    public static int pedirPuntosExtra(Scanner sc, int nota) {
        int puntosExtra;
        int notaFinal;
        System.out.print("\nIntroduce el total de puntos extra (0-10): ");
        puntosExtra = sc.nextInt();
        notaFinal = nota + puntosExtra;
        if (notaFinal >= 100) {
            notaFinal = 100;
        }
        return notaFinal;
    }

    /* Permite ponderar una nota.
     * 
     * @param porcentaje el peso de la nota
     * @param nota la nota del examen a ponderar
     * @return la nota ponderada.
     */
    public static double obtenerNotaPonderada(int porcentaje, double nota) {
        return ((porcentaje / 100.0) * nota);
    }

    /* Permite pedir el numero de unidades que hay en la asignatura y sus correspondientes notas.
     * 
     * @param sc objeto que usamos para leer datos desde el teclado
     * @return la nota total de las unidades
     */
    public static double pedirNotasUnidades(Scanner sc) {
        int numeroUnidades;
        double notaFinalUnidades;
        System.out.println("\nUNIDADES:");
        System.out.print("Introduce el número de unidades: ");
        numeroUnidades = sc.nextInt();
        notaFinalUnidades = obtenerTotalNotaUnidades(sc, numeroUnidades);
        return notaFinalUnidades;
    }

    /* Permite sumas todas las notas de las unidades de una asignatura.
     * 
     * @param sc objeto que usamos para leer datos desde el teclado
     * @param numeroUnidades cantidad de unidades estudiadas en una asignatura
     * @return la nota total de las unidades
     */
    public static double obtenerTotalNotaUnidades(Scanner sc, int numeroUnidades) {
        double notaTotalUnidades = 0;
        for (int unidad = 1; unidad <= numeroUnidades; unidad++) {
            notaTotalUnidades += calcularNotaUnidad(sc, unidad);
        }
        System.out.println("\n\nTOTAL UDs");
        System.out.println("Nota final ponderada de las UDs (sobre " + PUNTUACION_MAXIMA + "): " + notaTotalUnidades);
        return notaTotalUnidades;
    }

    /* Permite calcular la nota ponderada de una unidad.
     * 
     * @param sc objeto que usamos para leer datos desde el teclado
     * @param numeroUnidad la unidad de la que estamos calculando sus notas
     * @return la nota de la unidad ponderada
     */
    public static double calcularNotaUnidad(Scanner sc, int numeroUnidad) {
        int pesoUnidad;
        int puntuacionObtenida;
        System.out.println("\nUD " + numeroUnidad + ":");
        System.out.print("Introduce el peso de la UD (0-" + PUNTUACION_MAXIMA + "): ");
        pesoUnidad = sc.nextInt();
        System.out.print("\nIntroduce la puntuación obtenida (0-" + PUNTUACION_MAXIMA + "): ");
        puntuacionObtenida = sc.nextInt();
        return obtenerNotaPonderada(pesoUnidad, puntuacionObtenida);
    }

    /* Permite obtener la nota final de un alumno en una asignatura sumando la nota total de los examenes
     * con la nota total de las unidades.
     * 
     * @param notaExamenes nota total de los examenes
     * @param notaUnidades nota total de las unidades
     * @return nota final del alumno.
     */
    public static double obtenerNotaFinal(double notaExamenes, double notaUnidades) {
        double resultado;
        resultado = obtenerNotaPonderada(PORCENTAJE_TOTAL_EXAMENES_UNIDADES, notaExamenes) + obtenerNotaPonderada(PORCENTAJE_TOTAL_EXAMENES_UNIDADES, notaUnidades);
        return resultado;
    }

    /* Muestra un mensaje con una escala segun la nota final del alumno.
     * 
     * @param nota la nota final del alumno
     */
    public static void mostrarEscala(double nota) {
        double escalaExcelenteTrabajo = 4.0;
        double escalaMuyBuenTrabajo = 3.0;
        double escalaBuenTrabajo = 2.0;
        double escalaTrabajoPorHacer = 1.5;
        double escalaOtraVezSera = 0.0;
        double escala;
        String mensaje;
        if (nota == PUNTUACION_EXCELENTE_TRABAJO) {
            escala = escalaExcelenteTrabajo;
            mensaje = "Excelente trabajo";
        } else if (nota >= PUNTUACION_MINIMA_MUY_BUEN_TRABAJO) {
            escala = escalaMuyBuenTrabajo;
            mensaje = "Muy buen trabajo";
        } else if (nota >= PUNTUACION_MINIMA_BUEN_TRABAJO) {
            escala = escalaBuenTrabajo;
            mensaje = "Buen trabajo";
        } else if (nota >= PUNTUACION_MINIMA_TRABAJO_POR_HACER) {
            escala = escalaTrabajoPorHacer;
            mensaje = "Todavía tienes trabajo por hacer";
        } else {
            escala = escalaOtraVezSera;
            mensaje = "Otra vez será";
        }
        System.out.printf("%nTOTAL FINAL: %.1f%n", nota);
        System.out.println("La nota final en una escala del 0 al 4 es al menos: " + escala);
        System.out.println(mensaje);
    }
}
