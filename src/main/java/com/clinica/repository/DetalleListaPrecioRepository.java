package com.clinica.repository;

import com.clinica.model.DetalleListaPrecio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetalleListaPrecioRepository extends JpaRepository<DetalleListaPrecio, Long> {
    
}
