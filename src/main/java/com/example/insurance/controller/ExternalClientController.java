package com.example.insurance.controller;

import com.example.insurance.dto.ExternalClientRequest;
import com.example.insurance.dto.ExternalClientResponseList;
import com.example.insurance.model.Exams;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;

import java.net.http.HttpResponse;
import java.util.List;

@RestController
@RequestMapping("api/v1/external")
@AllArgsConstructor
public class ExternalClientController {

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping()
    public ExternalClientResponseList getAllExternals() throws URISyntaxException, IOException, InterruptedException {
        //  return examsService.allExams();
        //HttpRequest request = HttpRequest.newBuilder(new URI("http://localhost:8090/api/v1/clients")).GET().build();
        //HttpResponse<Object> response = HttpClient.newBuilder().build().send(request, HttpResponse.BodyHandlers.ofString());
        //List<ExternalClientRequest> externalClientList = response.body();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> objectHttpEntity = new HttpEntity<>(httpHeaders);
        ResponseEntity<Object> exchange = null;
        ExternalClientResponseList externalClientRequest = null;
        ExternalClientResponseList externalClientResponseList;
        try {
            //exchange = new RestTemplate().exchange("http://localhost:8090/api/v1/clients/5", HttpMethod.GET, objectHttpEntity, Object.class);
            HttpClient client = HttpClient.newHttpClient();

            // Construir la solicitud HTTP
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("http://localhost:8090/api/v1/clients"))
                    .GET()
                    .build();

            // Enviar la solicitud y obtener la respuesta
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Obtener el cuerpo de la respuesta
            String responseBody = response.body();
            ObjectMapper objectMapper = new ObjectMapper();
            List<ExternalClientRequest> externalClientRequestList = objectMapper.readValue(responseBody, new TypeReference<List<ExternalClientRequest>>() {});
            externalClientResponseList = new ExternalClientResponseList(externalClientRequestList);

        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }


        return externalClientResponseList;

    }


}

