// Utils.java
package com.assessment;

import java.util.Random;
import java.util.stream.IntStream;

public class Utils {

    public static int[] generateRandomNumbers(int size) {
        Random random = new Random();

        return IntStream.range(0, size)
                .map(i -> random.nextInt(100) + 1)
                .toArray();
    }
}
