package com.clinica.model;

import jakarta.persistence.Entity; //Permite convertir la clase en entidad
import jakarta.persistence.GeneratedValue; //permite generar el id automáticamente
import jakarta.persistence.GenerationType; //define como se genera el id
import jakarta.persistence.Id; //permite marcar la clave primaria

@Entity //indica que prestacion va a ser una tabla
public class Prestacion {
    @Id //marca el atributo id como clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) //generar el id automáticamente
    
    private Long idPrestacion; //guarda el atributo identificador de la Prestacion
    private String descripcion; 
    private String categoria;
    private int stock; //cantidad disponible de la prestacion (cupos)

    public Prestacion() { //constructor vacío requerido por JPA
    }

    public Prestacion(String descripcion, String categoria) { //constructor con parámetros
        this.descripcion = descripcion;
        this.categoria = categoria;
    }

    public void setId(Long idPrestacion) { 
        this.idPrestacion = idPrestacion;
    }

    public Long getId() { 
        return idPrestacion;
    }

    public String getDescripcion() { 
        return descripcion;
    }

    public void setDescripcion(String descripcion) { 
        this.descripcion = descripcion;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getCategoria() { 
        return categoria;
    }

    public void setCategoria(String categoria) { 
        this.categoria = categoria;
    }

@Override
    public String toString() { 
        return "Prestacion{" +
                "idPrestacion=" + idPrestacion +
                ", descripcion='" + descripcion + '\'' +
                ", categoria='" + categoria + '\'' +
                ", stock=" + stock +
                '}';
    }
}
