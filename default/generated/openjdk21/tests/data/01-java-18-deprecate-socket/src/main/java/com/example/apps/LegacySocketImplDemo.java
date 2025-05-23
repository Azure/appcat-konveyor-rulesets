public class LegacySocketImplDemo {
    public static void main(String[] args) {
        // These properties instructed the JVM to use legacy socket implementations
        System.setProperty("jdk.net.usePlainSocketImpl", "true");
        System.setProperty("jdk.net.usePlainDatagramSocketImpl", "true");

        // Print property values (has no effect in JDK 15+)
        System.out.println("Using legacy SocketImpl: " + System.getProperty("jdk.net.usePlainSocketImpl"));
        System.out.println("Using legacy DatagramSocketImpl: " + System.getProperty("jdk.net.usePlainDatagramSocketImpl"));

        try (java.net.Socket socket = new java.net.Socket("example.com", 80)) {
            System.out.println("Connected to: " + socket.getRemoteSocketAddress());
        } catch (Exception e) {
            e.printStackTrace();
        }

        try (java.net.DatagramSocket datagramSocket = new java.net.DatagramSocket()) {
            System.out.println("Datagram socket created at: " + datagramSocket.getLocalSocketAddress());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
