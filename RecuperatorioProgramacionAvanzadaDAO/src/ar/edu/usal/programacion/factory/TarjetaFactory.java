package ar.edu.usal.programacion.factory;

import ar.edu.usal.programacion.dao.TarjetaDAO;
import ar.edu.usal.programacion.domain.Tarjeta;
import ar.edu.usal.programacion.domain.TipoTarjeta;
import ar.edu.usal.programacion.util.FileUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TarjetaFactory implements TarjetaDAO {

    private static final String TARJETAS_FILE = "tarjetas.txt";

    @Override
    public void crear(Tarjeta tarjeta) {
        List<String> lines;
        try {
            lines = FileUtil.readLines(TARJETAS_FILE);
        } catch (IOException e) {
            lines = new ArrayList<>();
        }
        lines.add(tarjeta.toString());
        try {
            FileUtil.writeLines(TARJETAS_FILE, lines);
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo de tarjetas: " + e.getMessage());
        }
    }

    @Override
    public Tarjeta obtenerPorNumero(String numero) {
        try {
            List<String> lines = FileUtil.readLines(TARJETAS_FILE);
            for (String line : lines) {
                Tarjeta tarjeta = parseTarjeta(line);
                if (tarjeta.getNumero().equals(numero)) {
                    return tarjeta;
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo de tarjetas: " + e.getMessage());
        }
        return null;
    }

    @Override
    public void actualizar(Tarjeta tarjeta) {
        List<Tarjeta> tarjetas = obtenerTodos();
        for (int i = 0; i < tarjetas.size(); i++) {
            if (tarjetas.get(i).getNumero().equals(tarjeta.getNumero())) {
                tarjetas.set(i, tarjeta);
                break;
            }
        }
        List<String> lines = new ArrayList<>();
        for (Tarjeta t : tarjetas) {
            lines.add(t.toString());
        }
        try {
            FileUtil.writeLines(TARJETAS_FILE, lines);
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo de tarjetas: " + e.getMessage());
        }
    }

    @Override
    public void eliminar(Tarjeta tarjeta) {
        List<Tarjeta> tarjetas = obtenerTodos();
        tarjetas.removeIf(t -> t.getNumero().equals(tarjeta.getNumero()));
        List<String> lines = new ArrayList<>();
        for (Tarjeta t : tarjetas) {
            lines.add(t.toString());
        }
        try {
            FileUtil.writeLines(TARJETAS_FILE, lines);
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo de tarjetas: " + e.getMessage());
        }
    }

    @Override
    public List<Tarjeta> obtenerTodos() {
        List<Tarjeta> tarjetas = new ArrayList<>();
        try {
            List<String> lines = FileUtil.readLines(TARJETAS_FILE);
            for (String line : lines) {
                tarjetas.add(parseTarjeta(line));
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo de tarjetas: " + e.getMessage());
        }
        return tarjetas;
    }

    private Tarjeta parseTarjeta(String line) {
        String[] datos = line.split(",");
        String numero = datos[0];
        String marca = datos[1];
        String tipoStr = datos[2];
        boolean tarjetaPrincipal = Boolean.parseBoolean(datos[3]);

        // Convertir el tipo de tarjeta de String a TipoTarjeta usando valueOf
        TipoTarjeta tipo = TipoTarjeta.valueOf(tipoStr);

        return new Tarjeta(numero, marca, tipo, tarjetaPrincipal);
    }
}
