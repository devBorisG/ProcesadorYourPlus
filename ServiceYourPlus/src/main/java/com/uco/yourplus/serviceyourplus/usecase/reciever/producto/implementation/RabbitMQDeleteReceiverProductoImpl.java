package com.uco.yourplus.serviceyourplus.usecase.reciever.producto.implementation;

import com.uco.yourplus.crosscuttingyourplus.exceptions.service.ServiceCustomException;
import com.uco.yourplus.crosscuttingyourplus.helper.json.MapperJsonObject;
import com.uco.yourplus.crosscuttingyourplus.properties.ProductoPropertiesCatalogProducer;
import com.uco.yourplus.serviceyourplus.domain.ProductoDomain;
import com.uco.yourplus.serviceyourplus.domain.ResponseDomain;
import com.uco.yourplus.serviceyourplus.domain.enumeration.StateResponse;
import com.uco.yourplus.serviceyourplus.usecase.producer.response.ConfigRabbitContentResponse;
import com.uco.yourplus.serviceyourplus.usecase.producto.EliminarProducto;
import com.uco.yourplus.serviceyourplus.usecase.reciever.producto.RabbitMQDeleteReceiverProducto;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@EnableConfigurationProperties(ProductoPropertiesCatalogProducer.class)
public class RabbitMQDeleteReceiverProductoImpl implements RabbitMQDeleteReceiverProducto {

    private final EliminarProducto useCase;
    private final MapperJsonObject mapperJsonObject;

    private final RabbitTemplate rabbitTemplate;

    private final ConfigRabbitContentResponse configRabbitContentResponse;

    private final ProductoPropertiesCatalogProducer producer;

    public RabbitMQDeleteReceiverProductoImpl(EliminarProducto useCase, MapperJsonObject mapperJsonObject, RabbitTemplate rabbitTemplate,
                                              ConfigRabbitContentResponse configRabbitContentResponse, @Qualifier("productoPropertiesCatalogProducer") ProductoPropertiesCatalogProducer producer) {
        this.useCase = useCase;
        this.mapperJsonObject = mapperJsonObject;
        this.rabbitTemplate = rabbitTemplate;
        this.configRabbitContentResponse = configRabbitContentResponse;
        this.producer = producer;
    }

    @RabbitListener(queues = "${yourplus.management.producto.queue.delete}")
    @Override
    public void execute(String message) {
        StateResponse stateResponse = StateResponse.SUCCESS;
        final ResponseDomain<ProductoDomain> responseDomain = new ResponseDomain();
        try {
            ProductoDomain domain = mapperJsonObject.execute(message, ProductoDomain.class).get();
            useCase.execute(domain);
            responseDomain.setStateResponse(stateResponse);
            responseDomain.setMessage("Producto eliminado con éxito");
        }catch (ServiceCustomException exception){
            stateResponse = StateResponse.ERROR;
            responseDomain.setStateResponse(stateResponse);
            if (exception.isTechnicalException()){
                responseDomain.setMessage("Algo salio mal eliminando el producto, intenta nuevamente");
            }else {
                responseDomain.setMessage(exception.getMessage());
            }
        }catch (Exception exception){
            stateResponse = StateResponse.ERROR;
            responseDomain.setStateResponse(stateResponse);
            responseDomain.setMessage("Ocurrió un error fatal, intentalo en unos minutos");
        }finally {
            MessageProperties messageProperties = configRabbitContentResponse.generateMessageProperties(responseDomain.getId());
            Optional<Message> bodyMessage = configRabbitContentResponse.getBodyMessage(responseDomain,messageProperties);
            rabbitTemplate.convertAndSend(producer.getExchange(),producer.getRoutingkey().getDelete(),bodyMessage.get());
        }
    }
}
