package controlador;

import modelo.ModeloBusquedaLineal;

public class ControladorBusquedaLineal {
    private ModeloBusquedaLineal modelo;

    public ControladorBusquedaLineal() {
        this.modelo = new ModeloBusquedaLineal();
    }

    // Método para agregar número con validación de duplicados
    public boolean agregarNumero(int numero) {
        return modelo.agregarNumero(numero);
    }

    // Método para buscar un número
    public int buscarNumero(int objetivo) {
        return modelo.buscar(objetivo);
    }

    // Método para obtener los datos almacenados
    public String obtenerDatos() {
        return modelo.getDatos().toString();
    }
}
