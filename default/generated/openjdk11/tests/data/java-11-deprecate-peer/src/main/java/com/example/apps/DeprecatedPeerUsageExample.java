import java.awt.*;
import java.awt.peer.FontPeer;
import java.awt.peer.ComponentPeer;

public class DeprecatedPeerUsageExample {

    public static void main(String[] args) {
        // Component.getPeer() usage
        Frame frame = new Frame("Deprecated Peer Example");
        frame.setSize(300, 200);
        frame.setVisible(true);

        // Deprecated: getPeer()
        ComponentPeer peer = frame.getPeer(); // Deprecated in Java 9, removed in Java 11
        System.out.println("Component peer: " + peer);

        // Font.getPeer() usage
        Font font = new Font("Dialog", Font.PLAIN, 12);
        FontPeer fontPeer = font.getPeer(); // Deprecated in Java 9, removed in Java 11
        System.out.println("Font peer: " + fontPeer);

        frame.dispose();
    }
}
