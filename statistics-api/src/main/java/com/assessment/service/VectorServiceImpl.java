// VectorServiceImpl.java (Service implementation)
package com.assessment.service;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import org.jvnet.hk2.annotations.Service;

import com.assessment.dao.VectorDAO;
import com.assessment.model.VectorEntity;

@RequestScoped
@Service
public class VectorServiceImpl implements VectorService {
    @Inject
    private VectorDAO vectorDAO;

    @Override
    public VectorEntity getVectorById(int vectorId) {
        return vectorDAO.getVectorById(vectorId);
    }

    @Override
    public double calculateMean(int[] vector) {
        // Implementation of mean calculation
        // ...

        return 0.0;
    }

    @Override
    public double calculateStandardDeviation(int[] vector) {
        // Implementation of standard deviation calculation
        // ...

        return 0.0;
    }
}