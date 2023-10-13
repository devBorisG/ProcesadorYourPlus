package com.uco.yourplus.repositoryyourplus.config;

import com.uco.yourplus.crosscuttingyourplus.properties.LaboratorioPropertiesCatalogProducer;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(LaboratorioPropertiesCatalogProducer.class)
public class LaboratorioMessagingConfig {

    private LaboratorioPropertiesCatalogProducer properties;

    public LaboratorioMessagingConfig(@Qualifier("laboratorioPropertiesCatalogProducer") LaboratorioPropertiesCatalogProducer properties){
        this.properties = properties;
    }

    @Bean
    public Queue saveQueue(){
        return new Queue(properties.getQueue().getSave());
    }

    @Bean
    public Queue deleteQueue(){
        return new Queue(properties.getQueue().getDelete());
    }

    @Bean
    public Queue updateQueue(){
        return new Queue(properties.getQueue().getUpdate());
    }

    @Bean
    public Queue listQueue(){
        return new Queue(properties.getQueue().getList());
    }

    @Bean
    public TopicExchange TopicExchange(){
        return new TopicExchange(properties.getExchange());
    }

    @Bean
    public Binding saveBinding(Queue saveQueue, TopicExchange exchange){
        return BindingBuilder.bind(saveQueue).to(exchange).with(properties.getRoutingKey().getSave());
    }

    @Bean
    public Binding deleteBinding(Queue deleteQueue, TopicExchange exchange){
        return BindingBuilder.bind(deleteQueue).to(exchange).with(properties.getRoutingKey().getDelete());
    }

    @Bean
    public Binding updateBinding(Queue updateQueue, TopicExchange exchange){
        return BindingBuilder.bind(updateQueue).to(exchange).with(properties.getRoutingKey().getUpdate());
    }

    @Bean
    public Binding listBinding(Queue listQueue,TopicExchange exchange){
        return BindingBuilder.bind(listQueue).to(exchange).with(properties.getRoutingKey().getList());
    }
}
