import java.lang.*;

public class ObsoleteRuntimeMethodsExample {
    public static void main(String[] args) {
        Runtime runtime = Runtime.getRuntime();

        // These methods were deprecated and non-functional long before JDK 13
        runtime.traceInstructions(true); // ❌ Removed in JDK 13
        runtime.traceMethodCalls(true);  // ❌ Removed in JDK 13

        System.out.println("Tracing instructions and method calls enabled.");
    }
}
