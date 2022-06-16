package com.spring.sleuthserver;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.ConnectionFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class QueueConfiguration {

    @Value("${queue.name}")
    private String name;

    private final CachingConnectionFactory rabbitConnectionFactory;

    @Bean
    public Queue queue() {
        return new Queue(name, true);
    }

    @Bean
    public ConnectionFactory rabbitSourceConnectionFactory() {
        return rabbitConnectionFactory.getRabbitConnectionFactory();
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

}
