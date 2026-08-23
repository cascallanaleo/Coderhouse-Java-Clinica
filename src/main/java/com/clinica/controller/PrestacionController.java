package com.clinica.controller;

import com.clinica.model.Prestacion;
import java.util.Optional;
import com.clinica.service.PrestacionService;
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
@RequestMapping("/prestacion")
public class PrestacionController {

     @Autowired
    private PrestacionService prestacionService;

    @GetMapping(
        value = "/{idPrestacion}",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> getPrestacionById(
        @PathVariable(name = "idPrestacion") Long idPrestacion
    ) {
        Optional<Prestacion> prestacion =
            prestacionService.getPrestacionById(idPrestacion);

        if (prestacion.isPresent()) {
            return ResponseEntity.ok(prestacion.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(
        consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> guardarPrestacion(
        @RequestBody Prestacion prestacion
    ) {
        try {
            Prestacion prestacionGuardada =
                prestacionService.savePrestacion(prestacion);

            return ResponseEntity
                .created(URI.create("/prestacion/" + prestacionGuardada.getId()))
                .body(prestacionGuardada);

        } catch (Exception e) {
            return ResponseEntity
                .internalServerError()
                .body(null);
        }
    }
}