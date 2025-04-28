# Java Reflection Demo

This project demonstrates the behavior of `Class.getMethod` and `Class.getMethods` in Java before version 8, particularly focusing on how methods from interfaces are inherited and how default methods are handled.

## Purpose

The primary goal of this project is to illustrate the differences in method retrieval using reflection in Java, especially in the context of interface inheritance. It highlights how Java 8 introduced default methods in interfaces and how this change affects method resolution.

## Project Structure

The project is organized as follows:

```
java-reflection-demo
├── src
│   └── main
│       └── java
│           └── com
│               └── example
│                   └── reflection
│                       ├── DefaultMethodDemo.java
│                       ├── Main.java
│                       └── interfaces
│                           ├── BaseInterface.java
│                           └── ChildInterface.java
├── pom.xml
└── README.md
```

### File Descriptions

- **DefaultMethodDemo.java**: Contains the `DefaultMethodDemo` class that demonstrates the behavior of `Class.getMethod` and `Class.getMethods` before Java 8, showing how methods from interfaces are inherited.

- **Main.java**: The entry point of the application. It creates instances of the interfaces and uses reflection to retrieve methods, illustrating the differences in behavior before Java 8.

- **BaseInterface.java**: Defines the `BaseInterface` with a method `void display()`, serving as a base interface.

- **ChildInterface.java**: Defines the `ChildInterface` that extends `BaseInterface` and includes a default method `default void show()`. This demonstrates how default methods were introduced in Java 8.

- **pom.xml**: The Maven configuration file that specifies project dependencies, build settings, and plugins required to compile and run the project.

## How to Run

1. Clone the repository or download the project files.
2. Navigate to the project directory.
3. Use Maven to compile and run the project:

   ```bash
   mvn clean install
   mvn exec:java -Dexec.mainClass="com.example.reflection.Main"
   ```

4. Observe the output to see how methods are retrieved and the impact of default methods in interfaces.

## Conclusion

This project serves as a practical demonstration of Java reflection and method inheritance, providing insights into the changes introduced in Java 8 regarding default methods in interfaces.