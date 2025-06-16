import javax.rmi.CORBA.Tie;
import com.sun.corba.se.impl.orbutil.CorbaResourceUtil;

public class DeprecatedCorbaExample {

    public void useDeprecatedCorba() {
        // javax.rmi.CORBA.Tie is part of the removed java.corba module
        Tie tie = null;

        // com.sun.corba.* classes are also removed
        CorbaResourceUtil util = new CorbaResourceUtil();

        // Just dummy usage to avoid unused variable warnings
        if (tie == null) {
            System.out.println("Tie is null (deprecated API)");
        }

        System.out.println("Using CorbaResourceUtil (deprecated API): " + util);
    }

    public static void main(String[] args) {
        DeprecatedCorbaExample example = new DeprecatedCorbaExample();
        example.useDeprecatedCorba();
    }
}
