package ar.edu.usal.programacion.domain;

public class Tarjeta {
    private String numero;
    private String marca;
    private TipoTarjeta tipoTarjeta;
    private boolean tarjetaPrincipal;

    public Tarjeta(String numero, String marca, TipoTarjeta tipoTarjeta, boolean tarjetaPrincipal) {
        this.numero = numero;
        this.marca = marca;
        this.tipoTarjeta = tipoTarjeta;
        this.tarjetaPrincipal = tarjetaPrincipal;
    }


    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public TipoTarjeta getTipoTarjeta() {
        return tipoTarjeta;
    }

    public void setTipoTarjeta(TipoTarjeta tipoTarjeta) {
        this.tipoTarjeta = tipoTarjeta;
    }

    public boolean isTarjetaPrincipal() {
        return tarjetaPrincipal;
    }

    public void setTarjetaPrincipal(boolean tarjetaPrincipal) {
        this.tarjetaPrincipal = tarjetaPrincipal;
    }

    public String toString() {
        return "Número: " + numero + "\n" +
                "Marca: " + marca + "\n" +
                "Tipo de Tarjeta: " + tipoTarjeta + "\n" +
                "Tarjeta Principal: " + tarjetaPrincipal + "\n";
    }
}