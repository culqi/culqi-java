package com.culqi.pruebas.controllers;

import com.culqi.pruebas.domain.SuscripcionData;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

/**
 * Created by admin_InSolutions on 9/09/2016.
 */
@RestController
@RequestMapping
public class NuevaSuscripcionController {

    private static final Logger log = Logger.getLogger(NuevaSuscripcionController.class);

    @Value("${culqi.config.codigoComercio}")
    private String Codigo_Comercio;

    @Value("${culqi.config.llaveSecreta}")
    private String Llave_Secreta;

    @Value("${culqi.url.mod_pago_suscripciones}")
    private String URLModuloPagoSuscripciones;

    @RequestMapping(value = "/nueva-suscripcion", method = RequestMethod.POST)
    public ResponseEntity<String> create(@Validated @RequestBody SuscripcionData suscripcionData){
        RestTemplate restTemplateNuevaSuscripcion = new RestTemplate();

        JSONObject requestSuscripcion = new JSONObject();
        requestSuscripcion.put("codigo_comercio", suscripcionData.getCodigo_comercio());
        requestSuscripcion.put("codigo_pais", suscripcionData.getCodigo_pais());
        requestSuscripcion.put("direccion", suscripcionData.getDireccion());
        requestSuscripcion.put("ciudad", suscripcionData.getCiudad());
        requestSuscripcion.put("telefono", suscripcionData.getTelefono());
        requestSuscripcion.put("nombre", suscripcionData.getNombre());
        requestSuscripcion.put("apellido", suscripcionData.getApellido());
        requestSuscripcion.put("correo_electronico", suscripcionData.getCorreo_electronico());
        requestSuscripcion.put("usuario", suscripcionData.getUsuarioId());
        requestSuscripcion.put("plan_id", suscripcionData.getPlan_id());
        requestSuscripcion.put("token", suscripcionData.getToken());
        if (suscripcionData.getCargos_predefinidos()){
            requestSuscripcion.put("cargos", suscripcionData.getGenerarCargos());
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Bearer " + Llave_Secreta);

        HttpEntity<String> entity = new HttpEntity<>(requestSuscripcion.toString(), headers);

        ResponseEntity<String> responseSuscripcion;
        HashMap<String, Object> respuestaSuscripcion = new HashMap<>();

        try {

            responseSuscripcion = restTemplateNuevaSuscripcion.exchange(URLModuloPagoSuscripciones, HttpMethod.POST, entity, String.class);

            log.info("Respuesta de la creación de la suscripción: " + responseSuscripcion.toString());

            respuestaSuscripcion.put("respuesta", "Suscripción creada exitosamente.");

            ObjectMapper objectMapper = new ObjectMapper();
            String test = "";
            try {
                test = objectMapper.writeValueAsString(respuestaSuscripcion);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }

            return ResponseEntity.ok(test);

        } catch (HttpClientErrorException e) {

            log.info("Respuesta de la creación de la suscripción: " + e.getResponseBodyAsString());

            respuestaSuscripcion.put("error", "Error al crear una suscripción.");

            ObjectMapper objectMapper = new ObjectMapper();
            String test = "";
            try {
                test = objectMapper.writeValueAsString(respuestaSuscripcion);
            } catch (JsonProcessingException x) {
                x.printStackTrace();
            }

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(test);
        }
    }
}
