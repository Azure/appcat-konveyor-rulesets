package com.example.messaging;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

public class MessagingService {

    private ConnectionFactory connectionFactory;

    public MessagingService(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public void sendMessage(String queueName, String message) {
        try {
            Connection connection = connectionFactory.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            javax.jms.Queue queue = session.createQueue(queueName);
            MessageProducer producer = session.createProducer(queue);
            TextMessage textMessage = session.createTextMessage(message);
            producer.send(textMessage);
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```