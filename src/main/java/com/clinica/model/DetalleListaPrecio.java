package com.example.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class DetalleListaPrecio {
    @Id
    private Long listaPrecioId;
    @Id
    private Long idPrestacion;
    private double precio;

    public DetalleListaPrecio() {
    }

    public DetalleListaPrecio(Long listaPrecioId, Long idPrestacion, double precio) {
        this.listaPrecioId = listaPrecioId;
        this.idPrestacion = idPrestacion;
        this.precio = precio;
    }

    public Long getListaPrecioId() {
        return listaPrecioId;
    }

    public void setListaPrecioId(Long listaPrecioId) {
        this.listaPrecioId = listaPrecioId;
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

    @Override
    public String toString() {
        return "DetalleListaPrecio{" +
                "listaPrecioId=" + listaPrecioId +
                ", idPrestacion=" + idPrestacion +
                ", precio=" + precio +
                '}';
    }
}
