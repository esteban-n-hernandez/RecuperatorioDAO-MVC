package ar.edu.usal.programacion.mvc.view.vendedor;

import ar.edu.usal.programacion.dao.impl.VendedorDAOImpl;
import ar.edu.usal.programacion.domain.Vendedor;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class MenuVendedor {

    private JFrame frame;
    private JPanel panel;
    private JTextField txtRazonSocial;
    private JTextField txtDomicilio;
    private JTextField txtCuit;
    private JButton btnGuardar;


    public MenuVendedor() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Crear Vendedor");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(500, 300);

        panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel lblTitulo = new JLabel("Crear Vendedor");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(lblTitulo, gbc);

        JLabel lblRazonSocial = new JLabel("Razón Social:");
        txtRazonSocial = new JTextField();
        txtRazonSocial.setPreferredSize(new Dimension(200, 30));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(lblRazonSocial, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(txtRazonSocial, gbc);

        JLabel lblDomicilio = new JLabel("Domicilio:");
        txtDomicilio = new JTextField();
        txtDomicilio.setPreferredSize(new Dimension(200, 30));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(lblDomicilio, gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(txtDomicilio, gbc);

        JLabel lblCuit = new JLabel("CUIT:");
        txtCuit = new JTextField();
        txtCuit.setPreferredSize(new Dimension(200, 30));
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(lblCuit, gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(txtCuit, gbc);

        btnGuardar = new JButton("Guardar");
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(btnGuardar, gbc);

        frame.getContentPane().add(panel);

        btnGuardar.addActionListener(e -> {
            try {
                guardarVendedor();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        frame.setVisible(true);
    }

    private void guardarVendedor() throws IOException {
        VendedorDAOImpl vendedorDAO = new VendedorDAOImpl();
        String razonSocial = txtRazonSocial.getText();
        String domicilio = txtDomicilio.getText();
        String cuit = txtCuit.getText();

        Vendedor datosVendedor = new Vendedor(razonSocial, domicilio, Integer.parseInt(cuit));

        vendedorDAO.guardarVendedor(datosVendedor);

        frame.dispose();
    }

    public void mostrar() {
        frame.setVisible(true);
    }
}
