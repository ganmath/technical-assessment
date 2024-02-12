package com.assessment;

/**
 * Functional interface for handling exceptions in a functional way.
 *
 * @param <T> the type of the result
 */
@FunctionalInterface
public interface ExceptionHandlerFunctionalInterface<T> {
    T run() throws Exception;
}