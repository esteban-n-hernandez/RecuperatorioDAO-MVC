package ar.edu.usal.programacion.dao.impl;

import ar.edu.usal.programacion.dao.ArticuloDAO;
import ar.edu.usal.programacion.domain.Articulo;
import ar.edu.usal.programacion.domain.Vendedor;
import ar.edu.usal.programacion.util.FileUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ArticuloDAOImpl implements ArticuloDAO {

    private static final String ARTICULOS_FILE = "articulos.txt";

    @Override
    public void crear(Articulo articulo) throws IOException {
        List<String> lines = new ArrayList<>();
        String filepath = cargarProperties();
        lines.add(articulo.toStringd());
        FileUtil.guardar(filepath, lines, "Articulo");
    }

    @Override
    public void actualizar(Articulo articulo) throws IOException {
        List<Articulo> articulos = obtenerTodos();
        for (int i = 0; i < articulos.size(); i++) {
            if (articulos.get(i).getId() == articulo.getId()) {
                articulos.set(i, articulo);
                break;
            }
        }
        List<String> lines = new ArrayList<>();
        for (Articulo a : articulos) {
            lines.add(a.toString());
        }
        try {
            FileUtil.writeLines(ARTICULOS_FILE, lines);
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo de artículos: " + e.getMessage());
        }
    }

    @Override
    public void eliminar(Articulo articulo) throws IOException {
        List<Articulo> articulos = obtenerTodos();
        articulos.removeIf(a -> a.getId() == articulo.getId());
        List<String> lines = new ArrayList<>();
        for (Articulo a : articulos) {
            lines.add(a.toString());
        }
        try {
            FileUtil.writeLines(ARTICULOS_FILE, lines);
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo de artículos: " + e.getMessage());
        }
    }

    @Override
    public List<Articulo> obtenerTodos() throws IOException {
        List<Articulo> articulos = new ArrayList<>();
        Properties properties = new Properties();
        properties.load(new FileReader("config.properties"));
        String filePath = properties.getProperty("ruta.ficheros.articulos");
        try {
            List<String> lines = FileUtil.readLines(filePath);
            articulos = parseArticulos(lines);
        } catch (IOException e) {
            System.out.println("Error al leer el archivo de artículos: " + e.getMessage());
        }
        return articulos;
    }

    public static List<Articulo> parseArticulos(List<String> lines) throws IOException {
        List<Articulo> articulos = new ArrayList<>();

        for (String line : lines) {
            String[] keyValuePairs = line.split(";");
            int id = 0;
            String nombre = "";
            String descripcion = "";
            String categoria = "";
            int cantidad = 0;
            double precio = 0.0;
            List<Vendedor> vendedores = new ArrayList<>();

            for (String keyValue : keyValuePairs) {
                String[] pair = keyValue.split(":");
                if (pair.length != 2) {
                    System.out.println("Faltan datos");
                }
                String key = pair[0].trim();
                String value = pair[1].trim();

                switch (key) {
                    case "Artículo ID":
                        id = Integer.parseInt(value);
                        break;
                    case "Nombre":
                        nombre = value;
                        break;
                    case "Descripción":
                        descripcion = value;
                        break;
                    case "Categoría":
                        categoria = value;
                        break;
                    case "Cantidad":
                        cantidad = Integer.parseInt(value);
                        break;
                    case "Precio":
                        precio = Double.parseDouble(value);
                        break;
                    default:
                        System.out.println("Clave desconocida: " + key);
                }
            }

            articulos.add(new Articulo(id, nombre, descripcion, categoria, cantidad, precio, vendedores));
        }
        return articulos;
    }


    private String cargarProperties() throws IOException {
        Properties properties = new Properties();
        properties.load(new FileReader("config.properties"));
        return properties.getProperty("ruta.ficheros.articulos");
    }

}


