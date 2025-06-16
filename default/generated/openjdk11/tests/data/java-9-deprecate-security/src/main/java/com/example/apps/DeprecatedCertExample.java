import javax.security.cert.X509Certificate;
import javax.security.cert.CertificateFactory;
import java.io.FileInputStream;

import java.security.acl.Acl;
import java.security.acl.Permission;
import java.security.acl.AclEntry;
import java.security.acl.Owner;

import com.sun.security.auth.callback.DialogCallbackHandler;

import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.login.LoginContext;

public class DeprecatedCertExample {
    public static void main(String[] args) throws Exception {
        FileInputStream fis = new FileInputStream("certificate.cer");
        CertificateFactory cf = CertificateFactory.getInstance("X.509");
        X509Certificate cert = (X509Certificate) cf.generateCertificate(fis);
        System.out.println("Issuer DN: " + cert.getIssuerDN());
        fis.close();

        CallbackHandler handler = new DialogCallbackHandler();
        LoginContext lc = new LoginContext("MyLoginConfig", handler);
        lc.login();
    }
}
