package modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ModeloBusquedaLineal {
    private List<Integer> datos;

    public ModeloBusquedaLineal() {
        this.datos = new ArrayList<>();
    }

    // Método para agregar un número sin duplicados y mantener orden
    public boolean agregarNumero(int numero) {
        if (!datos.contains(numero)) { // Verifica que no exista
            datos.add(numero);
            Collections.sort(datos); // Ordena la lista automáticamente
            return true; // Indica que se agregó con éxito
        }
        return false; // Indica que el número ya existía
    }

    // Método de búsqueda secuencial
    public int buscar(int objetivo) {
        for (int i = 0; i < datos.size(); i++) {
            if (datos.get(i) == objetivo) {
                return i; // Retorna la posición donde se encontró
            }
        }
        return -1; // Si no se encuentra el número
    }

    // Método para obtener la lista actual
    public List<Integer> getDatos() {
        return datos;
    }
}
