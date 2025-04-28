# BigDecimal StripTrailingZeros Demo

This project demonstrates the behavior of the `BigDecimal.stripTrailingZeros` method before Java SE 8. 

## Overview

In Java SE 8, the `BigDecimal.stripTrailingZeros` method was changed to return `BigDecimal.ZERO` when called on a `BigDecimal` instance that is numerically equal to zero. This project illustrates the behavior prior to this change, where the method would return the original `BigDecimal` instance instead.

## Project Structure

```
bigdecimal-demo
├── src
│   └── main
│       └── java
│           └── com
│               └── example
│                   └── bigdecimal
│                       ├── BigDecimalStripTrailingZerosDemo.java
│                       └── Main.java
├── pom.xml
└── README.md
```

## How to Run

1. Ensure you have Java and Maven installed on your machine.
2. Clone the repository or download the project files.
3. Navigate to the project directory in your terminal.
4. Compile the project using Maven:
   ```
   mvn clean install
   ```
5. Run the demonstration:
   ```
   mvn exec:java -Dexec.mainClass="com.example.bigdecimal.Main"
   ```

## Expected Output

When you run the project, you should see output demonstrating that the `stripTrailingZeros` method returns the original `BigDecimal` instance for zero values, rather than `BigDecimal.ZERO`. 

This project serves as a useful reference for understanding the changes made to the `BigDecimal` class in Java SE 8.