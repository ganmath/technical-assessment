package com.assessment.controller;

import com.assessment.model.VectorEntity;
import com.assessment.service.VectorService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public String calculateStatistics(@PathVariable int vectorId) {
        VectorEntity vector = vectorService.getVectorById(vectorId);

        if (vector == null) {
            return "Vector not found";
        }

        double mean = calculateMean(vector.getNumbersArray());
        double standardDeviation = calculateStandardDeviation(vector.getNumbersArray());

        return String.format("Mean: %.2f, Standard Deviation: %.2f", mean, standardDeviation);
    }

    // Dummy method to calculate mean (replace with your actual logic)
    private double calculateMean(int[] vector) {
        return vector.length > 0 ? (double) sum(vector) / vector.length : 0;
    }

    // Dummy method to calculate standard deviation (replace with your actual logic)
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
}
