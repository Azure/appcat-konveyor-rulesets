import software.amazon.awssdk.services.bedrockruntime.BedrockRuntimeClient;
import software.amazon.awssdk.services.bedrockruntime.BedrockRuntimeAsyncClient;
import software.amazon.awssdk.services.bedrockruntime.model.InvokeModelRequest;
import software.amazon.awssdk.services.bedrockruntime.model.InvokeModelResponse;
import software.amazon.awssdk.services.bedrockruntime.model.InvokeModelWithResponseStreamRequest;
import software.amazon.awssdk.services.bedrockruntime.model.ConverseStreamRequest;
import software.amazon.awssdk.services.bedrock.BedrockClient;
import software.amazon.awssdk.services.bedrock.model.ListFoundationModelsRequest;
import com.amazonaws.services.bedrockruntime.AmazonBedrockRuntime;
import com.amazonaws.services.bedrock.AmazonBedrock;

public class BedrockExample {

    private static final String MODEL_ID = "anthropic.claude-3-sonnet-20240229-v1:0";
    private static final String EMBEDDING_MODEL_ID = "amazon.titan-embed-text-v2:0";

    public static void main(String[] args) {
        // AWS SDK v2 - Bedrock Runtime
        BedrockRuntimeClient runtimeClient = BedrockRuntimeClient.builder()
                .build();

        InvokeModelRequest request = InvokeModelRequest.builder()
                .modelId(MODEL_ID)
                .contentType("application/json")
                .accept("application/json")
                .body(software.amazon.awssdk.core.SdkBytes.fromUtf8String(
                        "{\"prompt\": \"Hello, how are you?\", \"max_tokens\": 200}"))
                .build();

        InvokeModelResponse response = runtimeClient.invokeModel(request);
        System.out.println("Response: " + response.body().asUtf8String());

        // AWS SDK v2 - Streaming API
        BedrockRuntimeAsyncClient asyncClient = BedrockRuntimeAsyncClient.builder().build();

        InvokeModelWithResponseStreamRequest streamRequest = InvokeModelWithResponseStreamRequest.builder()
                .modelId(MODEL_ID)
                .contentType("application/json")
                .accept("application/json")
                .body(software.amazon.awssdk.core.SdkBytes.fromUtf8String(
                        "{\"prompt\": \"Tell me a story\", \"max_tokens\": 1000}"))
                .build();

        asyncClient.invokeModelWithResponseStream(streamRequest, responseHandler -> {});

        // AWS SDK v2 - Converse Stream API
        ConverseStreamRequest converseStreamRequest = ConverseStreamRequest.builder()
                .modelId(MODEL_ID)
                .build();

        asyncClient.converseStream(converseStreamRequest, responseHandler -> {});

        // AWS SDK v2 - Bedrock management
        BedrockClient bedrockClient = BedrockClient.builder().build();
        bedrockClient.listFoundationModels(ListFoundationModelsRequest.builder().build());

        runtimeClient.close();
        asyncClient.close();
        bedrockClient.close();
    }
}
