import java.security.spec.PSSParameterSpec;

import javax.crypto.spec.OAEPParameterSpec;

public class DeprecatedPSSUsage {
    public static void main(String[] args) {
        // Using deprecated default constant
        PSSParameterSpec defaultSpec = PSSParameterSpec.DEFAULT;

        // Using deprecated constructor with only salt length
        PSSParameterSpec legacySpec = new PSSParameterSpec(20);

        System.out.println("Default spec: " + defaultSpec);
        System.out.println("Legacy spec: " + legacySpec);

        // Uses SHA-1 and outdated MGF settings
        OAEPParameterSpec spec = OAEPParameterSpec.DEFAULT;

        System.out.println("Legacy OAEPParameterSpec: " + legacySpec);
    }
}
