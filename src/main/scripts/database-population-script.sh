#!/bin/bash

# Set the path to the JAR files
JAR_PATH="target/lib/postgresql-42.2.23.jar:target/technical-assessment-1.0.0.jar"


export JAVA_HOME='/C/Program Files/Java/jdk-17'
export PATH=$JAVA_HOME/bin:$PATH

echo "JAVA_HOME: $JAVA_HOME"
echo "PATH: $PATH"


# Split the JAR path into an array
IFS=":" read -ra JAR_FILES <<< "$JAR_PATH"

# Check if each JAR file exists
for JAR_FILE in "${JAR_FILES[@]}"; do
  if [ ! -f "$JAR_FILE" ]; then
    echo "Error: JAR file not found at $JAR_FILE"
    exit 1
  fi
done

# Run the Java application with the specified classpath and JAR files
java -cp "$JAR_PATH" com.assessment.DatabasePopulator