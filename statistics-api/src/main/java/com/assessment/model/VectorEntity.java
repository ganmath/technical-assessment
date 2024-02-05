// VectorEntity.java (Entity class representing the Vector)
package com.assessment.model;

import javax.persistence.*;

@Entity
@Table(name = "number_vectors", schema = "production_schema")
public class VectorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vector_id")
    private int vectorId;

    @Column(name = "vector_name")
    private String vectorName;

    @ElementCollection
    @CollectionTable(name = "vector_numbers", joinColumns = @JoinColumn(name = "vector_id"))
    @Column(name = "number")
    private int[] numbers;

    // Getter and Setter for vectorId

    public String getVectorName() {
        return vectorName;
    }

    public void setVectorName(String vectorName) {
        this.vectorName = vectorName;
    }

    public int[] getNumbers() {
        return numbers;
    }

    public void setNumbers(int[] numbers) {
        this.numbers = numbers;
    }
}