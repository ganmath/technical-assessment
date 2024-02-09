/**
 * The VectorEntity class represents a numerical vector entity in the application.
 * It is mapped to the "number_vectors" table in the "production_schema" schema of the database.
 */
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


	/**
     * The unique identifier for the vector entity.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vector_id")
    private int vectorId;

    /**
     * The size of the vector.
     */
    @Column(name = "size", nullable = false)
    private Integer size;

    /**
     * The name of the vector. Defaults to "DefaultVectorName" if not specified.
     */
    @Column(name = "vector_name", nullable = false)
    private String vectorName = "DefaultVectorName"; // Set a default value

    /**
     * The array of integers representing the vector.
     */
    @Column(name = "numbers_array", columnDefinition = "INT ARRAY", nullable = false)
    private int[] numbersArray;

    /**
     * The timestamp indicating when the vector entity was created.
     */
    @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp createdAt;

    // Constructors, getters, and setters...

    /**
     * Retrieves the unique identifier of the vector.
     *
     * @return The vectorId.
     */
    public int getVectorId() {
        return vectorId;
    }

    /**
     * Sets the unique identifier of the vector.
     *
     * @param vectorId The vectorId to be set.
     */
    public void setVectorId(int vectorId) {
        this.vectorId = vectorId;
    }

    /**
     * Retrieves the name of the vector.
     *
     * @return The vectorName.
     */
    public String getVectorName() {
        return vectorName;
    }

    /**
     * Sets the name of the vector.
     *
     * @param vectorName The vectorName to be set.
     */
    public void setVectorName(String vectorName) {
        this.vectorName = vectorName;
    }

    /**
     * Retrieves the array of integers representing the vector.
     *
     * @return The numbersArray.
     */
    public int[] getNumbersArray() {
        return numbersArray;
    }

    /**
     * Sets the array of integers representing the vector.
     *
     * @param numbersArray The numbersArray to be set.
     */
    public void setNumbersArray(int[] numbersArray) {
        this.numbersArray = numbersArray;
    }

    /**
     * Retrieves the timestamp indicating when the vector entity was created.
     *
     * @return The createdAt timestamp.
     */
    public Timestamp getCreatedAt() {
        return createdAt;
    }

    /**
     * Sets the timestamp indicating when the vector entity was created.
     *
     * @param createdAt The createdAt timestamp to be set.
     */
    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
    

    /**
     * Retrieves the size of the vector.
     *
     * @return The size of the vector. May be null if not set.
     */
    public Integer getSize() {
        return size;
    }

    /**
     * Sets the size of the vector.
     *
     * @param size The size to be set for the vector.
     */
    public void setSize(Integer size) {
        this.size = size;
    }

}
