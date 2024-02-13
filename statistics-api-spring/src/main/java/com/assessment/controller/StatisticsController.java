/**
 * The StatisticsController class is a Spring REST controller responsible for handling vector-related operations.
 * It exposes endpoints for calculating statistics based on a vector and creating random vectors.
 */
package com.assessment.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assessment.model.VectorEntity;
import com.assessment.service.VectorService;

@RestController
@RequestMapping("/api/vectors")
public class StatisticsController {

	private static final Logger log = LoggerFactory.getLogger(StatisticsController.class);

	private final VectorService vectorService;

	/**
	 * Constructor for StatisticsController, injecting the VectorService dependency.
	 *
	 * @param vectorService The VectorService used for vector-related operations.
	 */
	@Autowired
	public StatisticsController(VectorService vectorService) {
		this.vectorService = vectorService;
	}

	/**
	 * Endpoint to calculate statistics (mean and standard deviation) for a vector
	 * identified by its ID.
	 *
	 * @param vectorId The ID of the vector for which statistics are to be
	 *                 calculated.
	 * @return ResponseEntity containing the calculated statistics or an error
	 *         response.
	 */
	@GetMapping("/{vectorId}")
	public ResponseEntity<?> calculateStatistics(@PathVariable int vectorId) {
		VectorEntity vector = vectorService.getVectorById(vectorId);

		if (vector == null) {
			// Return a custom error response in JSON format
			String errorMessage = "Vector with ID " + vectorId + " not found.";
			ErrorResponse errorResponse = new ErrorResponse(errorMessage);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
		}

		double mean = vectorService.calculateMean(vector.getNumbersArray());
		double standardDeviation = vectorService.calculateStandardDeviation(vector.getNumbersArray());

		String message = "Statistics calculated successfully for Vector ID " + vectorId;

		// Create a StatisticsResponse object with the calculated values
		StatisticsResponse response = new StatisticsResponse(mean, standardDeviation, message);

		// Return the response with a 200 OK status
		return ResponseEntity.ok(response);
	}

	/**
	 * Helper method to handle various exceptions and provide appropriate error
	 * responses.
	 *
	 * @param e              The exception to be handled.
	 * @param defaultMessage The default error message if the exception is not
	 *                       explicitly handled.
	 * @return ResponseEntity containing the error response.
	 */
	private ResponseEntity<ErrorResponse> handleException(Exception e, String defaultMessage) {
		String errorMessage;
		HttpStatus status;

		if (e instanceof HttpMediaTypeNotAcceptableException) {
			errorMessage = defaultMessage +" Requested media type is not supported.";
			status = HttpStatus.NOT_ACCEPTABLE;
		} else if (e instanceof DataAccessException) {
			// Handle specific database-related exceptions
			errorMessage = defaultMessage +" Error accessing the database: " + e.getMessage();
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		} else if (e instanceof ValidationException) {
			// Handle specific validation errors
			errorMessage = "Invalid request: " + e.getMessage();
			status = HttpStatus.BAD_REQUEST;
		} else {
			// Handle any other unexpected exceptions
			errorMessage = defaultMessage + " Unexpected exception occurred: " + e.getMessage();
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			log.error(errorMessage, e); // Log the unexpected exception
		}

		ErrorResponse errorResponse = new ErrorResponse(errorMessage);
		return new ResponseEntity<>(errorResponse, status);
	}

	/**
	 * Endpoint to create a new vector based on the provided VectorRequest.
	 *
	 * @param vectorRequest The request payload containing vector details.
	 * @return ResponseEntity containing the response or an error response.
	 */
	@PostMapping("/create")
	public ResponseEntity<?> createVector(@RequestBody VectorRequest vectorRequest) {

		try {
			validateVectorRequest(vectorRequest);

			// Set other properties if needed
			VectorEntity vector = vectorService.generateRandomVector(vectorRequest);
			vectorService.saveVector(vector);

			CreateVectorResponse response = new CreateVectorResponse("Vector created successfully",
					vector.getVectorId(), vector.getVectorName());

			return ResponseEntity.ok(response);
		} catch (ValidationException e) {
			// Handle specific validation errors
			return ResponseEntity.badRequest().body(new ErrorResponse("Invalid request: " + e.getMessage()));
		} catch (DataAccessException e) {
			// Handle database-related exceptions using the handleException method
			return handleException(e, "Error accessing database.");
		} catch (Exception e) {
			// Handle any other unexpected exceptions using the handleException method
			return handleException(e, "Error creating vector.");
		}
	}

	/**
	 * Validates the properties of a VectorRequest to ensure it meets required
	 * criteria.
	 */
	private void validateVectorRequest(VectorRequest vectorRequest) {
		/**
		 * Validates the properties of a VectorRequest to ensure it meets required
		 * criteria.
		 *
		 * @param vectorRequest The VectorRequest object to be validated.
		 * @throws ValidationException If the validation criteria are not met, an
		 *                             exception is thrown with a descriptive message.
		 */
		if (vectorRequest == null || vectorRequest.getSize() == null || vectorRequest.getVectorName() == null
				|| vectorRequest.getVectorName().trim().isEmpty()) {
			throw new ValidationException("Size and non-empty name parameters are required.");
		}

		// Additional validation logic if needed
	}


	/**
	 * Inner class representing the JSON response structure for calculated
	 * statistics.
	 */
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

	/**
	 * Inner class representing the JSON structure for error responses.
	 */
	public static class ErrorResponse {
		private final String errorMessage;

		public ErrorResponse(String errorMessage) {
			this.errorMessage = errorMessage;
		}

		public String getErrorMessage() {
			return errorMessage;
		}
	}

	/**
	 * Inner class representing the JSON response structure for creating a vector.
	 */
	public static class CreateVectorResponse {
		private final String message;
		private final int vectorId;
		private final String vectorName;

		public CreateVectorResponse(String message, int vectorId, String vectorName) {
			this.message = message;
			this.vectorId = vectorId;
			this.vectorName = vectorName;
		}


		// Getters for JSON serialization...
	}

	/**
	 * Custom exception class for validation errors.
	 */
	public static class ValidationException extends RuntimeException {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public ValidationException(String message) {
			super(message);
		}
	}

	/**
	 * Inner class representing the JSON request payload for vector creation.
	 */
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
}
