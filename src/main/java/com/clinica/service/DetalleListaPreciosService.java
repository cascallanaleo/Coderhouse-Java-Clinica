package com.clinica.service;

import com.clinica.model.DetalleListaPrecio;
import com.clinica.model.ListaPrecio;
import com.clinica.model.Prestacion;
import com.clinica.repository.DetalleListaPrecioRepository;
import com.clinica.repository.ListaPrecioRepository;
import com.clinica.repository.PrestacionRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service //indica que esta clase pertenece a la capa de servicio (lógica de negocio)
public class DetalleListaPreciosService {
    
    @Autowired //solicitar la instancia que se creo en spring 
    private DetalleListaPrecioRepository detalleListaPrecioRepository;

    @Autowired
    private ListaPrecioRepository listaPrecioRepository;

    @Autowired
    private PrestacionRepository prestacionRepository;

    public DetalleListaPrecio saveDetalleListaPrecio(DetalleListaPrecio detalleListaPrecio) {
        if (detalleListaPrecio.getListaPrecio() == null || detalleListaPrecio.getListaPrecio().getId() == null) {
            throw new IllegalArgumentException("Falta indicar la lista de precios");
        }
        if (detalleListaPrecio.getPrestacion() == null || detalleListaPrecio.getPrestacion().getId() == null) {
            throw new IllegalArgumentException("Falta indicar la prestacion");
        }

        Long idListaPrecio = detalleListaPrecio.getListaPrecio().getId();
        ListaPrecio listaPrecio = listaPrecioRepository.findById(idListaPrecio).orElse(null);
        if (listaPrecio == null) {
            throw new IllegalArgumentException("La lista de precios " + idListaPrecio + " no existe");
        }

        Long idPrestacion = detalleListaPrecio.getPrestacion().getId();
        Prestacion prestacion = prestacionRepository.findById(idPrestacion).orElse(null);
        if (prestacion == null) {
            throw new IllegalArgumentException("La prestacion " + idPrestacion + " no existe");
        }

        detalleListaPrecio.setListaPrecio(listaPrecio);
        detalleListaPrecio.setPrestacion(prestacion);
        return detalleListaPrecioRepository.save(detalleListaPrecio);
    }

    public List<DetalleListaPrecio> getAllDetalleListaPrecios() {
        return detalleListaPrecioRepository.findAll();
    }

    public Optional<DetalleListaPrecio> getDetalleListaPrecioById(Long idDetalleListaPrecio) {
        return detalleListaPrecioRepository.findById(idDetalleListaPrecio);
    }

        public void deleteDetalleListaPrecio(Long idDetalleListaPrecio) {
        detalleListaPrecioRepository.deleteById(idDetalleListaPrecio);
    }

}
