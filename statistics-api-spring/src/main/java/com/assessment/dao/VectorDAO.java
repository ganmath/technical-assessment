/**
 * The VectorDAO interface extends JpaRepository for performing CRUD operations on VectorEntity.
 * It provides standard methods for working with JPA entities and the underlying database.
 */
package com.assessment.dao;

import com.assessment.model.VectorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VectorDAO extends JpaRepository<VectorEntity, Integer> {

    // No additional methods are declared here, as JpaRepository provides standard CRUD operations.

    /**
     * This interface inherits standard CRUD methods from JpaRepository for the VectorEntity entity.
     * Custom queries or methods can be added here if additional functionality is required beyond standard CRUD operations.
     * Spring Data JPA will automatically generate the necessary queries based on method naming conventions.
     */

}
