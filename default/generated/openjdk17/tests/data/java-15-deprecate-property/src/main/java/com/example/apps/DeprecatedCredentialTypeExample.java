import javax.management.remote.JMXServiceURL;
import javax.management.remote.rmi.RMIConnectorServer;
import javax.management.remote.rmi.RMIServerImpl;
import javax.management.remote.JMXConnectorServer;
import javax.management.remote.JMXConnectorServerFactory;
import java.util.HashMap;
import java.util.Map;

public class DeprecatedCredentialTypeExample {
    public static void main(String[] args) throws Exception {
        JMXServiceURL url = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://localhost:9999/jmxrmi");

        Map<String, Object> env = new HashMap<>();
        env.put(RMIConnectorServer.CREDENTIAL_TYPE, "javax.security.auth.Subject"); // ❌ Deprecated and removed

        JMXConnectorServer cs = JMXConnectorServerFactory.newJMXConnectorServer(url, env, null);
        cs.start();
    }
}
