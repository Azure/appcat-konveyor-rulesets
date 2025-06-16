import java.security.cert.X509Certificate;
import java.io.FileInputStream;
import java.security.cert.CertificateFactory;
import java.security.cert.X509CRL;
import java.security.cert.X509CertSelector;
import java.security.cert.X509CRLSelector;

public class DeprecatedDNUsage {
    public static void main(String[] args) throws Exception {
        FileInputStream fis = new FileInputStream("certificate.crt");
        CertificateFactory cf = CertificateFactory.getInstance("X.509");
        X509Certificate cert = (X509Certificate) cf.generateCertificate(fis);

        // 🔴 Deprecated methods
        System.out.println("Issuer DN: " + cert.getIssuerDN());
        System.out.println("Subject DN: " + cert.getSubjectDN());

        FileInputStream fis = new FileInputStream("crlfile.crl");
        CertificateFactory cf = CertificateFactory.getInstance("X.509");
        X509CRL crl = (X509CRL) cf.generateCRL(fis);

        // 🔴 Deprecated method
        System.out.println("CRL Issuer DN: " + crl.getIssuerDN());

        X509CertSelector selector = new X509CertSelector();

        // 🔴 Deprecated methods
        selector.setIssuer("CN=Example CA");
        selector.setSubject("CN=Test Subject");
        System.out.println("Issuer: " + selector.getIssuerAsString());
        System.out.println("Subject: " + selector.getSubjectAsString());


        X509CRLSelector selector1 = new X509CRLSelector();
        // 🔴 Deprecated method
        selector1.addIssuerName("CN=Example CA");
    }
}
