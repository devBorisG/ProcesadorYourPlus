package com.uco.yourplus.serviceyourplus.usecase.producer.laboratorio.implementation;

import com.uco.yourplus.crosscuttingyourplus.exceptions.crosscutting.CrosscuttingCustomException;
import com.uco.yourplus.crosscuttingyourplus.exceptions.service.ServiceCustomException;
import com.uco.yourplus.crosscuttingyourplus.helper.json.MapperJsonObject;
import com.uco.yourplus.serviceyourplus.domain.ProductoDomain;
import com.uco.yourplus.serviceyourplus.usecase.producer.producto.ConfigRabbitContentProductoDomain;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.core.MessagePropertiesBuilder;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class ConfigRabbitContentLaboratorioDomainImpl implements ConfigRabbitContentProductoDomain {

    private final MapperJsonObject mapperJsonObject;

    public ConfigRabbitContentLaboratorioDomainImpl(MapperJsonObject mapperJsonObject) {
        this.mapperJsonObject = mapperJsonObject;
    }

    @Override
    public Optional<Message> getBodyMessage(ProductoDomain object, MessageProperties messageProperties) {
        try{
            Optional<String> textMessage = mapperJsonObject.executeGson(object);
            return textMessage.map(msg -> MessageBuilder.withBody(msg.getBytes())
                    .andProperties(messageProperties)
                    .build());
        } catch(CrosscuttingCustomException e){
            throw e;
        } catch(Exception e){
            throw ServiceCustomException.createTechnicalException(e,"Ocurrio un error inesperado generando el cuerpo");
        }
    }

    @Override
    public MessageProperties generateMessageProperties(UUID identifier) {
        return MessagePropertiesBuilder.newInstance()
                .setContentType(MessageProperties.CONTENT_TYPE_JSON)
                .setHeader("idMessage",identifier.toString())
                .build();
    }
}
