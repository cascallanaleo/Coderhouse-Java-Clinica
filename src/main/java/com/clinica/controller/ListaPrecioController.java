package com.clinica.controller;

import com.clinica.model.ListaPrecio;
import java.util.Optional;
import com.clinica.service.ListaPrecioService;
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
@RequestMapping("/listaprecio")
public class ListaPrecioController {

     @Autowired
    private ListaPrecioService listaPrecioService;

    @GetMapping(
        value = "/{idListaPrecio}",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> getListaPrecioById(
        @PathVariable(name = "idListaPrecio") Long idListaPrecio
    ) {
        Optional<ListaPrecio> listaPrecio =
            listaPrecioService.getListaPrecioById(idListaPrecio);

        if (listaPrecio.isPresent()) {
            return ResponseEntity.ok(listaPrecio.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(
        consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> guardarListaPrecio(
        @RequestBody ListaPrecio listaPrecio
    ) {
        try {
            ListaPrecio listaPrecioGuardada =
                listaPrecioService.saveListaPrecio(listaPrecio);

            return ResponseEntity
                .created(URI.create("/listaprecio/" + listaPrecioGuardada.getId()))
                .body(listaPrecioGuardada);

        } catch (Exception e) {
            return ResponseEntity
                .internalServerError()
                .body(null);
        }
    }

    @GetMapping(
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> getAllListaPrecios() {
        return ResponseEntity.ok(listaPrecioService.getAllListaPrecios());
    }

    @PutMapping(
        value = "/{idListaPrecio}",
        consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> actualizarListaPrecio(
        @PathVariable(name = "idListaPrecio") Long idListaPrecio,
        @RequestBody ListaPrecio listaPrecio
    ) {
        try {
            Optional<ListaPrecio> existente = listaPrecioService.getListaPrecioById(idListaPrecio);

            if (existente.isPresent()) {
                listaPrecio.setId(idListaPrecio);
                ListaPrecio listaPrecioActualizado = listaPrecioService.saveListaPrecio(listaPrecio);
                return ResponseEntity.ok(listaPrecioActualizado);
            } else {
                return ResponseEntity.notFound().build();
            }

        } catch (Exception e) {
            return ResponseEntity
                .internalServerError()
                .body(null);
        }
    }

    @DeleteMapping("/{idListaPrecio}")
    public ResponseEntity<?> eliminarListaPrecio(
        @PathVariable(name = "idListaPrecio") Long idListaPrecio
    ) {
        try {
            Optional<ListaPrecio> existente = listaPrecioService.getListaPrecioById(idListaPrecio);

            if (existente.isPresent()) {
                listaPrecioService.deleteListaPrecio(idListaPrecio);
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
