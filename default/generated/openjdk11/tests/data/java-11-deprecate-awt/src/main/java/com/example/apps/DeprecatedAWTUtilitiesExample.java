import java.awt.Frame;
import com.sun.awt.AWTUtilities;

@SuppressWarnings("removal")
public class DeprecatedAWTUtilitiesExample {

    public void makeTransparent(Frame frame, float transparency) {
        // This method was deprecated and marked for removal
        AWTUtilities.setWindowOpacity(frame, transparency);
    }
}
