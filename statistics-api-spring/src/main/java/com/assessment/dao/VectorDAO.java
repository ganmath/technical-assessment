package com.assessment.dao;

import com.assessment.model.VectorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VectorDAO extends JpaRepository<VectorEntity, Integer> {

    // Add custom queries if needed

}
