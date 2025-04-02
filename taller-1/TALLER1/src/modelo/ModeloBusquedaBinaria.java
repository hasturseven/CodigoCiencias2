package modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ModeloBusquedaBinaria {
    private List<Double> datos;

    public ModeloBusquedaBinaria() {
        this.datos = new ArrayList<>();
    }

    // Agregar número a la lista ordenadamente sin repetir
    public boolean agregarNumero(double numero) {
        if (!datos.contains(numero)) {  // Verificar que no esté repetido
            datos.add(numero);
            Collections.sort(datos); // Ordenar la lista automáticamente
            return true;
        }
        return false;  // Indicar que el número ya estaba en la lista
    }

    // Búsqueda Binaria
    public int buscar(double objetivo) {
        int izquierda = 0, derecha = datos.size() - 1;
        while (izquierda <= derecha) {
            int medio = izquierda + (derecha - izquierda) / 2;
            if (datos.get(medio) == objetivo) {
                return medio; // Retorna la posición del número
            } else if (datos.get(medio) < objetivo) {
                izquierda = medio + 1;
            } else {
                derecha = medio - 1;
            }
        }
        return -1; // Retorna -1 si el número no está en la lista
    }

    // Obtener datos almacenados
    public List<Double> getDatos() {
        return datos;
    }

    // Reiniciar la lista
    public void reiniciarLista() {
        datos.clear();
    }
}
