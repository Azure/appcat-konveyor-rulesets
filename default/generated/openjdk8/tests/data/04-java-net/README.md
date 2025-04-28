# Datagram Packet Demo

This project demonstrates the behavior of the `java.net.DatagramPacket` constructors in different versions of Java, specifically focusing on the handling of `java.net.SocketException`.

## Project Structure

```
datagram-packet-demo
├── src
│   └── main
│       └── java
│           └── com
│               ├── DatagramPacketPreJava8.java
│               └── DatagramPacketJava8.java
├── pom.xml
└── README.md
```

## Files

- **DatagramPacketPreJava8.java**: This file demonstrates the behavior of the `java.net.DatagramPacket` constructors before Java 8. It includes a method that attempts to create a `DatagramPacket` using a `SocketAddress` and catches the `SocketException`, illustrating how the exception handling was done prior to Java 8.

- **DatagramPacketJava8.java**: This file demonstrates the behavior of the `java.net.DatagramPacket` constructors in Java 8 and later. It shows that the constructors no longer declare `SocketException` and how to handle the absence of this exception in the code.

## How to Run

1. Ensure you have Maven installed on your machine.
2. Clone the repository or download the project files.
3. Navigate to the project directory in your terminal.
4. Run the following command to compile and execute the examples:

   ```
   mvn clean compile exec:java -Dexec.mainClass="com.example.DatagramPacketPreJava8"
   ```

   and

   ```
   mvn clean compile exec:java -Dexec.mainClass="com.example.DatagramPacketJava8"
   ```

## Differences in Behavior

- **Before Java 8**: The `DatagramPacket` constructors that accept a `SocketAddress` declared that a `SocketException` could be thrown, even though it was never actually thrown. Code that caught this exception would need to be modified when upgrading to Java 8.

- **Java 8 and Later**: The constructors no longer declare that a `SocketException` can be thrown. This change simplifies exception handling for developers, as they no longer need to account for this exception when using these constructors.