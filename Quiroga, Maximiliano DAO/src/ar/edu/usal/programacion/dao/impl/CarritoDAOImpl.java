package ar.edu.usal.programacion.dao.impl;

import ar.edu.usal.programacion.dao.CarritoDAO;
import ar.edu.usal.programacion.domain.*;
import ar.edu.usal.programacion.util.FileUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CarritoDAOImpl implements CarritoDAO {

    private static final String CARRITOS_FILE = "carritos.txt";

    @Override
    public void crear(Carrito carrito) {
        List<String> lines;
        try {
            lines = FileUtil.readLines(CARRITOS_FILE);
        } catch (IOException e) {
            lines = new ArrayList<>();
        }
        lines.add(carrito.toString());
        try {
            FileUtil.writeLines(CARRITOS_FILE, lines);
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo de carritos: " + e.getMessage());
        }
    }

    @Override
    public Carrito obtenerPorNumero(int numeroCarrito) {
        try {
            List<String> lines = FileUtil.readLines(CARRITOS_FILE);
            for (String line : lines) {
                Carrito carrito = parseCarrito(line);
                if (carrito.getNumeroCarrito() == numeroCarrito) {
                    return carrito;
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo de carritos: " + e.getMessage());
        }
        return null;
    }

    @Override
    public void actualizar(Carrito carrito) {
        List<Carrito> carritos = obtenerTodos();
        for (int i = 0; i < carritos.size(); i++) {
            if (carritos.get(i).getNumeroCarrito() == carrito.getNumeroCarrito()) {
                carritos.set(i, carrito);
                break;
            }
        }
        List<String> lines = new ArrayList<>();
        for (Carrito c : carritos) {
            lines.add(c.toString());
        }
        try {
            FileUtil.writeLines(CARRITOS_FILE, lines);
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo de carritos: " + e.getMessage());
        }
    }

    @Override
    public void eliminar(Carrito carrito) {
        List<Carrito> carritos = obtenerTodos();
        carritos.removeIf(c -> c.getNumeroCarrito() == carrito.getNumeroCarrito());
        List<String> lines = new ArrayList<>();
        for (Carrito c : carritos) {
            lines.add(c.toString());
        }
        try {
            FileUtil.writeLines(CARRITOS_FILE, lines);
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo de carritos: " + e.getMessage());
        }
    }

    @Override
    public List<Carrito> obtenerTodos() {
        List<Carrito> carritos = new ArrayList<>();
        try {
            List<String> lines = FileUtil.readLines(CARRITOS_FILE);
            for (String line : lines) {
                carritos.add(parseCarrito(line));
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo de carritos: " + e.getMessage());
        }
        return carritos;
    }

    private Carrito parseCarrito(String carritoData) {
        String[] datos = carritoData.split(",");
        int numeroCarrito = Integer.parseInt(datos[0]);
        EstadoCarrito estado = EstadoCarrito.valueOf(datos[1]);
        Cliente cliente = parseCliente(datos[2]);
        List<Articulo> articulos = parseArticulos(datos[3]);
        return new Carrito(numeroCarrito, estado, cliente, articulos);
    }


    private Cliente parseCliente(String clienteData) {
        String[] datos = clienteData.split(",");
        String dni = datos[0];
        String nombre = datos[1];
        String apellido = datos[2];
        List<Domicilio> domicilios = parseDomicilios(datos[3]);
        List<Tarjeta> tarjetas = parseTarjetas(datos[4]);
        return new Cliente(dni, nombre, apellido, domicilios, tarjetas);
    }


    private List<Articulo> parseArticulos(String articulosData) {
        List<Articulo> articulos = new ArrayList<>();
        String[] articuloArray = articulosData.split(";");
        for (String articuloData : articuloArray) {
            String[] datos = articuloData.split(",");
            int id = Integer.parseInt(datos[0]);
            String nombre = datos[1];
            String descripcion = datos[2];
            String categoria = datos[3];
            int cantidad = Integer.parseInt(datos[4]);
            double precio = Double.parseDouble(datos[5]);
            List<Vendedor> vendedores = parseVendedores(datos[6]);
            Articulo articulo = new Articulo(id, nombre, descripcion, categoria, cantidad, precio, vendedores);
            articulos.add(articulo);
        }
        return articulos;
    }

    private List<Vendedor> parseVendedores(String vendedoresData) {
        List<Vendedor> vendedores = new ArrayList<>();
        String[] vendedorArray = vendedoresData.split(";");
        for (String vendedorData : vendedorArray) {
            String[] datos = vendedorData.split(",");
            String nombre = datos[0];
            String apellido = datos[1];
            int id = Integer.parseInt(datos[2]);
            List<Articulo> articulos = new ArrayList<>();
            Vendedor vendedor = new Vendedor(nombre, apellido, id);
            vendedores.add(vendedor);
        }
        return vendedores;
    }

    private List<Domicilio> parseDomicilios(String domiciliosData) {
        List<Domicilio> domicilios = new ArrayList<>();
        String[] domicilioArray = domiciliosData.split(";");
        for (String domicilioData : domicilioArray) {
            String[] datos = domicilioData.split(",");
            String calle = datos[0];
            String numero = datos[1];
            String codigoPostal = datos[2];
            String localidad = datos[3];
            boolean domicilioPrincipal = Boolean.parseBoolean(datos[4]);
            Domicilio domicilio = new Domicilio(calle, numero, codigoPostal, localidad, domicilioPrincipal);
            domicilios.add(domicilio);
        }
        return domicilios;
    }

    private List<Tarjeta> parseTarjetas(String tarjetasData) {
        List<Tarjeta> tarjetas = new ArrayList<>();
        String[] tarjetaArray = tarjetasData.split(";");
        for (String tarjetaData : tarjetaArray) {
            String[] datos = tarjetaData.split(",");
            String numero = datos[0];
            String marca = datos[1];
            TipoTarjeta tipoTarjeta = TipoTarjeta.valueOf(datos[2]);
            boolean tarjetaPrincipal = Boolean.parseBoolean(datos[3]);
            Tarjeta tarjeta = new Tarjeta(numero, marca, tipoTarjeta, tarjetaPrincipal);
            tarjetas.add(tarjeta);
        }
        return tarjetas;
    }


}
