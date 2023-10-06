package com.uco.yourplus.repositoryyourplus.config;

import lombok.Value;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.naming.Binding;
import java.util.Queue;

@Configuration
public class MessagingConfig {

    //exchange
    @Value("${yourplus.management.producto.response.exchange}")
    private String exchange;

    //queues
    @Value("${yourplus.management.producto.queue.save.response}")
    private String queueSave;

    @Value("${yourplus.management.producto.queue.delete.response}")
    private String queueDelete;

    @Value("${yourplus.management.producto.queue.update.response}")
    private String queueUpdate;

    @Value("${yourplus.management.producto.queue.list.response}")
    private String queueList;

    //routing keys

    @Value("${yourplus.management.producto.routingkey.save.response}")
    private String routingKeySave;

    @Value("${yourplus.management.producto.routingkey.delete.response}")
    private String routingKeyDelete;

    @Value("${yourplus.management.producto.routingkey.update.response}")
    private String routingKeyUpdate;

    @Value("${yourplus.management.producto.routingkey.list.response}")
    private String routingKeyList;

    //Spring bean for producer save queue
    @Bean
    public Queue saveQueue(){
        return new Queue(queueSave);
    }

    //Spring bean for producer delete queue
    @Bean
    public Queue deleteQueue(){
        return new Queue(queueDelete);
    }

    //Spring bean for producer update queue
    @Bean
    public Queue updateQueue(){
        return new Queue(queueUpdate);
    }

    //Spring bean for producer list queue
    @Bean
    public Queue listQueue(){
        return new Queue(queueList);
    }

    //Spring bean for producer exchange
    @Bean
    public TopicExchange exchange(){
        return new TopicExchange(exchange);
    }

    //Binding between queue save an exchange using routing key
    @Bean
    public Binding saveBinding(Queue saveQueue, TopicExchange exchange){
        return BindingBuilder.bind(saveQueue)
                .to(exchange)
                .with(routingKeySave);
    }

    //Binding between queue delete an exchange using routing key
    @Bean
    public Binding deleteBinding(Queue deleteQueue, TopicExchange exchange){
        return BindingBuilder.bind(deleteQueue)
                .to(exchange)
                .with(routingKeyDelete);
    }

    //Binding between queue update an exchange using routing key
    @Bean
    public Binding updateBinding(Queue updateQueue, TopicExchange exchange){
        return BindingBuilder.bind(updateQueue)
                .to(exchange)
                .with(routingKeyUpdate);
    }

    //Binding between queue list an exchange using routing key
    @Bean
    public Binding listBinding(Queue listQueue, TopicExchange exchange){
        return BindingBuilder.bind(listQueue)
                .to(exchange)
                .with(routingKeyList);
    }

}
