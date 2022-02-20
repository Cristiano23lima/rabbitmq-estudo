package com.cristiano.vendas.configs;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProducerRabbitMQ {
    @Value("${spring.rabbitmq.template.default-receive-queue}")
    private String queue;

    @Value("${spring.rabbitmq.template.exchange}")
    private String exchange;

    @Bean
    public Queue queue(){
        return new Queue(queue, true);
    }

    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange(exchange);
    }

    @Bean
    public Binding binding(){
        return BindingBuilder.bind(queue()).to(directExchange()).withQueueName();
    }
}
