package com.example.cra;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.security.SecureRandom;

/**
 * Test data for CRA insecure random number detection rules.
 */
public class InsecureRandomExamples {

    // === java.util.Random (cra-insecure-random-01000) ===

    // Detected: new Random()
    private Random random = new Random();

    public String generateToken() {
        // Using java.util.Random for token generation (INSECURE)
        Random rng = new Random();
        StringBuilder token = new StringBuilder();
        for (int i = 0; i < 32; i++) {
            token.append(Integer.toHexString(rng.nextInt(16)));
        }
        return token.toString();
    }

    public String generateSessionId() {
        Random rng = new Random(System.currentTimeMillis());
        return Long.toHexString(rng.nextLong());
    }

    // === Math.random() (cra-insecure-random-math-02000) ===

    // Detected: Math.random()
    public String generateNonce() {
        double value = Math.random();
        return String.valueOf((long) (value * 1000000000));
    }

    public int generateVerificationCode() {
        // Using Math.random() for verification code (INSECURE)
        return (int) (Math.random() * 900000) + 100000;
    }

    // === ThreadLocalRandom (cra-insecure-random-threadlocal-03000) ===

    // Detected: ThreadLocalRandom.current()
    public byte[] generateKey() {
        byte[] key = new byte[16];
        for (int i = 0; i < key.length; i++) {
            key[i] = (byte) ThreadLocalRandom.current().nextInt(256);
        }
        return key;
    }

    // === Safe usage (should NOT trigger rules) ===

    public byte[] generateSecureToken() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] token = new byte[32];
        secureRandom.nextBytes(token);
        return token;
    }
}
