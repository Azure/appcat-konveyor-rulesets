package com.example.apps;

import org.ietf.jgss.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class DeprecatedGSSContextUsage {

    public void useDeprecatedMethods(GSSContext context, byte[] token) throws Exception {
        // Using InputStream forms (deprecated as of JDK 21)
        InputStream tokenStream = new ByteArrayInputStream(token);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        MessageProp messageProp = new MessageProp(0, true);

        context.initSecContext(tokenStream, outputStream); // Deprecated

        // Similarly deprecated:
        context.acceptSecContext(tokenStream, outputStream); // Deprecated

        context.wrap(tokenStream, outputStream, messageProp); // Deprecated
        context.unwrap(tokenStream, outputStream, messageProp); // Deprecated

        context.getMIC(tokenStream, outputStream, messageProp); // Deprecated
        context.verifyMIC(tokenStream, tokenStream, messageProp); // Deprecated
    }
}
