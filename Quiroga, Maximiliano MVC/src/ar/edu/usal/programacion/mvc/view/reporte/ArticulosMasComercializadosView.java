package ar.edu.usal.programacion.mvc.view.reporte;

import ar.edu.usal.programacion.dao.impl.ArticuloDAOImpl;
import ar.edu.usal.programacion.domain.Articulo;
import ar.edu.usal.programacion.domain.Vendedor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class ArticulosMasComercializadosView extends JFrame {

    private JTextField txtNombreEmpresa;
    private JButton btnBuscar;
    private JTextArea txtAreaResultados;

    public ArticulosMasComercializadosView() {
        initialize();
    }

    private void initialize() {
        setTitle("Artículos Más Comercializados");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel de búsqueda
        JPanel panelBusqueda = new JPanel();
        panelBusqueda.setLayout(new FlowLayout());

        JLabel lblNombreEmpresa = new JLabel("Nombre de Empresa:");
        txtNombreEmpresa = new JTextField(20);
        btnBuscar = new JButton("Buscar");

        panelBusqueda.add(lblNombreEmpresa);
        panelBusqueda.add(txtNombreEmpresa);
        panelBusqueda.add(btnBuscar);

        // Área de resultados
        txtAreaResultados = new JTextArea();
        txtAreaResultados.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(txtAreaResultados);

        // Agregar componentes al frame
        add(panelBusqueda, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // Configurar ActionListener para el botón Buscar
        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombreEmpresa = txtNombreEmpresa.getText();
                if (!nombreEmpresa.isEmpty()) {
                    List<String> resultados = null;
                    try {
                        resultados = obtenerArticulosMasComercializados(nombreEmpresa);
                        mostrarResultados(resultados);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }

                }
            }
        });
    }

    private List<String> obtenerArticulosMasComercializados(String nombreEmpresa) throws IOException {
        Map<String, Integer> contadorArticulos = new HashMap<>();

        // Obtener la lista de artículos desde ArticuloDAOImpl
        ArticuloDAOImpl articuloDAO = new ArticuloDAOImpl();
        List<Articulo> articulos = articuloDAO.obtenerTodos();

        // Iterar sobre la lista de artículos
        for (Articulo articulo : articulos) {
            List<Vendedor> vendedores = articulo.getVendedores();

            // Verificar si alguno de los vendedores coincide con el nombre de la empresa buscada
            for (Vendedor vendedor : vendedores) {
                if (vendedor.getRazonSocial().equalsIgnoreCase(nombreEmpresa)) {
                    // Incrementar el contador de artículos
                    int contador = contadorArticulos.getOrDefault(articulo.getNombre(), 0);
                    contadorArticulos.put(articulo.getNombre(), contador + 1);
                    break; // Salir del bucle interno si se encontró una coincidencia
                }
            }
        }

        // Ordenar los artículos por cantidad vendida en orden descendente
        List<Map.Entry<String, Integer>> listaOrdenada = new ArrayList<>(contadorArticulos.entrySet());
        listaOrdenada.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

        // Obtener los nombres de los artículos más comercializados
        List<String> resultados = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : listaOrdenada) {
            resultados.add(entry.getKey());
        }

            return resultados;
    }
    private void mostrarResultados(List<String> resultados) {
        txtAreaResultados.setText("");

        if (resultados.isEmpty()) {
            txtAreaResultados.append("No se encontraron artículos comercializados para la empresa especificada.");
        } else {
            txtAreaResultados.append("Artículos más comercializados para la empresa:\n\n");
            for (String articulo : resultados) {
                txtAreaResultados.append("- " + articulo + "\n");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ArticulosMasComercializadosView view = new ArticulosMasComercializadosView();
            view.setVisible(true);
        });
    }
}
