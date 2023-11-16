package com.uco.yourplus.serviceyourplus.usecase.reciever.laboratorio.implementation;

import com.uco.yourplus.crosscuttingyourplus.exceptions.service.ServiceCustomException;
import com.uco.yourplus.crosscuttingyourplus.helper.json.MapperJsonObject;
import com.uco.yourplus.crosscuttingyourplus.properties.LaboratorioPropertiesCatalogProducer;
import com.uco.yourplus.serviceyourplus.domain.LaboratorioDomain;
import com.uco.yourplus.serviceyourplus.domain.ResponseDomain;
import com.uco.yourplus.serviceyourplus.domain.enumeration.StateResponse;
import com.uco.yourplus.serviceyourplus.usecase.laboratorio.EliminarLaboratorio;
import com.uco.yourplus.serviceyourplus.usecase.producer.response.ConfigRabbitContentResponse;
import com.uco.yourplus.serviceyourplus.usecase.reciever.laboratorio.RabbitMQDeleteReceiverLaboratorio;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@EnableConfigurationProperties(LaboratorioPropertiesCatalogProducer.class)
public class RabbitMQDeleteReceiverLaboratorioImpl implements RabbitMQDeleteReceiverLaboratorio {

    private final EliminarLaboratorio useCase;

    private final MapperJsonObject mapperJsonObject;
    private final RabbitTemplate rabbitTemplate;
    private final ConfigRabbitContentResponse configRabbitContentResponse;

    private final LaboratorioPropertiesCatalogProducer producer;

    public RabbitMQDeleteReceiverLaboratorioImpl(EliminarLaboratorio useCase, MapperJsonObject mapperJsonObject, RabbitTemplate rabbitTemplate,
                                                 ConfigRabbitContentResponse configRabbitContentResponse,@Qualifier("laboratorioPropertiesCatalogProducer") LaboratorioPropertiesCatalogProducer producer) {
        this.useCase = useCase;
        this.mapperJsonObject = mapperJsonObject;
        this.rabbitTemplate = rabbitTemplate;
        this.configRabbitContentResponse = configRabbitContentResponse;
        this.producer = producer;
    }

    @RabbitListener(queues = "${yourplus.management.laboratorio.response.queue.delete}")
    @Override
    public void execute(String message) {
        StateResponse stateResponse = StateResponse.SUCCESS;
        final ResponseDomain<LaboratorioDomain> responseDomain = new ResponseDomain<>();
        try {
            LaboratorioDomain domain = mapperJsonObject.execute(message, LaboratorioDomain.class).get();
            useCase.execute(domain);
            responseDomain.setStateResponse(stateResponse);
            responseDomain.setMessage("Laboratorio eliminado con éxito");
        } catch (ServiceCustomException exception) {
            stateResponse = StateResponse.ERROR;
            responseDomain.setStateResponse(stateResponse);
            if (exception.isTechnicalException()) {
                responseDomain.setMessage("Algo salio mal eliminando el laboratorio, intenta nuevamente");
            } else {
                responseDomain.setMessage(exception.getMessage());
            }
        } catch (Exception exception) {
            stateResponse = StateResponse.ERROR;
            responseDomain.setStateResponse(stateResponse);
            responseDomain.setMessage("Ocurrió un error fatal, intentalo en unos minutos");
        } finally {
            MessageProperties messageProperties = configRabbitContentResponse.generateMessageProperties(responseDomain.getId());
            Optional<Message> bodyMessage = configRabbitContentResponse.getBodyMessage(responseDomain, messageProperties);
            rabbitTemplate.convertAndSend(producer.getExchange(),producer.getRoutingkey().getDelete(),bodyMessage.get());
        }
    }
}
