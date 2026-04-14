package com.example.cra;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.cert.X509Certificate;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.AllowAllHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;

/**
 * Test data for CRA insecure TLS/SSL detection rules.
 */
public class InsecureTlsExamples {

    // === Insecure TLS Protocol Versions (cra-insecure-tls-protocol-01000) ===

    public SSLContext getSslContext() throws Exception {
        // Detected: SSLContext.getInstance("SSL")
        return SSLContext.getInstance("SSL");
    }

    public SSLContext getSslv3Context() throws Exception {
        // Detected: SSLContext.getInstance("SSLv3")
        return SSLContext.getInstance("SSLv3");
    }

    public SSLContext getTls10Context() throws Exception {
        // Detected: SSLContext.getInstance("TLSv1")
        return SSLContext.getInstance("TLSv1");
    }

    public SSLContext getTls11Context() throws Exception {
        // Detected: SSLContext.getInstance("TLSv1.1")
        return SSLContext.getInstance("TLSv1.1");
    }

    public SSLContext getGenericTlsContext() throws Exception {
        // Detected: SSLContext.getInstance("TLS") - may negotiate down to TLSv1.0
        return SSLContext.getInstance("TLS");
    }

    public void configureInsecureProtocols(SSLSocket socket) {
        // Detected: setEnabledProtocols with SSLv3
        socket.setEnabledProtocols(new String[]{"SSLv3", "TLSv1"});
    }

    public void configureInsecureTls11(SSLSocket socket) {
        // Detected: setEnabledProtocols with TLSv1.1
        socket.setEnabledProtocols(new String[]{"TLSv1.1", "TLSv1.2"});
    }

    // === Trust All Certificates (cra-insecure-tls-trust-all-03000) ===

    // Detected: implements X509TrustManager (trust-all pattern)
    public static class TrustAllCerts implements X509TrustManager {
        public X509Certificate[] getAcceptedIssuers() { return null; }
        public void checkClientTrusted(X509Certificate[] certs, String authType) {}
        public void checkServerTrusted(X509Certificate[] certs, String authType) {}
    }

    public void useNoopHostnameVerifier() throws Exception {
        // Detected: NoopHostnameVerifier
        HttpsURLConnection conn = (HttpsURLConnection) new java.net.URL("https://example.com").openConnection();
        conn.setHostnameVerifier(NoopHostnameVerifier.INSTANCE);
    }

    public void useAllowAllHostnameVerifier() {
        // Detected: ALLOW_ALL_HOSTNAME_VERIFIER
        SSLConnectionSocketFactory factory = new SSLConnectionSocketFactory(
            (javax.net.ssl.SSLContext) null,
            SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER
        );
    }

    // === Hostname Verification Disabled (cra-insecure-tls-hostname-verify-04000) ===

    // Detected: HostnameVerifier lambda returning true
    HostnameVerifier allHostsValid = (hostname, session) -> true;

    // === Insecure Cipher Suites (cra-insecure-tls-cipher-suite-05000) ===

    public void configureInsecureCiphers(SSLSocket socket) {
        // Detected: SSL_RSA_WITH_NULL, SSL_RSA_WITH_DES, TLS_RSA_WITH_RC4
        socket.setEnabledCipherSuites(new String[]{
            "SSL_RSA_WITH_NULL_MD5",
            "SSL_RSA_WITH_DES_CBC_SHA",
            "TLS_RSA_WITH_RC4_128_SHA",
            "SSL_DH_anon_WITH_3DES_EDE_CBC_SHA",
            "TLS_RSA_EXPORT_WITH_RC4_40_MD5"
        });
    }

    // === Safe usage (should NOT trigger rules) ===

    public SSLContext getSecureTlsContext() throws Exception {
        return SSLContext.getInstance("TLSv1.3");
    }

    public SSLContext getSecureTls12Context() throws Exception {
        return SSLContext.getInstance("TLSv1.2");
    }
}
