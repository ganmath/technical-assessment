// VectorDAO.java (Data Access Object interface)
package com.assessment.dao;

import com.assessment.model.VectorEntity;

public interface VectorDAO {
    VectorEntity getVectorById(int vectorId);
}