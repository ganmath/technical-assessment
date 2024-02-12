package com.assessment;

import java.util.Random;
import java.util.stream.IntStream;

/**
 * Utility class providing methods for generating random numbers.
 */
public class Utils {

	/**
	 * Generates an array of random integers within the range of 1 to 100.
	 *
	 * @param size the size of the array
	 * @return an array of random integers
	 */
	public static int[] generateRandomNumbers(int size) {
		Random random = new Random();

		return IntStream.range(0, size).map(i -> random.nextInt(100) + 1).toArray();
	}
}
