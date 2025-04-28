# javafx-builder-example

This project demonstrates the use of the deprecated `Builder<T>` interface in JavaFX, showcasing how to create a simple JavaFX application using the builder pattern.

## Project Structure

```
javafx-builder-example
├── src
│   └── main
│       └── java
│           └── com
│               └── example
│                   └── javafxbuilder
│                       ├── Main.java
│                       └── BuilderExample.java
├── pom.xml
└── README.md
```

## Getting Started

To build and run this JavaFX application, follow these steps:

1. **Clone the repository** or download the project files to your local machine.

2. **Navigate to the project directory** in your terminal.

3. **Build the project** using Maven:
   ```
   mvn clean install
   ```

4. **Run the application**:
   ```
   mvn javafx:run
   ```

## Overview of the Example

- **Main.java**: This is the entry point of the JavaFX application. It initializes the JavaFX application and sets up the primary stage.

- **BuilderExample.java**: This file demonstrates the use of the `Builder<T>` interface in JavaFX. It includes a class that uses the builder pattern to create a JavaFX UI component.

## Note

This project uses JavaFX features that have been deprecated in later versions. It serves as an educational example for understanding the builder pattern in JavaFX prior to its removal.