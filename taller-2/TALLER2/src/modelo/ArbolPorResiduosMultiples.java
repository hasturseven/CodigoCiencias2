package modelo;
import java.util.*;

public class ArbolPorResiduosMultiples{

    static class Nodo {
        char letra;
        Nodo[] hijos = new Nodo[4];  // 4 hijos para 00, 01, 10, 11

        Nodo(char letra) {
            this.letra = letra;
        }
    }

    public static class Arbol {
        public Nodo raiz = new Nodo('-'); // Raíz vacía

        public void insertar(char letra) {
            int ascii = (int) letra;
            String binario = String.format("%8s", Integer.toBinaryString(ascii)).replace(' ', '0');
            
            System.out.println("Insertando letra: " + letra);
            System.out.println("ASCII de '" + letra + "': " + ascii);
            System.out.println("Binario de '" + letra + "': " + binario);

            Nodo actual = raiz;

            for (int i = 0; i < binario.length(); i += 2) {
                String par = binario.substring(i, i + 2);
                int indice = obtenerIndice(par);

                System.out.println("  Par de bits: " + par + " -> Indice: " + indice);

                if (actual.hijos[indice] == null) {
                    System.out.println("    Nodo vacio, insertando '" + letra + "' en la posicion [" + i/2 + "]");
                    actual.hijos[indice] = new Nodo(letra);
                    return;
                } else {
                    System.out.println("    Nodo ya ocupado, moviendo a la siguiente posicion.");
                    actual = actual.hijos[indice];
                }
            }
        }

        private int obtenerIndice(String par) {
            switch (par) {
                case "00": return 0;
                case "01": return 1;
                case "10": return 2;
                case "11": return 3;
                default: throw new IllegalArgumentException("Par de bits invalido: " + par);
            }
        }

        public void imprimir(Nodo nodo, String prefijo) {
            if (nodo == null) return;
            if (nodo.letra != '-') {
                System.out.println(prefijo + nodo.letra);
            }
            for (int i = 0; i < 4; i++) {
                imprimir(nodo.hijos[i], prefijo + "  ");
            }
        }

        public void recorrerYGuardar(Nodo nodo, List<Character> resultado) {
            if (nodo == null) return;
            if (nodo.letra != '-') {
                resultado.add(nodo.letra);
            }
            for (Nodo hijo : nodo.hijos) {
                recorrerYGuardar(hijo, resultado);
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Arbol arbol = new Arbol();

        System.out.print("Ingresa una palabra: ");
        String palabra = scanner.nextLine();

        for (char letra : palabra.toCharArray()) {
            arbol.insertar(letra);
        }

        System.out.println("\nEstructura final del arbol n-ario:");
        arbol.imprimir(arbol.raiz, "");

        List<Character> orden = new ArrayList<>();
        arbol.recorrerYGuardar(arbol.raiz, orden);

        System.out.println("\nOrden de letras en el arbol (preorden):");
        for (char c : orden) {
            System.out.print(c + " ");
        }
        System.out.println();
    }
}

