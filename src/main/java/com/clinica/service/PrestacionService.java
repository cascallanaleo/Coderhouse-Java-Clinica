package com.clinica.service;

import com.clinica.model.Prestacion;
import com.clinica.repository.PrestacionRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrestacionService {

    @Autowired 
    private PrestacionRepository prestacionRepository;

    public Prestacion savePrestacion(Prestacion prestacion) {
        return prestacionRepository.save(prestacion);
    }
    public List<Prestacion> getAllPrestaciones() {
        return prestacionRepository.findAll();
    }
    public Optional<Prestacion> getPrestacionById(Long idPrestacion) {
        return prestacionRepository.findById(idPrestacion);
    }
    public void deletePrestacion(Long idPrestacion) {
        prestacionRepository.deleteById(idPrestacion);
    }

    
}
