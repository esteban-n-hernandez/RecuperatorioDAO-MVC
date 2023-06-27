package ar.edu.usal.programacion.dao;

import ar.edu.usal.programacion.domain.Tarjeta;

import java.util.List;

public interface TarjetaDAO {
    void crear(Tarjeta tarjeta);
    Tarjeta obtenerPorNumero(String numero);
    List<Tarjeta> obtenerTodos();
    void actualizar(Tarjeta tarjeta);
    void eliminar(Tarjeta tarjeta);
}
