import java.lang.Compiler;
public class CompilerExample {
    public static void main(String[] args) {
        String compiler = System.getProperty("java.compiler");

        // Sample logic
        for (int i = 0; i < 1_000_000; i++) {
            Math.sqrt(i); // some load
        }

        System.out.println("Calling Compiler.compileClass...");

        // This does nothing in modern JVMs
        boolean result = java.lang.Compiler.compileClass(CompilerExample.class);

        System.out.println("Result of compileClass: " + result);
    }
}
