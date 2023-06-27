package ar.edu.usal.programacion.mvc.view.cliente;
import ar.edu.usal.programacion.dao.impl.ClienteDAOImpl;
import ar.edu.usal.programacion.domain.Cliente;
import ar.edu.usal.programacion.domain.Domicilio;
import ar.edu.usal.programacion.domain.Tarjeta;
import ar.edu.usal.programacion.domain.TipoTarjeta;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RegistrarClienteView {

    private JFrame frame;
    private JPanel panel;
    private JTextField txtNombre;
    private JTextField txtApellido;
    private JTextField txtDNI;
    private JButton btnAgregarDomicilio;
    private JButton btnAgregarTarjeta;
    private JButton btnGuardar;

    private List<Domicilio> domicilios;
    private List<Tarjeta> tarjetas;
    private ClienteDAOImpl clienteDAO;

    public RegistrarClienteView() {
        domicilios = new ArrayList<>();
        tarjetas = new ArrayList<>();
        clienteDAO = new ClienteDAOImpl();
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Crear Cliente");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(500, 500);

        panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel lblTitulo = new JLabel("Crear Cliente");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(lblTitulo, gbc);

        JLabel lblNombre = new JLabel("Nombre:");
        txtNombre = new JTextField();
        txtNombre.setPreferredSize(new Dimension(200, 30));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(lblNombre, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(txtNombre, gbc);

        JLabel lblApellido = new JLabel("Apellido:");
        txtApellido = new JTextField();
        txtApellido.setPreferredSize(new Dimension(200, 30));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(lblApellido, gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(txtApellido, gbc);

        JLabel lblDNI = new JLabel("DNI:");
        txtDNI = new JTextField();
        txtDNI.setPreferredSize(new Dimension(200, 30));
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(lblDNI, gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(txtDNI, gbc);

        btnAgregarDomicilio = new JButton("Agregar Domicilio");
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(btnAgregarDomicilio, gbc);

        btnAgregarTarjeta = new JButton("Agregar Tarjeta");
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(btnAgregarTarjeta, gbc);

        btnGuardar = new JButton("Guardar");
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(btnGuardar, gbc);

        frame.getContentPane().add(panel);

        btnAgregarDomicilio.addActionListener(e -> agregarDomicilio());

        btnAgregarTarjeta.addActionListener(e -> agregarTarjeta());

        btnGuardar.addActionListener(e -> {
            try {
                guardarCliente();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        frame.setVisible(true);
    }

    private void agregarDomicilio() {
        String calle = JOptionPane.showInputDialog(frame, "Ingrese la calle:");
        String numero = JOptionPane.showInputDialog(frame, "Ingrese el número:");
        String codigoPostal = JOptionPane.showInputDialog(frame, "Ingrese el código postal:");
        String localidad = JOptionPane.showInputDialog(frame, "Ingrese la localidad:");

        int opcionSeleccionada = JOptionPane.showConfirmDialog(frame, "¿Es el domicilio principal?", "Domicilio Principal", JOptionPane.YES_NO_OPTION);

        boolean domicilioPrincipal = opcionSeleccionada == JOptionPane.YES_OPTION;

        Domicilio domicilio = new Domicilio(calle, numero, codigoPostal, localidad, domicilioPrincipal);
        domicilios.add(domicilio);
        System.out.println(domicilio);
        System.out.println("Domicilio agregado");
    }

    private void agregarTarjeta() {
        String numero = JOptionPane.showInputDialog(frame, "Ingrese el número de tarjeta:");
        String marca = JOptionPane.showInputDialog(frame, "Ingrese la marca de la tarjeta:");
        TipoTarjeta tipoTarjeta = obtenerTipoTarjeta(); // Debes implementar el método obtenerTipoTarjeta()
        int opcionSeleccionada = JOptionPane.showConfirmDialog(frame, "¿Es la tarjeta principal?", "Tarjeta Principal", JOptionPane.YES_NO_OPTION);

        boolean tarjetaPrincipal = opcionSeleccionada == JOptionPane.YES_OPTION;


        Tarjeta tarjeta = new Tarjeta(numero, marca, tipoTarjeta, tarjetaPrincipal);
        System.out.println(tarjeta);
        tarjetas.add(tarjeta);
        System.out.println("Tarjeta agregada");
    }

    private TipoTarjeta obtenerTipoTarjeta() {
        String[] opciones = { "Débito", "Crédito", "Prepaga" };
        int opcionSeleccionada = JOptionPane.showOptionDialog(frame, "Seleccione el tipo de tarjeta:", "Tipo de Tarjeta",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, opciones, opciones[0]);

        switch (opcionSeleccionada) {
            case 0:
                return TipoTarjeta.DEBITO;
            case 1:
                return TipoTarjeta.CREDITO;
            case 2:
                return TipoTarjeta.PREPAGA;
            default:
                return null;
        }
    }
    private void guardarCliente() throws IOException {
        String nombre = txtNombre.getText();
        String apellido = txtApellido.getText();
        String dni = txtDNI.getText();

        Cliente cliente = new Cliente(nombre, apellido, dni, domicilios, tarjetas);

        // Mostrar la información del cliente por consola
        System.out.println("Información del cliente:");
        System.out.println("Nombre: " + cliente.getNombre());
        System.out.println("Apellido: " + cliente.getApellido());
        System.out.println("DNI: " + cliente.getDni());
        System.out.println("Domicilios:");
        for (Domicilio domicilio : cliente.getDomicilios()) {
            System.out.println("- Calle: " + domicilio.getCalle());
            System.out.println("- Número: " + domicilio.getNumero());
            System.out.println("- Código Postal: " + domicilio.getCodigoPostal());
            System.out.println("- Localidad: " + domicilio.getLocalidad());
            System.out.println("- Es Domicilio Principal: " + domicilio.isDomicilioPrincipal());
        }
        System.out.println("Tarjetas:");
        for (Tarjeta tarjeta : cliente.getTarjetas()) {
            System.out.println("- Número: " + tarjeta.getNumero());
            System.out.println("- Marca: " + tarjeta.getMarca());
            System.out.println("- Tipo de Tarjeta: " + tarjeta.getTipoTarjeta());
            System.out.println("- Es Tarjeta Principal: " + tarjeta.isTarjetaPrincipal());
        }

        JOptionPane.showMessageDialog(frame, "Cliente guardado exitosamente");

        clienteDAO.crear(cliente);

        frame.dispose();
    }

    public void mostrar() {
        frame.setVisible(true);
    }

}
