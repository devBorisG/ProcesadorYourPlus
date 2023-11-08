package com.uco.yourplus.serviceyourplus.usecase.reciever.producto.implementation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uco.yourplus.crosscuttingyourplus.exceptions.service.ServiceCustomException;
import com.uco.yourplus.serviceyourplus.domain.ResponseDomain;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ConfigurateSendResponse<T> {

    public ResponseDomain<T> setIdForMessage(String message) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(message);
            ResponseDomain<T> responseDomain = new ResponseDomain<>();
            responseDomain.setId(UUID.fromString(jsonNode.get("id").asText()));
            return responseDomain;
        } catch (JsonProcessingException exception) {
            throw ServiceCustomException.createTechnicalException(exception, "No se pudo agregar el id del mensaje");
        }
    }
}
