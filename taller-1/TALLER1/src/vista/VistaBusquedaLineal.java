package vista;

import controlador.ControladorBusquedaLineal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VistaBusquedaLineal extends JFrame {
    private ControladorBusquedaLineal controlador;
    private DefaultListModel<Double> modeloLista;
    private JList<Double> listaNumeros;
    private JTextField campoNumero;
    private JTextField campoBuscar;

    public VistaBusquedaLineal() {
        controlador = new ControladorBusquedaLineal();
        modeloLista = new DefaultListModel<>();
        listaNumeros = new JList<>(modeloLista);
        campoNumero = new JTextField(10);
        campoBuscar = new JTextField(10);

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

        // Botón para reiniciar la lista
        JButton btnReiniciar = new JButton("Ingresar nueva lista");
        add(btnReiniciar);

        // Etiqueta para mostrar resultado
        JLabel lblResultado = new JLabel("");
        add(lblResultado);

        // Acción del botón "Agregar"
        btnAgregar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    double numero = Double.parseDouble(campoNumero.getText());
                    boolean agregado = controlador.agregarNumero(numero);

                    if (agregado) {
                        actualizarLista();
                        campoNumero.setText(""); // Limpiar el campo
                    } else {
                        JOptionPane.showMessageDialog(null, "El número ya está en la lista.");
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
                    double objetivo = Double.parseDouble(campoBuscar.getText());
                    int resultado = controlador.buscarNumero(objetivo);
                    if (resultado != -1) {
                        lblResultado.setText("Número encontrado en posición: " + resultado);
                    } else {
                        lblResultado.setText("Número NO encontrado.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Ingrese un número válido.");
                }
            }
        });

        // Acción del botón "Reiniciar lista"
        btnReiniciar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controlador.reiniciarLista();
                modeloLista.clear();
                lblResultado.setText("");
                JOptionPane.showMessageDialog(null, "Lista reiniciada.");
            }
        });

        setVisible(true);
    }

    // Método para actualizar la lista en la interfaz gráfica
    private void actualizarLista() {
        modeloLista.clear();
        String datos = controlador.obtenerDatos().replaceAll("[\\[\\]]", ""); // Eliminar corchetes
        if (!datos.isEmpty()) { // Verificar que no esté vacío
            String[] numeros = datos.split(", ");
            for (String num : numeros) {
                try {
                    modeloLista.addElement(Double.parseDouble(num));
                } catch (NumberFormatException e) {
                    System.out.println("Error al convertir: " + num);
                }
            }
        }
    }
    public static void main(String[] args) {
        new VistaBusquedaLineal();
    }
}
