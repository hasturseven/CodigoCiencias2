package vista;

import controlador.ControladorBusquedaLineal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VistaBusquedaLineal extends JFrame {
    private ControladorBusquedaLineal controlador;
    private DefaultListModel<Integer> modeloLista;
    private JList<Integer> listaNumeros;
    private JTextField campoNumero;
    private JTextField campoBuscar;
    private JLabel lblResultado;

    public VistaBusquedaLineal() {
        controlador = new ControladorBusquedaLineal();
        modeloLista = new DefaultListModel<>();
        listaNumeros = new JList<>(modeloLista);
        campoNumero = new JTextField(10);
        campoBuscar = new JTextField(10);
        lblResultado = new JLabel("");

        setTitle("Búsqueda Secuencial");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        // Panel para agregar números
        JPanel panelAgregar = new JPanel();
        panelAgregar.add(new JLabel("Número:"));
        panelAgregar.add(campoNumero);
        JButton btnAgregar = new JButton("Agregar");
        panelAgregar.add(btnAgregar);
        add(panelAgregar);

        // Lista de números agregados
        add(new JScrollPane(listaNumeros));

        // Panel para buscar un número
        JPanel panelBuscar = new JPanel();
        panelBuscar.add(new JLabel("Buscar:"));
        panelBuscar.add(campoBuscar);
        JButton btnBuscar = new JButton("Buscar");
        panelBuscar.add(btnBuscar);
        add(panelBuscar);

        // Etiqueta para mostrar resultado
        add(lblResultado);

        // Acción del botón "Agregar"
        btnAgregar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int numero = Integer.parseInt(campoNumero.getText());
                    boolean agregado = controlador.agregarNumero(numero);
                    if (agregado) {
                        actualizarLista(); // Mantener lista ordenada
                        campoNumero.setText(""); // Limpiar campo
                    } else {
                        JOptionPane.showMessageDialog(null, "El número ya existe en la lista.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Ingrese un número válido.");
                }
            }
        });

        // Acción del botón "Buscar"
        btnBuscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int objetivo = Integer.parseInt(campoBuscar.getText());
                    int resultado = controlador.buscarNumero(objetivo);
                    if (resultado != -1) {
                        lblResultado.setText("Número encontrado en posición: " + (resultado+1));
                    } else {
                        lblResultado.setText("Número NO encontrado.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Ingrese un número válido.");
                }
            }
        });

        setVisible(true);
    }

    // Método para actualizar la lista visual ordenada
    private void actualizarLista() {
        modeloLista.clear();
        String datos = controlador.obtenerDatos().replaceAll("[\\[\\]]", ""); // Elimina corchetes
        if (!datos.isEmpty()) { // Verifica que la lista no esté vacía
            for (String numStr : datos.split(", ")) {
                try {
                    int num = Integer.parseInt(numStr);
                    modeloLista.addElement(num);
                } catch (NumberFormatException e) {
                    System.out.println("Error al convertir: " + numStr);
                }
            }
        }
    }
    public static void main(String[] args) {
        new VistaBusquedaLineal();
    }
}
