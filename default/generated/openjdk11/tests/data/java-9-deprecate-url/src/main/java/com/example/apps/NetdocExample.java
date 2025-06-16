import java.net.URL;

public class NetdocExample {
    public static void main(String[] args) throws Exception {
        URL url = new URL("netdoc:http://example.com/docs/MyClass.html");
        System.out.println(url);
    }
}
