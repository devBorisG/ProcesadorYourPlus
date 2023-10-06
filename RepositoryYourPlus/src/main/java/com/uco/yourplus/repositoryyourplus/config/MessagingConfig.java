package com.uco.yourplus.repositoryyourplus.config;

import com.uco.yourplus.crosscuttingyourplus.properties.ProductoPropertiesCatalogProducer;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.*;

@Configuration
@EnableConfigurationProperties(ProductoPropertiesCatalogProducer.class)
public class MessagingConfig {

    private ProductoPropertiesCatalogProducer properties;

    public MessagingConfig(@Qualifier("productoPropertiesCatalogProducer") ProductoPropertiesCatalogProducer properties) {
        this.properties = properties;
    }

    //Spring bean for producer save queue
    @Bean
    public Queue saveQueue(){
        return new Queue(properties.getQueue().getSave());
    }

    //Spring bean for producer delete queue
    @Bean
    public Queue deleteQueue(){
        return new Queue(properties.getQueue().getDelete());
    }

    //Spring bean for producer update queue
    @Bean
    public Queue updateQueue(){
        return new Queue(properties.getQueue().getUpdate());
    }

    //Spring bean for producer list queue
    @Bean
    public Queue listQueue(){
        return new Queue(properties.getQueue().getList());
    }

    //Spring bean for producer exchange
    @Bean
    public TopicExchange exchange(){
        return new TopicExchange(properties.getExchange());
    }

    //Binding between queue save an exchange using routing key
    @Bean
    public Binding saveBinding(Queue saveQueue, TopicExchange exchange){
        return BindingBuilder.bind(saveQueue)
                .to(exchange)
                .with(properties.getRoutingkey().getSave());
    }

    //Binding between queue delete an exchange using routing key
    @Bean
    public Binding deleteBinding(Queue deleteQueue, TopicExchange exchange){
        return BindingBuilder.bind(deleteQueue)
                .to(exchange)
                .with(properties.getRoutingkey().getDelete());
    }

    //Binding between queue update an exchange using routing key
    @Bean
    public Binding updateBinding(Queue updateQueue, TopicExchange exchange){
        return BindingBuilder.bind(updateQueue)
                .to(exchange)
                .with(properties.getRoutingkey().getUpdate());
    }

    //Binding between queue list an exchange using routing key
    @Bean
    public Binding listBinding(Queue listQueue, TopicExchange exchange){
        return BindingBuilder.bind(listQueue)
                .to(exchange)
                .with(properties.getRoutingkey().getList());
    }

}
