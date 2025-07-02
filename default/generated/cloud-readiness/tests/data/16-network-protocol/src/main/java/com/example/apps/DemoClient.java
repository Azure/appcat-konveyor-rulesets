package com.example;

// Unsecured Apache HTTP client imports (Detected by java.referenced: IMPORT)
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

// Deprecated internal Sun classes (Detected by java.referenced: IMPORT)
import sun.net.www.http.HttpClient;

import java.io.IOException;

public class DemoClient {
    public static void main(String[] args) throws IOException {
        // Unsecured URL usage in Java code (Detected by builtin.filecontent)
        String endpoint = "http://insecure.example.com/api/data";
        String ftpResource = "ftp://fileserver.example.com/resource.txt";

        String hhtpsEndpoint = "https://insecure.example.com/api/data";
        String ldapEndpoint = "ldap://hostname";
        String lkdapsEndpoint = "ldaps://host/org?cn,sn?base?(uid=admin)";

        System.out.println("Attempting to reach: " + endpoint);
        System.out.println("Fetching file from: " + ftpResource);

        // Use of Apache CloseableHttpClient (potentially insecure)
        CloseableHttpClient client = HttpClients.createDefault();
        client.close();
    }
}
