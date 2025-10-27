import jakarta.xml.rpc.Call;
import jakarta.xml.rpc.ServiceFactory;
import org.apache.xmlrpc.client.XmlRpcTransport;
import org.apache.xmlrpc.client.XmlRpcClient;
import redstone.xmlrpc.XmlRpcInvocationInterceptor;
import redstone.xmlrpc.XmlRpcParser;

public class JakartaRPC {
	
	public interface MyCall extends Call;
	
	public interface MyXmlRpcTransport extends XmlRpcTransport;
	
	public interface MyXmlRpcInvocationInterceptor extends XmlRpcInvocationInterceptor;
	
	public static void main(String argv[]) {
		ServiceFactory sf = new ServiceFactory();
		XmlRpcClient client = new XmlRpcClient();
		XmlRpcParser parse = new XmlRpcParser();
	}

}
