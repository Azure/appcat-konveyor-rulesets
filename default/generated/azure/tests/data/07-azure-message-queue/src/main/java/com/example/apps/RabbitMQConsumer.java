package com.example.rabbitmq;

import com.rabbitmq.client.*;
import com.example.websocket.NewsWebSocket;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

@WebListener
public class RabbitMQConsumer implements ServletContextListener {
    
    private static final String RABBITMQ_HOST = "localhost";
    private static final String QUEUE_NAME = "news";
    private Connection connection;
    private Channel channel;
    
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("========================================");
        System.out.println("RabbitMQConsumer: Starting initialization...");
        System.out.println("========================================");
        try {
            // Create connection to RabbitMQ
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost(RABBITMQ_HOST);
            // Optional: Set username and password if required
            // factory.setUsername("guest");
            // factory.setPassword("guest");
            
            System.out.println("Connecting to RabbitMQ at " + RABBITMQ_HOST + "...");
            connection = factory.newConnection();
            channel = connection.createChannel();
            System.out.println("✓ Connected to RabbitMQ successfully!");
            
            // Declare queue (idempotent)
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            System.out.println("✓ Queue '" + QUEUE_NAME + "' declared");
            
            System.out.println("✓ Waiting for messages from RabbitMQ queue: " + QUEUE_NAME);
            System.out.println("========================================");
            
            // Set up consumer
            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String message = new String(delivery.getBody(), "UTF-8");
                System.out.println("========================================");
                System.out.println(">>> Received from RabbitMQ: " + message);
                
                // Broadcast to all connected WebSocket clients
                try {
                    NewsWebSocket.broadcast(message);
                    System.out.println(">>> Broadcasted to WebSocket clients");
                } catch (Exception e) {
                    System.err.println(">>> ERROR broadcasting to WebSocket: " + e.getMessage());
                    e.printStackTrace();
                }
                System.out.println("========================================");
            };
            
            channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> { });
            
        } catch (IOException | TimeoutException e) {
            System.err.println("========================================");
            System.err.println("ERROR: Failed to connect to RabbitMQ!");
            System.err.println("========================================");
            e.printStackTrace();
            System.err.println("Error connecting to RabbitMQ: " + e.getMessage());
            System.err.println("Make sure RabbitMQ is running: docker ps");
            System.err.println("========================================");
        }
    }
    
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        try {
            if (channel != null && channel.isOpen()) {
                channel.close();
            }
            if (connection != null && connection.isOpen()) {
                connection.close();
            }
            System.out.println("RabbitMQ connection closed");
        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
        }
    }
}
