package ar.edu.usal.programacion.dao.impl;

import ar.edu.usal.programacion.dao.VendedorDAO;
import ar.edu.usal.programacion.domain.Articulo;
import ar.edu.usal.programacion.domain.Vendedor;
import ar.edu.usal.programacion.util.FileUtil;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class VendedorDAOImpl implements VendedorDAO {

    private static final String VENDEDORES_FILE = "vendedores.txt";

    @Override
    public void guardarVendedor(Vendedor vendedor) throws IOException {
        List<String> lines = new ArrayList<>();
        String filepath = cargarProperties();
        lines.add(vendedor.toString());
        FileUtil.guardar(filepath, lines, "Empresa");
    }

    @Override
    public void actualizarVendedor(Vendedor vendedor) {
        List<Vendedor> vendedores = obtenerTodosLosVendedores();
        for (int i = 0; i < vendedores.size(); i++) {
            if (vendedores.get(i).getCuit() == vendedor.getCuit()) {
                vendedores.set(i, vendedor);
                break;
            }
        }
        List<String> lines = new ArrayList<>();
        for (Vendedor v : vendedores) {
            lines.add(v.toString());
        }
        try {
            FileUtil.writeLines(VENDEDORES_FILE, lines);
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo de vendedores: " + e.getMessage());
        }
    }

    @Override
    public void eliminarVendedor(Vendedor vendedor) {
        List<Vendedor> vendedores = obtenerTodosLosVendedores();
        vendedores.removeIf(v -> v.getCuit() == vendedor.getCuit());
        List<String> lines = new ArrayList<>();
        for (Vendedor v : vendedores) {
            lines.add(v.toString());
        }
        try {
            FileUtil.writeLines(VENDEDORES_FILE, lines);
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo de vendedores: " + e.getMessage());
        }
    }

    @Override
    public Vendedor obtenerVendedorPorId(int id) {
        try {
            List<String> lines = FileUtil.readLines(VENDEDORES_FILE);
            for (String line : lines) {
                Vendedor vendedor = parseVendedor(line);
                if (vendedor.getCuit() == id) {
                    return vendedor;
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo de vendedores: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Vendedor> obtenerTodosLosVendedores() {
        List<Vendedor> vendedores = new ArrayList<>();
        try {
            List<String> lines = FileUtil.readLines(VENDEDORES_FILE);
            for (String line : lines) {
                vendedores.add(parseVendedor(line));
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo de vendedores: " + e.getMessage());
        }
        return vendedores;
    }

    private Vendedor parseVendedor(String line) {
        String[] datos = line.split(",");
        String nombre = datos[0];
        String apellido = datos[1];
        int id = Integer.parseInt(datos[2]);
        List<Articulo> articulos = new ArrayList<>();
        if (datos.length > 3) {
            String[] articulosData = datos[3].split(";");
            for (String articuloData : articulosData) {
                String[] articuloDatos = articuloData.split(":");
                int articuloId = Integer.parseInt(articuloDatos[0]);
                String articuloNombre = articuloDatos[1];
                String descripcion = articuloDatos[2];
                String categoria = articuloDatos[3];
                int cantidad = Integer.parseInt(articuloDatos[4]);
                double precio = Double.parseDouble(articuloDatos[5]);
                List<Vendedor> vendedores = parseVendedores(articuloDatos[6]);
                Articulo articulo = new Articulo(articuloId, articuloNombre, descripcion, categoria, cantidad, precio, vendedores);
                articulos.add(articulo);
            }
        }
        return new Vendedor(nombre, apellido, id);
    }

    private List<Vendedor> parseVendedores(String vendedoresData) {
        List<Vendedor> vendedores = new ArrayList<>();
        String[] vendedorArray = vendedoresData.split(";");
        for (String vendedorData : vendedorArray) {
            String[] datos = vendedorData.split(",");
            String nombre = datos[0];
            String apellido = datos[1];
            int id = Integer.parseInt(datos[2]);
            Vendedor vendedor = new Vendedor(nombre, apellido, id);
            vendedores.add(vendedor);
        }
        return vendedores;
    }


    private String cargarProperties() throws IOException {
        Properties properties = new Properties();
        properties.load(new FileReader("config.properties"));
        return properties.getProperty("ruta.ficheros.empresas");
    }
}
