import java.io.*;
import java.lang.Runtime;

public class LocalizedStreamExample {
    public static void main(String[] args) throws IOException {
        InputStream original = new FileInputStream("example.txt");
        Runtime runtime = Runtime.getRuntime();
        // Deprecated and removed
        InputStream localized = runtime.getLocalizedInputStream(original);

        OutputStream output = new FileOutputStream("out.txt");
        // Deprecated and removed
        OutputStream localizedOut = runtime.getLocalizedOutputStream(output);
    }
}
