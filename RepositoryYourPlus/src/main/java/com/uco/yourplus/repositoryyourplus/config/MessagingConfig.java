package com.uco.yourplus.repositoryyourplus.config;

import com.uco.yourplus.crosscuttingyourplus.properties.LaboratorioPropertiesCatalogProducer;
import com.uco.yourplus.crosscuttingyourplus.properties.ProductoPropertiesCatalogProducer;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({ProductoPropertiesCatalogProducer.class, LaboratorioPropertiesCatalogProducer.class})
public class MessagingConfig {

    private ProductoPropertiesCatalogProducer properties;
    private LaboratorioPropertiesCatalogProducer labProperties;

    public MessagingConfig(@Qualifier("productoPropertiesCatalogProducer") ProductoPropertiesCatalogProducer properties,
                           @Qualifier("laboratorioPropertiesCatalogProducer") LaboratorioPropertiesCatalogProducer labProperties) {
        this.properties = properties;
        this.labProperties = labProperties;
    }

    //Spring bean for producer save queue
    @Bean
    public Queue saveQueue() {
        return new Queue(properties.getQueue().getSave());
    }

    //Spring bean for producer delete queue
    @Bean
    public Queue deleteQueue() {
        return new Queue(properties.getQueue().getDelete());
    }

    //Spring bean for producer update queue
    @Bean
    public Queue updateQueue() {
        return new Queue(properties.getQueue().getUpdate());
    }

    //Spring bean for producer list queue
    @Bean
    public Queue listQueue() {
        return new Queue(properties.getQueue().getList());
    }

    //Spring bean for producer exchange
    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(properties.getExchange());
    }

    //Queues for laboratorio

    @Bean
    public Queue saveQueueLab(){
        return new Queue(labProperties.getQueue().getSave());
    }

    @Bean
    public Queue deleteQueueLab(){
        return new Queue(labProperties.getQueue().getDelete());
    }

    @Bean
    public Queue updateQueueLab(){
        return new Queue(labProperties.getQueue().getUpdate());
    }

    @Bean
    public Queue listQueueLab(){
        return new Queue(labProperties.getQueue().getList());
    }

    //Binding between queue save an exchange using routing key
    @Bean
    public Binding saveBinding(Queue saveQueue, TopicExchange exchange) {
        return BindingBuilder.bind(saveQueue)
                .to(exchange)
                .with(properties.getRoutingkey().getSave());
    }

    //Binding between queue delete an exchange using routing key
    @Bean
    public Binding deleteBinding(Queue deleteQueue, TopicExchange exchange) {
        return BindingBuilder.bind(deleteQueue)
                .to(exchange)
                .with(properties.getRoutingkey().getDelete());
    }

    //Binding between queue update an exchange using routing key
    @Bean
    public Binding updateBinding(Queue updateQueue, TopicExchange exchange) {
        return BindingBuilder.bind(updateQueue)
                .to(exchange)
                .with(properties.getRoutingkey().getUpdate());
    }

    //Binding between queue list an exchange using routing key
    @Bean
    public Binding listBinding(Queue listQueue, TopicExchange exchange) {
        return BindingBuilder.bind(listQueue)
                .to(exchange)
                .with(properties.getRoutingkey().getList());
    }

    @Bean
    public TopicExchange topicExchangeLab(){
        return new TopicExchange(labProperties.getExchange());
    }

    @Bean
    public Binding saveBindingLab(Queue saveQueueLab, TopicExchange topicExchangeLab){
        return BindingBuilder.bind(saveQueueLab).to(topicExchangeLab).with(labProperties.getRoutingKey().getSave());
    }

    @Bean
    public Binding deleteBindingLab(Queue deleteQueueLab, TopicExchange topicExchangeLab){
        return BindingBuilder.bind(deleteQueueLab).to(topicExchangeLab).with(labProperties.getRoutingKey().getDelete());
    }

    @Bean
    public Binding updateBindingLab(Queue updateQueueLab, TopicExchange topicExchangeLab){
        return BindingBuilder.bind(updateQueueLab).to(topicExchangeLab).with(labProperties.getRoutingKey().getUpdate());
    }

    @Bean
    public Binding listBindingLab(Queue listQueueLab, TopicExchange topicExchangeLab){
        return BindingBuilder.bind(listQueueLab).to(topicExchangeLab).with(labProperties.getRoutingKey().getList());
    }

}
