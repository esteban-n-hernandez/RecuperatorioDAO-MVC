package ar.edu.usal.programacion.dao.impl;

import ar.edu.usal.programacion.dao.ClienteDAO;
import ar.edu.usal.programacion.domain.Cliente;
import ar.edu.usal.programacion.domain.Domicilio;
import ar.edu.usal.programacion.domain.Tarjeta;
import ar.edu.usal.programacion.domain.TipoTarjeta;
import ar.edu.usal.programacion.util.FileUtil;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Properties;

public class ClienteDAOImpl implements ClienteDAO {
    private static final String CLIENTES_FILE = "clientes.txt";

    @Override
    public void crear(Cliente cliente) throws IOException {
        List<String> lines = new ArrayList<>();
        String filepath = cargarProperties();
        lines.add(cliente.toString());
        FileUtil.guardar(filepath, lines, "Cliente");
    }

    @Override
    public Cliente obtenerPorId(String dni) {
        try {
            List<String> lines = FileUtil.readLines(CLIENTES_FILE);
            for (String line : lines) {
                Cliente cliente = parseCliente(line);
                if (cliente.getDni().equals(dni)) {
                    return cliente;
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo de clientes: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Cliente> obtenerTodos() {
        List<Cliente> clientes = new ArrayList<>();
        try {
            List<String> lines = FileUtil.readLines(CLIENTES_FILE);
            for (String line : lines) {
                clientes.add(parseCliente(line));
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo de clientes: " + e.getMessage());
        }
        return clientes;
    }

    private Cliente parseCliente(String line) {
        String[] datos = line.split(",");
        String nombre = datos[0];
        String apellido = datos[1];
        String dni = datos[2];
        List<Domicilio> domicilios = parseDomicilios(datos[3]);
        List<Tarjeta> tarjetas = parseTarjetas(datos[4]);
        return new Cliente(nombre, apellido, dni, domicilios, tarjetas);
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
            String tipoStr = datos[2];
            boolean tarjetaPrincipal = Boolean.parseBoolean(datos[3]);

            // Convertir el tipo de tarjeta de String a TipoTarjeta usando valueOf
            TipoTarjeta tipo = TipoTarjeta.valueOf(tipoStr);

            Tarjeta tarjeta = new Tarjeta(numero, marca, tipo, tarjetaPrincipal);
            tarjetas.add(tarjeta);
        }
        return tarjetas;
    }


    private String cargarProperties() throws IOException {
        Properties properties = new Properties();
        properties.load(new FileReader("config.properties"));
        return properties.getProperty("ruta.ficheros.clientes");
    }

}