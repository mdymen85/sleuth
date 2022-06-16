package com.spring.sleuthconsumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
@Slf4j
@RequiredArgsConstructor
public class QueueConsumerService {

    private final ObjectMapper objectMapper;

    @RabbitListener(queues = {"${queue.name}"})
    public void receive(String message, @Headers Map<String, Object> headers) throws IOException {

        log.info("headers {}", headers);

        var testObject = objectMapper.readValue(message, TestObject.class);

        log.info("Receiveing by queue {}", testObject);
    }

}
