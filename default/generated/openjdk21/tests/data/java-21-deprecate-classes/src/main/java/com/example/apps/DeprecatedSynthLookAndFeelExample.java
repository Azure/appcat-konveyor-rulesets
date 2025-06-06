import javax.swing.plaf.synth.SynthLookAndFeel;
import java.net.URL;

public class DeprecatedSynthLookAndFeelExample {
    public static void main(String[] args) throws Exception {
        SynthLookAndFeel synth = new SynthLookAndFeel();
        URL url = new URL("file:///tmp/dummy.xml");
        try {
            synth.load(url);
            System.out.println("Called SynthLookAndFeel.load(URL)");
        } catch (Exception e) {
            System.out.println("Exception during SynthLookAndFeel.load: " + e.getMessage());
        }
    }
}
