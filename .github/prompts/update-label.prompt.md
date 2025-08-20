Analyze rules under these folder
- default/generated/azure
- default/generated/openjdk*,
- default/generated/cloud-readiness,
- default/generated/00-discovery,
- default/generated/technolog*

For each rule, there is a labels property, for example:
```
  labels:
  - konveyor.io/source=spring
  - target=azure-appservice
  - target=azure-aks
  - target=azure-container-apps
  - domain=java-upgrade
  - category=framework-upgrade
  - version
  - os=windows
  - os=linux
```

I'm care about three labels:
- target
- os
- capability

`- target` will point to "azure-aks", "azure-appservice", "azure-container-apps"
`- os` will point to "windows", "linux", it means your target migrate os
`- capability` will point to "cloud-readiness", "containerization", "openjdk11", "openjdk17", "openjdk21", or others like "openjdk", "openjdk8+", etc

Not all rules need these labels together.

The rules is used to detect issues for migrate on-premised applications to cloud environments.
You can analyze the rule according to the rule description, rule messages, rule condition and so on to know the rule

What I want:
- Check if `- target`, `- os`, `- capability` are appropriate for the detected issues.
- Update the label if it has some problems.

For example, if the rule is only work for azure-appservice, then `- target` should be "azure-appservice"

I'm not asking you to give me some script, i want you to help me analyze the rules and suggest appropriate labels based on the detected issues.