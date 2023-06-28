package ar.edu.usal.programacion.dao;

import ar.edu.usal.programacion.domain.Vendedor;

import java.io.IOException;
import java.util.List;


//Se encarga de interactuar con la persistencia de los vendedores, como guardar, obtener, actualizar y eliminar vendedores.
public interface VendedorDAO {

    void guardarVendedor(Vendedor vendedor) throws IOException;

    void actualizarVendedor(Vendedor vendedor);

    void eliminarVendedor(Vendedor vendedor);

    Vendedor obtenerVendedorPorId(int id);

    List<Vendedor> obtenerTodosLosVendedores();

}
