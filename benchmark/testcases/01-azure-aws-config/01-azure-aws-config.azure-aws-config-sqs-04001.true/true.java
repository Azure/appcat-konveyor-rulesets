```java
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;

public class SqsExample {
    public static void main(String[] args) {
        // Create an Amazon SQS client
        AmazonSQS sqs = AmazonSQSClientBuilder.defaultClient();

        // Example operation using Amazon SQS
        System.out.println("Amazon SQS Client created: " + sqs);
    }
}
```