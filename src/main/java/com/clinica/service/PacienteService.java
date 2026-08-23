package com.clinica.service;

import com.clinica.model.Paciente;
import com.clinica.repository.PacienteRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service //indica que esta clase pertenece a la capa de servicio (lógica de negocio)
public class PacienteService {
    
    @Autowired //solicitar la instancia que se creo en spring 
    private PacienteRepository pacienteRepository;

    public Paciente savePaciente(Paciente paciente) {
        return pacienteRepository.save(paciente);
    }

    public List<Paciente> getAllPacientes() {
        return pacienteRepository.findAll();
    }

    public Optional<Paciente> getPacienteById(Long idPaciente) {
        return pacienteRepository.findById(idPaciente);
    }

        public void deletePaciente(Long idPaciente) {
        pacienteRepository.deleteById(idPaciente);
    }

}
