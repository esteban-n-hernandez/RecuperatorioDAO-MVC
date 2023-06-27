package ar.edu.usal.programacion.dao;

import ar.edu.usal.programacion.domain.Articulo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

// Se encarga de interactuar con la persistencia de los artículos, como crear, obtener, actualizar y eliminar.
public interface ArticuloDAO {


    void crear(Articulo articulo) throws IOException;

    void actualizar(Articulo articulo) throws IOException;

    void eliminar(Articulo articulo) throws IOException;

    List<Articulo> obtenerTodos() throws IOException;
}