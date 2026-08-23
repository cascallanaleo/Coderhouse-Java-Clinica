package com.clinica.repository;

import com.clinica.model.Prestacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrestacionRepository extends JpaRepository<Prestacion, Long> {
}

