// ExceptionHandlerFunctionalInterface.java
package com.assessment;

@FunctionalInterface
public interface ExceptionHandlerFunctionalInterface<T> {
    T run() throws Exception;
}