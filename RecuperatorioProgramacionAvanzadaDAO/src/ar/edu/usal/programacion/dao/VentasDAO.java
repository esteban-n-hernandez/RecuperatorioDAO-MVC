package ar.edu.usal.programacion.dao;

import ar.edu.usal.programacion.domain.Articulo;
import ar.edu.usal.programacion.domain.Venta;

import java.io.IOException;
import java.util.List;

// Se encarga de interactuar con la persistencia de los ventas, como crear, obtener, actualizar y eliminar.
public interface VentasDAO {

    void crear(Venta articulo) throws IOException;

    void actualizar(Venta articulo) throws IOException;

    void eliminar(Venta articulo) throws IOException;

    List<Venta> obtenerTodos() throws IOException;
}