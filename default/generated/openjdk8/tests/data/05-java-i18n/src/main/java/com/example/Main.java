public class Main {
    public static void main(String[] args) {
        // Initialize the custom locale service provider
        CustomLocaleServiceProvider provider = new CustomLocaleServiceProvider();

        // Test supported locales
        String[] testLocales = {"en_US", "fr_FR", "es_ES", "de_DE", "zh_CN"};

        for (String locale : testLocales) {
            boolean isSupported = provider.isSupportedLocale(locale);
            System.out.println("Locale " + locale + " supported: " + isSupported);
        }
    }
}