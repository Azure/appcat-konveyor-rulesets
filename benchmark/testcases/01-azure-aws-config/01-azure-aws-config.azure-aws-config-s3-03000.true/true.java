package com.example;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;

public class S3Example {
    public static void main(String[] args) {
        AmazonS3 s3Client = createS3Client();
        s3Client.putObject(new PutObjectRequest("my-bucket", "my-key", "my-content"));
    }

    private static AmazonS3 createS3Client() {
        // Create an AmazonS3 client
        return null; // Implementation omitted for brevity
    }
}