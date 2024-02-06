package com.assessment.model;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "number_vectors", schema = "production_schema")
public class VectorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vector_id")
    private int vectorId;
    
    @Column(name = "size", nullable = false)
    private Integer size;

    public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	@Column(name = "vector_name", nullable = false)
    private String vectorName = "DefaultVectorName"; // Set a default value


    @Column(name = "numbers_array", columnDefinition = "INT ARRAY", nullable = false)
    private int[] numbersArray;

    @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp createdAt;

    // Constructors, getters, and setters

    public int getVectorId() {
        return vectorId;
    }

    public void setVectorId(int vectorId) {
        this.vectorId = vectorId;
    }

    public String getVectorName() {
        return vectorName;
    }

    public void setVectorName(String vectorName) {
        this.vectorName = vectorName;
    }

    public int[] getNumbersArray() {
        return numbersArray;
    }

    public void setNumbersArray(int[] numbersArray) {
        this.numbersArray = numbersArray;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}
