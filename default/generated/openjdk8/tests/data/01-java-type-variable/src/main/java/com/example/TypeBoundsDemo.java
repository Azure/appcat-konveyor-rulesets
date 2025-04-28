public class TypeBoundsDemo {
    public static void main(String[] args) {
        // Create a type variable with multiple bounds
        // Note: This is a conceptual demonstration. Actual type variable creation
        // requires a compiler context which is not available in standard Java code.
        
        // Example of type variable declaration (conceptual)
        // <T extends Number & Comparable<T>> 
        // The upper bounds would be Number and Comparable<T>
        
        // Simulate the behavior of TypeVariable.getUpperBound before Java 8
        // In Java 7 and earlier, this would return a DeclaredType instead of IntersectionType.
        
        System.out.println("Demonstrating TypeVariable.getUpperBound behavior before Java 8:");
        
        // Here we would normally invoke the processor to analyze the type variable
        // and print the upper bounds, but this requires a full annotation processing setup.
        
        // For demonstration purposes, we will just print a message.
        System.out.println("Expected behavior: DeclaredType returned for multiple bounds.");
    }
}