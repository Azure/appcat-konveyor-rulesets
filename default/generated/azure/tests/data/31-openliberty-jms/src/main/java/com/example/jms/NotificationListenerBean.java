package com.example.jms;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@MessageDriven(
    activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/NotificationsTopic"),
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic"),
        @ActivationConfigProperty(propertyName = "subscriptionDurability", propertyValue = "Durable"),
        @ActivationConfigProperty(propertyName = "clientId", propertyValue = "NotificationClient"),
        @ActivationConfigProperty(propertyName = "subscriptionName", propertyValue = "NotificationSubscription")
    }
)
public class NotificationListenerBean implements MessageListener {

    @Override
    public void onMessage(Message message) {
        try {
            if (message instanceof TextMessage) {
                TextMessage textMessage = (TextMessage) message;
                String text = textMessage.getText();
                String subject = textMessage.getStringProperty("Subject");
                
                System.out.println("Received notification: " + text);
                System.out.println("Subject: " + subject);
                
                // Process the notification
                processNotification(text, subject);
            } else {
                System.out.println("Received message of unsupported type: " + message.getClass().getName());
            }
        } catch (JMSException e) {
            throw new RuntimeException("Error processing notification", e);
        }
    }
    
    private void processNotification(String notificationText, String subject) {
        // Notification processing logic
        System.out.println("Processing notification with subject: " + subject);
        
        // Simulate notification handling
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        System.out.println("Notification processed: " + subject);
    }
} 