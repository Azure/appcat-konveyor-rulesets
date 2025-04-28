import com.sun.corba.se.spi.orb.ORB;

public class RestrictedCorbaExample {
    public static void main(String[] args) {
        System.setSecurityManager(new SecurityManager());

        try {
            // Try to use a CORBA class (this is now restricted)
            ORB orb = ORB.init(args, null);
            System.out.println("ORB initialized: " + orb);
        } catch (SecurityException e) {
            System.err.println("Access denied: " + e);
        }
    }
}
