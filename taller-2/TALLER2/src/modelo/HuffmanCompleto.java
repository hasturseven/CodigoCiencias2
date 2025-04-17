package modelo;
import java.util.*;

public class HuffmanCompleto {

    // Clase Nodo para árbol de Huffman
    static class Nodo {
        String simbolo;
        int frecuencia;
        Nodo izquierda, derecha;

        Nodo(String simbolo, int frecuencia) {
            this.simbolo = simbolo;
            this.frecuencia = frecuencia;
        }

        Nodo(Nodo izquierda, Nodo derecha) {
            this.simbolo = "(" + izquierda.simbolo + "+" + derecha.simbolo + ")";
            this.frecuencia = izquierda.frecuencia + derecha.frecuencia;
            this.izquierda = izquierda;
            this.derecha = derecha;
        }

        boolean esHoja() {
            return izquierda == null && derecha == null;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Escribe una frase: ");
        String frase = scanner.nextLine();

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

        // Mostrar tabla ordenada
        List<Map.Entry<String, Integer>> letrasOrdenadas = new ArrayList<>(frecuencias.entrySet());
        letrasOrdenadas.sort(Map.Entry.comparingByValue());

        System.out.println("\nTabla de letras individuales ordenadas de menor a mayor frecuencia:");
        for (Map.Entry<String, Integer> entrada : letrasOrdenadas) {
            String letra = entrada.getKey();
            int cantidad = entrada.getValue();
            double porcentaje = (cantidad * 100.0) / totalLetras;
            System.out.printf("%s: %d (%.2f%%)%n", letra, cantidad, porcentaje);
        }

        // Crear nodos hoja y construir el árbol de Huffman
        PriorityQueue<Nodo> cola = new PriorityQueue<>(Comparator.comparingInt(n -> n.frecuencia));

        for (Map.Entry<String, Integer> entrada : frecuencias.entrySet()) {
            cola.add(new Nodo(entrada.getKey(), entrada.getValue()));
        }

        while (cola.size() > 1) {
            Nodo n1 = cola.poll();
            Nodo n2 = cola.poll();
            Nodo combinado = new Nodo(n1, n2);
            cola.add(combinado);
        }

        Nodo raiz = cola.poll();

        System.out.println("\nArbol de Huffman completamente agrupado:");
        System.out.println("Estructura: " + raiz.simbolo);
        System.out.printf("Total de letras: %d (%.2f%%)%n", raiz.frecuencia, 100.0);

        System.out.println("\nCodigos Huffman:");
        Map<String, String> codigos = new HashMap<>();
        generarCodigos(raiz, "", codigos);

        codigos.entrySet().stream()
            .sorted(Map.Entry.comparingByKey())
            .forEach(entry -> System.out.printf("%s: %s%n", entry.getKey(), entry.getValue()));

        scanner.close();
    }

    // Recorrido para generar los códigos Huffman
    static void generarCodigos(Nodo nodo, String codigo, Map<String, String> mapa) {
        if (nodo == null) return;

        if (nodo.esHoja()) {
            mapa.put(nodo.simbolo, codigo);
            return;
        }

        generarCodigos(nodo.izquierda, codigo + "0", mapa);
        generarCodigos(nodo.derecha, codigo + "1", mapa);
    }
    public static void procesarFrase(String frase) {
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

        System.out.println("Tabla de frecuencias:");
        for (Map.Entry<String, Integer> entrada : letrasOrdenadas) {
            String letra = entrada.getKey();
            int cantidad = entrada.getValue();
            double porcentaje = (cantidad * 100.0) / totalLetras;
            System.out.printf("%s: %d (%.2f%%)%n", letra, cantidad, porcentaje);
        }

        PriorityQueue<Nodo> cola = new PriorityQueue<>(Comparator.comparingInt(n -> n.frecuencia));
        for (Map.Entry<String, Integer> entrada : frecuencias.entrySet()) {
            cola.add(new Nodo(entrada.getKey(), entrada.getValue()));
        }

        while (cola.size() > 1) {
            Nodo izq = cola.poll();
            Nodo der = cola.poll();
            Nodo combinado = new Nodo(izq, der);
            cola.add(combinado);
        }

        Nodo raiz = cola.poll();
        System.out.println("Árbol de Huffman construido. Recorrido Preorden:");
        imprimirPreorden(raiz, "");
    }

    private static void imprimirPreorden(Nodo nodo, String prefijo) {
        if (nodo != null) {
            System.out.println(prefijo + nodo.simbolo + " (" + nodo.frecuencia + ")");
            imprimirPreorden(nodo.izquierda, prefijo + "  ");
            imprimirPreorden(nodo.derecha, prefijo + "  ");
        }
    }

}
