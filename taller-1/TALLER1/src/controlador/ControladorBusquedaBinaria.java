package controlador;

import modelo.ModeloBusquedaBinaria;

public class ControladorBusquedaBinaria {
    private ModeloBusquedaBinaria modelo;

    public ControladorBusquedaBinaria() {
        this.modelo = new ModeloBusquedaBinaria();
    }

    // Método para agregar números
    public boolean agregarNumero(double numero) {
        return modelo.agregarNumero(numero);
    }

    // Método para buscar un número
    public int buscarNumero(double objetivo) {
        return modelo.buscar(objetivo);
    }

    // Método para obtener los datos almacenados
    public String obtenerDatos() {
        return modelo.getDatos().toString();
    }

    // Método para reiniciar la lista
    public void reiniciarLista() {
        modelo.reiniciarLista();
    }
}
