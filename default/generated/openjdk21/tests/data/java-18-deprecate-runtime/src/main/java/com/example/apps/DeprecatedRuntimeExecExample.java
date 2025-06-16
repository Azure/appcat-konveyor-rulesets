import java.io.IOException;
import java.lang.*;

public class DeprecatedRuntimeExecExample {
    public static void main(String[] args) {
        try {
            // This may fail if the path contains spaces or special characters
            Runtime runtime = Runtime.getRuntime();
            Process process = runtime.exec("notepad C:\\Program Files\\notes.txt");
            process.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
