import java.lang.ThreadGroup;

public class ThreadGroupExample {
    public static void main(String[] args) {
        // Create a new ThreadGroup
        ThreadGroup myThreadGroup = new ThreadGroup("CustomThreadGroup");
        
        // In older Java versions (pre-Java 1.2), this method was used
        // to control whether threads in this group could be suspended
        // for low memory conditions
        myThreadGroup.allowThreadSuspension(true);
        
        // Create and start a thread in this thread group
        Thread thread = new Thread(myThreadGroup, () -> {
            try {
                System.out.println("Thread running in custom thread group");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "WorkerThread");
        
        thread.start();
        
        // Print information about the thread group
        System.out.println("Thread group name: " + myThreadGroup.getName());
        System.out.println("Active threads: " + myThreadGroup.activeCount());
    }
}