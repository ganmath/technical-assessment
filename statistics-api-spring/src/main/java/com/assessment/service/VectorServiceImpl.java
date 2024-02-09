
/**
 * The VectorServiceImpl class is an implementation of the VectorService interface.
 * It provides functionality for retrieving, calculating statistics, saving, and generating random vectors.
 */
package com.assessment.service;

import com.assessment.controller.StatisticsController.VectorRequest;
import com.assessment.dao.VectorDAO;
import com.assessment.model.VectorEntity;

import java.time.LocalDateTime;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class VectorServiceImpl implements VectorService {

    private final VectorDAO vectorDAO;

    /**
     * Constructor for VectorServiceImpl, injecting the VectorDAO dependency.
     *
     * @param vectorDAO The VectorDAO used for database operations.
     */
    @Autowired
    public VectorServiceImpl(VectorDAO vectorDAO) {
        this.vectorDAO = vectorDAO;
    }

    /**
     * Retrieves a vector by its unique identifier.
     *
     * @param vectorId The ID of the vector to be retrieved.
     * @return The VectorEntity with the specified ID, or null if not found.
     */
    @Override
    public VectorEntity getVectorById(int vectorId) {
        return vectorDAO.findById(vectorId).orElse(null);
    }

    /**
     * Calculates the mean (average) of the given vector.
     *
     * @param vector An array of integers representing the vector.
     * @return The mean of the vector. If the vector is empty, returns 0.
     */
    @Override
    public double calculateMean(int[] vector) {
        if (vector.length == 0) {
            return 0;
        }
        return (double) sum(vector) / vector.length;
    }

    /**
     * Calculates the standard deviation of the given vector.
     *
     * @param vector An array of integers representing the vector.
     * @return The standard deviation of the vector. If the vector is empty, returns 0.
     */
    @Override
    public double calculateStandardDeviation(int[] vector) {
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
    
    
    /**
     * Saves a vector entity to the underlying data store.
     *
     * @param vector The VectorEntity to be saved.
     * @return The saved VectorEntity.
     */
    @Override
    public VectorEntity saveVector(VectorEntity vector) {
        return vectorDAO.save(vector);
    }
    
    /**
     * Generates a random vector based on the provided VectorRequest.
     *
     * @param vectorRequest The VectorRequest containing details for generating a random vector.
     * @return The generated VectorEntity.
     */

    @Override
    public VectorEntity generateRandomVector(VectorRequest vectorRequest) {
    	
    	VectorEntity vector = new VectorEntity();
        vector.setSize(vectorRequest.getSize());
        vector.setVectorName(vectorRequest.getVectorName());
        
        int size = vector.getSize();
        int[] numbersArray = new int[size];

        Random random = new Random();

        for (int i = 0; i < size; i++) {
            numbersArray[i] = random.nextInt(100); // Adjust the range as needed
        }

        vector.setNumbersArray(numbersArray);
        return vector;
    }
    
    
    /**
     * Calculates the sum of elements in the given array.
     *
     * @param array An array of integers.
     * @return The sum of elements in the array.
     */
    private int sum(int[] array) {
        int sum = 0;
        for (int value : array) {
            sum += value;
        }
        return sum;
    }
}

