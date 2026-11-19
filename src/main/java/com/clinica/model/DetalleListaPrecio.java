package com.clinica.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class DetalleListaPrecio {
//    @Id
//    private Long listaPrecioId;
//    @Id
//    private Long idPrestacion;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDetalleListaPrecio;

    @ManyToOne
    private ListaPrecio listaPrecio;

    @ManyToOne
    private Prestacion prestacion;

    private double precio;

    public DetalleListaPrecio() {
    }

    public DetalleListaPrecio(ListaPrecio listaPrecio, Prestacion prestacion, double precio) {
        this.listaPrecio = listaPrecio;
        this.prestacion = prestacion;
        this.precio = precio;
    }

    public Long getId() {
        return idDetalleListaPrecio;
    }

    public void setId(Long id) {
        this.idDetalleListaPrecio = id;
    }

    public ListaPrecio getListaPrecio() {
        return listaPrecio;
    }

    public void setListaPrecio(ListaPrecio listaPrecio) {
        this.listaPrecio = listaPrecio;
    }

    public Prestacion getPrestacion() {
        return prestacion;
    }

    public void setPrestacion(Prestacion prestacion) {
        this.prestacion = prestacion;
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
                "idDetalleListaPrecio=" + idDetalleListaPrecio +
                ", listaPrecio=" + listaPrecio +
                ", prestacion=" + prestacion +
                ", precio=" + precio +
                '}';
    }
}
