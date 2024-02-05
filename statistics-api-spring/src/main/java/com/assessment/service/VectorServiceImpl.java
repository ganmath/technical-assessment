package com.assessment.service;

import com.assessment.dao.VectorDAO;
import com.assessment.model.VectorEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VectorServiceImpl implements VectorService {

    private final VectorDAO vectorDAO;

    @Autowired
    public VectorServiceImpl(VectorDAO vectorDAO) {
        this.vectorDAO = vectorDAO;
    }

    @Override
    public VectorEntity getVectorById(int vectorId) {
        // Use the findById method provided by JpaRepository
        return vectorDAO.findById(vectorId).orElse(null);
    }

    @Override
    public double calculateMean(int[] vector) {
        if (vector.length == 0) {
            return 0.0;
        }

        double sum = 0;
        for (int value : vector) {
            sum += value;
        }

        return sum / vector.length;
    }

    @Override
    public double calculateStandardDeviation(int[] vector) {
        if (vector.length == 0) {
            return 0.0;
        }

        double mean = calculateMean(vector);
        double sumSquaredDeviations = 0;

        for (int value : vector) {
            sumSquaredDeviations += Math.pow(value - mean, 2);
        }

        return Math.sqrt(sumSquaredDeviations / vector.length);
    }
}
