package adn;
 /******************************************************************************************************************
 * 
 * Nombre: Jorge Martin Del Pino Contreras 
 * Fecha: 17/01/2024 
 * Modulo: Programación. 
 * UD5. 
 * Tarea: PROG05 - Tarea evaluativa 01: Programa que permite generar información sobre secuencias de nucleótidos de ADN contenidas en un fichero.
 * Autoevaluación: https://docs.google.com/document/d/1sVMCq0xk0sF8AvT71PH7stX-zkR4ZbGAJCZ5k_IC-Cw/edit?usp=sharing
 *                 https://youtu.be/gcK01-8ayFU
 * Descripción del programa: Se imprime por consola una breve introducción para conocer el funcionamiento u objetivo global del programa.
 *                           Despues el programa pedira el nombre de 2 archivos, el primero sera el archivo que queremos abrir para luego procesarlo
 *                           y el segundo sera el nombre del archivo destino donde se guardara la información procesada correctamente y lo que se guarde
 *                           en el archivo, tambien se mostrara por consola.
 * 
 *******************************************************************************************************************/

// Libreria importadas para hacer uso de la clase Scanner, File y PrintStream y DecimalFormat.
import java.util.*;
import java.io.*;
import java.text.DecimalFormat;

// Creamos la clase en la cual el nombre debe coincidir con el nombre del fichero .java.
public class Adn {

    static final int NUMBER_OF_FILES = 2;
    static final int MINIMUM_CODON_COUNT = 5;
    static final double PERCENTAGE_CG = 30;
    static final int NUCLEOTIDES_PER_CODON = 3;
    static final char[] NUCLEOTIDES = {'A', 'C', 'G', 'T', '-'};
    static final double[] NUCLEOTIDE_MASSES = {135.128, 111.103, 151.128, 125.107, 100.000};
    static final String[] VALID_CODONS = {"ATG", "TAA", "TAG", "TGA"};
    
