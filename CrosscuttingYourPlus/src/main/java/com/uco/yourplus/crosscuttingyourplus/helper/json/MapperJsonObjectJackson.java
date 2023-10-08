package com.uco.yourplus.crosscuttingyourplus.helper.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.uco.yourplus.crosscuttingyourplus.exceptions.crosscutting.CrosscuttingCustomException;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Component
public class MapperJsonObjectJackson implements MapperJsonObject {
    @Override
    public Optional<String> execute(Object object) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return Optional.ofNullable(objectMapper.writeValueAsString(object));
        } catch (JsonProcessingException exception) {
            throw CrosscuttingCustomException.createTechnicalException(exception, "Ocurrió un error al procesar (generar) el contenido Json");
        } catch (Exception exception) {
            throw CrosscuttingCustomException.createTechnicalException(exception, "Ocurrió un error inesperado al intentar mappear el objeto a Json");
        }
    }

    @Override
    public <T> Optional<T> execute(String json, Class<T> targetClass) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return Optional.ofNullable(objectMapper.readValue(json, targetClass));
        } catch (JsonMappingException exception) {
            throw CrosscuttingCustomException.createTechnicalException(exception, "Ocurrió un error al intentar asignar el contenido del Json al objeto");
        } catch (JsonProcessingException exception) {
            throw CrosscuttingCustomException.createTechnicalException(exception, "Ocurrió un error al procesar (analizar) el contenido Json");
        } catch (Exception exception) {
            throw CrosscuttingCustomException.createTechnicalException(exception, "Ocurrió un error inesperado al convertir el Json a Objeto");
        }
    }

    @Override
    public Optional<String> executeGson(Object object) {
        try {
            Gson gson = new GsonBuilder().serializeNulls()
                    .registerTypeAdapter(LocalDateTime.class, (JsonDeserializer<LocalDateTime>) (json, typeOfT, context) -> LocalDateTime.parse(json.getAsString(), DateTimeFormatter.ISO_LOCAL_DATE_TIME))
                    .create();
            String objectStr = gson.toJson(object);
            return Optional.ofNullable(objectStr);
        } catch (Exception exception) {
            throw CrosscuttingCustomException.createTechnicalException(exception, "Ocurrió un error al intentar crear al Gson");
        }
    }
}
