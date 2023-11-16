package com.uco.yourplus.serviceyourplus.usecase.reciever.categoria.implementation;

import com.uco.yourplus.crosscuttingyourplus.exceptions.service.ServiceCustomException;
import com.uco.yourplus.crosscuttingyourplus.helper.json.MapperJsonObject;
import com.uco.yourplus.crosscuttingyourplus.properties.CategoriaPropertiesCatalogProducer;
import com.uco.yourplus.serviceyourplus.domain.CategoriaDomain;
import com.uco.yourplus.serviceyourplus.domain.ResponseDomain;
import com.uco.yourplus.serviceyourplus.domain.enumeration.StateResponse;
import com.uco.yourplus.serviceyourplus.usecase.categoria.ConsultarCategorias;
import com.uco.yourplus.serviceyourplus.usecase.producer.response.ConfigRabbitContentResponse;
import com.uco.yourplus.serviceyourplus.usecase.reciever.categoria.RabbitMQListReceiveCategoria;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RabbitMQListReceiverCategoriaImpl implements RabbitMQListReceiveCategoria {

    private final ConsultarCategorias useCase;
    private final MapperJsonObject mapperJsonObject;
    private final RabbitTemplate rabbitTemplate;
    private final ConfigRabbitContentResponse configRabbitContentResponse;
    private final CategoriaPropertiesCatalogProducer producer;

    public RabbitMQListReceiverCategoriaImpl(ConsultarCategorias useCase, MapperJsonObject mapperJsonObject, RabbitTemplate rabbitTemplate,
                                             ConfigRabbitContentResponse configRabbitContentResponse, @Qualifier("categoriaPropertiesCatalogProducer") CategoriaPropertiesCatalogProducer producer) {
        this.useCase = useCase;
        this.mapperJsonObject = mapperJsonObject;
        this.rabbitTemplate = rabbitTemplate;
        this.configRabbitContentResponse = configRabbitContentResponse;
        this.producer = producer;
    }

    @RabbitListener(queues = "${yourplus.management.categoria.response.queue.list}")
    @Override
    public void execute(CategoriaDomain domain) {
        StateResponse stateResponse = StateResponse.SUCCESS;
        ResponseDomain responseDomain = new ResponseDomain();
        try {
            useCase.execute(Optional.of(domain));
            responseDomain.setStateResponse(stateResponse);
            responseDomain.setMessage("Lista de categorias");
        } catch (ServiceCustomException exception) {
            stateResponse = StateResponse.ERROR;
            responseDomain.setStateResponse(stateResponse);
            if (exception.isTechnicalException()) {
                responseDomain.setMessage("Algo salio mal consultando las categorias, intenta nuevamente");
            } else {
                responseDomain.setMessage(exception.getMessage());
            }
        } catch (Exception exception) {
            stateResponse = StateResponse.ERROR;
            responseDomain.setStateResponse(stateResponse);
            responseDomain.setMessage("Ocurrió un error fatal, intentalo en unos minutos");
        } finally {
            MessageProperties messageProperties = configRabbitContentResponse.generateMessageProperties(responseDomain.getId());
            Optional<Message> bodyMessage = configRabbitContentResponse.getBodyMessage(responseDomain,messageProperties);
            rabbitTemplate.convertAndSend(producer.getExchange(),producer.getRoutingKey().getList(),bodyMessage.get());
        }
    }
}
