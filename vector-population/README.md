# Technical Assessment Project

This project is designed for population of a PostgreSQL database with vector of any given size of randomly generated numbers ranging from 1 to 10.

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

Handles the population of a PostgreSQL database with vector of any given size of randomly generated numbers ranging from 1 to 100.

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

- DatabasePopulator.java
This the main java code which populates the generated numbers in the required table
- Utils.java
This is Util class

## SQL Scripts
ddl_script.sql
This is ddl script to create the table

## PowerShell Script
database-population-script.ps1
This is the script to run the program

## Property Files

- database.properties
- exception-messages.properties
