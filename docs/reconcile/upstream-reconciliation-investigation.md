# Upstream Reconciliation Investigation and Plan

## Table of Contents

- [Executive Summary](#executive-summary)
- [Current State Analysis](#current-state-analysis)
  - [Repository Divergence](#repository-divergence)
- [Detailed File and Rule Analysis](#detailed-file-and-rule-analysis)
  - [Summary Statistics (Comprehensive Rule Analysis)](#summary-statistics-comprehensive-rule-analysis)
  - [Key Changes Analysis](#key-changes-analysis)
- [Cost and Effort Analysis](#cost-and-effort-analysis)
  - [1. Contributing Our Rules to Upstream](#1-contributing-our-rules-to-upstream)
  - [2. Merging Upstream Changes to Our Fork](#2-merging-upstream-changes-to-our-fork)
- [Example Contribution Process](#example-contribution-process)
  - [Sample Rule Contribution: Azure Database Migration Rule](#sample-rule-contribution-azure-database-migration-rule)
- [Appendix: Detailed Rule ID Inventories for Modified Folders](#appendix-detailed-rule-id-inventories-for-modified-folders)
  - [Modified Folders Scope](#modified-folders-scope)
  - [A.1 New Rules Added in Fork (241 total)](#a1-new-rules-added-in-fork-241-total)
  - [A.2 Upstream Rules Not Synced to Fork (94 total)](#a2-upstream-rules-not-synced-to-fork-94-total)
---

## Executive Summary

This investigation analyzes the effort and cost to reconcile our Azure AppCAT ruleset fork with the upstream Konveyor repository. The main challenges are:

1. **Contributing our Azure rules back to upstream** - significant effort required
2. **Merging upstream changes** - extensive conflicts due to divergent development
3. **Ruleset definition differences** - structural and format changes between repositories

## Current State Analysis

### Repository Divergence
- **Fork commits ahead:** 262 commits (massive divergence from upstream)
- **Upstream commits ahead:** 7 commits with recent improvements
- **Key Issue:** Significant structural differences in ruleset definitions and labeling conventions

## Detailed File and Rule Analysis

### Summary Statistics (Comprehensive Rule Analysis)
| Category | Fork Rules | Upstream Rules | New Rules | Missing Upstream / Delete Rules |
|----------|------------|----------------|-----------|------------------|
| **Azure Rules** | 88 | 47 | +70 | 29  |
| **Cloud Readiness** | 58 | 39 | +36 | 17  |
| **OpenJDK 7** | 7 | 7 | +0 | 0  |
| **OpenJDK 8** | 14 | 0 | +14 | 0  |
| **OpenJDK 11** | 41 | 17 | +39 | 15 |
| **OpenJDK 17** | 30 | 13 | +27 | 10 |
| **OpenJDK 21** | 34 | 16 | +30 | 12 |
| **OS Compatibility** | 2 | 2 | +0 | 0|
| **Technology Usage** | 698 | 684 | +25 | 11  | 
| **Total** | **972** | **825** | **+241** | **94** |

> "Note": The above data only compares rules located in the same folder between the AppCAT ruleset and the upstream rulesets.
Some insight rules have been moved by AppCAT from the technology-usage folder to other folders such as azure (for example, apm-0001). Although these rules exist in the upstream, they are treated as new rules when calculating Azure-related rules.
Meanwhile, some rules in AppCAT have updated their ruleId. Since the current comparison is based only on the ruleId rather than the full rule content, these rules are also recognized as new.

### Key Changes Analysis

#### **1. Format and Labeling Transformation (Universal)**
- **File naming:** `.windup.yaml` → `.yaml` format across all modified folders
- **Label system overhaul:**
  - **Removed:** `konveyor.io/source=*` and `konveyor.io/target=*` prefixes
  - **Added:** Direct labels, E.g. `target=azure-aks`, `capability=openjdk11`, `os=windows`
  - **Enhanced:** `domain` and `category` labels for dashboard integration
  - **Improved:** More specific source technology labels

#### **2. Content Enhancements**
- **Add rules:** Add more rules, work with app modernization
- **Update rules:**
    - **Effort scoring:** More accurate effort estimations 
    - **Message content:** Detailed migration guidance with step-by-step instructions
    - **Rule conditions:** More precise detection patterns and file matching
    - **Link additions:** Comprehensive Microsoft Learn documentation links

#### **3. Modified Folders**

Only rules under these folders has been updated, others are directlty sync from upstream:

- default/generated/azure
- default/generated/cloud-readiness
- default/generated/openjdk*
- default/generated/os
- default/generated/technology-usage

See [Appendix](#appendix-detailed-rule-id-inventories-for-modified-folders) for more Add/not-sync rules.

## Cost and Effort Analysis

### **1. Contributing Our Rules to Upstream**

- Label Convention Migration
- File Naming Standardization
- Content Review and Documentation
- Submit PR 

**Total Estimated Effort:** **1-2 weeks/1 developer** 

### **2. Merging Upstream Changes to Our Fork**

- Merge upstream
- Resolve conflict
- File naming and Content change
- Add Tests
- PR review

**Total Estimated Effort:** **2-3 weeks / 1 developer** 

## Example Contribution Process

### **Sample Rule Contribution: Azure Database Migration Rule**

#### **Step 1: Rule Format Conversion**

**Current Fork Format:**
```yaml
- category: mandatory
  description: PostgreSQL JDBC driver detected
  effort: 5
  labels:
  - target=azure-aks
  - target=azure-container-apps  
  - target=azure-appservice
  - domain=database
  - category=jdbc-driver-migration
  - os=windows
  - os=linux
  ruleID: azure-database-postgresql-01000
  message: |-
    PostgreSQL JDBC driver detected. Consider migrating to Azure Database for PostgreSQL.
    
    **Migration Steps:**
    1. Provision Azure Database for PostgreSQL Flexible Server
    2. Update connection strings to use Azure endpoint
    3. Configure SSL/TLS connections for security
    4. Implement Azure AD authentication if needed
```

**Required Upstream Format:**
```yaml
- category: mandatory  
  description: PostgreSQL JDBC driver detected
  effort: 5
  labels:
  - konveyor.io/source=postgresql
  - konveyor.io/target=azure-aks
  - konveyor.io/target=azure-container-apps
  - konveyor.io/target=azure-appservice
  - database
  - jdbc
  ruleID: azure-database-postgresql-01000
  message: |-
    PostgreSQL JDBC driver detected. Consider migrating to Azure Database for PostgreSQL.
    
    **Migration Steps:**
    1. Provision Azure Database for PostgreSQL Flexible Server  
    2. Update connection strings to use Azure endpoint
    3. Configure SSL/TLS connections for security
    4. Implement Azure AD authentication if needed
```

#### **Step 2: File Naming Conversion**
- **Current:** `24-azure-database.yaml`
- **Required:** `24-azure-database.windup.yaml`

#### **Step 3: Test File Updates**
- **Current:** `24-azure-database.test.yaml`
- **Required:** `24-azure-database.windup.test.yaml`

#### **Step 4: Contribution Submission**
1. **Create feature branch:** `feature/azure-database-migration-rules`
2. **Submit targeted PR:** Focus on database migration rules only
3. **Provide documentation:** 
   - Rule purpose and scope
   - Test coverage report
   - Azure migration scenarios covered
4. **Community engagement:** Respond to review feedback promptly

---

## Appendix: Detailed Rule ID Inventories for Modified Folders

This appendix lists specific rule IDs in the modified folders, categorized by **New Rules Added in Fork** and **Upstream Rules Not Synced**.

### **Modified Folders Scope**
- `default/generated/azure`
- `default/generated/cloud-readiness`
- `default/generated/openjdk*`
- `default/generated/os`
- `default/generated/technology-usage`

## **A.1 New Rules Added in Fork (241 total)**

### **A.1.1 Azure Rules (70 new rules)**
**APM Integration Rules:**
- apm-00001, apm-00002, apm-00003

**Authentication Rules:**
- auth-00000, auth-01000, auth-02000, auth-03000, auth-04000

**Cache Migration Rules:**
- azure-cache-redis-01000

**Database Migration Rules:**
- azure-database-config-mongodb-02000, azure-database-microsoft-cassandra-04000, azure-database-microsoft-mariadb-06000
- azure-database-microsoft-mongodb-05000, azure-database-microsoft-oracle-07000, azure-database-microsoft-sql-03000
- azure-database-mysql-01000, azure-database-postgresql-02000, database-reliability-01000

**Message Queue Migration Rules:**
- azure-message-queue-activemq-01000, azure-message-queue-amqp-02000, azure-message-queue-config-artemis-01000
- azure-message-queue-config-kafka-01000, azure-message-queue-config-rabbitmq-01000, azure-message-queue-rabbitmq-01000
- azure-message-queue-spring-jms-rabbitmq-01000

**OpenLiberty Integration Rules:**
- openliberty-database-00001, openliberty-database-00002, openliberty-database-00003, openliberty-database-00004
- openliberty-database-00005, openliberty-database-00006, openliberty-database-00007, openliberty-database-00008
- openliberty-database-00009, openliberty-filesystem-00001, openliberty-filesystem-00002, openliberty-filesystem-00003
- openliberty-filesystem-00004, openliberty-jms-00001, openliberty-jms-00002, openliberty-jms-00003
- openliberty-jms-00004, openliberty-jms-00005, openliberty-jms-00006, openliberty-logging-00001, openliberty-logging-00002

**Spring Framework Migration Rules:**
- spring-boot-to-azure-key-vault-01000, spring-boot-to-azure-openfeign-01000
- spring-framework-version-01000, jakarta-ee-version-01000

**Identity and Security Rules:**
- java-ldap-to-msft-entra-id-01000

### **A.1.2 Cloud Readiness Rules (36 new rules)**
**Build Tool Detection:**
- ant-build-tool-00001

**Clustering Assessment:**
- clustering-00000

**Container Readiness:**
- dockerfile-00000, dockerfile-00010, dockerfile-00020, dockerfile-00030

**Embedded Cache Detection:**
- embedded-cache-01000, embedded-cache-02000, embedded-cache-03000, embedded-cache-04000, embedded-cache-05000
- embedded-cache-06000, embedded-cache-07000, embedded-cache-08000, embedded-cache-09000, embedded-cache-10000
- embedded-cache-11000, embedded-cache-12000, embedded-cache-13000, embedded-cache-14000, embedded-cache-15000, embedded-cache-16000

**External Configuration:**
- external-config-00000

**Hardcoded Dependencies:**
- hardcoded-urls-00001, hardcoded-urls-00002

**Legacy Technology Detection:**
- java-corba-00000, java-rmi-00000, java-rmi-00001, java-rpc-00000, jca-00000

**Native Dependencies:**
- jni-native-code-00000, jni-native-code-00001

**Local Storage Patterns:**
- local-storage-00001, local-storage-00002, local-storage-00003, local-storage-00004, local-storage-00005, local-storage-00006

### **A.1.3 OpenJDK 8 Rules (14 new rules - AppCAT exclusive)**
**Java 8 Deprecation and API Changes:**
- java-8-deprecate-apt-00001 (APT annotation processing deprecation)
- java-8-deprecate-callback-00001 (Callback interface deprecation)
- java-8-deprecate-corba-00001 (CORBA implementation deprecation)
- java-8-deprecate-javafx-builder-00001 (JavaFX Builder pattern deprecation)
- java-8-deprecate-log-00001 (Legacy logging framework deprecation)
- java-8-deprecate-odbc-00001 (ODBC bridge deprecation)
- java-8-deprecate-pack-00001, java-8-deprecate-pack-00002 (Pack200 utility deprecation)
- java-8-deprecate-security-00001, java-8-deprecate-security-00002 (Security API changes)
- java-8-deprecate-security-manager-00001, java-8-deprecate-security-manager-00002 (Security Manager deprecation)
- java-8-deprecate-stream-00001 (Early Stream API patterns)
- java-8-deprecate-thread-00001 (Thread API deprecation)

### **A.1.4 OpenJDK 11 Rules (39 new rules)**
**Java 9 Deprecation Rules:**
- java-9-deprecate-agent-00000, java-9-deprecate-dom-00000, java-9-deprecate-javafx-00000, java-9-deprecate-log-00000
- java-9-deprecate-pack-00000, java-9-deprecate-peer-00000, java-9-deprecate-property-00000, java-9-deprecate-property-00001
- java-9-deprecate-reflect-00000, java-9-deprecate-reflect-00010, java-9-deprecate-security-00000, java-9-deprecate-security-00001
- java-9-deprecate-security-00002, java-9-deprecate-tracing-00000, java-9-deprecate-unsafe-00000, java-9-deprecate-unsafe-00001
- java-9-deprecate-url-00000

**Java 10 Deprecation Rules:**
- java-10-deprecate-dom-00000, java-10-deprecate-javafx-00000, java-10-deprecate-runtime-00000, java-10-deprecate-security-00000
- java-10-deprecate-security-00001, java-10-deprecate-security-00002, java-10-deprecate-security-00003, java-10-deprecate-security-00004

**Java 11 Deprecation Rules:**
- java-11-deprecate-awt-00000, java-11-deprecate-corba-00000, java-11-deprecate-javaee-00000, java-11-deprecate-javaee-00001
- java-11-deprecate-pack-00000, java-11-deprecate-peer-00000, java-11-deprecate-property-00000, java-11-deprecate-property-00001
- java-11-deprecate-security-00000, java-11-deprecate-security-00001, java-11-deprecate-stream-00000, java-11-deprecate-thread-00000
- java-11-deprecate-unsafe-00000, java-11-deprecate-unsafe-00001

**Java API Removal Rules:**
- java-removals-00000, java-removals-00010

### **A.1.5 OpenJDK 17 Rules (27 new rules)**
**Java 12-15 Deprecation Rules:**
- java-12-deprecate-finalize-00000, java-12-deprecate-finalize-00001, java-12-deprecate-security-00000
- java-13-deprecate-runtime-00000, java-13-deprecate-security-00000
- java-14-deprecate-pack-00000, java-14-deprecate-property-00000, java-14-deprecate-property-00001
- java-14-deprecate-security-00000, java-14-deprecate-thread-00000
- java-15-deprecate-property-00000, java-15-deprecate-signer-00000, java-15-deprecate-ssl-00000, java-15-deprecate-ssl-00001

**Java 16-17 Deprecation Rules:**
- java-16-deprecate-security-00000, java-16-deprecate-thread-group-00000
- java-17-deprecate-applet-00000, java-17-deprecate-security-manager-00000, java-17-deprecate-security-manager-00010
- java-17-deprecate-security-manager-00020, java-17-deprecate-security-manager-00030, java-17-deprecate-security-manager-00040
- java-17-deprecate-security-manager-00050, java-17-deprecate-security-manager-00060, java-17-deprecate-security-manager-00070
- java-17-deprecate-socket-00000, java-17-deprecate-unsafe-00000

**Package Removal Rules:**
- removed-packages-00000, removed-packages-00010

### **A.1.6 OpenJDK 21 Rules (30 new rules)**
**Java 18-20 Deprecation Rules:**
- java-18-deprecate-finalize-00000, java-18-deprecate-finalize-00001, java-18-deprecate-property-00000
- java-18-deprecate-runtime-00000, java-18-deprecate-security-00000, java-18-deprecate-socket-00000
- java-18-deprecate-thread-00000, java-18-deprecate-unsafe-00000
- java-19-deprecate-class-00010, java-19-deprecate-locale-00000, java-19-deprecate-param-spec-00000
- java-19-deprecate-param-spec-00001, java-19-deprecate-thread-00000, java-19-deprecate-thread-00001
- java-19-deprecate-thread-00002
- java-20-deprecate-jmx-00000, java-20-deprecate-net-00000, java-20-deprecate-thread-00000
- java-20-deprecate-thread-00001, java-20-deprecate-thread-00002

**Java 21 Deprecation Rules:**
- java-21-deprecate-classes-00010, java-21-deprecate-dynamic-agents-00000, java-21-deprecate-file-00000
- java-21-deprecate-file-00001, java-21-deprecate-jmx-00000, java-21-deprecate-jmx-00001
- java-21-deprecate-property-00000, java-21-deprecate-property-00001, java-21-deprecate-signer-00000
- java-21-deprecate-thread-00000

**UTF-8 Default Changes:**
- utf-8-by-default-00000, utf-8-by-default-00010, utf-8-by-default-00020, utf-8-by-default-00030

### **A.1.7 Technology Usage Rules (25 new rules)**
**Enhanced technology detection patterns** - Specific rule IDs available in technology-usage folder analysis

### **A.1.8 OS Compatibility Rules (2 new rules)**
- os-specific-00001, os-specific-00002

## **A.2 Upstream Rules Not Synced to Fork (94 total)**

### **A.2.1 Azure Rules Not Synced (29 rules)**
**Spring Boot to Azure Integration (15 rules):**
- spring-boot-to-azure-cache-*
- spring-boot-to-azure-database-*
- spring-boot-to-azure-feign-*
- spring-boot-to-azure-identity-provider-*
- spring-boot-to-azure-jms-broker-*

**Spring Cloud Version Management (3 rules):**
- spring-cloud-to-azure-version-*

**Generic Azure Service Configuration (11 rules):**
- azure-file-system-*, azure-logging-*, azure-certificates-*, azure-postgres-workload-identity-*

### **A.2.2 Cloud Readiness Rules Not Synced (17 rules)**
**Basic containerization and platform-agnostic patterns not merged from upstream**

### **A.2.3 OpenJDK Rules Not Synced (37 total)**
**OpenJDK 8 (0 rules):**
- No upstream OpenJDK 8 rules exist - AppCAT exclusive implementation

**OpenJDK 11 (15 rules):**
- java-removals-00020, java-removals-00030, java-removals-00040, java-removals-00041, java-removals-00050
- java-removals-00060, java-removals-00100, java-removals-00110, java-removals-00120, java-removals-00130
- java-removals-00140, java-removals-00150, removed-javaee-modules-00000, removed-javaee-modules-00010
- removed-javaee-modules-00020

**OpenJDK 17 (10 rules):**
- Additional Java 12-17 deprecation patterns not synced

**OpenJDK 21 (12 rules):**
- Additional Java 18-21 deprecation patterns not synced
