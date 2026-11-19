package com.clinica.controller;

import com.clinica.model.Prestacion;
import java.util.Optional;
import com.clinica.service.PrestacionService;
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

    @GetMapping(
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> getAllPrestaciones() {
        return ResponseEntity.ok(prestacionService.getAllPrestaciones());
    }

    @PutMapping(
        value = "/{idPrestacion}",
        consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> actualizarPrestacion(
        @PathVariable(name = "idPrestacion") Long idPrestacion,
        @RequestBody Prestacion prestacion
    ) {
        try {
            Optional<Prestacion> existente = prestacionService.getPrestacionById(idPrestacion);

            if (existente.isPresent()) {
                prestacion.setId(idPrestacion);
                Prestacion prestacionActualizado = prestacionService.savePrestacion(prestacion);
                return ResponseEntity.ok(prestacionActualizado);
            } else {
                return ResponseEntity.notFound().build();
            }

        } catch (Exception e) {
            return ResponseEntity
                .internalServerError()
                .body(null);
        }
    }

    @DeleteMapping("/{idPrestacion}")
    public ResponseEntity<?> eliminarPrestacion(
        @PathVariable(name = "idPrestacion") Long idPrestacion
    ) {
        try {
            Optional<Prestacion> existente = prestacionService.getPrestacionById(idPrestacion);

            if (existente.isPresent()) {
                prestacionService.deletePrestacion(idPrestacion);
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
