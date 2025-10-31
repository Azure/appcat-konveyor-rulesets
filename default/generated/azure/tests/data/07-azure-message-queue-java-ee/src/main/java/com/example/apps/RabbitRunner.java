package com.example.apps;
import com.rabbitmq.client.*;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.ejb.Stateless;

@Startup
@Singleton
public class RabbitRunner {

    private static final String EXCHANGE_NAME = "bid_topic_exchange";
    private static final String ROUTING_KEY = "bid.highest";
    private static final String QUEUE_NAME = "user_bid_notifications";

    private Connection connection;
    private Channel channel;  

    @PostConstruct
    public void init() throws Exception {
      // Initialize RabbitMQ connection and channel
      ConnectionFactory factory = new ConnectionFactory();
      factory.setHost("localhost");
      factory.setPort(5672);
      factory.setUsername("guest");
      factory.setPassword("guest");

      connection = factory.newConnection();
      channel = connection.createChannel();

      channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC, true);
      channel.queueDeclare(QUEUE_NAME, true, false, false, null); // durable, not exclusive, not auto-delete
      channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, ROUTING_KEY);
      System.out.println("Waiting for messages...");
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println("Received message: " + message);
            // Process the message (e.g., send notification to user)
        };
        channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> { });
    }
    
    @PreDestroy
    public void cleanup() throws Exception {
        if (channel != null && channel.isOpen()) {
            channel.close();
        }
        if (connection != null && connection.isOpen()) {    
            connection.close();
        }           
    }
}
