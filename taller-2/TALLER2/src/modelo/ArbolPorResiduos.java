package modelo;
import java.util.*;


public class ArbolPorResiduos {

    static class Nodo {
        char letra;
        Nodo izquierda, derecha;

        Nodo(char letra) {
            this.letra = letra;
            izquierda = derecha = null;
        }
    }

    public static class ArbolBinario {
        Nodo raiz;

        public void insertarConInfo(char letra) {
            int valor = letra - 'a' + 1;
            String binario = String.format("%8s", Integer.toBinaryString(valor)).replace(' ', '0');

            System.out.println("Letra: " + letra + "  Posición alfabetica: " + valor + "  Binario: " + binario);
            raiz = insertarRecursivo(raiz, letra, binario, 0);
        }

        private Nodo insertarRecursivo(Nodo nodo, char letra, String binario, int index) {
            if (nodo == null) {
                System.out.println(" Insertando '" + letra + "' en posicion libre.\n");
                return new Nodo(letra);
            }

            char direccion = binario.charAt(index);
            System.out.println(" Nodo ocupado. Avanzando por '" + direccion + "' ("
                    + (direccion == '0' ? "izquierda" : "derecha") + ")");

            if (direccion == '0') {
                nodo.izquierda = insertarRecursivo(nodo.izquierda, letra, binario, (index + 1) % 8);
            } else {
                nodo.derecha = insertarRecursivo(nodo.derecha, letra, binario, (index + 1) % 8);
            }

            return nodo;
        }

        public void imprimirInOrden() {
            System.out.println("\n Recorrido In-Orden:");
            imprimirInOrdenRecursivo(raiz);
            System.out.println();
        }

        private void imprimirInOrdenRecursivo(Nodo nodo) {
            if (nodo != null) {
                imprimirInOrdenRecursivo(nodo.izquierda);
                System.out.print(nodo.letra + " ");
                imprimirInOrdenRecursivo(nodo.derecha);
            }
        }

        public void imprimirPorNiveles() {
            if (raiz == null) return;

            System.out.println("\n Arbol por niveles:");
            Queue<Nodo> cola = new LinkedList<>();
            cola.add(raiz);

            while (!cola.isEmpty()) {
                int nivel = cola.size();
                while (nivel-- > 0) {
                    Nodo actual = cola.poll();
                    System.out.print(actual.letra + " ");
                    if (actual.izquierda != null) cola.add(actual.izquierda);
                    if (actual.derecha != null) cola.add(actual.derecha);
                }
                System.out.println(); // Salto de línea por nivel
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArbolBinario arbol = new ArbolBinario();

        System.out.print(" Ingresa una palabra: ");
        String palabra = scanner.nextLine().toLowerCase();

        System.out.println("\n Proceso de insercion:");
        for (char letra : palabra.toCharArray()) {
            if (Character.isLetter(letra)) {
                arbol.insertarConInfo(letra);
            } else {
                System.out.println( letra + "' ignorado (no es letra).");
            }
        }

        arbol.imprimirInOrden();
        arbol.imprimirPorNiveles();

        scanner.close();
    }
}

