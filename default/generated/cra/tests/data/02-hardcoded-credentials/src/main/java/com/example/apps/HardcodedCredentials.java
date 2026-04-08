package com.example.apps;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Test data for CRA hardcoded credential detection rules.
 * Each method or field triggers specific rules.
 */
public class HardcodedCredentials {

    // Rule cra-hardcoded-credential-00001: Hardcoded password variable
    private String password = "admin123";

    // Rule cra-hardcoded-credential-00001: Hardcoded API key variable
    private String apiKey = "sk-1234567890abcdef";

    // Rule cra-hardcoded-credential-00002: JDBC connection with hardcoded credentials
    public Connection getConnection() throws Exception {
        String url = "jdbc:mysql://localhost:3306/mydb";
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "admin", "pass123");
        return conn;
    }
}
