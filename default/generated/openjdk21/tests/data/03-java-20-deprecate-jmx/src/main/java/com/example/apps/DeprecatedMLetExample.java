import javax.management.loading.MLet;
import javax.management.loading.MLetContent;
import javax.management.loading.MLetMBean;
import javax.management.loading.PrivateMLet;

public class DeprecatedMLetExample {
    public static void main(String[] args) {
        System.out.println("Using deprecated javax.management.loading classes.");

        // Example instantiation (just illustrative, these are deprecated)
        MLet mlet = new MLet();
        System.out.println("Created MLet instance: " + mlet);
    }
}
