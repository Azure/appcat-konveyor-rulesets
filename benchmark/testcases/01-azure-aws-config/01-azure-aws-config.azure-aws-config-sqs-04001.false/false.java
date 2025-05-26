```java
import com.azure.messaging.servicebus.ServiceBusClientBuilder;

public class AzureServiceBusExample {
    public static void main(String[] args) {
        // Create an Azure Service Bus client
        var serviceBusClient = new ServiceBusClientBuilder()
                .connectionString("<YOUR_CONNECTION_STRING>")
                .buildClient();

        // Example operation using Azure Service Bus
        System.out.println("Azure Service Bus Client created: " + serviceBusClient);
    }
}
```