# Type Bounds Demo

This project demonstrates the behavior of `javax.lang.model.type.TypeVariable.getUpperBound` before Java 8. It showcases how type variables with multiple bounds were handled in earlier versions of Java, specifically focusing on the differences in the return values of the `getUpperBound` method.

## Project Structure

```
type-bounds-demo
├── src
│   └── main
│       └── java
│           ├── com
│           │   └── example
│           │       ├── TypeBoundsDemo.java
│           │       ├── MyProcessor.java
│           │       └── TypeVisitorImpl.java
│   └── test
│       └── java
│           └── com
│               └── example
│                   └── TypeBoundsDemoTest.java
├── build.gradle
├── pom.xml
└── README.md
```

## Getting Started

To build and run the project, you can use either Gradle or Maven. Follow the instructions below based on your preferred build tool.

### Using Gradle

1. Make sure you have Gradle installed on your machine.
2. Navigate to the project directory.
3. Run the following command to build the project:

   ```
   ./gradlew build
   ```

4. To run the demo, execute:

   ```
   ./gradlew run
   ```

### Using Maven

1. Ensure you have Maven installed.
2. Navigate to the project directory.
3. Build the project with:

   ```
   mvn clean install
   ```

4. To run the demo, use:

   ```
   mvn exec:java -Dexec.mainClass="com.example.TypeBoundsDemo"
   ```

## Explanation of Behavior

In this project, we demonstrate how `TypeVariable.getUpperBound` behaves when dealing with type variables that have multiple bounds. Before Java 8, the method returned an instance of `DeclaredType` for such type variables. This behavior changed in Java 8, where it now returns an instance of `IntersectionType`.

The project includes a main class (`TypeBoundsDemo`) that sets up a type variable with multiple bounds and retrieves the upper bound, printing the result to the console. Additionally, the `MyProcessor` class uses the Java Compiler API to analyze type variables, and the `TypeVisitorImpl` class implements the `TypeVisitor` interface to handle declared types.

## Running Tests

To ensure the functionality is as expected, unit tests are provided in the `TypeBoundsDemoTest` class. You can run the tests using the following command:

### Using Gradle

```
./gradlew test
```

### Using Maven

```
mvn test
```

## Conclusion

This project serves as a practical demonstration of the changes in behavior of `TypeVariable.getUpperBound` from versions prior to Java 8 to Java 8 and beyond. It is a useful resource for understanding type bounds in Java and the implications of these changes for developers.