package com.clinica.service;

import com.clinica.model.DetalleListaPrecio;
import com.clinica.repository.DetalleListaPrecioRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service //indica que esta clase pertenece a la capa de servicio (lógica de negocio)
public class DetalleListaPreciosService {
    
    @Autowired //solicitar la instancia que se creo en spring 
    private DetalleListaPrecioRepository detalleListaPrecioRepository;

    public DetalleListaPrecio saveDetalleListaPrecio(DetalleListaPrecio detalleListaPrecio) {
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