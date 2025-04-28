// even if auto-import, still manually import for java language server use
import java.math.BigDecimal;

public class BigDecimalStripTrailingZerosDemo {
    public void demonstrateStripTrailingZeros() {
        BigDecimal zeroValue1 = new java.math.BigDecimal("0.00");
        BigDecimal zeroValue2 = new java.math.BigDecimal("0.0000");
        BigDecimal zeroValue3 = new java.math.BigDecimal("0");

        System.out.println("Before Java SE 8:");
        System.out.println("Original value: " + zeroValue1 + ", After stripTrailingZeros: " + zeroValue1.stripTrailingZeros());
        System.out.println("Original value: " + zeroValue2 + ", After stripTrailingZeros: " + zeroValue2.stripTrailingZeros());
        System.out.println("Original value: " + zeroValue3 + ", After stripTrailingZeros: " + zeroValue3.stripTrailingZeros());
    }
}