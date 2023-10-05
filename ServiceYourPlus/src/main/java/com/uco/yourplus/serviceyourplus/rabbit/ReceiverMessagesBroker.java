package com.uco.yourplus.serviceyourplus.rabbit;

import com.uco.yourplus.serviceyourplus.usecase.producto.RegistrarProducto;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReceiverMessagesBroker {

    @Autowired
    RegistrarProducto registrarProducto;

    @RabbitListener(queues = "${yourplus.management.producto.queue.save}")
    public void receiverMessagesProcessClient(String message) {
//        ProductoDomain productoDomain = objectMapper.readValue(message, ProductoDomain.class)
        System.out.println(message);
    }
}
