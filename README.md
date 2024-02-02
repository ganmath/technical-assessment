# Technical Assessment Project

This project is designed for [Provide a brief description of the project's purpose].

## Table of Contents
- [Project Overview](#project-overview)
- [Project Structure](#project-structure)
- [Prerequisites](#prerequisites)
- [Setup](#setup)
- [Running the Project](#running-the-project)
- [Java Code](#java-code)
- [SQL Scripts](#sql-scripts)
- [PowerShell Script](#powershell-script)
- [Property Files](#property-files)

## Project Overview

[Provide a brief overview of the project's purpose and functionality.]

## Project Structure

The project follows a standard Maven project structure:

```
project-root/
│   src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── assessment/
│   │   │           ├── DatabasePopulator.java
│   │   │           └── Utils.java
│   │   └── resources/
│   │       ├── database.properties
│   │       ├── exception-messages.properties
│   │       └── ddl_script.sql
│   └── test/
│       └── java/
│           └── com/
│               └── assessment/
│                   └── DatabasePopulatorTest.java
├── target/
│   └── ... (Maven build output)
└── src/
    └── main/
        └── scripts/
            └── database-population-script.sh
```

## Prerequisites

- [Java JDK 17](https://www.oracle.com/java/technologies/javase-downloads.html)
- [PostgreSQL Database](https://www.postgresql.org/download/)
- [Maven](https://maven.apache.org/download.cgi)
- [PowerShell](https://docs.microsoft.com/en-us/powershell/)

## Setup

1. **Clone the Repository:**

    ```bash
    git clone https://github.com/your-username/technical-assessment.git
    cd technical-assessment
    ```

2. **Database Setup:**

    - Create a PostgreSQL database.
    - Execute the DDL script (`ddl_script.sql`) to create the necessary table.

3. **Maven Build:**

    ```bash
    mvn clean package
    ```

## Running the Project

Execute the provided PowerShell script to run the Java application:

```powershell
.\src\main\scripts\database-population-script.ps1
```

## Java Code
DatabasePopulator.java
[Provide detailed documentation for the DatabasePopulator class.]

Utils.java
[Provide detailed documentation for the Utils class.]

## SQL Scripts
ddl_script.sql
[Document the purpose of the DDL script and its contents.]

## PowerShell Script
database-population-script.ps1
[Document the purpose of the PowerShell script and its contents.]

## Property Files
database.properties
[Document the properties in the database.properties file and their purpose.]

exception-messages.properties
[Document the properties in the exception-messages.properties file and their purpose.]
