package com.clinica.controller;

import com.clinica.model.Paciente;
import java.util.Optional;
import com.clinica.service.PacienteService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import java.net.URI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
}