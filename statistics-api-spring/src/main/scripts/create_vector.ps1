# PowerShell script to call the RESTful microservice and create a vector

# Specify vector parameters as command-line arguments
$size = $args[0]
$vectorName = $args[1]

# Check if size and vectorName are provided
if (-not $size -or -not $vectorName) {
    Write-Output "Please provide size and vectorName as command-line arguments."
    exit 1
}

# URL for the RESTful microservice
$url = "http://localhost:8080/api/vectors/create"

# Create JSON payload for the request
$jsonPayload = @{
    size = $size
    vectorName = $vectorName
} | ConvertTo-Json

try {
    # Invoke the RESTful API to create a vector
    $response = Invoke-RestMethod -Uri $url -Method Post -Body $jsonPayload -ContentType "application/json"
    
    # Display the result
    Write-Output "Vector created successfully:"
    Write-Output "Vector ID: $($response.vectorId)"
    Write-Output "Vector Name: $($response.vectorName)"
} catch {
    # Handle errors
    Write-Output "Error: $_"
    exit 1
}