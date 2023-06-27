package ar.edu.usal.programacion.mvc.view.articulo;

import ar.edu.usal.programacion.dao.impl.ArticuloDAOImpl;
import ar.edu.usal.programacion.domain.Articulo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class ConsultarArticulosView {

    private JFrame frame;
    private JPanel panel;
    private JLabel titleLabel;
    private JTable table;
    private DefaultTableModel tableModel;


    public ConsultarArticulosView() throws IOException {
        initialize();
    }

    private void initialize() throws IOException {
        // Crear la ventana de consulta de artículos
        frame = new JFrame("Consulta de Artículos");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(500, 500);

        // Crear el panel principal
        panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Crear el título de la ventana
        titleLabel = new JLabel("Consulta de Artículos");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        panel.add(titleLabel, BorderLayout.NORTH);

        // Crear la tabla
        tableModel = new DefaultTableModel();
        tableModel.addColumn("ID");
        tableModel.addColumn("Nombre");
        tableModel.addColumn("Descripción");
        tableModel.addColumn("Categoría");
        tableModel.addColumn("Cantidad");
        tableModel.addColumn("Precio");
        tableModel.addColumn("Vendedor");

        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Crear el botón de consulta
        // Agregar el panel a la ventana
        cargarDatosEnTabla();
        frame.getContentPane().add(panel);

    }

    private void cargarDatosEnTabla() throws IOException {
        ArticuloDAOImpl articuloDAO = new ArticuloDAOImpl();
        List<Articulo> articulos = articuloDAO.obtenerTodos();

        // Limpiar la tabla antes de cargar los nuevos datos
        tableModel.setRowCount(0);

        // Cargar los datos en la tabla
        for (Articulo articulo : articulos) {
            Object[] rowData = {
                    articulo.getId(),
                    articulo.getNombre(),
                    articulo.getDescripcion(),
                    articulo.getCategoria(),
                    articulo.getCantidad(),
                    articulo.getPrecio(),
                    articulo.getVendedores()
            };
            tableModel.addRow(rowData);
        }
    }

    public void mostrar() {
        // Mostrar la ventana de consulta de artículos
        frame.setVisible(true);
    }
}
