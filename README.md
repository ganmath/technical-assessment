# Technical Assessment Project

The "Technical Assessment Project" is a comprehensive solution designed to address various aspects of data management, statistical analysis, and Java development. This multi-module project encompasses two distinct submodules, each serving specific functionalities:

1. [Vector Population Module](vector-population/README.md): Handles the population of a PostgreSQL database with vector of any given size of randomly generated numbers ranging from 1 to 100.

2. [Statistics API Module](statistics-api-spring/README.md): Provides an API for statistical analysis on the populated data. This API also helps to create vector of numbers mentioned above.

## Index

- [Main Project Overview](#main-project-overview)
- [Vector Population Module](#1-vector-population-module)
  - [Purpose](#11-purpose)
  - [Key Features](#12-key-features)
  - [How to Use](#13-how-to-use)
- [Statistics API Module](#2-statistics-api-module)
  - [Purpose](#21-purpose)
  - [Key Features](#22-key-features)
  - [How to Use](#23-how-to-use)
- [Project Structure](#project-structure)
- [Prerequisites](#prerequisites)

## Main Project Overview

The primary objectives of the "Technical Assessment Project" include:

### 1. Modular Architecture:

The project follows a modular architecture, allowing for focused development, maintainability, and scalability. Each submodule operates independently, serving a purpose of creating vectors and it statistics.

### 2. Data Population (Vector Population Module):

#### 1.1 Purpose:

The "Vector Population" submodule focuses on efficiently populating a PostgreSQL database with generated data.

#### 1.2 Key Features:

- Creation of database tables.
- Execution of DDL scripts.
- Efficient handling of data population tasks.

#### 1.3 How to Use:

1. **Clone the Repository:**
   ```bash
   git clone https://github.com/your-username/technical-assessment.git
   cd technical-assessment
   
## Prerequisites
Ensure the following software is installed on your system:

- Java JDK 17
- PostgreSQL Database
- Maven
- PowerShell

## Setup and Database Configuration:

Create a PostgreSQL database.
Execute the DDL script (ddl_script.sql) in the "vector-population" submodule to create the necessary table.

## Building and Running:
```
mvn clean install
```

More detailed instructions can be found in the Vector Population README.

### 3. Statistical Analysis (Statistics API Module):

#### 2.1 Purpose:
The "Statistics API" submodule provides a robust API for performing statistical analysis on data stored in the PostgreSQL database.

#### 2.2 Key Features:
- Utilization of Spring Boot for microservice architecture.
- Calculation of mean and standard deviation for given vectors.

#### 2.3 How to Use:
- Follow the specific instructions in the Statistics API Module README for running the API.
- Explore the API endpoints for statistical calculations.

## Project Structure
The project adheres to a standard Maven project structure with the following high-level layout:

```
technical-assessment/
│   vector-population/
│       └── (vector-population files and directories)
│   statistics-api-spring/
│       └── (statistics-api-spring files and directories)
│   └── pom.xml
├── target/
│   └── ... (Maven build output)
├── .gitignore
├── mvnw
├── mvnw.cmd
├── pom.xml
└── README.md
```
