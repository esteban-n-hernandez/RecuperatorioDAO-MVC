package ar.edu.usal.programacion.mvc.model;

import ar.edu.usal.programacion.domain.Domicilio;
import ar.edu.usal.programacion.domain.Tarjeta;

import java.util.List;

public class ClienteModel {
    private String nombre;
    private String apellido;
    private String dni;
    private List<Domicilio> domicilios;
    private List<Tarjeta> tarjetas;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public List<Domicilio> getDomicilios() {
        return domicilios;
    }

    public void setDomicilios(List<Domicilio> domicilios) {
        this.domicilios = domicilios;
    }

    public List<Tarjeta> getTarjetas() {
        return tarjetas;
    }

    public void setTarjetas(List<Tarjeta> tarjetas) {
        this.tarjetas = tarjetas;
    }
}
