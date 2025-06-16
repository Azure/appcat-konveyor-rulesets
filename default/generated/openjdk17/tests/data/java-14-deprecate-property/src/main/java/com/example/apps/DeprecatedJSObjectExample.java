import netscape.javascript.JSObject;
import java.applet.Applet;

public class DeprecatedJSObjectExample extends Applet {
    @Override
    public void start() {
        // ❌ Deprecated and removed: always returns null after JDK 11
        JSObject window = JSObject.getWindow(this);
        if (window != null) {
            window.call("alert", new Object[]{"Hello from Java!"});
        }

        String value = System.getProperty("sun.nio.cs.map");
    }
}
