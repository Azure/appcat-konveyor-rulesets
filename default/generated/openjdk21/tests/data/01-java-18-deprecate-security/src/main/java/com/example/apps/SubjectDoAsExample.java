import javax.security.auth.Subject;
import javax.security.auth.login.LoginContext;
import java.security.PrivilegedAction;

public class SubjectDoAsExample {

    public static void main(String[] args) {
        try {
            // Assume a LoginContext has been properly configured with a JAAS login module
            LoginContext loginContext = new LoginContext("SampleLogin");
            loginContext.login();

            Subject subject = loginContext.getSubject();

            // Deprecated: Using Subject.doAs to run privileged action as a Subject
            Subject.doAs(subject, (PrivilegedAction<Void>) () -> {
                System.out.println("Running privileged action as: " + subject);
                // Perform actions requiring Subject identity here
                return null;
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
