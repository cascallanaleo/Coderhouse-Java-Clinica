package com.clinica.service;

import com.clinica.model.Comprobante;
import com.clinica.model.DetalleListaPrecio;
import com.clinica.model.LineaComprobante;
import com.clinica.model.Paciente;
import com.clinica.model.Prestacion;
import com.clinica.repository.ComprobanteRepository;
import com.clinica.repository.DetalleListaPrecioRepository;
import com.clinica.repository.PacienteRepository;
import com.clinica.repository.PrestacionRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComprobanteService {

    @Autowired
    private ComprobanteRepository comprobanteRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private PrestacionRepository prestacionRepository;

    @Autowired
    private DetalleListaPrecioRepository detalleListaPrecioRepository;

    @Autowired
    private FechaService fechaService;

    public Comprobante saveComprobante(Comprobante comprobante) {

        // el paciente tiene que existir
        if (comprobante.getPaciente() == null || comprobante.getPaciente().getId() == null) {
            throw new IllegalArgumentException("Falta indicar el paciente");
        }
        Long idPaciente = comprobante.getPaciente().getId();
        Paciente paciente = pacienteRepository.findById(idPaciente).orElse(null);
        if (paciente == null) {
            throw new IllegalArgumentException("El paciente " + idPaciente + " no existe");
        }

        if (comprobante.getLineas() == null || comprobante.getLineas().isEmpty()) {
            throw new IllegalArgumentException("El comprobante no tiene lineas");
        }

        Comprobante nuevo = new Comprobante();
        nuevo.setPaciente(paciente);

        // primero reviso todas las lineas, todavia no toco el stock
        for (LineaComprobante linea : comprobante.getLineas()) {
            if (linea.getPrestacion() == null || linea.getPrestacion().getId() == null) {
                throw new IllegalArgumentException("Falta indicar la prestacion en una linea");
            }
            Long idPrestacion = linea.getPrestacion().getId();
            Prestacion prestacion = prestacionRepository.findById(idPrestacion).orElse(null);
            if (prestacion == null) {
                throw new IllegalArgumentException("La prestacion " + idPrestacion + " no existe");
            }
            if (linea.getCantidad() <= 0) {
                throw new IllegalArgumentException("La cantidad tiene que ser mayor a 0");
            }
            if (linea.getCantidad() > prestacion.getStock()) {
                throw new IllegalArgumentException("No hay stock suficiente de la prestacion " + idPrestacion
                        + ". Pedido: " + linea.getCantidad() + ", disponible: " + prestacion.getStock());
            }

            double precio = buscarPrecio(idPrestacion);
            nuevo.getLineas().add(new LineaComprobante(prestacion, linea.getCantidad(), precio));
        }

        // estaba todo bien: descuento el stock y calculo los totales
        double total = 0;
        int cantidadTotal = 0;
        for (LineaComprobante linea : nuevo.getLineas()) {
            Prestacion prestacion = linea.getPrestacion();
            prestacion.setStock(prestacion.getStock() - linea.getCantidad());
            prestacionRepository.save(prestacion);

            total = total + linea.getPrecioUnitario() * linea.getCantidad();
            cantidadTotal = cantidadTotal + linea.getCantidad();
        }

        nuevo.setFecha(fechaService.obtenerFecha());
        nuevo.setTotal(total);
        nuevo.setCantidadProductos(cantidadTotal);

        return comprobanteRepository.save(nuevo);
    }

    // busca el ultimo precio cargado para la prestacion
    private double buscarPrecio(Long idPrestacion) {
        DetalleListaPrecio ultimo = null;
        for (DetalleListaPrecio detalle : detalleListaPrecioRepository.findAll()) {
            if (detalle.getPrestacion().getId().equals(idPrestacion)) {
                if (ultimo == null || detalle.getId() > ultimo.getId()) {
                    ultimo = detalle;
                }
            }
        }
        if (ultimo == null) {
            throw new IllegalArgumentException("La prestacion " + idPrestacion + " no tiene precio cargado");
        }
        return ultimo.getPrecio();
    }

    public List<Comprobante> getAllComprobantes() {
        return comprobanteRepository.findAll();
    }

    public Optional<Comprobante> getComprobanteById(Long idComprobante) {
        return comprobanteRepository.findById(idComprobante);
    }
}
