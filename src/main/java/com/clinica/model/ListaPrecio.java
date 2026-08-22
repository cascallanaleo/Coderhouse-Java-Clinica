package com.example.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDate;

@Entity
public class ListaPrecio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descripcion;
    private LocalDate vigencia;

    public ListaPrecio() {
    }

    public ListaPrecio(String descripcion, LocalDate vigencia) {
        this.descripcion = descripcion;
        this.vigencia = vigencia;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDate getVigencia() {
        return vigencia;
    }

    public void setVigencia(LocalDate vigencia) {
        this.vigencia = vigencia;
    }

    @Override
    public String toString() {
        return "ListaPrecio{" +
                "id=" + id +
                ", descripcion='" + descripcion + '\'' +
                ", vigencia=" + vigencia +
                '}';
    }
}
