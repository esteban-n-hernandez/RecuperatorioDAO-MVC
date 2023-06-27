package ar.edu.usal.programacion.mvc.model;

public class DomicilioModel {
    private String calle;
    private String numero;
    private String codigoPostal;
    private String localidad;
    private boolean domicilioPrincipal;

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public boolean isDomicilioPrincipal() {
        return domicilioPrincipal;
    }

    public void setDomicilioPrincipal(boolean domicilioPrincipal) {
        this.domicilioPrincipal = domicilioPrincipal;
    }

    // Constructor, getters y setters
}
