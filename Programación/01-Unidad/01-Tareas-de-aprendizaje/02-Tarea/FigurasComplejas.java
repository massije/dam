// Jorge Martin Del Pino Contreras
// DAM
// Curso 2023

import java.lang.*;

// Dibuja 2 rombos y una equis(x)
public class FigurasComplejas {
   public static void main(String[] args){
      rombo();
      System.out.println("");
      rombo();
      System.out.println("");
      equis();
   }
   
   public static void dibujarV(){
      System.out.println("\\    /");
      System.out.println(" \\  / ");
      System.out.println("  \\/  ");
   }
   
   public static void dibujarVInvertida(){
      System.out.println("  /\\  ");
      System.out.println(" /  \\ ");
      System.out.println("/    \\");
   }
   
   /* Metodo que llama a otros 2 metodos para contruir el rombo y
   asi evitar la redundancia */
   public static void rombo(){
      dibujarVInvertida();
      dibujarV();
   }
   
   /* Metodo que llama a otros 2 metodos para contruir la equis y
   asi evitar la redundancia */
   public static void equis(){
      dibujarV();
      dibujarVInvertida();
   }
}