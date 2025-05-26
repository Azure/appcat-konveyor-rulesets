```java
package example;

import software.amazon.awssdk.services.sqs.SqsClient;

public class SqsExample {
    public static void main(String[] args) {
        // Create an SQS client using the AWS SDK for Java 2.x
        SqsClient sqsClient = SqsClient.create();
        
        // Example usage of the SQS client
        System.out.println("Amazon SQS client initialized: " + sqsClient);
    }
}
```