package com.example.messaging;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;

public class MessagingService {

    private final RabbitTemplate rabbitTemplate;

    public MessagingService(ConnectionFactory connectionFactory) {
        this.rabbitTemplate = new RabbitTemplate(connectionFactory);
    }

    public void sendMessage(String queueName, String message) {
        rabbitTemplate.convertAndSend(queueName, message);
    }
}