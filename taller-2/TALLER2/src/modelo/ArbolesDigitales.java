package modelo;
import java.util.Scanner;

public class ArbolesDigitales {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArbolBinario arbol = new ArbolBinario();
        
        System.out.print("Ingrese una palabra: ");
        String palabra = scanner.nextLine();
        
        System.out.println("Procesando cada letra...");
        for (char letra : palabra.toCharArray()) {
            int valorAscii = (int) letra;
            String binario = Integer.toBinaryString(valorAscii);
            System.out.println(letra + " -> " + valorAscii + " -> " + binario);
            arbol.insertar(binario, valorAscii);
        }
        
        System.out.println("Recorrido preorden del arbol:");
        arbol.imprimirPreorden(arbol.raiz);
        
        scanner.close();
    }
}
