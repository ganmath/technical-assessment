param (
    [Parameter(Mandatory=$true)]
    [int]$vectorId
)

# Set the base URL of your RESTful microservice
$baseUrl = "http://localhost:8080/api/statistics"

# Build the complete URL with the vector ID
$url = "$baseUrl/$vectorId"

# Invoke the RESTful endpoint to get statistics
$response = Invoke-RestMethod -Uri $url -Method Get

# Display the result
Write-Host "Mean: $($response.Mean), Standard Deviation: $($response.StandardDeviation)"
