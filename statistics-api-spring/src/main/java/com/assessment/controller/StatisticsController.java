package com.assessment.controller;

import com.assessment.model.VectorEntity;
import com.assessment.service.VectorService;
import org.springframework.beans.factory.annotation.Autowired;
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
	public ResponseEntity<StatisticsResponse> calculateStatistics(@PathVariable int vectorId) {
		VectorEntity vector = vectorService.getVectorById(vectorId);

		if (vector == null) {
			// Return a 404 response
			return ResponseEntity.notFound().build();
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
	public ResponseEntity<String> createVector(@RequestBody SizeRequest sizeRequest) {
		if (sizeRequest == null || sizeRequest.getSize() == null || sizeRequest.getVectorName() == null
				|| sizeRequest.getVectorName().trim().isEmpty()) {
			return ResponseEntity.badRequest()
					.body("Size and non-empty vectorName parameters are required in the request payload.");
		}

		try {

			// Set other properties if needed
			VectorEntity vector = vectorService.generateRandomVector(sizeRequest);
			vectorService.saveVector(vector);

			return ResponseEntity.ok("Vector created successfully with ID: " + vector.getVectorId());
		} catch (Exception e) {
			// Handle any other exceptions that may occur during vector creation or saving
			return ResponseEntity.status(500).body("Error creating vector: " + e.getMessage());
		}
	}

	// Inner class representing the JSON request payload
	public static class SizeRequest {
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
}
