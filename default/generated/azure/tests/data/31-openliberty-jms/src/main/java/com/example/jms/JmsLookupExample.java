package com.example.jms;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.jms.*;
import java.util.Properties;

public class JmsLookupExample {

    public void sendMessageUsingJndi(String text) {
        try {
            // JNDI lookups for JMS resources
            Context ctx = new InitialContext();
            ConnectionFactory connectionFactory = (ConnectionFactory) ctx.lookup("java:comp/env/jms/ConnectionFactory");
            Queue queue = (Queue) ctx.lookup("java:comp/env/jms/OrdersQueue");
            Topic topic = (Topic) ctx.lookup("jms/NotificationsTopic");
            
            // Use the resources
            Connection connection = connectionFactory.createConnection();
            try {
                Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
                
                // Send to queue
                MessageProducer producer = session.createProducer(queue);
                TextMessage message = session.createTextMessage(text);
                producer.send(message);
                System.out.println("Message sent to queue using JNDI lookup");
                
                // Send to topic
                MessageProducer topicProducer = session.createProducer(topic);
                TextMessage topicMessage = session.createTextMessage("Notification: " + text);
                topicProducer.send(topicMessage);
                System.out.println("Message sent to topic using JNDI lookup");
                
            } finally {
                connection.close();
            }
            
        } catch (NamingException | JMSException e) {
            e.printStackTrace();
        }
    }
    
    public void createCustomContext() {
        try {
            // Create a custom initial context with properties
            Properties props = new Properties();
            props.setProperty(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.fscontext.RefFSContextFactory");
            props.setProperty(Context.PROVIDER_URL, "file:/tmp");
            
            Context initialContext = new InitialContext(props);
            
            // Lookup JMS resources
            ConnectionFactory factory = (ConnectionFactory) initialContext.lookup("jms/ConnectionFactory");
            Queue requestQueue = (Queue) initialContext.lookup("jms/RequestQueue");
            
            // Use the resources...
            
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }
} 