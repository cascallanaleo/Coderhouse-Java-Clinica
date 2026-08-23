package com.clinica.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class DetalleListaPrecio {
//    @Id
//    private Long listaPrecioId;
//    @Id
//    private Long idPrestacion;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDetalleListaPrecio;

    private Long idListaPrecio;
    private Long idPrestacion;
    private double precio;

    public DetalleListaPrecio() {
    }

    public DetalleListaPrecio(Long idListaPrecio, Long idPrestacion, double precio) {
        this.idListaPrecio = idListaPrecio;
        this.idPrestacion = idPrestacion;
        this.precio = precio;
    }

    public Long getListaPrecioId() {
        return idListaPrecio;
    }

    public void setListaPrecioId(Long idListaPrecio) {
        this.idListaPrecio = idListaPrecio;
    }

    public Long getIdPrestacion() {
        return idPrestacion;
    }

    public void setIdPrestacion(Long idPrestacion) {
        this.idPrestacion = idPrestacion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Long getId() {
        return idDetalleListaPrecio;
    }

    public void setId(Long id) {
        this.idDetalleListaPrecio = id;
    }

    @Override
    public String toString() {
        return "DetalleListaPrecio{" +  
                "idDetalleListaPrecio=" + idDetalleListaPrecio +
                "idListaPrecio=" + idListaPrecio +
                ", idPrestacion=" + idPrestacion +
                ", precio=" + precio +
                '}';
    }
}
