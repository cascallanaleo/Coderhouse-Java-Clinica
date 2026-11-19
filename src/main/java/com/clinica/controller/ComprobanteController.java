package com.clinica.controller;

import com.clinica.model.Comprobante;
import java.util.Optional;
import com.clinica.service.ComprobanteService;
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
@RequestMapping("/comprobante")
public class ComprobanteController {

    @Autowired
    private ComprobanteService comprobanteService;

    @GetMapping(
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> getAllComprobantes() {
        return ResponseEntity.ok(comprobanteService.getAllComprobantes());
    }

    @GetMapping(
        value = "/{idComprobante}",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> getComprobanteById(
        @PathVariable(name = "idComprobante") Long idComprobante
    ) {
        Optional<Comprobante> comprobante =
            comprobanteService.getComprobanteById(idComprobante);

        if (comprobante.isPresent()) {
            return ResponseEntity.ok(comprobante.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(
        consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> guardarComprobante(
        @RequestBody Comprobante comprobante
    ) {
        try {
            Comprobante comprobanteGuardado =
                comprobanteService.saveComprobante(comprobante);

            return ResponseEntity
                .created(URI.create("/comprobante/" + comprobanteGuardado.getId()))
                .body(comprobanteGuardado);

        } catch (IllegalArgumentException e) {
            // no paso alguna validacion: devuelvo el mensaje
            return ResponseEntity
                .badRequest()
                .body(e.getMessage());

        } catch (Exception e) {
            return ResponseEntity
                .internalServerError()
                .body(null);
        }
    }
}
