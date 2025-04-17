package controlador;

import modelo.ArbolBinario;
import modelo.ArbolPorResiduos;
import modelo.ArbolPorResiduosMultiples;
import modelo.HuffmanCompleto;

import java.util.Scanner;

public class ControladorArboles {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingresa una palabra para probar todos los árboles: ");
        String palabra = scanner.nextLine();

        System.out.println("\n=== Árbol Binario ===");
        probarArbolBinario(palabra);

        System.out.println("\n=== Árbol por Residuos ===");
        probarArbolPorResiduos(palabra);

        System.out.println("\n=== Árbol por Residuos Múltiples ===");
        probarArbolPorResiduosMultiples(palabra);

        System.out.println("\n=== Árbol de Huffman ===");
        probarHuffman(palabra);

        scanner.close();
    }

    private static void probarArbolBinario(String palabra) {
        ArbolBinario arbol = new ArbolBinario();

        for (char letra : palabra.toCharArray()) {
            int ascii = (int) letra;
            String binario = Integer.toBinaryString(ascii);
            arbol.insertar(binario, ascii);
        }

        System.out.print("Recorrido Preorden: ");
        arbol.imprimirPreorden(arbol.raiz);
        System.out.println();
    }

    private static void probarArbolPorResiduos(String palabra) {
        ArbolPorResiduos.ArbolBinario arbol = new ArbolPorResiduos.ArbolBinario();

        for (char letra : palabra.toLowerCase().toCharArray()) {
            if (Character.isLetter(letra)) {
                arbol.insertarConInfo(letra);
            }
        }

        arbol.imprimirInOrden();
        arbol.imprimirPorNiveles();
    }

    private static void probarArbolPorResiduosMultiples(String palabra) {
        ArbolPorResiduosMultiples.Arbol arbol = new ArbolPorResiduosMultiples.Arbol();

        for (char letra : palabra.toCharArray()) {
            arbol.insertar(letra);
        }

        System.out.println("Árbol n-ario:");
        arbol.imprimir(arbol.raiz, "");

        System.out.print("Recorrido Preorden: ");
        var resultado = new java.util.ArrayList<Character>();
        arbol.recorrerYGuardar(arbol.raiz, resultado);
        for (char c : resultado) {
            System.out.print(c + " ");
        }
        System.out.println();
    }

    private static void probarHuffman(String palabra) {
        HuffmanCompleto.procesarFrase(palabra); // Lo haremos ahora.
    }
}
