import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TypeBoundsDemoTest {

    @Test
    void testGetUpperBound() {
        // Create an instance of TypeBoundsDemo
        TypeBoundsDemo demo = new TypeBoundsDemo();
        
        // Call the method to retrieve the upper bound
        String upperBound = demo.getUpperBound();
        
        // Assert that the upper bound is as expected
        assertEquals("ExpectedUpperBound", upperBound);
    }
}