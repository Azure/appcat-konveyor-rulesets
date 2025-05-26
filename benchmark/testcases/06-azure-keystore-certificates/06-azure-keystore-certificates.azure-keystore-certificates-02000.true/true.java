```java
import java.security.KeyStore;
import java.io.FileInputStream;

public class KeyStoreExample {
    public static void main(String[] args) {
        try {
            KeyStore keyStore = KeyStore.getInstance("JKS");
            FileInputStream fis = new FileInputStream("keystore.jks");
            keyStore.load(fis, "password".toCharArray());
            System.out.println("KeyStore loaded successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```