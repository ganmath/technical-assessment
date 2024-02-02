# Set the path to the JAR files
$JAR_PATH = "target/lib/postgresql-42.2.23.jar;target/vector-population-1.0.0.jar"

# Set the path to the JDK 17 installation
$env:JAVA_HOME = 'C:\Program Files\Java\jdk-17'
$env:PATH = "$env:JAVA_HOME\bin;$env:PATH"

Write-Host "JAVA_HOME: $env:JAVA_HOME"
Write-Host "PATH: $env:PATH"

# Split the JAR path into an array
$JAR_FILES = $JAR_PATH -split ';'

# Check if each JAR file exists
foreach ($JAR_FILE in $JAR_FILES) {
    if (-not (Test-Path $JAR_FILE -PathType Leaf)) {
        Write-Host "Error: JAR file not found at $JAR_FILE"
        exit 1
    }
}

# Run the Java application with the specified classpath and JAR files
java -cp "$JAR_PATH" com.assessment.DatabasePopulator
