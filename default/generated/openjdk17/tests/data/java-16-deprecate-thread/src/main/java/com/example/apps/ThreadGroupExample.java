import javax.security.auth.Subject;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;
import java.security.PrivilegedAction;
import java.util.Set;

import java.lang.*;

public class ThreadGroupExample {
    public static void main(String[] args) {
        ThreadGroup group = new ThreadGroup("LegacyGroup");

        // Deprecated and now non-functional in Java 19+
        group.setDaemon(true);
        System.out.println("Is Daemon: " + group.isDaemon());

        Thread t1 = new Thread(group, () -> {
            System.out.println("Thread 1 running in group.");
        });

        t1.start();

        try {
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Deprecated methods: have no effect or throw exceptions
        try {
            group.destroy();  // Does nothing
            System.out.println("Destroyed group: " + group.getName());
        } catch (Exception e) {
            System.out.println("Exception on destroy(): " + e);
        }

        System.out.println("isDestroyed(): " + group.isDestroyed()); // Always returns false

        try {
            group.suspend();  // Throws UnsupportedOperationException
        } catch (UnsupportedOperationException e) {
            System.out.println("suspend() is unsupported: " + e);
        }

        try {
            group.resume();  // Throws UnsupportedOperationException
        } catch (UnsupportedOperationException e) {
            System.out.println("resume() is unsupported: " + e);
        }

        try {
            group.stop();  // Throws UnsupportedOperationException
        } catch (UnsupportedOperationException e) {
            System.out.println("stop() is unsupported: " + e);
        }
    }
}