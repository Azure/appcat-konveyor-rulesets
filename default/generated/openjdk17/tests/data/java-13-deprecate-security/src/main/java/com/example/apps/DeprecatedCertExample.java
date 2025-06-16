import javax.security.cert.X509Certificate;

public class DeprecatedCertExample {
    public static void main(String[] args) {
        try {
            // Simulated DER-encoded certificate bytes
            byte[] certBytes = getFakeCertBytes();

            // ❌ Deprecated: This uses javax.security.cert.X509Certificate
            X509Certificate cert = X509Certificate.getInstance(certBytes);
            System.out.println("Issuer: " + cert.getIssuerDN());
        } catch (javax.security.cert.CertificateException e) {
            e.printStackTrace();
        }
    }

    private static byte[] getFakeCertBytes() {
        // Normally you'd read this from a real certificate file
        return new byte[]{};
    }
}
