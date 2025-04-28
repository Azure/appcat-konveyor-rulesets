public class DatagramPacketJava8 {
    public static void main(String[] args) {
        System.out.println("Demonstrating DatagramPacket behavior in Java 8 and later.");

        // Attempt to create a DatagramPacket using a SocketAddress
        try {
            // Create a DatagramPacket (this will not throw SocketException)
            DatagramPacket packet = new DatagramPacket(new byte[1024], 1024, new InetSocketAddress("localhost", 12345));
            System.out.println("DatagramPacket created successfully: " + packet);
        } catch (Exception e) {
            // Handle any other exceptions that may arise
            System.err.println("An unexpected exception occurred: " + e.getMessage());
        }
    }
}