package com.example.logging;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Demonstrates using Java Util Logging (JUL) in a Java EE application
 */
public class JULExample {
    
    // Get logger for the current class
    private static final Logger LOGGER = Logger.getLogger(JULExample.class.getName());
    
    public void performAction(String input) {
        LOGGER.info("Starting operation, input: " + input);
        
        try {
            // Log debug level information
            LOGGER.fine("Processing input parameters...");
            
            if (input == null || input.isEmpty()) {
                // Log warning level information
                LOGGER.warning("Received empty input parameter");
                return;
            }
            
            // Simulate business logic
            processData(input);
            
            // Log info level message
            LOGGER.info("Operation completed successfully");
            
        } catch (Exception e) {
            // Log error level message
            LOGGER.log(Level.SEVERE, "Error occurred during processing", e);
        }
    }
    
    private void processData(String data) {
        // Log more information
        LOGGER.fine("Processing data: " + data);
        
        // Business logic
        if ("error".equalsIgnoreCase(data)) {
            throw new RuntimeException("Error scenario demonstration");
        }
    }
} 