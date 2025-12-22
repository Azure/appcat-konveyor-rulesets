import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import com.tibco.tibjms.TibjmsConnectionFactory;

public class TibcoEmsJmsSimpleDemo {

    public static void main(String[] args) throws Exception {
        String serverUrl = "tcp://localhost:7222";
        String username = "admin";
        String password = "admin";

        String queueName = "demo.queue";

        TibjmsConnectionFactory connectionFactory = new TibjmsConnectionFactory(serverUrl);

        Connection connection = null;
        Session session = null;

        try {
            connection = connectionFactory.createConnection(username, password);

            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            Destination destination = session.createQueue(queueName);

            connection.start();

            MessageProducer producer = session.createProducer(destination);
            TextMessage out = session.createTextMessage("hello from TIBCO EMS via JMS");
            producer.send(out);
            System.out.println("Sent: " + out.getText());

            MessageConsumer consumer = session.createConsumer(destination);
            Message in = consumer.receive(3000);

            if (in == null) {
                System.out.println("No message received within timeout.");
            } else if (in instanceof TextMessage) {
                System.out.println("Received: " + ((TextMessage) in).getText());
            } else {
                System.out.println("Received non-text message: " + in.getClass().getName());
            }

            consumer.close();
            producer.close();

        } finally {
            if (session != null) session.close();
            if (connection != null) connection.close();
        }
    }
}