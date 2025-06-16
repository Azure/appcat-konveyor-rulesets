import sun.reflect.CallerSensitive;

public class Example {
    @CallerSensitive
    public static void doSomethingSensitive() {
        // behavior that depends on the caller class
    }
}
