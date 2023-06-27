package ar.edu.usal.programacion.domain;

import java.util.List;

public class Cliente {
    private String nombre;
    private String apellido;
    private String dni;
    private List<Domicilio> domicilios;
    private List<Tarjeta> tarjetas;

    public Cliente(String nombre, String apellido, String dni, List<Domicilio> domicilios, List<Tarjeta> tarjetas) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.domicilios = domicilios;
        this.tarjetas = tarjetas;
    }

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

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Nombre: ").append(nombre).append("\n");
        sb.append("Apellido: ").append(apellido).append("\n");
        sb.append("DNI: ").append(dni).append("\n");
        sb.append("Domicilios:\n");
        for (Domicilio domicilio : domicilios) {
            sb.append(domicilio.toString()).append("\n");
        }
        sb.append("Tarjetas:\n");
        for (Tarjeta tarjeta : tarjetas) {
            sb.append(tarjeta.toString()).append("\n");
        }
        return sb.toString();
    }
}
