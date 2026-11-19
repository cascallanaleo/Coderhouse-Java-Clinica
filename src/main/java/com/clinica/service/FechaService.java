package com.clinica.service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import org.springframework.stereotype.Service;

@Service
public class FechaService {

    public Date obtenerFecha() {
        try {
            HttpClient cliente = HttpClient.newHttpClient();
            HttpRequest pedido = HttpRequest.newBuilder()
                    .uri(URI.create("http://worldclockapi.com/api/json/utc/now"))
                    .timeout(Duration.ofSeconds(3))
                    .build();
            HttpResponse<String> respuesta = cliente.send(pedido, HttpResponse.BodyHandlers.ofString());

                       String texto = respuesta.body();
            String clave = "\"currentDateTime\":\"";
            int inicio = texto.indexOf(clave) + clave.length();
            int fin = texto.indexOf("\"", inicio);
            String fecha = texto.substring(inicio, fin).replace("Z", "");

            LocalDateTime fechaHora = LocalDateTime.parse(fecha);
            return Date.from(fechaHora.toInstant(ZoneOffset.UTC));

        } catch (Exception e) {
            // si el servicio falla uso la fecha del sistema
            return new Date();
        }
    }
}
