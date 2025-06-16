import java.awt.dnd.DropTargetContext;
import java.awt.dnd.peer.DropTargetContextPeer;

public class DnDPeerUsage {
    public void example(DropTargetContext context) {
        // Access to peer interface (❌ removed)
        DropTargetContextPeer peer = context.getDropTargetContextPeer();
        peer.setTargetActions(1); // Not available anymore
    }
}
