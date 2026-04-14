package com.example.cra;

import javax.crypto.spec.SecretKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.mail.PasswordAuthentication;

/**
 * Test data for CRA hard-coded credentials detection rules.
 */
public class HardcodedCredentialExamples {

    // === Hard-coded Password (cra-hardcoded-credential-password-01000) ===

    // Detected: password = "..."
    private String password = "MyS3cretP@ssword!";

    // Detected: .setPassword("...")
    public void configureDatabase() {
        DataSource ds = new DataSource();
        ds.setPassword("dbPassword123");
    }

    // Detected: new PasswordAuthentication("...", "...")
    public PasswordAuthentication getAuth() {
        return new PasswordAuthentication("admin", "secretpass");
    }

    // Detected: .password("...")
    public void buildConnection() {
        ConnectionBuilder builder = new ConnectionBuilder();
        builder.password("hardcodedPassword");
    }

    // === Hard-coded API Key / Secret (cra-hardcoded-credential-apikey-02000) ===

    // Detected: api_key = "..."
    private String api_key = "sk-1234567890abcdef1234567890abcdef";

    // Detected: secretKey = "..."
    private String secretKey = "wJalrXUtnFEMI/K7MDENG/bPxRfiCYEXAMPLEKEY";

    // Detected: access_token = "..."
    private String access_token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkw";

    // Detected: client_secret = "..."
    private String client_secret = "my-super-secret-client-secret-value";

    // === Default/Well-Known Password (cra-hardcoded-credential-default-pwd-04000) ===

    // Detected: password = "admin"
    private String adminPwd = "admin";
    public void setDefaultPassword() {
        // Detected: .setPassword("changeit")
        ds.setPassword("changeit");
    }

    // Detected: password = "password"
    String defaultPass = "password";

    // === Hard-coded Crypto Key (cra-hardcoded-credential-crypto-key-05000) ===

    // Detected: new SecretKeySpec("...".getBytes(), ...)
    public SecretKeySpec getHardcodedKey() {
        return new SecretKeySpec("ThisIsASecretKey".getBytes(), "AES");
    }

    // Detected: new SecretKeySpec(new byte[]{...})
    public SecretKeySpec getHardcodedKeyBytes() {
        return new SecretKeySpec(new byte[]{0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07,
                                            0x08, 0x09, 0x0a, 0x0b, 0x0c, 0x0d, 0x0e, 0x0f}, "AES");
    }

    // Detected: new IvParameterSpec("...".getBytes())
    public IvParameterSpec getHardcodedIv() {
        return new IvParameterSpec("1234567890123456".getBytes());
    }

    // Detected: new IvParameterSpec(new byte[]{...})
    public IvParameterSpec getHardcodedIvBytes() {
        return new IvParameterSpec(new byte[]{0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07,
                                              0x08, 0x09, 0x0a, 0x0b, 0x0c, 0x0d, 0x0e, 0x0f});
    }

    // === Safe usage (should NOT trigger rules) ===

    public String getPasswordFromEnv() {
        return System.getenv("DB_PASSWORD");
    }

    public String getApiKeyFromConfig(Config config) {
        return config.getString("api.key");
    }

    // Stub classes for compilation
    static class DataSource { void setPassword(String p) {} }
    static class ConnectionBuilder { void password(String p) {} }
    static class Config { String getString(String k) { return ""; } }
    DataSource ds = new DataSource();
}
