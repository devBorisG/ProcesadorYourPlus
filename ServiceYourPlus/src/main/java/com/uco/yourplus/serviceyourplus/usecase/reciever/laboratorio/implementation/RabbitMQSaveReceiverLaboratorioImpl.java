package com.uco.yourplus.serviceyourplus.usecase.reciever.laboratorio.implementation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uco.yourplus.crosscuttingyourplus.exceptions.service.ServiceCustomException;
import com.uco.yourplus.crosscuttingyourplus.helper.json.MapperJsonObject;
import com.uco.yourplus.crosscuttingyourplus.properties.LaboratorioPropertiesCatalogProducer;
import com.uco.yourplus.serviceyourplus.domain.LaboratorioDomain;
import com.uco.yourplus.serviceyourplus.domain.ResponseDomain;
import com.uco.yourplus.serviceyourplus.domain.enumeration.StateResponse;
import com.uco.yourplus.serviceyourplus.usecase.laboratorio.RegistrarLaboratorio;
import com.uco.yourplus.serviceyourplus.usecase.producer.response.ConfigRabbitContentResponse;
import com.uco.yourplus.serviceyourplus.usecase.reciever.laboratorio.RabbitMQSaveReceiverLaboratorio;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@EnableConfigurationProperties(LaboratorioPropertiesCatalogProducer.class)
public class RabbitMQSaveReceiverLaboratorioImpl implements RabbitMQSaveReceiverLaboratorio {

    private final RegistrarLaboratorio useCase;
    private final MapperJsonObject mapperJsonObject;
    private final RabbitTemplate rabbitTemplate;
    private final ConfigRabbitContentResponse configRabbitContentResponse;
    private final LaboratorioPropertiesCatalogProducer producer;

    public RabbitMQSaveReceiverLaboratorioImpl(RegistrarLaboratorio useCase, MapperJsonObject mapperJsonObject, RabbitTemplate rabbitTemplate,
                                               ConfigRabbitContentResponse configRabbitContentResponse,@Qualifier("laboratorioPropertiesCatalogProducer") LaboratorioPropertiesCatalogProducer producer) {
        this.useCase = useCase;
        this.mapperJsonObject = mapperJsonObject;
        this.rabbitTemplate = rabbitTemplate;
        this.configRabbitContentResponse = configRabbitContentResponse;
        this.producer = producer;
    }

    @RabbitListener(queues = "${yourplus.management.laboratorio.response.queue.save}")
    @Override
    public void execute(String message) {
        try{
            ResponseDomain<LaboratorioDomain> responseDomain = setIdForMessage(message);
            MessageProperties messageProperties = configRabbitContentResponse.generateMessageProperties(responseDomain.getId());
            sentResponse(responseDomain,message,messageProperties);
        } catch (JsonProcessingException exception){
            throw ServiceCustomException.createTechnicalException(exception,"No se pudo agregar el id del mensaje");
        } catch (Exception exception){
            throw ServiceCustomException.createTechnicalException(exception,"Ocurrio un error inesperado");
        }
    }

    private ResponseDomain<LaboratorioDomain> setIdForMessage(String message) throws JsonProcessingException{
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(message);
        ResponseDomain<LaboratorioDomain> responseDomain = new ResponseDomain<>();
        responseDomain.setId(UUID.fromString(jsonNode.get("id").asText()));
        return responseDomain;
    }

    private void sentResponse(ResponseDomain<LaboratorioDomain> responseDomain, String message, MessageProperties messageProperties){
        StateResponse stateResponse = StateResponse.SUCCESS;
        Optional<Message> bodyMessage = Optional.empty();
        try{
            LaboratorioDomain domain = mapperJsonObject.execute(message,LaboratorioDomain.class).get();
            useCase.execute(domain);
            responseDomain.setStateResponse(stateResponse);
            responseDomain.setMessage("Laboratorio registrado con exito");
            bodyMessage = configRabbitContentResponse.getBodyMessage(responseDomain,messageProperties);
        } catch(ServiceCustomException exception){
            stateResponse = StateResponse.ERROR;
            responseDomain.setStateResponse(stateResponse);
            if(exception.isTechnicalException()){
                responseDomain.setMessage("Algo salio mal registrando el laboratorio, intente nuevamente");
            } else{
                responseDomain.setMessage(exception.getMessage());
            }
            bodyMessage = configRabbitContentResponse.getBodyMessage(responseDomain,messageProperties);
        } catch (Exception exception){
            stateResponse = StateResponse.ERROR;
            responseDomain.setStateResponse(stateResponse);
            responseDomain.setMessage("Ocurrio un error fatal, intente de nuevo en unos minutos");
            bodyMessage = configRabbitContentResponse.getBodyMessage(responseDomain,messageProperties);
        } finally{
            rabbitTemplate.convertAndSend(producer.getExchange(),producer.getRoutingKey().getSave(),bodyMessage.get());
        }
    }
}
