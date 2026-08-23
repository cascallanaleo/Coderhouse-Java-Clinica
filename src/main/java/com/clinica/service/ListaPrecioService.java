package com.clinica.service;

import com.clinica.model.ListaPrecio;
import com.clinica.repository.ListaPrecioRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ListaPrecioService {
    @Autowired
    private ListaPrecioRepository listaPrecioRepository;   

    public ListaPrecio saveListaPrecio(ListaPrecio listaPrecio) {
        return listaPrecioRepository.save(listaPrecio);
    }
    public List<ListaPrecio> getAllListaPrecios() {
        return listaPrecioRepository.findAll();
    }
    public Optional<ListaPrecio> getListaPrecioById(Long idListaPrecio) {
        return listaPrecioRepository.findById(idListaPrecio);
    }
    public void deleteListaPrecio(Long idListaPrecio) {
        listaPrecioRepository.deleteById(idListaPrecio);
    }


    
}
