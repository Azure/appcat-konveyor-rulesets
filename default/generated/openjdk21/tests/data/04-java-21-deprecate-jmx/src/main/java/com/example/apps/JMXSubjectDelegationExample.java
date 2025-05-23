import javax.management.MBeanServerConnection;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import javax.security.auth.Subject;
import java.util.HashMap;
import java.util.Map;

import javax.management.remote.rmi.RMIIIOPServerImpl;
import javax.management.remote.rmi.RMIServerImpl;
import java.rmi.server.RMISocketFactory;

public class JMXSubjectDelegationExample {
    public static void main(String[] args) throws Exception {
        // Connect to the JMX server
        JMXServiceURL url = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://localhost:9999/jmxrmi");
        Map<String, Object> env = new HashMap<>();
        // Optional credentials setup
        env.put(JMXConnector.CREDENTIALS, new String[]{"admin", "adminpass"});

        JMXConnector connector = JMXConnectorFactory.connect(url, env);

        // Deprecated method usage
        Subject subject = new Subject(); // Usually you'd initialize with principals
        MBeanServerConnection mbsc = connector.getMBeanServerConnection(subject); // ⚠️ DEPRECATED

        System.out.println("Got MBeanServerConnection with delegated Subject.");

        // Continue interacting with MBeans...
        connector.close();

        RMIServerImpl server = new RMIIIOPServerImpl(null, null, null, RMISocketFactory.getDefault());
        server.start();
        System.out.println("RMIIIOP JMX server started.");
    }
}
