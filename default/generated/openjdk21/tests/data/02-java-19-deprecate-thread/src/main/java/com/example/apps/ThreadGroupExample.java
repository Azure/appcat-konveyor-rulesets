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

        Thread thread = Thread.currentThread();

        // Discouraged: not final, can be overridden in subclasses
        long id = thread.getId();

        System.out.println("Thread ID (getId): " + id);

        try {
            // Simulate a subject (normally retrieved from LoginContext after login)
            Subject subject = new Subject();
            subject.getPrincipals().add(() -> "UserPrincipal");

            // Run privileged action as this subject
            String result = Subject.doAs(subject, (PrivilegedAction<String>) () -> {
                System.out.println("Running as subject: " + Subject.getSubject(java.security.AccessController.getContext()));
                return "Privileged Action Executed";
            });

            result = Subject.callAs(subject, null);

            System.out.println("Result: " + result);

            // This now always uses the default behavior (ThreadLocal no longer supported)
            Subject current = Subject.current();
            System.out.println("Current Subject (default impl): " + current);

        } catch (Exception e) {
            e.printStackTrace();
        }

        String value = System.getProperty("jdk.security.auth.subject.useTL");
    }
}
