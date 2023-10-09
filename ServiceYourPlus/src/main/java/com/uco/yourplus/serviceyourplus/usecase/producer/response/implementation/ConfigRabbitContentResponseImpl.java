package com.uco.yourplus.serviceyourplus.usecase.producer.response.implementation;

import com.uco.yourplus.crosscuttingyourplus.exceptions.crosscutting.CrosscuttingCustomException;
import com.uco.yourplus.crosscuttingyourplus.exceptions.service.ServiceCustomException;
import com.uco.yourplus.crosscuttingyourplus.helper.json.MapperJsonObject;
import com.uco.yourplus.serviceyourplus.domain.ResponseDomain;
import com.uco.yourplus.serviceyourplus.usecase.producer.response.ConfigRabbitContentResponse;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.core.MessagePropertiesBuilder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ConfigRabbitContentResponseImpl implements ConfigRabbitContentResponse {

    private final MapperJsonObject mapperJsonObject;

    public ConfigRabbitContentResponseImpl(MapperJsonObject mapperJsonObject) {
        this.mapperJsonObject = mapperJsonObject;
    }


    @Override
    public Optional<Message> getBodyMessage(ResponseDomain object, MessageProperties messageProperties) {
        try {
            Optional<String> textMessage = mapperJsonObject.executeGson(object);
            return textMessage.map(msg -> MessageBuilder.withBody(msg.getBytes()).andProperties(messageProperties).build());
        } catch (CrosscuttingCustomException exception) {
            throw exception;
        } catch (Exception exception) {
            throw ServiceCustomException.createTechnicalException(exception, "Ocurrio un error inesperado generando el cuerpo");
        }
    }

    @Override
    public MessageProperties generateMessageProperties(UUID identifier) {
        return MessagePropertiesBuilder.newInstance().setContentType(MessageProperties.CONTENT_TYPE_JSON).setHeader("idMessage", identifier.toString()).build();
    }
}
