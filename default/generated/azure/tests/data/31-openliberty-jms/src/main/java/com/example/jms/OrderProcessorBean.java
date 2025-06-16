package com.example.jms;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@MessageDriven(
    activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/OrdersQueue"),
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
        @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge")
    }
)
public class OrderProcessorBean implements MessageListener {

    @Override
    public void onMessage(Message message) {
        try {
            if (message instanceof TextMessage) {
                TextMessage textMessage = (TextMessage) message;
                String text = textMessage.getText();
                String orderId = textMessage.getStringProperty("OrderID");
                
                System.out.println("Received order: " + text);
                System.out.println("Order ID: " + orderId);
                
                // Process the order
                processOrder(text, orderId);
            } else {
                System.out.println("Received message of unsupported type: " + message.getClass().getName());
            }
        } catch (JMSException e) {
            throw new RuntimeException("Error processing message", e);
        }
    }
    
    private void processOrder(String orderText, String orderId) {
        // Order processing logic
        System.out.println("Processing order " + orderId);
        
        // Simulate processing time
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        System.out.println("Order " + orderId + " processed successfully");
    }
} 