/**
 * The VectorService interface defines the contract for operations related to vectors.
 * It declares methods for retrieving vectors by ID, saving vectors, calculating mean and standard deviation,
 * and generating random vectors based on VectorRequest.
 */
package com.assessment.service;

import com.assessment.controller.StatisticsController.VectorRequest;
import com.assessment.model.VectorEntity;

public interface VectorService {

    /**
     * Retrieves a vector by its unique identifier.
     *
     * @param vectorId The ID of the vector to be retrieved.
     * @return The VectorEntity with the specified ID, or null if not found.
     */
    VectorEntity getVectorById(int vectorId);

    /**
     * Saves a vector entity to the underlying data store.
     *
     * @param vector The VectorEntity to be saved.
     * @return The saved VectorEntity.
     */
    VectorEntity saveVector(VectorEntity vector);

    /**
     * Calculates the mean (average) of the given vector.
     *
     * @param vector An array of integers representing the vector.
     * @return The mean of the vector. If the vector is empty, returns 0.
     */
    double calculateMean(int[] vector);

    /**
     * Calculates the standard deviation of the given vector.
     *
     * @param vector An array of integers representing the vector.
     * @return The standard deviation of the vector. If the vector is empty, returns 0.
     */
    double calculateStandardDeviation(int[] vector);

    /**
     * Generates a random vector based on the provided VectorRequest.
     *
     * @param sizeRequest The VectorRequest containing details for generating a random vector.
     * @return The generated VectorEntity.
     */
    VectorEntity generateRandomVector(VectorRequest sizeRequest);
}
