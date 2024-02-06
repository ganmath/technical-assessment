package com.assessment.service;

import com.assessment.controller.StatisticsController.SizeRequest;
import com.assessment.model.VectorEntity;

public interface VectorService {

    VectorEntity getVectorById(int vectorId);
    
    VectorEntity saveVector(VectorEntity vector);

    double calculateMean(int[] vector);

    double calculateStandardDeviation(int[] vector);

	VectorEntity generateRandomVector(SizeRequest sizeRequest);
}
