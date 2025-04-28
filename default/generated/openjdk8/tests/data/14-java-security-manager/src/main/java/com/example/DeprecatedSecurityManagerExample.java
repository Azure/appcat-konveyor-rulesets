import java.awt.*;
import java.security.Permission;
import java.lang.SecurityManager;

public class DeprecatedSecurityManagerExample {
    public static void main(String[] args) {
        System.setSecurityManager(new SecurityManager());

        // Deprecated: Used to check permission for top-level window
        boolean allowed = System.getSecurityManager().checkTopLevelWindow(new Frame());
        System.out.println("Is top-level window allowed? " + allowed);

        // Deprecated: Used to check clipboard access
        System.getSecurityManager().checkSystemClipboardAccess();

        // Deprecated: Used to check access to AWT event queue
        System.getSecurityManager().checkAwtEventQueueAccess();
    }
}
