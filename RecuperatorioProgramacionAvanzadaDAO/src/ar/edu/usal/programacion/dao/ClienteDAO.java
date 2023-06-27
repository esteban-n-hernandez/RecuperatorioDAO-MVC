package ar.edu.usal.programacion.dao;

import ar.edu.usal.programacion.domain.Cliente;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface ClienteDAO {
    void crear(Cliente cliente) throws IOException;
    Cliente obtenerPorId(String dni);
    List<Cliente> obtenerTodos();
}