// VectorService.java (Service interface)
package com.assessment.service;

import com.assessment.model.VectorEntity;

public interface VectorService {
    VectorEntity getVectorById(int vectorId);
    double calculateMean(int[] vector);
    double calculateStandardDeviation(int[] vector);
}