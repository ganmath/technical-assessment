package com.assessment.controller;

import com.assessment.model.VectorEntity;
import com.assessment.service.VectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/statistics")
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

        // Create a StatisticsResponse object with the calculated values
        StatisticsResponse response = new StatisticsResponse(mean, standardDeviation);

        // Return the response with a 200 OK status
        return ResponseEntity.ok(response);
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

        public StatisticsResponse(double mean, double standardDeviation) {
            this.mean = mean;
            this.standardDeviation = standardDeviation;
        }

        // Getters for JSON serialization
        public double getMean() {
            return mean;
        }

        public double getStandardDeviation() {
            return standardDeviation;
        }
    }
}
