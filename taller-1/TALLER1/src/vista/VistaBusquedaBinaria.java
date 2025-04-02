package vista;

import controlador.ControladorBusquedaBinaria;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VistaBusquedaBinaria extends JFrame {
    private ControladorBusquedaBinaria controlador;
    private DefaultListModel<Double> modeloLista;
    private JList<Double> listaNumeros;
    private JTextField campoNumero;
    private JTextField campoBuscar;
    private JButton btnAgregar, btnTerminarIngreso, btnBuscar, btnNuevaLista;

    public VistaBusquedaBinaria() {
        controlador = new ControladorBusquedaBinaria();
        modeloLista = new DefaultListModel<>();
        listaNumeros = new JList<>(modeloLista);
        campoNumero = new JTextField(10);
        campoBuscar = new JTextField(10);

        setTitle("Búsqueda Binaria");
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        // Panel para agregar números
        JPanel panelAgregar = new JPanel();
        panelAgregar.add(new JLabel("Número:"));
        panelAgregar.add(campoNumero);
        btnAgregar = new JButton("Agregar");
        panelAgregar.add(btnAgregar);
        add(panelAgregar);

        // Lista de números agregados
        add(new JScrollPane(listaNumeros));

        // Botón para terminar ingreso
        btnTerminarIngreso = new JButton("Terminar Ingreso");
        add(btnTerminarIngreso);

        // Panel de búsqueda (se oculta al inicio)
        JPanel panelBuscar = new JPanel();
        panelBuscar.add(new JLabel("Buscar:"));
        panelBuscar.add(campoBuscar);
        btnBuscar = new JButton("Buscar");
        panelBuscar.add(btnBuscar);
        add(panelBuscar);
        panelBuscar.setVisible(false); // Ocultar búsqueda al inicio

        // Etiqueta para mostrar resultado
        JLabel lblResultado = new JLabel("");
        add(lblResultado);

        // Botón para ingresar nueva lista (se oculta al inicio)
        btnNuevaLista = new JButton("Ingresar otra lista");
        add(btnNuevaLista);
        btnNuevaLista.setVisible(false);

        // Acción del botón "Agregar"
        btnAgregar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    double numero = Double.parseDouble(campoNumero.getText());
                    if (controlador.agregarNumero(numero)) {
                        actualizarLista();
                        campoNumero.setText(""); // Limpiar campo
                    } else {
                        JOptionPane.showMessageDialog(null, "El número ya está en la lista.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Ingrese un número válido.");
                }
            }
        });

        // Acción del botón "Terminar Ingreso"
        btnTerminarIngreso.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnTerminarIngreso.setVisible(false); // Ocultar botón de ingreso
                panelBuscar.setVisible(true); // Mostrar panel de búsqueda
                btnNuevaLista.setVisible(true); // Mostrar opción para nueva lista
                campoNumero.setEnabled(false); // Deshabilitar ingreso de datos
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

        // Acción del botón "Ingresar otra lista"
        btnNuevaLista.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controlador.reiniciarLista();
                modeloLista.clear();
                btnTerminarIngreso.setVisible(true);
                panelBuscar.setVisible(false);
                btnNuevaLista.setVisible(false);
                campoNumero.setEnabled(true);
                campoBuscar.setText("");
                lblResultado.setText("");
            }
        });

        setVisible(true);
    }

    // Método para actualizar la lista en la interfaz gráfica
    private void actualizarLista() {
        modeloLista.clear();
        String datos = controlador.obtenerDatos().replaceAll("[\\[\\]]", ""); // Eliminar corchetes
        if (!datos.isEmpty()) {
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
        new VistaBusquedaBinaria();
    }
}
