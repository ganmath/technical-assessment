// VectorDAOImpl.java (Data Access Object implementation)
package com.assessment.dao;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.assessment.model.VectorEntity;

@RequestScoped
public class VectorDAOImpl implements VectorDAO {
	@PersistenceContext(unitName = "statisticsPersistenceUnit")
	private EntityManager entityManager;

	@Override
	public VectorEntity getVectorById(int vectorId) {
		return entityManager.find(VectorEntity.class, vectorId);
	}
}