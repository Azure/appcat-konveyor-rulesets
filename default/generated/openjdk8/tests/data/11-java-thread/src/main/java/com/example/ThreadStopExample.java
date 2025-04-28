import java.lang.Thread;
import java.lang.UnsupportedOperationException;

public class ThreadStopExample {
    public static void main(String[] args) {
        // Creating a thread that will run for a long time
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        System.out.println("Thread is running...");
                        Thread.sleep(1000);
                    }
                } catch (InterruptedException e) {
                    System.out.println("Thread interrupted.");
                }
            }
        });

        // Start the thread
        thread.start();

        // Attempt to stop the thread using Thread.stop() (deprecated)
        try {
            System.out.println("Attempting to stop the thread...");
            thread.stop();  // This will throw an UnsupportedOperationException
        } catch (UnsupportedOperationException e) {
            System.err.println("Caught exception: " + e);
        }
    }
}
