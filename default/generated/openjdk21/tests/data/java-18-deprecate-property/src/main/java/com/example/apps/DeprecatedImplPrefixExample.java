public class DeprecatedImplPrefixExample {
    public static void main(String[] args) {
        String implPrefix = System.getProperty("impl.prefix");
        System.out.println("impl.prefix: " + implPrefix);
    }
}
