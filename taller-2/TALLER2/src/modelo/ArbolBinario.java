package modelo;

public class ArbolBinario {
    public Nodo raiz;

    public ArbolBinario() {
        this.raiz = new Nodo(-1); // Nodo raíz vacío (sin valor útil)
    }

    public void insertar(String binario, int valor) {
        Nodo actual = raiz;
        for (char bit : binario.toCharArray()) {
            if (bit == '0') {
                if (actual.izquierda == null) {
                    actual.izquierda = new Nodo(-1);
                }
                actual = actual.izquierda;
            } else if (bit == '1') {
                if (actual.derecha == null) {
                    actual.derecha = new Nodo(-1);
                }
                actual = actual.derecha;
            }
        }
        actual.valor = valor; // Asignamos el valor en la hoja
    }

    public void imprimirPreorden(Nodo nodo) {
        if (nodo != null) {
            if (nodo.valor != -1) {
                System.out.print(nodo.valor + " ");
            }
            imprimirPreorden(nodo.izquierda);
            imprimirPreorden(nodo.derecha);
        }
    }
}
