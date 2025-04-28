import javax.security.auth.callback.*;
import javax.security.auth.login.*;
import java.io.IOException;
import java.util.*;

import com.sun.security.auth.callback.DialogCallbackHandler;

public class DialogAuthExample {
    public static void main(String[] args) {
        try {
            // Use the deprecated DialogCallbackHandler
            CallbackHandler handler = new DialogCallbackHandler();

            // Specify a JAAS configuration (defined in login.config file or JVM arg)
            LoginContext context = new LoginContext("MyLogin", handler);

            // Attempt login
            context.login();

            System.out.println("Authentication successful!");

            // Logout
            context.logout();
        } catch (LoginException e) {
            System.err.println("Authentication failed: " + e.getMessage());
        }
    }
}
