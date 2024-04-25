# Overview
This reporsitory is a downstream distribution of the https://github.com/konveyor/rulesets. 

This repository is a future home for Azure Migrate application and code assessment for Java (AppCAT for Java) static code analysis Rulesets.  It is intended to contain future rules to aid static code analysis as well as issues shared by subject matter experts to aid creation of richer Rulesets.
* Intended for [Rules](https://github.com/konveyor/analyzer-lsp/blob/main/docs/rules.md) compatible with [konveyor/analyzer-lsp](https://github.com/konveyor/analyzer-lsp)

## Please contribute knowledge
The project is working to consolidate learnings from modernization engagements into codified rules to help future users.  We are actively asking anyone willing to share to open an issue and describe a problem they encountered bringing a legacy application to Azure.  

* Examples to show a few highlights of areas to consider:
  * OpenJDK11 framework to OpenJDK17
  * Legacy Java to Azure Spring Apps
  * Legacy Java to Azure Appservices
   
### What is an example of the kind of information that would help for a Rule?
* Let's say you recently updated a legacy Java application that was using a distributed cache and you brought it to Kubernetes but noticed that the new environment caused the discovery mechanism to break, so after bringing it to Kubernetes you needed to update to a different library for discovery of other endpoints for the cache. 
    * The above would be the exact kind of information we are asking you to open an issue about.  Include as much or as little information as you have time.  If you can supply more information of library names, versions, error messages, or even samples of source code that would be excellent...yet even if time does not permit and the issue is simpler and just a few sentences describing the problem that would be of great use to the community.  We will investigate deeper and look to incorporate into future Rulesets.
## User Group
For those interested, we have a [user group focused on improving the migration experience](https://github.com/konveyor/community/tree/main/ug-migration-experience) which is helping to share information on problems and solutions from their modernization engagements.  
* Please consider joining the email group: konveyor-ug-mig-exp@googlegroups.com
  * Join URL:  https://groups.google.com/g/konveyor-ug-mig-exp
* Or chat with us in kubernetes.slack.com:  (Get a slack invite via: https://slack.k8s.io/)
  * #[konveyor](https://kubernetes.slack.com/archives/CR85S82A2): General questions and discussions
  * #[konveyor-dev](https://kubernetes.slack.com/archives/C04QZJFQ0UA): Konveyor development related discussions

## Background Info of the Upstream Project:
* Konveyor began as a project delivering Java code analysis via [Windup](https://github.com/windup/windup) with a large collection of Java related [Rulesets](https://github.com/windup/windup-rulesets)
* The Konveyor community is working to expand analysis support to multiple languages with the first deliverable targeting a [Summer 2023 Release](https://github.com/orgs/konveyor/projects/59) with analysis leveraging a new approach based on reusing [Language Servers](https://microsoft.github.io/language-server-protocol/implementors/servers/) that implement the [Language Server Protocol](https://microsoft.github.io/language-server-protocol/)
* Konveyor's new Analysis engine is located at: [konveyor/analyzer-lsp](https://github.com/konveyor/analyzer-lsp) 
  * As of ~Summer 2023 it is planned to support:
    * [Java](https://github.com/konveyor/analyzer-lsp/tree/main/provider/java) via [eclipse/eclipse.jdt.ls](https://github.com/eclipse/eclipse.jdt.ls) with a custom LSP extension: [konveyor/java-analyzer-bundle](https://github.com/konveyor/java-analyzer-bundle)
    * [Go](https://github.com/konveyor/analyzer-lsp/tree/main/provider/golang) via [golang/tools/gopls](https://github.com/golang/tools/tree/master/gopls)
  * Post Summer 2023
    * .Net: A C# analyzer is next priority with language support via [OmniSharp/csharp-language-server-protocol](https://github.com/OmniSharp/csharp-language-server-protocol)
 * Spring 2023:
   * Until the [Summer 2023 Release](https://github.com/orgs/konveyor/projects/59) is available, Konveyor will continue to rely on [Windup](https://github.com/windup/windup) for Java analysis 
     * Rules written with the Windup syntax will remain compatible with the new [konveyor/analyzer-lsp](https://github.com/konveyor/analyzer-lsp) via conversion tool being developed at [fabianvf/windup-rulesets-yaml/blob/master/main.go](https://github.com/fabianvf/windup-rulesets-yaml/blob/master/main.go)

## Code of Conduct
Refer to Konveyor's Code of Conduct [here](https://github.com/konveyor/community/blob/main/CODE_OF_CONDUCT.md).

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
