package com.assessment;

/**
 * DatabaseExceptionHandler is a utility class for handling exceptions that may occur in the database-related operations.
 * It provides a configurable exception handling mechanism through a functional interface.
 *
 * <p>The class allows the setup of a custom exception handler using the {@link #setupExceptionHandler()} method.
 * The handler can be configured to perform specific actions when an exception occurs, in addition to printing
 * the exception stack trace.
 *
 * <p>Usage example:
 * <pre>
 * {@code
 * // Configure a custom exception handler
 * DatabaseExceptionHandler.setupExceptionHandler();
 *
 * try {
 *     // Database-related code that may throw an exception
 * } catch (Exception e) {
 *     // Handle the exception using the configured exception handler
 *     DatabaseExceptionHandler.handleException(e);
 * }
 * }
 * </pre>
 *
 * @author Ganesh Bhat
 * @version 1.0
 */
public class DatabaseExceptionHandler {

    /**
     * Functional interface for handling exceptions without a return value.
     *
     * @param <T> the type of the result (void in this case)
     */
    private static ExceptionHandlerFunctionalInterface<Void> exceptionHandler = () -> {
        System.err.println("Exception occurred! Handle it appropriately.");
        // Additional handling logic can be added here if needed
        return null;
    };

    /**
     * Configures a custom exception handler for DatabaseExceptionHandler.
     * This handler will be invoked when {@link #handleException(Exception)} is called.
     */
    public static void setupExceptionHandler() {
        // Additional setup, if needed
    }

    /**
     * Handles an exception by invoking the configured exception handler and printing the exception stack trace.
     *
     * @param e the exception to be handled
     */
    public static void handleException(Exception e) {
        try {
            exceptionHandler.run();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        e.printStackTrace();
        // Additional actions, if needed
    }
}
