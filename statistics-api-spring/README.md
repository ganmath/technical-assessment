# Statistics API with Spring

This project is a Spring Boot application that provides a RESTful API for working with numerical vectors, including operations such as calculating statistics, saving vectors, and generating random vectors.

## Table of Contents

- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Installation](#installation)
- [Project Structure](#project-structure)
- [Usage](#usage)
  - [Endpoints](#endpoints)
  - [Testing](#testing)
    -[Postman](#postman)
    -[PowerShell Scripts](#powershell-scripts)
- [Built With](#built-with)


## Getting Started

### Prerequisites

- Java Development Kit (JDK) 8 or higher
- Maven (for building and managing dependencies)
- Your favorite Integrated Development Environment (IDE) (e.g., IntelliJ IDEA, Eclipse)

### Installation

1. Clone the repository:

   ```bash
   git clone https://github.com/yourusername/statistics-api-spring.git

   
2. Navigate to the project directory:

   ```bash
   git clone https://github.com/yourusername/statistics-api-spring.git

3. Build the project using Maven:

   ```bash
   git clone https://github.com/yourusername/statistics-api-spring.git

4. Run the application:

   ```bash
   git clone https://github.com/yourusername/statistics-api-spring.git

## Project Structure

The project follows the standard Maven project structure:


  ```css
  
  statistics-api-spring
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── assessment
│   │   │           ├── controller
│   │   │           ├── dao
│   │   │           ├── model
│   │   │           ├── service
│   │   │           └── StatisticsApiSpringApplication.java
│   │   └── scripts
│   │       ├── get_statistics.ps1
│   │       └── create_vector.ps1
│   ├── resources
│   │   └── application.properties
│   └── test
│       └── java
│           └── com
│               └── assessment
│                   ├── controller
│                   └── service
├── target
├── .gitignore
├── mvnw
├── mvnw.cmd
├── pom.xml
└── README.md

```

The src/main/scripts folder contains PowerShell scripts for testing the application.

## Usage

### Endpoints

The API provides the following endpoints:

1. POST /api/vectors/create: Create a new vector based on the provided request.

2. GET /api/vectors/{vectorId}: Calculate statistics (mean and standard deviation) for the vector with the given ID.


Please refer to the StatisticsController for more details on the endpoints.

## Testing
### Postman
Postman is a popular API testing tool that allows you to interact with your APIs by sending requests and receiving responses. You can use Postman to test the endpoints provided by the Spring Boot application.

Postman Collection: Statistics API Postman Collection

## PowerShell Scripts
PowerShell scripts can also be used to test the endpoints of the Spring Boot application. Two scripts, get_statistics.ps1 and create_vector.ps1, have been provided for testing.

get_statistics.ps1: Retrieves statistics (mean and standard deviation) for a specified vector ID.

```powershell
./src/main/scripts/get_statistics.ps1 <vectorId>

```
Replace <vectorId>, <size>, and <vectorName> with the actual values you want to use for testing.create_vector.ps1: Creates a new vector with specified size and vector name.

```powershell
./src/main/scripts/create_vector.ps1 <size> <vectorName>

```

Replace vectorId, size, and vectorName with the actual values you want to use for testing.

## Built With
1. Spring Boot - Framework for building Java-based enterprise applications.
2. Spring Data JPA - Part of the larger Spring Data project that makes it easy to easily implement JPA-based repositories.
3. Maven - Dependency management and build tool.