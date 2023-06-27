package ar.edu.usal.programacion.domain;

import java.util.List;

public class Vendedor {
    private String razonSocial;
    private String domicilio;
    private int cuit;
    private List<Articulo> articulos;

    public Vendedor(String razonSocial, String domicilio, int cuit) {
        this.razonSocial = razonSocial;
        this.domicilio = domicilio;
        this.cuit = cuit;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public int getCuit() {
        return cuit;
    }

    public void setCuit(int cuit) {
        this.cuit = cuit;
    }

    public List<Articulo> getArticulos() {
        return articulos;
    }

    public void setArticulos(List<Articulo> articulos) {
        this.articulos = articulos;
    }

    public String toString() {
        return "Razón Social: " + razonSocial + "\n" +
                "Domicilio: " + domicilio + "\n" +
                "CUIT: " + cuit + "\n";
    }
}
