### azure ###
- 08-azure-password.windup.yaml: Xpath condition stops after finding a single match in a file https://github.com/konveyor/analyzer-lsp/issues/795
- 10-azure-system-config.windup.yaml：rules with METHOD_CALL locations do not work on calls to static methods https://github.com/konveyor/analyzer-lsp/issues/769
- 20-spring-boot-to-azure-spring-boot-version.windup.test.yaml: test command could not well handle multiple projects
- 21-spring-boot-to-azure-spring-cloud-version.windup.test.yaml: test command could not well handle multiple projects
- 09-azure-static-content.windup.yaml: comment this rule out, and we should improve this rule to make it less noisy:
    - we could skip the files that are called `index.html and index.html
    - we could look for HTML pages only located under src/main/resources 
    - we could look for HTML pages only located under src/main/webapp 

### cloud-readiness ###
- 06-jni-native-code.windup.yaml： rules with METHOD_CALL locations do not work on calls to static methods https://github.com/konveyor/analyzer-lsp/issues/769

### openjdk11 ###
- 01-java-removals.windup.yaml(java-removals-00000): rules with METHOD_CALL locations do not work on calls to static methods https://github.com/konveyor/analyzer-lsp/issues/769
- 01-java-removals.windup.test.yaml(removed-packages-00000): test passed locally but failed in CI.

### openjdk17 ###
- 05-security-manager-deprecation.windup.yaml(security-manager-deprecation-00020/0030/0040/0050): rules with METHOD_CALL locations do not work on calls to static methods https://github.com/konveyor/analyzer-lsp/issues/769
- 04-removed-packages.windup.test.yaml(removed-packages-00000): test passed locally but failed in CI.

### openjdk21 ###
- 01-deprecation-openjdk18.windup.yaml(deprecation-00005): rules with METHOD_CALL locations do not work on calls to static methods https://github.com/konveyor/analyzer-lsp/issues/769
- 04-deprecation-openjdk21.windup.yaml: rules with METHOD_CALL locations and with args do not work

### technology-usage ###
 
The following rule failed because builtin.hasTags is not working in test. An upstream fix is required.
- 03-web-technology-usage.windup.yaml
- 05-test-frameworks-technology-usage.windup.yaml
- 11-non-xml-rules-technology-usage.rhamt.yaml
- 14-markup-technology-usage.windup.yaml
- 20-javase-technology-usage.windup.yaml
- 26-embedded-framework-technology-usage.windup.yaml
- 28-ejb-technology-usage.windup.yaml
- 31-database-technology-usage.windup.yaml
- 35-configuration-management-technology-usage.windup.yaml
- 37-clustering-technology-usage.windup.yaml
- 39-apm-technology-usage.windup.yaml
- 45-3rd-party-spring-technology-usage.windup.yaml
 
Failed determine the cause of this failure. It may be helpful to track upstream updates in the future.
- 42-web.windup.yaml