import java.util.Locale;

public class DeprecatedLocaleUsage {
    public static void main(String[] args) {
        // Deprecated constructor usage
        Locale deprecatedLocale = new Locale("en", "US");
        System.out.println("Deprecated Locale: " + deprecatedLocale);
    }
}
