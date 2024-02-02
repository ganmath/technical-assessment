// DatabasePopulator.java
package com.assessment;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Arrays;
import java.util.Properties;

public class DatabasePopulator {

	private static final String PROPERTIES_FILE = "database.properties";
	private static final String EXCEPTION_MESSAGES_FILE = "exception-messages.properties";

	private static String jdbcUrl;
	private static String username;
	private static String password;
	private static String driver;
	private static String insertQuery;

	private static String noGeneratedKeyMessage;

	static {
		loadProperties();
		loadExceptionMessages();
		DatabaseExceptionHandler.setupExceptionHandler();
	}

	public static void main(String[] args) {
		ExceptionHandlerFunctionalInterface<Void> handler = () -> {
			Class.forName(driver);
			try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
				int[] randomNumbers = Utils.generateRandomNumbers(2000);
				int vectorId = insertVector(connection, "RandomVector", randomNumbers);

				System.out.println("Vector inserted with vector_id: " + vectorId);
			}
			return null;
		};
		handleExceptions(handler);
	}

	private static void loadProperties() {
		ExceptionHandlerFunctionalInterface<Void> handler = () -> {
			try (InputStream input = DatabasePopulator.class.getClassLoader().getResourceAsStream(PROPERTIES_FILE)) {
				Properties properties = new Properties();
				properties.load(input);

				jdbcUrl = getProperty(properties, "jdbc.url");
				username = getProperty(properties, "jdbc.username");
				password = getProperty(properties, "jdbc.password");
				driver = getProperty(properties, "jdbc.driver");
				insertQuery = getProperty(properties, "insert.query");
			} catch (IOException e) {
				throw new RuntimeException("Error loading properties file", e);
			}
			return null;
		};
		handleExceptions(handler);
	}

	private static void loadExceptionMessages() {
		ExceptionHandlerFunctionalInterface<Void> handler = () -> {
			try (InputStream input = DatabasePopulator.class.getClassLoader()
					.getResourceAsStream(EXCEPTION_MESSAGES_FILE)) {
				Properties exceptionMessages = new Properties();
				exceptionMessages.load(input);

				getProperty(exceptionMessages, "database.exception.insertFailure");
				noGeneratedKeyMessage = getProperty(exceptionMessages, "database.exception.noGeneratedKey");
			} catch (IOException e) {
				throw new RuntimeException("Error loading exception messages file", e);
			}
			return null;
		};

		handleExceptions(handler);

	}

	private static String getProperty(Properties properties, String key) {
		return properties.getProperty(key);
	}

	private static <T> T handleExceptions(ExceptionHandlerFunctionalInterface<T> handler) {
		try {
			return handler.run();
		} catch (Exception e) {
			DatabaseExceptionHandler.handleException(e);
			throw new RuntimeException("Exception occurred during execution", e);
		}
	}

	private static int insertVector(Connection connection, String vectorName, int[] numbers) {
		ExceptionHandlerFunctionalInterface<Integer> handler = () -> {
	        int generatedId;
	        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery, PreparedStatement.RETURN_GENERATED_KEYS)) {
	            preparedStatement.setString(1, vectorName);

	            // Convert int[] to java.sql.Array
	            Array array = connection.createArrayOf("INTEGER", Arrays.stream(numbers).boxed().toArray());

	            preparedStatement.setArray(2, array);
	            preparedStatement.executeUpdate();

	            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
	                if (generatedKeys.next()) {
	                    generatedId = generatedKeys.getInt(1);
	                } else {
	                    throw new SQLException(noGeneratedKeyMessage);
	                }
	            }
	        } catch (SQLException e) {
	            // Specific handling for SQL exceptions
	            throw new RuntimeException("Error executing SQL statement", e);
	        }
	        return generatedId;
	    };

		return handleExceptions(handler);
	}

}
