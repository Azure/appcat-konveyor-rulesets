```java
public class EnvironmentUsage {
    public static void main(String[] args) {
        // This program does not use any environment variables or system properties

        String hardcodedValue = "This is a constant value.";
        System.out.println("Hardcoded Value: " + hardcodedValue);

        java.util.List<String> list = java.util.Arrays.asList("Value1", "Value2");
        for (String value : list) {
            System.out.println("List Value: " + value);
        }
    }
}
```