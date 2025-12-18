import java.util.Properties;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import javax.naming.Context;
import javax.naming.InitialContext;

import com.tibco.tibjms.naming.TibjmsInitialContextFactory;

public class TibcoEmsJndiJmsDemo {

    public static void main(String[] args) throws Exception {
        // 1) EMS 连接信息（按你的环境改）
        String providerUrl = "tibjmsnaming://localhost:7222";
        String username = "admin";
        String password = "admin";

        // 2) JNDI 名称（需要与你的 EMS/JNDI 配置一致）
        String connectionFactoryJndiName = "ConnectionFactory";
        String destinationJndiName = "demo.queue"; // 可能是 Queue 的 JNDI 名，而不一定等于物理队列名

        Properties env = new Properties();
        env.put(Context.INITIAL_CONTEXT_FACTORY, TibjmsInitialContextFactory.class.getName());
        env.put(Context.PROVIDER_URL, providerUrl);
        env.put(Context.SECURITY_PRINCIPAL, username);
        env.put(Context.SECURITY_CREDENTIALS, password);

        InitialContext ic = new InitialContext(env);

        ConnectionFactory cf = (ConnectionFactory) ic.lookup(connectionFactoryJndiName);
        Destination destination = (Destination) ic.lookup(destinationJndiName);

        Connection connection = null;
        Session session = null;

        try {
            connection = cf.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            connection.start();

            MessageProducer producer = session.createProducer(destination);
            TextMessage out = session.createTextMessage("hello via TIBCO EMS JNDI + JMS");
            producer.send(out);
            System.out.println("Sent: " + out.getText());

            MessageConsumer consumer = session.createConsumer(destination);
            Message in = consumer.receive(3000);

            if (in instanceof TextMessage) {
                System.out.println("Received: " + ((TextMessage) in).getText());
            } else if (in == null) {
                System.out.println("No message received within timeout.");
            } else {
                System.out.println("Received non-text message: " + in.getClass().getName());
            }

            consumer.close();
            producer.close();
        } finally {
            if (session != null) session.close();
            if (connection != null) connection.close();
            ic.close();
        }
    }
}