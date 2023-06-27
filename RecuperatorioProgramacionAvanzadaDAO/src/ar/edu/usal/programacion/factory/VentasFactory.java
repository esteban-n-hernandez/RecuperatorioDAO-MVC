package ar.edu.usal.programacion.factory;

import ar.edu.usal.programacion.dao.VentasDAO;
import ar.edu.usal.programacion.domain.Venta;
import ar.edu.usal.programacion.util.FileUtil;

import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

public class VentasFactory implements VentasDAO {
    @Override
    public void crear(Venta articulo) throws IOException {

    }

    @Override
    public void actualizar(Venta articulo) throws IOException {

    }

    @Override
    public void eliminar(Venta articulo) throws IOException {

    }

    @Override
    public List<Venta> obtenerTodos() throws IOException {
        String path = cargarProperties();
        List<Venta> ventas = new ArrayList<>();
        try {
            List<String> lines = FileUtil.readLines(path);
            ventas = parseVentas(lines);
        } catch (IOException e) {
            System.out.println("Error al leer el archivo de ventas: " + e.getMessage());
        }
        return ventas;
    }

    private static List<Venta> parseVentas(List<String> lines) {
        List<Venta> compras = new ArrayList<>();
        for (String line : lines) {
            String[] parts = line.split(";");
            String nombreCliente = getValue(parts[0], "Nombre:");
            String dniCliente = getValue(parts[1], "DNI:");
            double monto = Double.parseDouble(getValue(parts[2], "Monto:"));
            Date fecha = parseFecha(getValue(parts[3], "Fecha:"));
            String vendedor = getValue(parts[4], "Vendedor: ");
            Venta compra = new Venta(nombreCliente, dniCliente, monto, fecha, vendedor);
            compras.add(compra);
        }
        return compras;
    }

    private static String getValue(String part, String key) {
        return part.substring(part.indexOf(key) + key.length());
    }

    private static Date parseFecha(String fechaStr) {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            return dateFormat.parse(fechaStr);
        } catch (java.text.ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    private String cargarProperties() throws IOException {
        Properties properties = new Properties();
        properties.load(new FileReader("config.properties"));
        return properties.getProperty("ruta.ficheros.ventas");
    }
}
