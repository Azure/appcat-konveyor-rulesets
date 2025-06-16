package com.example.logging;

import org.apache.log4j.Logger;

/**
 * Demonstrates using Log4j 1.x in a Java EE application
 */
public class Log4jExample {
    
    // Get logger for the current class
    private static final Logger logger = Logger.getLogger(Log4jExample.class);
    
    public void processOrder(String orderId) {
        logger.info("Starting order processing: " + orderId);
        
        try {
            // Log debug level information
            logger.debug("Validating order information...");
            
            if (orderId == null || orderId.isEmpty()) {
                // Log warning level information
                logger.warn("Received invalid order ID");
                return;
            }
            
            // Simulate business logic
            if (orderId.startsWith("TEST")) {
                logger.debug("Test order detected");
            }
            
            executeOrderProcessing(orderId);
            
            // Log info level message
            logger.info("Order processing completed successfully: " + orderId);
            
        } catch (Exception e) {
            // Log error level message
            logger.error("Error occurred during order processing: " + orderId, e);
        }
    }
    
    private void executeOrderProcessing(String orderId) {
        // Log more information
        logger.debug("Executing order processing workflow: " + orderId);
        
        // Business logic
        if ("ERROR-ORDER".equals(orderId)) {
            throw new RuntimeException("Order processing error demonstration");
        }
    }
} 