package ar.edu.usal.programacion.mvc.view.articulo;

import ar.edu.usal.programacion.dao.impl.ArticuloDAOImpl;
import ar.edu.usal.programacion.domain.Articulo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ComprarArticulosView {

    private JFrame frame;
    private JPanel panel;
    private JTable table;
    private DefaultTableModel tableModel;
    private List<Articulo> carrito;

    public ComprarArticulosView() throws IOException {
        initialize();
    }

    private void initialize() throws IOException {
        // Crear la ventana principal
        frame = new JFrame("Menú Principal");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);

        // Crear el panel principal
        panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Crear la tabla
        tableModel = new DefaultTableModel();
        tableModel.addColumn("ID");
        tableModel.addColumn("Nombre Articulo");
        tableModel.addColumn("Descripcion");
        tableModel.addColumn("Precio");
        tableModel.addColumn("Agregar al Carrito");

        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Crear el botón de agregar al carrito
        JButton btnAgregarCarrito = new JButton("Agregar al Carrito");
        panel.add(btnAgregarCarrito, BorderLayout.SOUTH);

        // Agregar el panel a la ventana
        frame.getContentPane().add(panel);

        // Cargar los artículos en la tabla
        cargarArticulos();

        // Agregar ActionListener al botón Agregar al Carrito
        btnAgregarCarrito.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                Articulo articulo = null;
                agregarAlCarrito(articulo);
                JOptionPane.showMessageDialog(frame, "Artículo agregado al carrito: " + articulo.getNombre());
            } else {
                JOptionPane.showMessageDialog(frame, "Seleccione un artículo de la tabla");
            }
        });
    }

    private void cargarArticulos() throws IOException {
        ArticuloDAOImpl articuloDAO = new ArticuloDAOImpl();
        List<Articulo> articulos = articuloDAO.obtenerTodos();

        for (Articulo articulo : articulos) {
            Object[] rowData = {
                    articulo.getId(),
                    articulo.getNombre(),
                    articulo.getDescripcion(),
                    articulo.getPrecio(),
                    "Agregar"
            };
            tableModel.addRow(rowData);
        }
    }


    private void agregarAlCarrito(Articulo articulo) {
        if (carrito == null) {
            carrito = new ArrayList<>();
        }
        carrito.add(articulo);
    }

    public void mostrar() {
        frame.setVisible(true);
    }
}
