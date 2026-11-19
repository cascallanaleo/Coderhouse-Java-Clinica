package com.clinica.controller;

import com.clinica.model.Paciente;
import java.util.Optional;
import com.clinica.service.PacienteService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import java.net.URI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/paciente")
public class PacienteController {

     @Autowired
    private PacienteService pacienteService;

    @GetMapping(
        value = "/{idPaciente}",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> getPacienteById(
        @PathVariable(name = "idPaciente") Long idPaciente
    ) {
        Optional<Paciente> paciente =
            pacienteService.getPacienteById(idPaciente);

        if (paciente.isPresent()) {
            return ResponseEntity.ok(paciente.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(
        consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> guardarPaciente(
        @RequestBody Paciente paciente
    ) {
        try {
            Paciente pacienteGuardado =
                pacienteService.savePaciente(paciente);

            return ResponseEntity
                .created(URI.create("/paciente/" + pacienteGuardado.getId()))
                .body(pacienteGuardado);

        } catch (Exception e) {
            return ResponseEntity
                .internalServerError()
                .body(null);
        }
    }

    @GetMapping(
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> getAllPacientes() {
        return ResponseEntity.ok(pacienteService.getAllPacientes());
    }

    @PutMapping(
        value = "/{idPaciente}",
        consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> actualizarPaciente(
        @PathVariable(name = "idPaciente") Long idPaciente,
        @RequestBody Paciente paciente
    ) {
        try {
            Optional<Paciente> existente = pacienteService.getPacienteById(idPaciente);

            if (existente.isPresent()) {
                paciente.setId(idPaciente);
                Paciente pacienteActualizado = pacienteService.savePaciente(paciente);
                return ResponseEntity.ok(pacienteActualizado);
            } else {
                return ResponseEntity.notFound().build();
            }

        } catch (Exception e) {
            return ResponseEntity
                .internalServerError()
                .body(null);
        }
    }

    @DeleteMapping("/{idPaciente}")
    public ResponseEntity<?> eliminarPaciente(
        @PathVariable(name = "idPaciente") Long idPaciente
    ) {
        try {
            Optional<Paciente> existente = pacienteService.getPacienteById(idPaciente);

            if (existente.isPresent()) {
                pacienteService.deletePaciente(idPaciente);
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.notFound().build();
            }

        } catch (Exception e) {
            return ResponseEntity
                .internalServerError()
                .body(null);
        }
    }
}
