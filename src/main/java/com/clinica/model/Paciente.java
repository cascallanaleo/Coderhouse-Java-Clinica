
package com.example.model;

import jakarta.persistence.Entity; //Permite convertir la clase en entidad
import jakarta.persistence.GeneratedValue; //permite generar el id automáticamente
import jakarta.persistence.GenerationType; //define como se genera el id
import jakarta.persistence.Id; //permite marcar la clave primaria
import java.time.LocalDate;
import java.time.Period;

@Entity //indica que paciente va a ser una tabla
public class Paciente {
    @Id //marca el atributo id como clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) //generar el id automáticamente
    
    private Long id; //guarda el atributo identificador del Paciente
    private String nombre; 
    private String domicilio;
    private String celular;
    private LocalDate fechaNacimiento;


    public Paciente() { //constructor vacío requerido por JPA
    }
    public Paciente(String nombre) { //constructor con parámetros
        this.nombre = nombre;
    }

    public void setId(Long id) { 
        this.id = id;
    }

    public Long getId() { 
        return id;
    }

    public String getNombre() { 
        return nombre;
    }

    public void setNombre(String nombre) { 
        this.nombre = nombre;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public int getEdad() {
        if (fechaNacimiento == null) {
            return 0;
        }
        return Period.between(fechaNacimiento, LocalDate.now()).getYears();
    }

@Override
    public String toString() { 
        return "Paciente{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", domicilio='" + domicilio + '\'' +
                ", celular='" + celular + '\'' +
                ", fechaNacimiento=" + fechaNacimiento +
                '}';
    }

}
    