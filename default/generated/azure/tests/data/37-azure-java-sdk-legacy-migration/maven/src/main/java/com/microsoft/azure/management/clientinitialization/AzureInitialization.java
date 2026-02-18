/**
 * https://github.com/Azure/azure-libraries-for-java/blob/b324fabb9ba2c9687614c800c6ae69e189ce990e/azure-mgmt-resources/src/test/java/com/microsoft/azure/management/resources/core/TestBase.java
 */
package com.microsoft.azure.management.clientinitialization;

import com.microsoft.azure.AzureEnvironment;
import com.microsoft.azure.AzureResponseBuilder;
import com.microsoft.aad.adal4j.AdalAuthorizationGrant;
import com.microsoft.azure.batch.AccountOperations;
import com.microsoft.azure.cognitiveservices.vision.computervision.ComputerVision;
import com.microsoft.azure.cosmosdb.rx.AsyncDocumentClient;
import com.microsoft.azure.documentdb.AccessCondition;
import com.microsoft.azure.eventgrid.DomainCredentials;
import com.microsoft.azure.eventhubs.BatchOptions;
import com.microsoft.azure.eventprocessorhost.BaseLease;
import com.microsoft.azure.keyvault.CertificateIdentifier;
import com.microsoft.azure.management.batch.implementation.BatchManager;
import com.microsoft.azure.servicebus.ClientFactory;
import com.microsoft.azure.applicationinsights.query.ApplicationInsightsDataClient;
import com.microsoft.azure.loganalytics.LogAnalyticsDataClient;
import com.microsoft.azure.credentials.ApplicationTokenCredentials;
import com.microsoft.azure.management.Azure;
import com.microsoft.windowsazure.services.blob.BlobConfiguration;
import com.microsoft.azure.serializer.AzureJacksonAdapter;
import com.microsoft.rest.LogLevel;
import com.microsoft.rest.RestClient;

import com.microsoft.azure.javamsalruntime.Account;
import com.microsoft.azure.management.resources.core.ResourceGroupTaggingInterceptor;
import com.microsoft.azure.management.resources.fluentcore.utils.ProviderRegistrationInterceptor;

import java.util.concurrent.TimeUnit;

public class AzureInitialization {
    public static void main(String[] args) {
        String clientId = System.getenv("AZURE_CLIENT_ID");
        String tenantId = System.getenv("AZURE_TENANT_ID");
        String clientSecret = System.getenv("AZURE_CLIENT_SECRET");
        String subscriptionId = System.getenv("AZURE_SUBSCRIPTION_ID");
        if (clientId == null || tenantId == null || clientSecret == null || subscriptionId == null) {
            throw new IllegalArgumentException("When running tests in record mode either 'AZURE_AUTH_LOCATION' or 'AZURE_CLIENT_ID, AZURE_TENANT_ID, AZURE_CLIENT_SECRET and AZURE_SUBSCRIPTION_ID' needs to be set");
        }

        ApplicationTokenCredentials credentials = new ApplicationTokenCredentials(clientId, tenantId, clientSecret, AzureEnvironment.AZURE);
        credentials.withDefaultSubscriptionId(subscriptionId);

        String baseUrl = credentials.environment().url(AzureEnvironment.Endpoint.RESOURCE_MANAGER);
        RestClient.Builder builder = new RestClient.Builder()
            .withBaseUrl(baseUrl)
            .withSerializerAdapter(new AzureJacksonAdapter())
            .withResponseBuilderFactory(new AzureResponseBuilder.Factory())
            .withInterceptor(new ProviderRegistrationInterceptor(credentials))
            .withNetworkInterceptor(new ResourceGroupTaggingInterceptor())
            .withCredentials(credentials)
            .withLogLevel(LogLevel.BODY_AND_HEADERS)
            .withReadTimeout(3, TimeUnit.MINUTES);

        Azure.Authenticated azureAuthed = Azure.authenticate(builder.build(), subscriptionId, credentials.domain());
        Azure azure = azureAuthed.withSubscription(subscriptionId);

        BatchManager batchManager = BatchManager.configure()
            .withLogLevel(LogLevel.BASIC)
            .withInterceptor(new ProviderRegistrationInterceptor(credentials))
            .authenticate(credentials, subscriptionId);
    }
}