    // Creamos el método main ya que siempre debe existir y siempre se define igual.
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        executeProgram(sc);
        sc.close();
    }
    
    // Empieza a ejecutar el programa.
    public static void executeProgram(Scanner scKeyboard) {
        displayIntroduction();
        String[] fileNamesList = getFileNames(scKeyboard);
        System.out.println("");
        processAndSaveFiles(fileNamesList[0], fileNamesList[1]);
    }

    // Muestra una introducción de como funciona el programa.
    public static void displayIntroduction() {
        System.out.println("\nEste programa genera información sobre");
        System.out.println("secuencias de nucleótidos de ADN contenidas en un fichero");
        System.out.println("También indicará si pueden codificar proteinas o no");
        System.out.println("Todos los resultados se guardarán en un fichero\n");
    }

    /* Permite introducir el nombre del archivo de entrada a procesar.
     * 
     * @param scKeyboard objeto que usamos para leer datos desde el teclado 
     * @return el nombre del archivo
     */
    public static String getFileName(Scanner scKeyboard) {
        System.out.print("Introduce el nombre del fichero: ");
        return scKeyboard.nextLine();
    }
    
    /* Permite introducir los nombres de los archivos que usaremos en el programa.
     * 
     * @param scKeyboard objeto que usamos para leer datos desde el teclado 
     * @return una lista con los nombres de archivo
     */
    public static String[] getFileNames(Scanner scKeyboard) {
        String[] fileNames = new String[NUMBER_OF_FILES];
        for (int index = 0; index < fileNames.length; index++) {
            fileNames[index] = getFileName(scKeyboard);
            System.out.println("");
        }
        return fileNames;
    }

    /* Permite establecer la conexión con un archivo ya existente
     * 
     * @param fileName nombre del archivo que abriremos
     * @return objeto Scanner conectado con el fichero
     */
    public static Scanner getFileConnection(String fileName) throws FileNotFoundException {
        File inputFile = new File(fileName);
        return new Scanner(inputFile);
    }
    
    /* Permite procesar el fichero de entrada y luego escribir lo procesado en un nuevo fichero.
     * 
     * @param inputFile nombre del fichero que abriremos para procesar
     * @param outputFile nombre del fichero donde escribiremos lo procesado
     */
    public static void processAndSaveFiles(String inputFile, String outputFile) {
        Scanner scFile = null;
        String processedSamples;
        try {
            scFile = getFileConnection(inputFile);
            processedSamples = processFile(scFile);
            PrintStream output = null;
            try {
                output = new PrintStream(new File(outputFile));
                writeTo(System.out, processedSamples);
                writeTo(output, processedSamples);
            }catch(FileNotFoundException e){
                errorMessage();
            }
            output.close();
        } catch (FileNotFoundException e) {
            errorMessage();
        }
        scFile.close();

    }
    
    // Muestra los mensajes de error que se daran a los usuarios o usuarias.
    public static void errorMessage() {
        System.out.println("Error: No se ha encontrado el archivo especificado para lectura o escritura.");
        System.out.println("Asegúrate de ingresar un nombre de archivo válido");
    }

    /* Permite processar el archivo con las secuencias de ADN completamente.
     * 
     * @param scFile objeto que usamos para leer datos desde un archivo
     * @return el texto procesado completamente
     */
    public static String processFile(Scanner scFile) {
        String[] sample;
        String processedSample = "";
        while (scFile.hasNextLine()) {
            sample = getSample(scFile);
            processedSample += processSample(sample);
        }
        return processedSample;
    }

    /* Permite extraer una muestra de ADN del archivo.
     * 
     * @param scFile objeto que usamos para leer datos desde un archivo
     * @return una lista de tamaño dos en la que se guarda la descripcion del ADN y su secuencia
     */
    public static String[] getSample(Scanner scFile) {
        String[] completeSample = new String[2];
        for (int index = 0; index < completeSample.length; index++) {
            completeSample[index] = scFile.nextLine();
        }
        return completeSample;
    }

    /* Permite processar una muestra de ADN.
     * 
     * @param sample lista que contiene los datos de una muestra de ADN
     * @return la muestra procesada en forma de cadena concatenada
     */
    public static String processSample(String[] sample) {
        int[] nucleotideCounters = getCounters(sample[1]);
        double totalMass;
        String[] masses;
        String[] codons;
        String sequenceToProcess = sample[1].toUpperCase();
        String processedSample = "";
        String description = "Descripción: " + sample[0] + "\n";
        String sequence = "Nucleótidos: " + sequenceToProcess + "\n";
        String counters = "Contadores: " + Arrays.toString(Arrays.copyOf(nucleotideCounters, nucleotideCounters.length - 1)) + "\n";
        totalMass = calculateTotalMass(nucleotideCounters);
        masses = getMasses(nucleotideCounters, totalMass);
        String mass = "Masa (%): " + Arrays.toString(masses) + " de " + roundToOneDecimal(totalMass) + "\n";
        codons = getCodons(sequenceToProcess);
        String codonList = "Lista Codones: " + Arrays.toString(codons) + "\n"; // aqui precesar secuencia
        String protein = "Es proteína: " + isProtein(codons, masses) + "\n\n";// aquí pasar lista de codones
        processedSample += description + sequence + counters + mass + codonList + protein;
        return processedSample;
    }

    /* Permite obtener una lista de contadores con el numero de veces totales  que se han repetido los nucleótidos en la secuencia de ADN
     * 
     * @param sequence secuencia de ADN de una muestra
     * @return la lista de contadores
     */
    public static int[] getCounters(String sequence) {
        int[] counters = new int[NUCLEOTIDES.length];
        for (int i = 0; i < counters.length; i++) {
            counters[i] = countRepeatedLetter(sequence, i);
        }
        return counters;
    }
    
    /* Permite obtener el numero de veces que se repite un nucleótido en una secuencia de ADN.
     * 
     * @param sequence secuencia de ADN de una muetra
     * @param index posición para saber con que nucleótido comparar
     * @return el numero de veces que se ha repetido un nucleótido
     */
    public static int countRepeatedLetter(String sequence, int index) {
        int count = 0;
        for (int i = 0; i < sequence.length(); i++) {
            if (NUCLEOTIDES[index] == sequence.toUpperCase().charAt(i)) {
                count++;
            }
        }
        return count;
    }

    /* Permite calcular la masas total de los nucleótidos.
     * 
     * @param counters lista que contiene las  veces que se han repetido los nucleótidos en una secuencia
     * @return la masa total de la secuencia de ADN
     */
    public static double calculateTotalMass(int[] counters) {
        double total = 0.0;
        for (int i = 0; i < counters.length; i++) {
            if (counters[i] != 0) {
                total += counters[i] * NUCLEOTIDE_MASSES[i];
            }
        }
        return total;
    }

    /* Permite redondear una cantidad a un decimal.
     * 
     * @param quantity cantidad que se va a redondear
     * @return la cantidad redondeada en forma de cadena
     */
    public static String roundToOneDecimal(double quantity) {
        DecimalFormat df = new DecimalFormat("0.0");
        return df.format(quantity);
    }

    /* Permite calcular cuanto porcentaje de masa de un solo nucleótido hay en una secuencia de ADN.
     * 
     * @param quantity numero de veces que se ha repetido un nucleótido en la secuencia de ADN
     * @param mass porcentaje de masa que le corresponde al nucleótido a tratar
     * @param totalMass masa total que hay en la secuencia de ADN
     * @return la masa del nucleótido
     */
    public static double calculateMassOfNucleotide(int quantity, double mass, double totalMass) {
        double nucleotideMass = (quantity * mass * 100) / totalMass;
        return nucleotideMass;
    }

    /* Permite obtener una lista con todas las masas de los nucleótidos que hay en una secuencia de ADN.
     * 
     * @param counters lista que contiene los contadores del numero de veces que se ha repedito cada nucleótido en la secuencia de ADN
     * @param totalMass masa total que hay en la secuencia de ADN
     * @return la cadena de caracteres procesada
     */
    public static String[] getMasses(int[] counters, double totalMass) {
        String[] masses = new String[NUCLEOTIDE_MASSES.length - 1];
        double mass;
        for (int i = 0; i < masses.length; i++) {
            if (counters[i] != 0) {
                mass = calculateMassOfNucleotide(counters[i], NUCLEOTIDE_MASSES[i], totalMass);
                masses[i] = roundToOneDecimal(mass);
            } else {
                masses[i] = "0.0";
            }
        }
        return masses;
    }
    
    /* Permite obtener una lista con todos los codones que hay en la secuencia de ADN.
     * 
     * @param sequence secuencia de una muestra de ADN
     * @return la lista con todos los codones obtenidos
     */
    public static String[] getCodons(String sequence) {
        String[] codons;
        boolean containsHyphen = sequence.contains("-");
        int sizeOfSequence;
        int numberOfCodons = 0;
        int index = 0;
        String codon = "";
        String sequenceToProcess;
        if (containsHyphen) {
            sequenceToProcess = sequence.replaceAll("-", "");
        } else {
            sequenceToProcess = sequence;
        }
        sizeOfSequence = sequenceToProcess.length();
        if (sizeOfSequence % NUCLEOTIDES_PER_CODON == 0) {
            numberOfCodons = sizeOfSequence / NUCLEOTIDES_PER_CODON;
        }
        codons = new String[numberOfCodons];
        for (int i = 0; i < numberOfCodons; i++) {
            for (int j = 0; j < NUCLEOTIDES_PER_CODON; j++) {
                codon += sequenceToProcess.charAt(index);
                index++;
            }
            codons[i] = codon;
            codon = "";
        }
        return codons;
    }

    /* Permite averiguar si una muestra de ADN es proteina o no.
     * 
     * @param codons lista con todos los codones hallados en una secuencia de ADN
     * @param masses lita con todas las masas calculadas que hay de cada nucleótido
     * @return una cadena diciendo si es o no es proteina
     */
    public static String isProtein(String[] codons, String[] masses) {
        String protein = "NO";
        double percentage = 0;
        if (codons.length >= MINIMUM_CODON_COUNT) {
            for (int i = 1; i <= 2; i++) {
                percentage += Double.parseDouble(masses[i]);
            }
            if (percentage >= PERCENTAGE_CG) {
                if (codons[0].equals(VALID_CODONS[0]) && (codons[codons.length - 1].equals(VALID_CODONS[1]) || codons[codons.length - 1].equals(VALID_CODONS[2]) || codons[codons.length - 1].equals(VALID_CODONS[3]))) {
                    protein = "SI";
                }
            }
        }
        return protein;
    }

    /* Permite escribir el texto procesado en el objeto que le especifiquemos.
     * 
     * @param output objeto donde se escribira el texto procesado
     * @param text texto que se escribira en dicho objeto
     */
    public static void writeTo(PrintStream output, String text) {
        Scanner scText = new Scanner(text);
        PrintStream wtFile = new PrintStream(output);
        while (scText.hasNext()) {
            String extractedLine = scText.nextLine();
            wtFile.println(extractedLine);
        }
    }
}
