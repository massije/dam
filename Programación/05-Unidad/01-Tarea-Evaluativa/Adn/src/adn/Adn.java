package adn;

import java.util.*;
import java.io.*;
import java.text.DecimalFormat;

public class Adn {

    static final int NUMBER_OF_FILES = 2;
    static final char[] NUCLEOTIDES = {'A', 'C', 'G', 'T', '-'};
    static final double[] NUCLEOTIDE_MASSES = {135.128, 111.103, 151.128, 125.107, 100.000}; 

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        programa(sc);

    }

    public static void programa(Scanner scKeyboard) {
        displayIntroduction();
        // primera posicion es el archivo de entrada
        // segunda posicion es el archivo de salida
        String[] fileNamesList = getFileNames(scKeyboard);
        System.out.println("");
        openFile(fileNamesList[0]);
        System.out.println("nombre del fichero de salida: " + fileNamesList[1]);
        //System.out.println(Arrays.toString(fileNamesList));
    }

    public static void displayIntroduction() {
        System.out.println("Este programa genera información sobre");
        System.out.println("secuencias de nucleótidos de ADN contenidas en un fichero");
        System.out.println("También indicará si pueden codificar proteinas o no");
        System.out.println("Todos los resultados se guardarán en un fichero\n");
    }

    public static String getFileName(Scanner scKeyboard) {
        System.out.print("Introduce el nombre del fichero: ");
        return scKeyboard.nextLine();
    }

    public static String[] getFileNames(Scanner scKeyboard) {
        String[] fileNames = new String[NUMBER_OF_FILES];
        for (int index = 0; index < fileNames.length; index++) {
            fileNames[index] = getFileName(scKeyboard);
        }
        return fileNames;
    }

    public static Scanner getFileConnection(String fileName) throws FileNotFoundException {
        File inputFile = new File(fileName);
        return new Scanner(inputFile);
    }

    public static void openFile(String inputFile) {
        Scanner scFile = null;
        String processedSamples;
        try {
            scFile = getFileConnection(inputFile);
            processedSamples = processFile(scFile);
            System.out.println(processedSamples);
        } catch (FileNotFoundException e) {
            System.out.println("no se puede abrir el archivo");
        }
    }
    
    public static String processFile(Scanner scFile) {
        String[] sample;
        String processedSample = "";
        while(scFile.hasNextLine()){
            sample = getSample(scFile);
            processedSample += processSample(sample);
        }
        return processedSample;
    }
    
    public static String[] getSample(Scanner scFile) {
        String[] completeSample = new String[2];        
        for (int index = 0; index < completeSample.length; index++) {
            completeSample[index] = scFile.nextLine();
        }
        return completeSample;
    }
    
    public static String processSample(String[] sample) {
        int[] nucleotideCounters = getCounters(sample[1]);
        String processedSample = "";
        String description = "Descripción: " + sample[0] + "\n";
        String sequence = "Nucleótidos: " + sample[1].toUpperCase() + "\n";
        String counters = "Contadores: " + Arrays.toString(Arrays.copyOf(nucleotideCounters, nucleotideCounters.length-1)) + "\n";
        double totalMass = calculateTotalMass(nucleotideCounters);
        String mass = "Masa (%): " + Arrays.toString(getMasses(nucleotideCounters, totalMass)) + " of " + roundToOneDecimal(totalMass) + "\n";
        String codonList = "Codones: \n";
        String isProtein = "Es proteína: Si\n\n";
        processedSample += description + sequence + counters + mass + codonList + isProtein;
        return processedSample;
    }
    
    public static int[] getCounters(String sequence){
        int[] counters = new int[NUCLEOTIDES.length];
        for (int i = 0; i < counters.length; i++) {
            counters[i] = countRepeatedLetter(sequence, i);
        }
        return counters;
    }
    
    public static int countRepeatedLetter(String sequence, int index){
        int count = 0;
        for(int i = 0; i < sequence.length(); i++) {
            if (NUCLEOTIDES[index] == sequence.toUpperCase().charAt(i)){
                count++;
            }
        }
        return count;
    }
    
    public static double calculateTotalMass(int[] counters){
        double total = 0.0;
        for (int i = 0; i < counters.length; i++) {
            if (counters[i] != 0) {
                total += counters[i] * NUCLEOTIDE_MASSES[i];
            }
        }
        
        return total;
    }
    
    public static String roundToOneDecimal(double quantity) {
        DecimalFormat df = new DecimalFormat("0.0");
        return df.format(quantity);
    }
    
    public static double calculateMassOfNucleotide(int quantity, double mass, double totalMass) {
        double nucleotideMass = (quantity * mass * 100) / totalMass;
        return nucleotideMass;
    }
    
    public static String[] getMasses(int[] counters, double totalMass) {
        String[] masses = new String[NUCLEOTIDE_MASSES.length-1];
        double mass = 0.0;
        for (int i = 0; i < masses.length; i++) {
            if (counters[i] != 0){
                mass = calculateMassOfNucleotide(counters[i], NUCLEOTIDE_MASSES[i], totalMass);
                masses[i] = roundToOneDecimal(mass);
            } else {
                masses[i] = "0.0";
            }
        }
        return masses;
    }
    
}
