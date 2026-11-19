package com.clinica.controller;

import com.clinica.model.DetalleListaPrecio;
import java.util.Optional;
import com.clinica.service.DetalleListaPreciosService;
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
@RequestMapping("/detallelistaprecio")
public class DetalleListaPrecioController {

     @Autowired
    private DetalleListaPreciosService detalleListaPreciosService;

    @GetMapping(
        value = "/{idDetalleListaPrecio}",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> getDetalleListaPrecioById(
        @PathVariable(name = "idDetalleListaPrecio") Long idDetalleListaPrecio
    ) {
        Optional<DetalleListaPrecio> detalleListaPrecio =
            detalleListaPreciosService.getDetalleListaPrecioById(idDetalleListaPrecio);

        if (detalleListaPrecio.isPresent()) {
            return ResponseEntity.ok(detalleListaPrecio.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(
        consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> guardarDetalleListaPrecio(
        @RequestBody DetalleListaPrecio detalleListaPrecio
    ) {
        try {
            DetalleListaPrecio detalleListaPrecioGuardada =
                detalleListaPreciosService.saveDetalleListaPrecio(detalleListaPrecio);

            return ResponseEntity
                .created(URI.create("/detallelistaprecio/" + detalleListaPrecioGuardada.getId()))
                .body(detalleListaPrecioGuardada);

        } catch (IllegalArgumentException e) {
            return ResponseEntity
                .badRequest()
                .body(e.getMessage());

        } catch (Exception e) {
            return ResponseEntity
                .internalServerError()
                .body(null);
        }
    }

    @GetMapping(
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> getAllDetalleListaPrecios() {
        return ResponseEntity.ok(detalleListaPreciosService.getAllDetalleListaPrecios());
    }

    @PutMapping(
        value = "/{idDetalleListaPrecio}",
        consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> actualizarDetalleListaPrecio(
        @PathVariable(name = "idDetalleListaPrecio") Long idDetalleListaPrecio,
        @RequestBody DetalleListaPrecio detalleListaPrecio
    ) {
        try {
            Optional<DetalleListaPrecio> existente = detalleListaPreciosService.getDetalleListaPrecioById(idDetalleListaPrecio);

            if (existente.isPresent()) {
                detalleListaPrecio.setId(idDetalleListaPrecio);
                DetalleListaPrecio detalleListaPrecioActualizado = detalleListaPreciosService.saveDetalleListaPrecio(detalleListaPrecio);
                return ResponseEntity.ok(detalleListaPrecioActualizado);
            } else {
                return ResponseEntity.notFound().build();
            }

        } catch (IllegalArgumentException e) {
            return ResponseEntity
                .badRequest()
                .body(e.getMessage());

        } catch (Exception e) {
            return ResponseEntity
                .internalServerError()
                .body(null);
        }
    }

    @DeleteMapping("/{idDetalleListaPrecio}")
    public ResponseEntity<?> eliminarDetalleListaPrecio(
        @PathVariable(name = "idDetalleListaPrecio") Long idDetalleListaPrecio
    ) {
        try {
            Optional<DetalleListaPrecio> existente = detalleListaPreciosService.getDetalleListaPrecioById(idDetalleListaPrecio);

            if (existente.isPresent()) {
                detalleListaPreciosService.deleteDetalleListaPrecio(idDetalleListaPrecio);
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
