package com.assessment.controller;

import com.assessment.model.VectorEntity;
import com.assessment.service.VectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/vectors")
public class StatisticsController {

	private final VectorService vectorService;

	@Autowired
	public StatisticsController(VectorService vectorService) {
		this.vectorService = vectorService;
	}

	@GetMapping("/{vectorId}")
	public ResponseEntity<?> calculateStatistics(@PathVariable int vectorId) {
		VectorEntity vector = vectorService.getVectorById(vectorId);

		if (vector == null) {
	        // Return a custom error response in JSON format
	        String errorMessage = "Vector with ID " + vectorId + " not found.";
	        ErrorResponse errorResponse = new ErrorResponse(errorMessage);
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
	    }

		double mean = calculateMean(vector.getNumbersArray());
		double standardDeviation = calculateStandardDeviation(vector.getNumbersArray());

		String message = "Statistics calculated successfully for Vector ID " + vectorId;

		// Create a StatisticsResponse object with the calculated values
		StatisticsResponse response = new StatisticsResponse(mean, standardDeviation, message);

		// Return the response with a 200 OK status
		return ResponseEntity.ok(response);
	}

	@PostMapping("/create")
	public ResponseEntity<String> createVector(@RequestBody VectorRequest vectorRequest) {
	    try {
	        validateVectorRequest(vectorRequest);

	        // Set other properties if needed
	        VectorEntity vector = vectorService.generateRandomVector(vectorRequest);
	        vectorService.saveVector(vector);

	        return ResponseEntity.ok("Vector created successfully with ID: " + vector.getVectorId());
	    } catch (ValidationException e) {
	        // Handle specific validation errors
	        return ResponseEntity.badRequest().body("Invalid request: " + e.getMessage());
	    } catch (DataAccessException e) {
	        // Handle database-related exceptions
	        return ResponseEntity.status(500).body("Error accessing database Something: " + e.getMessage());
	    } catch (Exception e) {
	        // Handle any other unexpected exceptions
	        return ResponseEntity.status(500).body("Error creating vector: " + e.getMessage());
	    }
	}

	// Validate the vectorRequest
	private void validateVectorRequest(VectorRequest vectorRequest) {
	    if (vectorRequest == null || vectorRequest.getSize() == null || vectorRequest.getVectorName() == null
	            || vectorRequest.getVectorName().trim().isEmpty()) {
	        throw new ValidationException("Size and non-empty name parameters are required.");
	    }

	    // Additional validation logic if needed
	}

	// Custom exception for validation errors
	public static class ValidationException extends RuntimeException {
	    /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public ValidationException(String message) {
	        super(message);
	    }
	}


	// Inner class representing the JSON request payload
	public static class VectorRequest {
		private Integer size;
		String vectorName;

		// Getters and setters

		public String getVectorName() {
			return vectorName;
		}

		public void setVectorName(String vectorName) {
			this.vectorName = vectorName;
		}

		public Integer getSize() {
			return size;
		}

		public void setSize(Integer size) {
			this.size = size;
		}
	}

	// Method to calculate mean
	private double calculateMean(int[] vector) {
		return vector.length > 0 ? (double) sum(vector) / vector.length : 0;
	}

	// Method to calculate standard deviation
	private double calculateStandardDeviation(int[] vector) {
		if (vector.length == 0) {
			return 0;
		}

		double mean = calculateMean(vector);
		double sumSquaredDeviations = 0;

		for (int value : vector) {
			sumSquaredDeviations += Math.pow(value - mean, 2);
		}

		return Math.sqrt(sumSquaredDeviations / vector.length);
	}

	private int sum(int[] array) {
		int sum = 0;
		for (int value : array) {
			sum += value;
		}
		return sum;
	}

	// Inner class representing the JSON response structure
	private static class StatisticsResponse {
		private final double mean;
		private final double standardDeviation;
		private final String message;

		public StatisticsResponse(double mean, double standardDeviation, String message) {
			this.mean = mean;
			this.standardDeviation = standardDeviation;
			this.message = message;
		}

		// Getters for JSON serialization
		public double getMean() {
			return mean;
		}

		public double getStandardDeviation() {
			return standardDeviation;
		}

		public String getMessage() {
			return message;
		}

	}
	
	
	// Define a custom ErrorResponse class for JSON error responses
	public static class ErrorResponse {
	    private final String errorMessage;

	    public ErrorResponse(String errorMessage) {
	        this.errorMessage = errorMessage;
	    }

	    public String getErrorMessage() {
	        return errorMessage;
	    }
	}
}
