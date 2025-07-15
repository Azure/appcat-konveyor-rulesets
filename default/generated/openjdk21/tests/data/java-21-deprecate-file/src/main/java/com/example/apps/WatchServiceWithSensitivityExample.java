import java.nio.file.*;
import java.util.List;
import java.util.concurrent.TimeUnit;

import java.io.File;
import java.io.IOException;

// ❗ This is now deprecated
import static com.sun.nio.file.SensitivityWatchEventModifier.HIGH;

public class WatchServiceWithSensitivityExample {
    public static void main(String[] args) throws Exception {
        WatchService watchService = FileSystems.getDefault().newWatchService();
        Path path = Paths.get("/tmp"); // use any path that exists on your system

        // Deprecated usage of SensitivityWatchEventModifier
        path.register(watchService,
                      new WatchEvent.Kind[]{StandardWatchEventKinds.ENTRY_CREATE},
                      HIGH);  // 👈 Deprecated & ignored on macOS now

        System.out.println("Watching /tmp for file creation...");

        WatchKey key;
        while ((key = watchService.poll(10, TimeUnit.SECONDS)) != null) {
            List<WatchEvent<?>> events = key.pollEvents();
            for (WatchEvent<?> event : events) {
                System.out.println("Event kind:" + event.kind() + ". File affected: " + event.context());
            }
            key.reset();
        }

        try {
            // Path with symbolic link or relative components
            File file = new File("./some/../test.txt");

            // Returns the canonical path: resolves symbolic links and ".."
            String canonicalPath = file.getCanonicalPath();
            System.out.println("Canonical Path: " + canonicalPath);

            // Returns the canonical File object
            File canonicalFile = file.getCanonicalFile();
            System.out.println("Canonical File: " + canonicalFile);

        } catch (IOException e) {
            e.printStackTrace();
        }

        String canonCaches = System.getProperty("sun.io.useCanonCaches");
        String canonPrefixCache = System.getProperty("sun.io.useCanonPrefixCache");
    }
}
