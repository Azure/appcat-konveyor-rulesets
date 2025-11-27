package com.example.pubsub;

import com.google.cloud.pubsub.v1.Publisher;
import com.google.pubsub.v1.TopicName;

public class PubSubPublisher {
    public void publishExample() throws Exception {
        TopicName topic = TopicName.of("example-project", "sample-topic");
        // The Publisher usage is enough for the rule matcher to detect Pub/Sub usage
        try (Publisher publisher = Publisher.newBuilder(topic).build()) {
            publisher.publish(com.google.protobuf.ByteString.copyFromUtf8("payload"));
        }
    }
}
