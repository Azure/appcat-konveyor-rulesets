import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.security.cert.X509Certificate;

public class DeprecatedPeerCertificateChainExample {
    public static void main(String[] args) throws Exception {
        SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();
        try (SSLSocket socket = (SSLSocket) factory.createSocket("example.com", 443)) {
            socket.startHandshake();
            SSLSession session = socket.getSession();

            // ❌ Deprecated method (now removed in JDK)
            X509Certificate[] chain = session.getPeerCertificateChain(); 
            for (X509Certificate cert : chain) {
                System.out.println("Subject: " + cert.getSubjectDN());
            }
        }

        // ❌ This will no longer work — provider removed
        Security.addProvider((java.security.Provider)
            Class.forName("com.sun.net.ssl.internal.ssl.Provider").newInstance());

        SSLContext context = SSLContext.getInstance("TLS", "SunJSSE");
        System.out.println("SSLContext created with legacy provider.");
    }
}
