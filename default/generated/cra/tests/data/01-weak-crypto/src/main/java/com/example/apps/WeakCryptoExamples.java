package com.example.apps;

import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.Signature;
import java.util.Random;
import javax.crypto.Cipher;

/**
 * Test data for CRA weak cryptography detection rules.
 * Each method triggers one or more specific rules.
 */
public class WeakCryptoExamples {

    // Rule cra-weak-crypto-00001: MD5 hash algorithm
    public void useMd5() throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] hash = md.digest("test".getBytes());
    }

    // Rule cra-weak-crypto-00002: SHA-1 hash algorithm
    public void useSha1() throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-1");
        byte[] hash = md.digest("test".getBytes());
    }

    // Rule cra-weak-crypto-00003: DES encryption
    public void useDes() throws Exception {
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
    }

    // Rule cra-weak-crypto-00004: 3DES/DESede encryption
    public void useTripleDes() throws Exception {
        Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
    }

    // Rule cra-weak-crypto-00005: RC4 stream cipher
    public void useRc4() throws Exception {
        Cipher cipher = Cipher.getInstance("RC4");
    }

    // Rule cra-weak-crypto-00006: ECB block cipher mode
    public void useEcbMode() throws Exception {
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
    }

    // Rule cra-weak-crypto-00007: Blowfish cipher
    public void useBlowfish() throws Exception {
        Cipher cipher = Cipher.getInstance("Blowfish/ECB/PKCS5Padding");
    }

    // Rule cra-weak-crypto-00008: Weak RSA key size (1024 bits)
    public void useWeakRsaKey() throws Exception {
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
        keyPairGen.initialize(1024);
    }

    // Rule cra-weak-crypto-00009: Weak signature algorithm (MD5withRSA)
    public void useWeakSignature() throws Exception {
        Signature sig = Signature.getInstance("MD5withRSA");
    }

    // Rule cra-weak-crypto-00010: Insecure random number generator
    public void useInsecureRandom() {
        Random random = new Random();
        int value = random.nextInt(100);
    }
}
