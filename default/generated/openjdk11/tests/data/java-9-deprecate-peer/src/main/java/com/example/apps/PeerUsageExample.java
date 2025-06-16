import java.awt.Button;
import java.awt.peer.ButtonPeer;

public class PeerUsageExample {
    public static void main(String[] args) {
        Button button = new Button("Click Me");

        // The getPeer() method used to return a ButtonPeer instance.
        // This method and the peer interfaces have been removed.
        ButtonPeer peer = (ButtonPeer) button.getPeer(); // ❌ Removed in JDK 9+
        peer.setLabel("New Label");
    }
}
