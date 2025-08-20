# Contributing to AppCAT Konveyor Rulesets

Thank you for your interest in contributing to the Azure Migrate Application and Code Assessment for Java (AppCAT for Java) static code analysis rulesets! This document provides comprehensive guidelines for contributing to this project.

We welcome all contributions, whether they're issues, code, documentation, or suggestions for new features. Below, you'll find our guidelines for contributing to AppCAT Konveyor Rulesets. This document provides comprehensive guidelines for contributing to this project.


## Table of Contents
1. [Code of Conduct](#code-of-conduct)
2. [How Can You Contribute?](#how-can-you-contribute)
3. [Getting Started](#getting-started)
4. [Rule Creation Guidelines](#rule-creation-guidelines)
5. [Submitting an Issue](#submitting-an-issue)
6. [Submitting a Pull Request](#submitting-a-pull-request)
7. [Contribution Process](#contribution-process)
8. [Legal Requirements](#legal-requirements)

---

### Code of Conduct
This project follows Microsoft's [Open Source Code of Conduct](https://opensource.microsoft.com/codeofconduct/). By participating, you agree to abide by its terms.

### How Can You Contribute?
Here are some ways you can help:
- **New rules**: You can contribute to the AppCAT Rulesets project with any new rules you might have come up with for doing
your own migrations, particularly focusing on Azure migration scenarios and technologies.
- **Feature Requests**: You can request new rules for existing technologies not yet covered by our rulesets.
- **Bug fixes**: Fix bugs in rules, or create new tests for uncovered scenarios.
- **Documentation**: Improve our documentation, tutorials, or guides.

### Getting Started

To begin contributing to AppCAT rulesets, follow these steps:

1. **Fork and Clone**: Fork this repository and create a new feature branch from `main`.

2. **Set up Development Environment**: Install Kantra CLI tool following the [Setting up Kantra and developing rules](https://github.com/konveyor/kantra/blob/main/docs/rules-quickstart.md#how-to-install-kantra) guide.

3. **Validate Your Setup**: Run existing rule tests using Kantra to ensure your environment is properly configured:
   ```bash
   kantra test /path/to/a/single/tests/file.test.yaml
   ```
   > **Note**: This will be replaced by AppCAT command in future releases.

4. **Learn Rule Development**: For comprehensive rule creation knowledge, review:
   - [Konveyor Rule Writing Quickstart Guide](https://github.com/konveyor/kantra/blob/main/docs/rules-quickstart.md)
   - [Rule Documentation](https://github.com/konveyor/analyzer-lsp/blob/main/docs/rules.md)
   - [Test Runner for YAML rules](https://github.com/konveyor/kantra/blob/main/docs/testrunner.md)

5. **Develop and Test**: Create your changes following our [Rule Creation Guidelines](#rule-creation-guidelines), write comprehensive tests, and validate your work.

### Rule Creation Guidelines
There are a few conventions that we follow when creating rules:

#### Directory structure
Rules can be found within the `default/generated` folder. Within this folder, rules are organized by target technology. We use the following labels to clarify the rule intent:

- `target`: the Azure service the rule applies to (for example `azure-aks`, `azure-container-apps`, `azure-appservice`).
- `capability`: the optimization goal or technical constraint the rule targets (for example `openjdk11`, `openjdk17`, `openjdk21`, `containerization`).
- `os`: the target operating system or platform (for example `windows`, `linux`).

The `source` label is used where relevant to indicate the original/source technology.

Additionally, within each rules directory, a `test` directory should be created to place the rules's tests. Since each
test needs data, a `data` directory must also be created inside `test`. For instance:

```shell
.
└── target
    ├── source1
    │   ├── rules-1.yaml
    │   ├── rules-2.yaml
    │   └── tests
    │       ├── data
    │       │   ├── rules-1
    │       │   └── rules-2
    │       ├── rules-1.test.yaml
    │       └── rules-2.test.yaml
    └── source2
        └── ...
```
Inside of each `data` folder, it is recommended to segregate the test data, so that each folder within it contains the
data for the tests of a single rules file. This way, other tests will be less affected by changes in the data.

#### Tests organization and file naming
- It is recommended that a single rules file holds rules for the migration of a related set of concerns within its particular
migration path. For instance, if migration from `A` to `B` implies changes related to data sources and web services,
two different rule files should be created, one containing the rules related to data sources and another one for web services. 
This makes it easier to find rules, create tests and data for them, and keep the structure organized in general.

- Rules should be named using hypens for word separation. Also, names for the rule files should indicate the target
technology and the concern of the rules they contain. If needed, they can also indicate the source technology.

- Test files should be named after the rule file they cover in the following way: `<rule-file>.test.yaml`

#### Rule IDs
Rule IDs must be unique within the rulesets being used, and contain in themselves the name of the rule file plus a
number. These numbers must have 5 digits, to follow a convention set in the old Windup rules.

#### Target Technologies
AppCAT currently exposes labels for rules in three groups: `target` (Azure services), `capability` (optimization goals / technical constraints), and `os` (target platforms). When writing rules, specify one or more of these labels as appropriate.

- target:
  - `azure-aks`
  - `azure-container-apps`
  - `azure-appservice`

- capability:
  - `openjdk11`
  - `openjdk17`
  - `openjdk21`
  - `containerization`

- os:
  - `windows`
  - `linux`

#### Domain and Category

AppCAT uses special labels `domain` and `category` for display and organization purposes (note: `source-category` has been deprecated). Please assign each rule an appropriate domain and category to ensure proper classification and optimal user experience. Repository owners will review and finalize the domain and category assignments during the code review process.

### Submitting an Issue
- Search existing issues to avoid duplicates.
- Provide as much detail as possible, including steps to reproduce.
- Label the issue appropriately (bug, enhancement, question, etc.).

### Submitting a Pull Request
- Ensure your branch is up-to-date with `main`.
- Make sure all tests pass before submitting.
- Include a detailed description of your changes.
- Reference related issues in the description (e.g., `Fixes #123`).

### Contribution Process

#### Adding New Rules - Special Requirements

When contributing new rules, additional steps are required for integration with the AppCAT ecosystem:

**For contributors with access to the Azure Java Migration Copilot VS Code Extension repository:**
- Navigate to the [Azure Java Migration Copilot VS Code Extension repository](https://github.com/devdiv-azure-service-dmitryr/azure-java-migration-copilot-vscode-extension)
- Add a rule entry in the [solution mapping](https://github.com/devdiv-azure-service-dmitryr/azure-java-migration-copilot-vscode-extension/blob/main/src/model/assess/solution-mapping.json) and link it to an existing chat type solution, or create a new solution with Chat type if needed. [Example PR](https://github.com/devdiv-azure-service-dmitryr/azure-java-migration-copilot-vscode-extension/pull/1760)
- Create an issue in the repo with the new rule information, and assign it to menghua. [Example Issue](https://github.com/devdiv-azure-service-dmitryr/azure-java-migration-copilot-vscode-extension/issues/1874)

**For external contributors without Azure Java Migration Copilot VS Code Extension repository access:**
- Create an issue in this ruleset repository with the label `integration-request`
- Include the Rule ID(s) of your new rule(s) and any relevant integration requirements or preferences
- Repository maintainers will handle the VS Code Extension integration process on your behalf

#### Review Criteria
All pull requests must meet these standards before merged:

**Technical Requirements:**
- All tests pass
- New rules include comprehensive tests
- Code follows naming conventions
- YAML syntax is valid and properly formatted
- Rule descriptions are clear and actionable
- Rule domain and category are appropriate and well-defined
- At least one team member from AppCAT team must review and approve

## Legal Requirements

### Contributor License Agreement (CLA)
This project welcomes contributions and suggestions. Most contributions require you to agree to a Contributor License Agreement (CLA) declaring that you have the right to, and actually do, grant us the rights to use your contribution. For details, visit https://cla.microsoft.com.

When you submit a pull request, a CLA-bot will automatically determine whether you need to provide a CLA and decorate the PR appropriately (e.g., label, comment). Simply follow the instructions provided by the bot. You will only need to do this once across all repositories using our CLA.
