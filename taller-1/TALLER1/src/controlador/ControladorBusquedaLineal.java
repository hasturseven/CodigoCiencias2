package controlador;

import modelo.ModeloBusquedaLineal;

public class ControladorBusquedaLineal {
    private ModeloBusquedaLineal modelo;

    public ControladorBusquedaLineal() {
        this.modelo = new ModeloBusquedaLineal();
    }

    // Agregar número sin repetir
    public boolean agregarNumero(double numero) {
        return modelo.agregarNumero(numero);
    }

    // Buscar número
    public int buscarNumero(double objetivo) {
        return modelo.buscar(objetivo);
    }

    // Obtener la lista de números en String
    public String obtenerDatos() {
        return modelo.getDatos().toString();
    }

    // Reiniciar lista
    public void reiniciarLista() {
        modelo.reiniciarLista();
    }
}
