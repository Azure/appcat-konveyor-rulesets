import java.awt.color.ICC_Profile;
import java.awt.image.ColorModel;
import java.awt.image.IndexColorModel;

import java.lang.*;

public class FinalizeUsageExample {
    public static void main(String[] args) {
        // Example ICC_Profile instance
        ICC_Profile profile = ICC_Profile.getInstance(ICC_Profile.CS_sRGB);

        // Example ColorModel (abstract, use a subclass)
        ColorModel colorModel = ColorModel.getRGBdefault();

        // Example IndexColorModel
        byte[] r = {(byte)255, 0, 0};
        byte[] g = {0, (byte)255, 0};
        byte[] b = {0, 0, (byte)255};
        IndexColorModel indexColorModel = new IndexColorModel(8, 3, r, g, b);

        // ❌ These calls used to do nothing and were deprecated
        // They will not compile/run in JDK 18+
        try {
            profile.finalize(); // removed in JDK 18
            colorModel.finalize(); // removed in JDK 18
            indexColorModel.finalize(); // removed in JDK 18
        } catch (Throwable t) {
            t.printStackTrace();
        }

        System.out.println("Finalize calls made (pre-JDK 18 behavior)");


        Runtime rt = Runtime.getRuntime();
        rt.runFinalization();  // ❌ Deprecated
        System.runFinalization();                // ❌ Deprecated
        
        Object obj = new Object();
        obj.finalize(); // ❌ Deprecated
    }
}
