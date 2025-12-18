import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import com.solacesystems.jms.SolConnectionFactory;
import com.solacesystems.jms.SolJmsUtility;

public class SolaceJmsSimpleDemo {

    public static void main(String[] args) throws Exception {
        String host = "tcp://localhost:55555";
        String msgVpn = "default";
        String username = "admin";
        String password = "admin";

        String queueName = "demo/queue";

        SolConnectionFactory cf = SolJmsUtility.createConnectionFactory();
        cf.setHost(host);
        cf.setVPN(msgVpn);
        cf.setUsername(username);
        cf.setPassword(password);

        Connection connection = null;
        Session session = null;

        try {
            connection = cf.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            Destination queue = session.createQueue(queueName);

            connection.start();

            MessageProducer producer = session.createProducer(queue);
            TextMessage out = session.createTextMessage("hello from Solace JMS");
            producer.send(out);
            System.out.println("Sent: " + out.getText());

            MessageConsumer consumer = session.createConsumer(queue);
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