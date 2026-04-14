package com.example.cra;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKeyFactory;
import org.apache.commons.codec.digest.DigestUtils;
import com.google.common.hash.Hashing;

/**
 * Test data for CRA weak cryptography detection rules.
 * Each method demonstrates a different type of weak crypto usage.
 */
public class WeakCryptoExamples {

    // === MD5 Usage (cra-weak-crypto-md5-01000) ===

    public byte[] hashWithMD5(String input) throws NoSuchAlgorithmException {
        // Detected: MessageDigest.getInstance("MD5")
        MessageDigest md = MessageDigest.getInstance("MD5");
        return md.digest(input.getBytes());
    }

    public byte[] hashWithMD5Lower(String input) throws NoSuchAlgorithmException {
        // Detected: MessageDigest.getInstance("md5")
        MessageDigest md = MessageDigest.getInstance("md5");
        return md.digest(input.getBytes());
    }

    public String apacheDigestMd5(String input) {
        // Detected: DigestUtils.md5Hex
        return DigestUtils.md5Hex(input);
    }

    public String guavaMd5(String input) {
        // Detected: Hashing.md5()
        return Hashing.md5().hashBytes(input.getBytes()).toString();
    }

    public byte[] hmacMd5Example(String input) throws Exception {
        // Detected: HmacMD5
        javax.crypto.Mac mac = javax.crypto.Mac.getInstance("HmacMD5");
        return mac.doFinal(input.getBytes());
    }

    // === SHA-1 Usage (cra-weak-crypto-sha1-02000) ===

    public byte[] hashWithSHA1(String input) throws NoSuchAlgorithmException {
        // Detected: MessageDigest.getInstance("SHA-1")
        MessageDigest md = MessageDigest.getInstance("SHA-1");
        return md.digest(input.getBytes());
    }

    public byte[] hashWithSHA1NoHyphen(String input) throws NoSuchAlgorithmException {
        // Detected: MessageDigest.getInstance("SHA1")
        MessageDigest md = MessageDigest.getInstance("SHA1");
        return md.digest(input.getBytes());
    }

    public String apacheDigestSha1(String input) {
        // Detected: DigestUtils.sha1Hex
        return DigestUtils.sha1Hex(input);
    }

    public String guavaSha1(String input) {
        // Detected: Hashing.sha1()
        return Hashing.sha1().hashBytes(input.getBytes()).toString();
    }

    public byte[] hmacSha1Example(String input) throws Exception {
        // Detected: HmacSHA1
        javax.crypto.Mac mac = javax.crypto.Mac.getInstance("HmacSHA1");
        return mac.doFinal(input.getBytes());
    }

    public byte[] signWithSHA1RSA(byte[] data) throws Exception {
        // Detected: SHA1WithRSA
        java.security.Signature sig = java.security.Signature.getInstance("SHA1WithRSA");
        return sig.sign();
    }

    // === DES / 3DES Usage (cra-weak-crypto-des-03000) ===

    public Cipher getDesCipher() throws Exception {
        // Detected: Cipher.getInstance("DES/CBC/PKCS5Padding")
        return Cipher.getInstance("DES/CBC/PKCS5Padding");
    }

    public Cipher getDesEdeCipher() throws Exception {
        // Detected: Cipher.getInstance("DESede/CBC/PKCS5Padding")
        return Cipher.getInstance("DESede/CBC/PKCS5Padding");
    }

    public SecretKeyFactory getDesKeyFactory() throws Exception {
        // Detected: SecretKeyFactory.getInstance("DES")
        return SecretKeyFactory.getInstance("DES");
    }

    public KeyGenerator getDesedeKeyGen() throws Exception {
        // Detected: KeyGenerator.getInstance("DESede")
        return KeyGenerator.getInstance("DESede");
    }

    // === RC4 Usage (cra-weak-crypto-rc4-04000) ===

    public Cipher getRc4Cipher() throws Exception {
        // Detected: Cipher.getInstance("RC4")
        return Cipher.getInstance("RC4");
    }

    public Cipher getArcfourCipher() throws Exception {
        // Detected: Cipher.getInstance("ARCFOUR")
        return Cipher.getInstance("ARCFOUR");
    }

    // === ECB Mode Usage (cra-weak-crypto-ecb-05000) ===

    public Cipher getAesEcbCipher() throws Exception {
        // Detected: Cipher.getInstance("AES/ECB/PKCS5Padding")
        return Cipher.getInstance("AES/ECB/PKCS5Padding");
    }

    public Cipher getDesEcbCipher() throws Exception {
        // Detected: Cipher.getInstance("DES/ECB/PKCS5Padding")
        return Cipher.getInstance("DES/ECB/PKCS5Padding");
    }

    // === Blowfish Usage (cra-weak-crypto-blowfish-06000) ===

    public Cipher getBlowfishCipher() throws Exception {
        // Detected: Cipher.getInstance("Blowfish/CBC/PKCS5Padding")
        return Cipher.getInstance("Blowfish/CBC/PKCS5Padding");
    }

    public KeyGenerator getBlowfishKeyGen() throws Exception {
        // Detected: KeyGenerator.getInstance("Blowfish")
        return KeyGenerator.getInstance("Blowfish");
    }

    // === Weak Password Hashing (cra-weak-crypto-password-hash-07000) ===

    @SuppressWarnings("deprecation")
    public Object getMd5PasswordEncoder() {
        // Detected: new Md5PasswordEncoder
        return new Md5PasswordEncoder();
    }

    @SuppressWarnings("deprecation")
    public Object getShaPasswordEncoder() {
        // Detected: new ShaPasswordEncoder
        return new ShaPasswordEncoder();
    }

    public Object getNoOpPasswordEncoder() {
        // Detected: NoOpPasswordEncoder.getInstance
        return NoOpPasswordEncoder.getInstance();
    }

    // === Safe usage (should NOT trigger rules) ===

    public byte[] hashWithSHA256(String input) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        return md.digest(input.getBytes());
    }

    public Cipher getAesGcmCipher() throws Exception {
        return Cipher.getInstance("AES/GCM/NoPadding");
    }
}
