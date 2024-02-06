-- Create a schema to organize the database objects
CREATE SCHEMA production_schema;

-- Create a table for vectors
CREATE TABLE production_schema.number_vectors (
    vector_id SERIAL PRIMARY KEY,
    size INT,
    vector_name VARCHAR(50) NOT NULL,
    numbers_array INT ARRAY NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create an index on vector_name for faster lookups
CREATE INDEX idx_vector_name ON production_schema.number_vectors (vector_name);