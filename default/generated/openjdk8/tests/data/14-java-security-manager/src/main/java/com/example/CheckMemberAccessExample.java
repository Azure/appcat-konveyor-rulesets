import java.lang.reflect.Member;
import java.lang.SecurityManager;

public class CheckMemberAccessExample {
    public static void main(String[] args) {
        // Install a custom SecurityManager
        System.setSecurityManager(new SecurityManager());

        try {
            // Attempt to check access to private members of the ExampleClass
            SecurityManager sm = new SecurityManager();
            sm.checkMemberAccess(ExampleClass.class, Member.DECLARED);
            System.out.println("Access to declared members allowed.");
        } catch (SecurityException e) {
            System.err.println("Access denied: " + e);
        }
    }
}

class ExampleClass {
    private String secret = "Top Secret";
}
