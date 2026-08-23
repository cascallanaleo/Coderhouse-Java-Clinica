package com.clinica.repository;

import com.clinica.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@repository //indica que esta interfaz pertenece a la capa de persistencia
public interface PacienteRepository extends JpaRepository<Paciente, Long> {
 
}
