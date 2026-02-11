package com.example;

import com.microsoft.azure.batch.AccountOperations;
import com.microsoft.azure.keyvault.CertificateIdentifier;
import com.microsoft.azure.servicebus.ClientFactory;
import com.microsoft.azure.management.Azure;

// Non-legacy import that should NOT trigger alerts
import com.microsoft.azure.msal4j.PublicClientApplication;

public class AzureLegacyService {
    public void init() {
        System.out.println("Using legacy Azure SDKs");
    }
}
