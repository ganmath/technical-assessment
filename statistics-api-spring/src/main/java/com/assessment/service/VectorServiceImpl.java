package com.assessment.service;

import com.assessment.controller.StatisticsController.SizeRequest;
import com.assessment.dao.VectorDAO;
import com.assessment.model.VectorEntity;

import java.util.Random;

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
        return vectorDAO.findById(vectorId).orElse(null);
    }

    @Override
    public double calculateMean(int[] vector) {
        if (vector.length == 0) {
            return 0;
        }
        return (double) sum(vector) / vector.length;
    }

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

    @Override
    public VectorEntity saveVector(VectorEntity vector) {
        return vectorDAO.save(vector);
    }

    @Override
    public VectorEntity generateRandomVector(SizeRequest sizeRequest) {
    	
    	VectorEntity vector = new VectorEntity();
        vector.setSize(sizeRequest.getSize());
        vector.setVectorName(sizeRequest.getVectorName());
        int size = vector.getSize();
        int[] numbersArray = new int[size];

        Random random = new Random();

        for (int i = 0; i < size; i++) {
            numbersArray[i] = random.nextInt(100); // Adjust the range as needed
        }

        vector.setNumbersArray(numbersArray);
        return vector;
    }

    private int sum(int[] array) {
        int sum = 0;
        for (int value : array) {
            sum += value;
        }
        return sum;
    }
}

