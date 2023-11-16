package com.uco.yourplus.serviceyourplus.usecase.reciever.laboratorio.implementation;

import com.uco.yourplus.crosscuttingyourplus.exceptions.service.ServiceCustomException;
import com.uco.yourplus.crosscuttingyourplus.helper.json.MapperJsonObject;
import com.uco.yourplus.crosscuttingyourplus.properties.LaboratorioPropertiesCatalogProducer;
import com.uco.yourplus.serviceyourplus.domain.LaboratorioDomain;
import com.uco.yourplus.serviceyourplus.domain.ResponseDomain;
import com.uco.yourplus.serviceyourplus.domain.enumeration.StateResponse;
import com.uco.yourplus.serviceyourplus.usecase.laboratorio.ConsultarLaboratorio;
import com.uco.yourplus.serviceyourplus.usecase.producer.response.ConfigRabbitContentResponse;
import com.uco.yourplus.serviceyourplus.usecase.reciever.laboratorio.RabbitMQListReceiverLaboratorio;
import com.uco.yourplus.serviceyourplus.usecase.reciever.producto.implementation.ConfigurateSendResponse;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RabbitMQListReceiverLaboratorioImpl implements RabbitMQListReceiverLaboratorio {

    private final ConsultarLaboratorio useCase;
    private final MapperJsonObject mapperJsonObject;
    private final RabbitTemplate rabbitTemplate;
    private final ConfigRabbitContentResponse configRabbitContentResponse;
    private final LaboratorioPropertiesCatalogProducer producer;

    private final ConfigurateSendResponse<LaboratorioDomain> configurateSendResponse;

    public RabbitMQListReceiverLaboratorioImpl(ConsultarLaboratorio useCase, MapperJsonObject mapperJsonObject, RabbitTemplate rabbitTemplate,
                                               ConfigRabbitContentResponse configRabbitContentResponse, @Qualifier("laboratorioPropertiesCatalogProducer") LaboratorioPropertiesCatalogProducer producer, ConfigurateSendResponse<LaboratorioDomain> configurateSendResponse) {
        this.useCase = useCase;
        this.mapperJsonObject = mapperJsonObject;
        this.rabbitTemplate = rabbitTemplate;
        this.configRabbitContentResponse = configRabbitContentResponse;
        this.producer = producer;
        this.configurateSendResponse = configurateSendResponse;
    }

    @RabbitListener(queues = "${yourplus.management.laboratorio.queue.list}")
    @Override
    public void execute(String message) {
        ResponseDomain<LaboratorioDomain> responseDomain = configurateSendResponse.setIdForMessage(message);
        MessageProperties messageProperties = configRabbitContentResponse.generateMessageProperties(responseDomain.getId());
        StateResponse stateResponse = StateResponse.SUCCESS;
        Optional<Message> bodyMessage = Optional.empty();
        try {
            LaboratorioDomain domain = mapperJsonObject.execute(message, LaboratorioDomain.class).get();
            List<LaboratorioDomain> laboratorioDomainList = useCase.execute(Optional.of(domain));

            responseDomain.setStateResponse(stateResponse);
            responseDomain.setMessage("Lista de laboratorios");
            responseDomain.setData(laboratorioDomainList);
            bodyMessage = configRabbitContentResponse.getBodyMessage(responseDomain, messageProperties);
        } catch (ServiceCustomException exception) {
            stateResponse = StateResponse.ERROR;
            responseDomain.setStateResponse(stateResponse);
            if (exception.isTechnicalException()) {
                responseDomain.setMessage("Algo salio mal consultando los laboratorios, intenta nuevamente");
            } else {
                responseDomain.setMessage(exception.getMessage());
            }
            bodyMessage = configRabbitContentResponse.getBodyMessage(responseDomain, messageProperties);
        } catch (Exception exception) {
            stateResponse = StateResponse.ERROR;
            responseDomain.setStateResponse(stateResponse);
            responseDomain.setMessage("Ocurrió un error fatal, intentalo en unos minutos");
            bodyMessage = configRabbitContentResponse.getBodyMessage(responseDomain,messageProperties);
        } finally {
            rabbitTemplate.convertAndSend(producer.getExchange(), producer.getRoutingkey().getList(), bodyMessage.get());
        }
    }
}
