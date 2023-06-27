package ar.edu.usal.programacion.domain;

import java.util.List;

public class Carrito {
    private int numeroCarrito;
    private EstadoCarrito estado;
    private Cliente cliente;
    private List<Articulo> articulos;

    public Carrito(int numeroCarrito, EstadoCarrito estado, Cliente cliente, List<Articulo> articulos) {
        this.numeroCarrito = numeroCarrito;
        this.estado = estado;
        this.cliente = cliente;
        this.articulos = articulos;
    }

    public int getNumeroCarrito() {
        return numeroCarrito;
    }

    public void setNumeroCarrito(int numeroCarrito) {
        this.numeroCarrito = numeroCarrito;
    }

    public EstadoCarrito getEstado() {
        return estado;
    }

    public void setEstado(EstadoCarrito estado) {
        this.estado = estado;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Articulo> getArticulos() {
        return articulos;
    }

    public void setArticulos(List<Articulo> articulos) {
        this.articulos = articulos;
    }
}


