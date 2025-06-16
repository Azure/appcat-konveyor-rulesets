import java.lang.Thread;
import java.lang.ThreadGroup;

public class DeprecatedThreadControl {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(() -> {
            while (true) {
                System.out.println("Running...");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    break;
                }
            }
        });

        t.start();

        // Deprecated method: suspend
        Thread.sleep(2000);
        t.suspend(); // ❌ Deprecated

        System.out.println("Thread suspended");

        // Deprecated method: resume
        Thread.sleep(2000);
        t.resume(); // ❌ Deprecated

        System.out.println("Thread resumed");

        t.interrupt();

        ThreadGroup group = new ThreadGroup("MyGroup");
        Thread t1 = new Thread(group, () -> {
            while (true) {
                System.out.println("T1 Running...");
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    break;
                }
            }
        });

        t1.start();

        // Deprecated methods
        Thread.sleep(1000);
        group.suspend(); // ❌ Deprecated
        System.out.println("Group suspended");

        Thread.sleep(1000);
        group.resume(); // ❌ Deprecated
        System.out.println("Group resumed");

        t1.interrupt();

        ThreadGroup group1 = new ThreadGroup("LegacyGroup");
        group1.allowThreadSuspension(true); // ❌ Deprecated
    }
}
