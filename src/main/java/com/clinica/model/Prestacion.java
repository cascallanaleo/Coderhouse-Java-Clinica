package com.example.model;

import jakarta.persistence.Entity; //Permite convertir la clase en entidad
import jakarta.persistence.GeneratedValue; //permite generar el id automáticamente
import jakarta.persistence.GenerationType; //define como se genera el id
import jakarta.persistence.Id; //permite marcar la clave primaria

@Entity //indica que prestacion va a ser una tabla
public class Prestacion {
    @Id //marca el atributo id como clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) //generar el id automáticamente
    
    private Long id; //guarda el atributo identificador de la Prestacion
    private String descripcion; 
    private String categoria;

    public Prestacion() { //constructor vacío requerido por JPA
    }

    public Prestacion(String descripcion, String categoria) { //constructor con parámetros
        this.descripcion = descripcion;
        this.categoria = categoria;
    }

    public void setId(Long id) { 
        this.id = id;
    }

    public Long getId() { 
        return id;
    }

    public String getDescripcion() { 
        return descripcion;
    }

    public void setDescripcion(String descripcion) { 
        this.descripcion = descripcion;
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
                "id=" + id +
                ", descripcion='" + descripcion + '\'' +
                ", categoria='" + categoria + '\'' +
                '}';
    }
}
