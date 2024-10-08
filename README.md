# Overview
This repository is a downstream distribution of the https://github.com/konveyor/rulesets. 

This repository is a future home for Azure Migrate application and code assessment for Java (AppCAT for Java) static code analysis Rulesets.  It is intended to contain future rules to aid static code analysis as well as issues shared by subject matter experts to aid creation of richer Rulesets.
* Intended for [Rules](https://github.com/konveyor/analyzer-lsp/blob/main/docs/rules.md) compatible with [konveyor/analyzer-lsp](https://github.com/konveyor/analyzer-lsp)

## Please contribute knowledge
The project is working to consolidate learnings from modernization engagements into codified rules to help future users.  We are actively asking anyone willing to share to open an issue and describe a problem they encountered bringing a legacy application to Azure.  
   
### What is an example of the kind of information that would help for a Rule?
* Let's say you recently updated a legacy Java application that was using a distributed cache and you brought it to Azure Kubernetes but noticed that the new environment caused the discovery mechanism to break, so after bringing it to Azure Kubernetes you needed to update to a different library for discovery of other endpoints for the cache. 
    * The above would be the exact kind of information we are asking you to open an issue about.  Include as much or as little information as you have time.  If you can supply more information of library names, versions, error messages, or even samples of source code that would be excellent...yet even if time does not permit and the issue is simpler and just a few sentences describing the problem that would be of great use to the community.  We will investigate deeper and look to incorporate into future Rulesets.

## Background Info of the Upstream Project:
* Konveyor began as a project delivering Java code analysis via [Windup](https://github.com/windup/windup) with a large collection of Java related [Rulesets](https://github.com/windup/windup-rulesets)
* The Konveyor community is working to expand analysis support to multiple languages with the first deliverable targeting a [Summer 2023 Release](https://github.com/orgs/konveyor/projects/59) with analysis leveraging a new approach based on reusing [Language Servers](https://microsoft.github.io/language-server-protocol/implementors/servers/) that implement the [Language Server Protocol](https://microsoft.github.io/language-server-protocol/)
* Konveyor's new Analysis engine is located at: [konveyor/analyzer-lsp](https://github.com/konveyor/analyzer-lsp) 
  * As of ~Summer 2023 it is planned to support:
    * [Java](https://github.com/konveyor/analyzer-lsp/tree/main/provider/java) via [eclipse/eclipse.jdt.ls](https://github.com/eclipse/eclipse.jdt.ls) with a custom LSP extension: [konveyor/java-analyzer-bundle](https://github.com/konveyor/java-analyzer-bundle)

# Contributing

This project welcomes contributions and suggestions.  Most contributions require you to agree to a
Contributor License Agreement (CLA) declaring that you have the right to, and actually do, grant us
the rights to use your contribution. For details, visit https://cla.opensource.microsoft.com.

When you submit a pull request, a CLA bot will automatically determine whether you need to provide
a CLA and decorate the PR appropriately (e.g., status check, comment). Simply follow the instructions
provided by the bot. You will only need to do this once across all repos using our CLA.

This project has adopted the [Microsoft Open Source Code of Conduct](https://opensource.microsoft.com/codeofconduct/).
For more information see the [Code of Conduct FAQ](https://opensource.microsoft.com/codeofconduct/faq/) or
contact [opencode@microsoft.com](mailto:opencode@microsoft.com) with any additional questions or comments.

## Trademarks

This project may contain trademarks or logos for projects, products, or services. Authorized use of Microsoft 
trademarks or logos is subject to and must follow 
[Microsoft's Trademark & Brand Guidelines](https://www.microsoft.com/en-us/legal/intellectualproperty/trademarks/usage/general).
Use of Microsoft trademarks or logos in modified versions of this project must not cause confusion or imply Microsoft sponsorship.
Any use of third-party trademarks or logos are subject to those third-party's policies.
