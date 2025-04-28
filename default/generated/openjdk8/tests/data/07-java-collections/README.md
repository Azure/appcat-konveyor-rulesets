# Locale Service Provider Sample

This project demonstrates the behavior of the `LocaleServiceProvider` class prior to Java 8. It includes a custom implementation of the `LocaleServiceProvider` that overrides the `isSupportedLocale` method to illustrate how locale support was handled before the changes introduced in Java 8.

## Project Structure

```
locale-service-provider-sample
├── src
│   └── main
│       └── java
│           └── com
│               └── example
│                   ├── Main.java
│                   └── provider
│                       └── CustomLocaleServiceProvider.java
├── pom.xml
└── README.md
```

## Getting Started

To set up and run the sample application, follow these steps:

1. **Clone the repository** or download the project files to your local machine.

2. **Navigate to the project directory** in your terminal.

3. **Build the project** using Maven. Run the following command:
   ```
   mvn clean install
   ```

4. **Run the application**. You can execute the main class using the following command:
   ```
   mvn exec:java -Dexec.mainClass="com.example.Main"
   ```

## Purpose

The purpose of this project is to provide a clear example of how the `LocaleServiceProvider` class functioned before the introduction of the new locale support mechanism in Java 8. It serves as a reference for developers who need to understand the changes made in the Java internationalization framework.

## License

This project is licensed under the MIT License - see the LICENSE file for details.