import java.io.IOException;
import java.net.ServerSocket;
import java.net.SocketImpl;
import java.net.SocketImplFactory;
import java.net.Socket;
import java.net.DatagramSocket;
import java.net.DatagramSocketImpl;
import java.net.DatagramSocketImplFactory;


public class DeprecatedServerSocketFactory {
    public static void main(String[] args) throws IOException {
        ServerSocket.setSocketFactory(new SocketImplFactory() {
            public SocketImpl createSocketImpl() {
                System.out.println("Custom ServerSocket factory used");
                return null; // Just a placeholder; actual implementation needed
            }
        });

        Socket.setSocketImplFactory(new SocketImplFactory() {
            public SocketImpl createSocketImpl() {
                System.out.println("Custom Socket factory used");
                return null; // Just a placeholder; actual implementation needed
            }
        });

        DatagramSocket.setDatagramSocketImplFactory(new DatagramSocketImplFactory() {
            public DatagramSocketImpl createDatagramSocketImpl() {
                System.out.println("Custom DatagramSocket factory used");
                return null; // Just a placeholder; actual implementation needed
            }
        });
    }
}
