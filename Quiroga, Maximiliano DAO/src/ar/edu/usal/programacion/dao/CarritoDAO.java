package ar.edu.usal.programacion.dao;

import ar.edu.usal.programacion.domain.Carrito;

import java.util.List;

public interface CarritoDAO {

    void crear(Carrito carrito);

    Carrito obtenerPorNumero(int numeroCarrito);

    void actualizar(Carrito carrito);

    void eliminar(Carrito carrito);

    List<Carrito> obtenerTodos();

}
