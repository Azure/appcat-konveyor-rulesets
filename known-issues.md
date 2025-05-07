### azure ###
- 08-azure-password.yaml: Xpath condition stops after finding a single match in a file https://github.com/konveyor/analyzer-lsp/issues/795
- 10-azure-system-config.yaml：rules with METHOD_CALL locations do not work on calls to static methods https://github.com/konveyor/analyzer-lsp/issues/769
- 20-spring-boot-to-azure-spring-boot-version.test.yaml: test command could not well handle multiple projects
- 21-spring-boot-to-azure-spring-cloud-version.test.yaml: test command could not well handle multiple projects
- 09-azure-static-content.yaml: comment this rule out, and we should improve this rule to make it less noisy:
    - we could skip the files that are called `index.html and index.html
    - we could look for HTML pages only located under src/main/resources 
    - we could look for HTML pages only located under src/main/webapp 

### cloud-readiness ###
- 06-jni-native-code.yaml： rules with METHOD_CALL locations do not work on calls to static methods https://github.com/konveyor/analyzer-lsp/issues/769

### openjdk11 ###
- 01-java-removals.yaml(java-removals-00000): rules with METHOD_CALL locations do not work on calls to static methods https://github.com/konveyor/analyzer-lsp/issues/769
- 01-java-removals.test.yaml(removed-packages-00000): test passed locally but failed in CI.

### openjdk17 ###
- 05-security-manager-deprecation.yaml(security-manager-deprecation-00020/0030/0040/0050): rules with METHOD_CALL locations do not work on calls to static methods https://github.com/konveyor/analyzer-lsp/issues/769
- 04-removed-packages.test.yaml(removed-packages-00000): test passed locally but failed in CI.

### openjdk21 ###
- 01-deprecation-openjdk18.yaml(deprecation-00005): rules with METHOD_CALL locations do not work on calls to static methods https://github.com/konveyor/analyzer-lsp/issues/769
- 04-deprecation-openjdk21.yaml: rules with METHOD_CALL locations and with args do not work

### technology-usage ###
 
The following rule failed because builtin.hasTags is not working in test. An upstream fix is required.
- 03-web-technology-usage.yaml
- 05-test-frameworks-technology-usage.yaml
- 11-non-xml-rules-technology-usage.rhamt.yaml
- 14-markup-technology-usage.yaml
- 20-javase-technology-usage.yaml
- 26-embedded-framework-technology-usage.yaml
- 28-ejb-technology-usage.yaml
- 31-database-technology-usage.yaml
- 35-configuration-management-technology-usage.yaml
- 37-clustering-technology-usage.yaml
- 39-apm-technology-usage.yaml
- 45-3rd-party-spring-technology-usage.yaml
 
Failed determine the cause of this failure. It may be helpful to track upstream updates in the future.
- 42-web.yaml