package cuentoslocos;

import java.util.*;
import java.io.*;

public class CuentosLocos {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(System.in);
        handleMenu(sc);
        sc.close();
    }

    public static void displayIntroduction() {
        System.out.println("Bienvenidos y bienvenidas al juego de los cuentos locos.");
        System.out.println("El programa te pedirá que introduxcas una serie de palabras");
        System.out.println("que se utilizarán para completar una historia.");
        System.out.println("El resultado se guardará en un fichero.");
        System.out.println("Puedes leer esas historias siempre que quieras.");
    }

    public static void displayMenu() {
        System.out.println();
        System.out.println("******* MENU *******");
        System.out.println("(C)rear un  \"Mad Lib\"");
        System.out.println("(V)er un \"Mad Lib\"");
        System.out.println("(S)alir");
    }

    public static char getUserChoice(Scanner sc) {
        System.out.print("Elija su opción: ");
        return sc.nextLine().toUpperCase().charAt(0);
    }

    public static boolean isValidChoice(char choice) {
        return choice == 'C' || choice == 'V' || choice == 'S';
    }

    public static void handleMenu(Scanner scKeyboard) throws FileNotFoundException {
        char userChoice;
        displayIntroduction();
        do {
            displayMenu();
            userChoice = getUserChoice(scKeyboard);
            if (isValidChoice(userChoice)) {
                switch (userChoice) {
                    case 'C':
                        System.out.println("\nhas elegido crear un mad lib");
                        createStory(scKeyboard);
                        break;
                    case 'V':
                        System.out.println("\nhas elegido ver un mad lib");
                        readStory(scKeyboard);
                        break;
                    case 'S':
                        System.out.println("\nsaliendo del programa");
                        break;
                }
            } else {
                System.out.println("\nopción incorrecta");
            }
        } while (userChoice != 'S');
    }

    public static void readStory(Scanner scKeyboard) throws FileNotFoundException {
        System.out.println("Ver un cuento:");
        Scanner scFile = getFileConnection(scKeyboard);
        readFile(scFile);
    }
    
    public static void readFile(Scanner scFile) {
        while(scFile.hasNextLine()){
            System.out.println(scFile.nextLine());
        }
    }

    public static void createStory(Scanner scKeyboard) throws FileNotFoundException {
        System.out.println("Crear un cuento:");
        Scanner scFile = getFileConnection(scKeyboard);
        System.out.print("Nombre del fichero de salida: ");
        String fileName = scKeyboard.nextLine();
        String processedText = processFile(scFile, scKeyboard);
        scFile.close();
        writeToFile(fileName, processedText);
        
    }
    
    public static void writeToFile(String fileName, String text) throws FileNotFoundException {
        String pathName = "./Files/" + fileName;
        File fichSalida = new File(pathName);
        PrintStream wtFile = new PrintStream(fichSalida);
        wtFile.println(text);
    }

    public static String processFile(Scanner scFile, Scanner scKeyboard) {
        String newText = "";
        while (scFile.hasNextLine()) {
            String extractedLine = scFile.nextLine();
            newText += processLine(scKeyboard, extractedLine);
        }
        return newText;
    }

    public static String processLine(Scanner scKeyboard, String text) {
        Scanner lineToProcess = new Scanner(text);
        String newText = "";
        String extractedWord;
        boolean isContained;
        if (lineToProcess.hasNext()) {
            extractedWord = lineToProcess.next();
            isContained = extractedWord.contains("<") && extractedWord.contains(">");
            if (isContained) {
                newText += processWord(scKeyboard, extractedWord);
            } else {
                newText += extractedWord;
            }

            while (lineToProcess.hasNext()) {
                extractedWord = lineToProcess.next();
                isContained = extractedWord.contains("<") && extractedWord.contains(">");
                if (isContained) {
                    newText += " " + processWord(scKeyboard, extractedWord);
                } else {
                    newText += " " + extractedWord;//processWord(extractedWord);
                }
            }
            newText += "\n";
        }
        return newText;
    }

    
    public static String processWord(Scanner scKeyboard, String word) {
        String processedWord = "";
        String processToWord = word.replaceAll("-", " ");
        int indexOpenTag = processToWord.indexOf("<");
        int indexCloseTag = processToWord.indexOf(">");
        String tag = processToWord.substring(indexOpenTag + 1, indexCloseTag);
        processedWord += getUserResponse(scKeyboard, tag);
        return processedWord;
    }
    
    public static String getUserResponse(Scanner scKeyboard, String tag){
        System.out.print(tag + ": ");
        String response = scKeyboard.nextLine();
        return response;
    }
     
    public static Scanner getFileConnection(Scanner scKeyboard) throws FileNotFoundException {
        System.out.print("Nombre del fichero que quieres leer: ");
        String fileName = scKeyboard.nextLine();
        String pathName = "./Files/" + fileName;
        File fichEntrada = new File(pathName);
        while (!fichEntrada.canRead()) {
            System.out.println("Fichero no encontrado. Inténtalo otra vez");
            System.out.print("Nombre del fichero: ");
            fileName = scKeyboard.nextLine();
            pathName = "./Files/" + fileName;
            fichEntrada = new File(pathName);
        }
        return new Scanner(fichEntrada);
    }

}
