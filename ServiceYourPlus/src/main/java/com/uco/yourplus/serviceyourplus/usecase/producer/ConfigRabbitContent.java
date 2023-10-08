package com.uco.yourplus.serviceyourplus.usecase.producer;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;

import java.util.Optional;
import java.util.UUID;

public interface ConfigRabbitContent<T> {
    Optional<Message> getBodyMessage(T object, MessageProperties messageProperties);

    MessageProperties generateMessageProperties(UUID identifier);
}
