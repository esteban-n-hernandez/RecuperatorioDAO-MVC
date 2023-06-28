package ar.edu.usal.programacion.mvc.view.articulo;

import ar.edu.usal.programacion.dao.impl.ArticuloDAOImpl;
import ar.edu.usal.programacion.domain.Articulo;
import ar.edu.usal.programacion.domain.Vendedor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CrearArticuloView {

    private JFrame frame;
    private JPanel panel;
    private JTextField txtId;
    private JTextField txtNombre;
    private JTextField txtDescripcion;
    private JTextField txtCategoria;
    private JTextField txtCantidad;
    private JTextField txtPrecio;
    private JButton btnAgregarVendedor;
    private JButton btnGuardar;

    private List<Vendedor> vendedores;
    private ArticuloDAOImpl articuloDAO;

    public CrearArticuloView() {
        vendedores = new ArrayList<>();
        articuloDAO = new ArticuloDAOImpl();
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Alta de Artículo");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(500, 500);

        panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Código para crear los componentes de la interfaz gráfica

        JLabel lblId = new JLabel("ID:");
        txtId = new JTextField();
        txtId.setPreferredSize(new Dimension(200, 30));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(lblId, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(txtId, gbc);

        JLabel lblNombre = new JLabel("Nombre:");
        txtNombre = new JTextField();
        txtNombre.setPreferredSize(new Dimension(200, 30));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(lblNombre, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(txtNombre, gbc);

        JLabel lblDescripcion = new JLabel("Descripción:");
        txtDescripcion = new JTextField();
        txtDescripcion.setPreferredSize(new Dimension(200, 30));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(lblDescripcion, gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(txtDescripcion, gbc);

        JLabel lblCategoria = new JLabel("Categoría:");
        txtCategoria = new JTextField();
        txtCategoria.setPreferredSize(new Dimension(200, 30));
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(lblCategoria, gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(txtCategoria, gbc);

        JLabel lblCantidad = new JLabel("Cantidad:");
        txtCantidad = new JTextField();
        txtCantidad.setPreferredSize(new Dimension(200, 30));
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(lblCantidad, gbc);
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(txtCantidad, gbc);

        JLabel lblPrecio = new JLabel("Precio:");
        txtPrecio = new JTextField();
        txtPrecio.setPreferredSize(new Dimension(200, 30));
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(lblPrecio, gbc);
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(txtPrecio, gbc);

        btnAgregarVendedor = new JButton("Agregar Vendedor");
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(btnAgregarVendedor, gbc);

        btnGuardar = new JButton("Guardar Artículo");
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(btnGuardar, gbc);

        frame.getContentPane().add(panel);
        frame.setVisible(true);

        // ActionListener para el botón "Agregar Vendedor"
        btnAgregarVendedor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarVendedor();
            }
        });

        // ActionListener para el botón "Guardar Artículo"
        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = txtId.getText();
                String nombre = txtNombre.getText();
                String descripcion = txtDescripcion.getText();
                String categoria = txtCategoria.getText();
                String cantidad = txtCantidad.getText();
                String precio = txtPrecio.getText();

                try {
                    guardarArticulo(id, nombre, descripcion, categoria, cantidad, precio);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    private void agregarVendedor() {
        String nombre = JOptionPane.showInputDialog(frame, "Ingrese el nombre del vendedor:");
        String apellido = JOptionPane.showInputDialog(frame, "Ingrese el apellido del vendedor:");
        int id = Integer.parseInt(JOptionPane.showInputDialog(frame, "Ingrese el ID del vendedor:"));

        Vendedor vendedor = new Vendedor(nombre, apellido, id);
        vendedores.add(vendedor);
        System.out.println("Vendedor agregado: " + vendedor);
    }

    //Llamar al DAO para guardar el Articulo.
    private void guardarArticulo(String id, String nombre, String descripcion, String categoria, String cantidad, String precio) throws IOException {
        Articulo articulo = new Articulo(Integer.parseInt(id), nombre, descripcion, categoria, Integer.parseInt(cantidad), Double.parseDouble(precio), vendedores);
        articulo.setVendedores(vendedores);
        articuloDAO.crear(articulo);

        System.out.println("Artículo guardado: " + nombre);
    }

    public void mostrar() {
        frame.setVisible(true);
    }
}
