import java.lang.ThreadDeath;
import java.lang.Thread;

public class SuspendResumeExample {
    public static void main(String[] args) {
        Thread worker = new Thread(() -> {
            int i = 0;
            while (true) {
                System.out.println("Working... " + i++);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    break;
                }
            }
        });

        worker.start();

        try {
            Thread.sleep(2000);

            // ❌ Deprecated and now removed
            System.out.println("Suspending thread...");
            worker.suspend();

            Thread.sleep(2000);

            System.out.println("Resuming thread...");
            worker.resume();

            Thread.sleep(2000);
            worker.interrupt(); // stop the thread
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        worker = new Thread(() -> {
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    System.out.println("Working...");
                    Thread.sleep(500);
                }
            } catch (InterruptedException e) {
                System.out.println("Thread interrupted");
            }
        });

        worker.start();

        try {
            worker.stop();
            System.out.println("Stopping thread...");
            worker.interrupt();  // Safe way to signal thread to stop
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            throw new ThreadDeath();  // Deprecated usage
        } catch (ThreadDeath td) {
            System.out.println("Caught ThreadDeath: " + td);
            // Usually ThreadDeath is rethrown to allow thread termination
            throw td;
        }
    }
}
