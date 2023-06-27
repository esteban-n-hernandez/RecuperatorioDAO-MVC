package ar.edu.usal.programacion.domain;

import java.util.Date;

public class Venta {

    private String nombreCliente;
    private String dniCliente;
    private double monto;
    private Date fecha;
    private String vendedor;

    public Venta(String nombreCliente, String dniCliente, Double monto, Date fecha, String vendedor) {
        this.nombreCliente = nombreCliente;
        this.dniCliente = dniCliente;
        this.monto = monto;
        this.fecha = fecha;
        this.vendedor = vendedor;
    }

    public String getVendedor() {
        return vendedor;
    }

    public void setVendedor(String vendedor) {
        this.vendedor = vendedor;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getDniCliente() {
        return dniCliente;
    }

    public void setDniCliente(String dniCliente) {
        this.dniCliente = dniCliente;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}
