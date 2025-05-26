```java
public class EnvironmentUsage {
    public static void main(String[] args) {
        // Using System.getenv to fetch environment variables
        String envVar = System.getenv("MY_ENV_VAR");

        // Using System.getProperty to fetch system properties
        String prop = System.getProperty("my.property");

        // Using System.setProperty to set a system property
        System.setProperty("my.property", "newValue");

        // Using System.setProperties to set properties from a Properties object
        java.util.Properties properties = new java.util.Properties();
        properties.setProperty("key", "value");
        System.setProperties(properties);

        System.out.println("Environment Variable: " + envVar);
        System.out.println("System Property: " + prop);
    }
}
```