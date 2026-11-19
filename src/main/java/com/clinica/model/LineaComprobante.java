package com.clinica.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class LineaComprobante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLineaComprobante;

    private int cantidad;
    private double precioUnitario; //precio que tenia la prestacion cuando se hizo la venta

    @ManyToOne
    private Prestacion prestacion;

    public LineaComprobante() {
    }

    public LineaComprobante(Prestacion prestacion, int cantidad, double precioUnitario) {
        this.prestacion = prestacion;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
    }

    public Long getId() {
        return idLineaComprobante;
    }

    public void setId(Long idLineaComprobante) {
        this.idLineaComprobante = idLineaComprobante;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public Prestacion getPrestacion() {
        return prestacion;
    }

    public void setPrestacion(Prestacion prestacion) {
        this.prestacion = prestacion;
    }

    @Override
    public String toString() {
        return "LineaComprobante{" +
                "idLineaComprobante=" + idLineaComprobante +
                ", cantidad=" + cantidad +
                ", precioUnitario=" + precioUnitario +
                ", prestacion=" + prestacion +
                '}';
    }
}
