package com.example.jms;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.*;

@Stateless
public class MessageSender {

    @Resource(lookup = "jms/ConnectionFactory")
    private ConnectionFactory connectionFactory;

    @Resource(lookup = "jms/OrdersQueue")
    private Queue ordersQueue;
    
    @Resource(name = "jms/NotificationsTopic")
    private Topic notificationsTopic;

    public void sendOrder(String orderId, String productId, int quantity) {
        try (Connection connection = connectionFactory.createConnection();
             Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE)) {

            // Send to queue
            MessageProducer producer = session.createProducer(ordersQueue);
            TextMessage message = session.createTextMessage();
            message.setText("Order: " + orderId + ", Product: " + productId + ", Quantity: " + quantity);
            message.setStringProperty("OrderID", orderId);
            producer.send(message);
            
            System.out.println("Order sent to queue: " + orderId);
            
        } catch (JMSException e) {
            throw new RuntimeException("Error sending order message", e);
        }
    }
    
    public void sendNotification(String subject, String content) {
        try (Connection connection = connectionFactory.createConnection();
             Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE)) {

            // Send to topic
            MessageProducer producer = session.createProducer(notificationsTopic);
            TextMessage message = session.createTextMessage();
            message.setText(content);
            message.setStringProperty("Subject", subject);
            producer.send(message);
            
            System.out.println("Notification sent: " + subject);
            
        } catch (JMSException e) {
            throw new RuntimeException("Error sending notification message", e);
        }
    }
} 