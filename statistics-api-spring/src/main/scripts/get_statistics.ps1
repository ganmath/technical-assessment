# PowerShell script to call the RESTful microservice and display Mean and Standard Deviation

# Specify the vector ID as a command-line argument
$vectorId = $args[0]

# Check if vector ID is provided
if (-not $vectorId) {
    Write-Output "Please provide a vector ID as a command-line argument."
    exit 1
}

# URL for the RESTful microservice
$url = "http://localhost:8080/api/statistics/$vectorId"

try {
    # Invoke the RESTful API
    $response = Invoke-RestMethod -Uri $url -Method Get
    
    # Extract Mean and Standard Deviation from the response
    $mean = $response.mean
    $stdDev = $response.standardDeviation
    
    # Display the results
    Write-Output "Vector ID: $vectorId"
    Write-Output "Mean: $mean"
    Write-Output "Standard Deviation: $stdDev"
} catch {
    # Handle errors
    Write-Output "Error: $_"
    exit 1
}
