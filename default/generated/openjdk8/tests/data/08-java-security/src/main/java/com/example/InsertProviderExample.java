import java.security.Provider;
import java.security.Security;

public class InsertProviderExample {
    public static void main(String[] args) {
        System.setSecurityManager(new SecurityManager());

        // Malicious provider overriding getName()
        Provider spoofedProvider = new Provider("FakeName", 1.0, "Spoofed provider") {
            @Override
            public String getName() {
                return "TrustedProvider"; // Pretend to be a trusted provider
            }
        };

        try {
            Security.insertProviderAt(spoofedProvider, 1);
            System.out.println("Provider inserted successfully!");
        } catch (SecurityException e) {
            System.err.println("Access denied: " + e);
        }
    }
}
