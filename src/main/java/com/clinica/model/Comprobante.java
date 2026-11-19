package com.clinica.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Comprobante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idComprobante;

    private Date fecha;
    private double total;
    private int cantidadProductos;

    @ManyToOne
    private Paciente paciente;

    //al guardar el comprobante se guardan tambien sus lineas
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_comprobante")
    private List<LineaComprobante> lineas = new ArrayList<>();

    public Comprobante() {
    }

    public Long getId() {
        return idComprobante;
    }

    public void setId(Long idComprobante) {
        this.idComprobante = idComprobante;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getCantidadProductos() {
        return cantidadProductos;
    }

    public void setCantidadProductos(int cantidadProductos) {
        this.cantidadProductos = cantidadProductos;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public List<LineaComprobante> getLineas() {
        return lineas;
    }

    public void setLineas(List<LineaComprobante> lineas) {
        this.lineas = lineas;
    }

    @Override
    public String toString() {
        return "Comprobante{" +
                "idComprobante=" + idComprobante +
                ", fecha=" + fecha +
                ", total=" + total +
                ", cantidadProductos=" + cantidadProductos +
                ", paciente=" + paciente +
                ", lineas=" + lineas +
                '}';
    }
}
