package com.example.apps;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.cert.X509Certificate;

/**
 * Test data for CRA insecure TLS/SSL configuration detection rules.
 * Each method triggers specific rules.
 */
public class InsecureTlsExamples {

    // Rule cra-insecure-tls-00001: Outdated TLS protocol (TLSv1)
    public void useTlsV1() throws Exception {
        SSLContext sslContext = SSLContext.getInstance("TLSv1");
    }

    // Rule cra-insecure-tls-00001: Outdated SSL protocol
    public void useSsl() throws Exception {
        SSLContext sslContext = SSLContext.getInstance("SSL");
    }

    // Rule cra-insecure-tls-00002: Trust all certificates
    public void setupTrustAllCerts() throws Exception {
        TrustManager[] trustAllCerts = new TrustManager[] {
            new X509TrustManager() {
                public X509Certificate[] getAcceptedIssuers() { return null; }
                public void checkClientTrusted(X509Certificate[] certs, String authType) { }
                public void checkServerTrusted(X509Certificate[] certs, String authType) { }
            }
        };
        SSLContext sc = SSLContext.getInstance("TLSv1.2");
        sc.init(null, trustAllCerts, new java.security.SecureRandom());
    }

    // Rule cra-insecure-tls-00003: Disabled hostname verification using NoopHostnameVerifier
    public void disableHostnameVerification() {
        // Using NoopHostnameVerifier which always returns true
        HostnameVerifier verifier = NoopHostnameVerifier.INSTANCE;
        HttpsURLConnection.setDefaultHostnameVerifier(verifier);
    }

    // Rule cra-insecure-tls-00004: Custom SSLSocketFactory
    public void useCustomSslSocketFactory() throws Exception {
        SSLContext sslContext = SSLContext.getInstance("TLSv1.2");
        sslContext.init(null, null, null);
        SSLSocketFactory factory = sslContext.getSocketFactory();
        HttpsURLConnection connection = null;
        connection.setSSLSocketFactory(factory);
    }
}
