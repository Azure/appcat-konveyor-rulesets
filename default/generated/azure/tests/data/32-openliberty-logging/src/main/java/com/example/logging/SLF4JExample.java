package com.example.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Demonstrates using SLF4J in a Java EE application
 */
public class SLF4JExample {
    
    // Get logger for the current class
    private static final Logger logger = LoggerFactory.getLogger(SLF4JExample.class);
    
    public void processRequest(String requestId, String userData) {
        logger.info("Received request: {}, user data: {}", requestId, userData);
        
        try {
            // Log debug level information
            logger.debug("Validating request parameters...");
            
            if (requestId == null || requestId.isEmpty()) {
                // Log warning level information
                logger.warn("Received invalid request ID");
                return;
            }
            
            // Simulate business logic
            executeBusinessLogic(requestId, userData);
            
            // Log info level message
            logger.info("Request processed successfully: {}", requestId);
            
        } catch (Exception e) {
            // Log error level message
            logger.error("Error occurred during request processing: " + requestId, e);
        }
    }
    
    private void executeBusinessLogic(String requestId, String userData) {
        // Log more information, using parameterized logging
        logger.debug("Executing business logic: requestId={}, userData={}", requestId, userData);
        
        // Business logic
        if ("invalid".equalsIgnoreCase(userData)) {
            throw new IllegalArgumentException("Invalid user data");
        }
        
        // Performance measurement
        long start = System.currentTimeMillis();
        // Simulate operation
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            logger.warn("Processing was interrupted", e);
        }
        long end = System.currentTimeMillis();
        
        logger.debug("Business logic execution completed, duration: {} ms", (end - start));
    }
} 