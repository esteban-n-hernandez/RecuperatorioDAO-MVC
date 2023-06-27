package ar.edu.usal.programacion.mvc.view.reporte;

import ar.edu.usal.programacion.dao.impl.VentasDAOImpl;
import ar.edu.usal.programacion.domain.Venta;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ReporteComprasSuperioresView {

    public void mostrar() throws IOException {
        // Obtener las compras superiores a $10,000
        VentasDAOImpl ventasDAO = new VentasDAOImpl();
        List<Venta> comprasSuperiores = ventasDAO.obtenerTodos();

        // Filtrar las compras superiores a $10,000
        List<Venta> comprasSuperioresFiltradas = filtrarComprasSuperiores(comprasSuperiores);

        // Crear la ventana de reporte
        JFrame reporteFrame = new JFrame("Compras Superiores a $10,000");
        reporteFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        reporteFrame.setSize(500, 500);

        // Crear el área de texto para mostrar el reporte
        JTextArea txtAreaReporte = new JTextArea();
        txtAreaReporte.setEditable(false);

        // Mostrar el reporte en el área de texto
        for (Venta venta : comprasSuperioresFiltradas) {
            txtAreaReporte.append("DNI: " + venta.getDniCliente() + "\n");
            txtAreaReporte.append("Nombre Cliente: " + venta.getNombreCliente() + "\n");
            txtAreaReporte.append("Fecha: " + venta.getFecha() + "\n");
            txtAreaReporte.append("Monto: " + venta.getMonto() + "\n");
            txtAreaReporte.append("-------------\n");
        }

        // Agregar el área de texto a la ventana
        reporteFrame.getContentPane().add(new JScrollPane(txtAreaReporte));

        // Mostrar la ventana de reporte
        reporteFrame.setVisible(true);
    }

    private List<Venta> filtrarComprasSuperiores(List<Venta> compras) {
        List<Venta> comprasSuperiores = new ArrayList<>();
        Calendar calendario = Calendar.getInstance();
        int mesActual = calendario.get(Calendar.MONTH) + 1;

        for (Venta compra : compras) {
            if (compra.getMonto() > 10000 && esMesActual(compra.getFecha(), mesActual)) {
                comprasSuperiores.add(compra);
            }
        }
        return comprasSuperiores;
    }

    private boolean esMesActual(Date fecha, int mesActual) {
        Calendar calendario = Calendar.getInstance();
        calendario.setTime(fecha);
        int mesVenta = calendario.get(Calendar.MONTH) + 1;

        return mesVenta == mesActual;
    }
}
