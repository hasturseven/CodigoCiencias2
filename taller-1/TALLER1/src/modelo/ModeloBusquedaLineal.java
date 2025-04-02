package modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ModeloBusquedaLineal {
    private List<Double> datos;

    public ModeloBusquedaLineal() {
        this.datos = new ArrayList<>();
    }

    // Método para agregar números sin permitir repetidos
    public boolean agregarNumero(double numero) {
        if (!datos.contains(numero)) { // Verifica que no esté repetido
            datos.add(numero);
            Collections.sort(datos); // Mantiene la lista ordenada automáticamente
            return true; // Indica que el número se agregó
        }
        return false; // Indica que el número ya existía
    }

    // Método para realizar la búsqueda secuencial
    public int buscar(double objetivo) {
        for (int i = 0; i < datos.size(); i++) {
            if (datos.get(i).equals(objetivo)) {
                return i; // Retorna la posición donde se encontró el número
            }
        }
        return -1; // Retorna -1 si no se encuentra el número
    }

    // Método para reiniciar la lista
    public void reiniciarLista() {
        datos.clear();
    }

    // Método para obtener todos los datos
    public List<Double> getDatos() {
        return datos;
    }
}
