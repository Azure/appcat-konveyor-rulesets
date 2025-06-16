import java.beans.PropertyChangeListener;
import java.util.jar.Pack200;

public class OldPack200Usage {
    public static void main(String[] args) {
        Pack200.Packer packer = Pack200.newPacker();

        PropertyChangeListener listener = evt -> {
            System.out.println("Property changed: " + evt.getPropertyName());
        };

        // These methods are now removed:
        packer.addPropertyChangeListener(listener);    // ❌ Removed
        packer.removePropertyChangeListener(listener); // ❌ Removed

        Pack200.UnPacker unpacker = Pack200.newUnPacker();

        PropertyChangeListener listener = evt -> {
            System.out.println("Property changed: " + evt.getPropertyName());
        };

        // These methods are now removed:
        unpacker.addPropertyChangeListener(listener);    // ❌ Removed
        unpacker.removePropertyChangeListener(listener); // ❌ Removed
    }
}
