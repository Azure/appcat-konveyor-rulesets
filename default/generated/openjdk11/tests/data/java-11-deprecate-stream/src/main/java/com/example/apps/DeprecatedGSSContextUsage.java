import org.ietf.jgss.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class DeprecatedGSSContextUsage {

    public void useDeprecatedMethods(GSSContext context, byte[] token) throws Exception {
        // Using InputStream forms (deprecated as of JDK 21)
        InputStream tokenStream = new ByteArrayInputStream(token);

        byte[] outputToken = context.initSecContext(tokenStream, token.length); // Deprecated
        System.out.println("initSecContext output token: " + outputToken.length);

        // Similarly deprecated:
        context.acceptSecContext(tokenStream, token.length); // Deprecated

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        context.wrap(tokenStream, outputStream); // Deprecated
        context.unwrap(tokenStream, outputStream); // Deprecated

        context.getMIC(tokenStream, outputStream); // Deprecated
        context.verifyMIC(tokenStream, tokenStream); // Deprecated
    }
}
