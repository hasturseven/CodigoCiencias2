package modelo;

import java.util.*;

public class ControladorArboles {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Selecciona el tipo de árbol a probar:");
        System.out.println("1. ArbolBinario (ASCII -> Binario)");
        System.out.println("2. ArbolPorResiduos (Binario por posición alfabética)");
        System.out.println("3. ArbolPorResiduosMultiples (N-ario con pares de bits)");
        System.out.println("4. Huffman (Codificación por frecuencia)");
        System.out.print("Opción: ");

        int opcion = scanner.nextInt();
        scanner.nextLine(); // limpiar buffer

        System.out.print("Ingresa una palabra o frase: ");
        String entrada = scanner.nextLine();

        switch (opcion) {
            case 1:
                pruebaArbolBinario(entrada);
                break;
            case 2:
                pruebaArbolPorResiduos(entrada);
                break;
            case 3:
                pruebaArbolPorResiduosMultiples(entrada);
                break;
            case 4:
                pruebaHuffman(entrada);
                break;
            default:
                System.out.println("Opción no válida.");
        }

        scanner.close();
    }

    static void pruebaArbolBinario(String palabra) {
        ArbolBinario arbol = new ArbolBinario();
        System.out.println("\nProcesando cada letra...");
        for (char letra : palabra.toCharArray()) {
            int valorAscii = (int) letra;
            String binario = Integer.toBinaryString(valorAscii);
            System.out.println(letra + " -> " + valorAscii + " -> " + binario);
            arbol.insertar(binario, valorAscii);
        }
        System.out.println("\nRecorrido preorden del arbol:");
        arbol.imprimirPreorden(arbol.raiz);
    }

    static void pruebaArbolPorResiduos(String palabra) {
        ArbolPorResiduos.ArbolBinario arbol = new ArbolPorResiduos.ArbolBinario();
        palabra = palabra.toLowerCase();
        System.out.println("\nProceso de inserción:");
        for (char letra : palabra.toCharArray()) {
            if (Character.isLetter(letra)) {
                arbol.insertarConInfo(letra);
            } else {
                System.out.println(letra + " ignorado (no es letra)");
            }
        }
        arbol.imprimirInOrden();
        arbol.imprimirPorNiveles();
    }

    static void pruebaArbolPorResiduosMultiples(String palabra) {
        ArbolPorResiduosMultiples.Arbol arbol = new ArbolPorResiduosMultiples.Arbol();
        for (char letra : palabra.toCharArray()) {
            arbol.insertar(letra);
        }
        System.out.println("\nEstructura final del árbol n-ario:");
        arbol.imprimir(arbol.raiz, "");
        List<Character> resultado = new ArrayList<>();
        arbol.recorrerYGuardar(arbol.raiz, resultado);
        System.out.println("\nOrden de letras en el árbol (preorden):");
        for (char c : resultado) {
            System.out.print(c + " ");
        }
        System.out.println();
    }

    static void pruebaHuffman(String frase) {
        frase = frase.toLowerCase().replaceAll(" ", "");
        Map<String, Integer> frecuencias = new HashMap<>();
        int totalLetras = 0;

        for (char c : frase.toCharArray()) {
            if (Character.isLetter(c)) {
                String letra = String.valueOf(c);
                frecuencias.put(letra, frecuencias.getOrDefault(letra, 0) + 1);
                totalLetras++;
            }
        }

        List<Map.Entry<String, Integer>> letrasOrdenadas = new ArrayList<>(frecuencias.entrySet());
        letrasOrdenadas.sort(Map.Entry.comparingByValue());

        System.out.println("\nTabla de letras individuales ordenadas de menor a mayor frecuencia:");
        for (Map.Entry<String, Integer> entrada : letrasOrdenadas) {
            String letra = entrada.getKey();
            int cantidad = entrada.getValue();
            double porcentaje = (cantidad * 100.0) / totalLetras;
            System.out.printf("%s: %d (%.2f%%)%n", letra, cantidad, porcentaje);
        }

        PriorityQueue<HuffmanCompleto.Nodo> cola = new PriorityQueue<>(Comparator.comparingInt(n -> n.frecuencia));

        for (Map.Entry<String, Integer> entrada : frecuencias.entrySet()) {
            cola.add(new HuffmanCompleto.Nodo(entrada.getKey(), entrada.getValue()));
        }

        while (cola.size() > 1) {
            HuffmanCompleto.Nodo izq = cola.poll();
            HuffmanCompleto.Nodo der = cola.poll();
            HuffmanCompleto.Nodo combinado = new HuffmanCompleto.Nodo(izq, der);
            cola.add(combinado);
        }

        HuffmanCompleto.Nodo raiz = cola.poll();
        System.out.println("\nCódigos Huffman:");
        mostrarCodigos(raiz, "");
    }

    static void mostrarCodigos(HuffmanCompleto.Nodo nodo, String codigo) {
        if (nodo == null) return;
        if (nodo.esHoja()) {
            System.out.println(nodo.simbolo + ": " + codigo);
        } else {
            mostrarCodigos(nodo.izquierda, codigo + "0");
            mostrarCodigos(nodo.derecha, codigo + "1");
        }
    }
}
