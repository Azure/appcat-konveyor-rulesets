import java.beans.PropertyChangeListener;
import java.util.logging.LogManager;

public class OldListenerExample {
    public static void main(String[] args) {
        LogManager logManager = LogManager.getLogManager();
        PropertyChangeListener listener = evt -> {
            System.out.println("Logging configuration changed!");
        };

        logManager.addPropertyChangeListener("config", listener);  // ❌ Removed
        logManager.removePropertyChangeListener("config", listener);  // ❌ Removed
    }
}
