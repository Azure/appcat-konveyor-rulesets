package my.custom.signer;

import com.sun.jarsigner.ContentSigner;
import java.security.*;
import java.util.*;

public class MyContentSigner implements ContentSigner {
    public byte[] signJar(byte[] toSign, PrivateKey privateKey, Certificate[] certChain, String tsaUrl) throws Exception {
        // Custom signing logic (e.g., use a third-party crypto lib)
        Signature sig = Signature.getInstance("SHA256withRSA");
        sig.initSign(privateKey);
        sig.update(toSign);
        return sig.sign();
    }

    public String getDigestAlgorithm() {
        return "SHA-256";
    }

    public String getEncryptionAlgorithm() {
        return "RSA";
    }

    public String getSignatureAlgorithm() {
        return "SHA256withRSA";
    }
}
