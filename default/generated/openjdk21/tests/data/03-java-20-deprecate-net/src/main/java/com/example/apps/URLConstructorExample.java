import java.net.URL;

public class URLConstructorExample {
    public static void main(String[] args) throws Exception {
        // Deprecated constructor usage
        URL url = new URL("https://example.com/path?query=123");
        System.out.println("URL: " + url);
    }
}
