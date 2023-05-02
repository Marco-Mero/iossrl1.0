package io.ssrl;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RecursionArrayMinTest extends TestConstants {

    @RepeatedTest(TESTS_TO_RUN)
    void testRandomArray() {
        int[] intArray;
        intArray = ArrayUtils.generateIntArray(MIN_VALUE, MAX_VALUE, MAX_LENGTH, LENGTH_DELTA);
        int iterativeMin = MAX_VALUE;
        for (int j = 0; j < intArray.length; j++) {
            iterativeMin = intArray[j] < iterativeMin ? intArray[j] : iterativeMin;
        }
        assertEquals(RecursionArrayMin.findMinimum(intArray), iterativeMin);
    }

    @Test
    void testNullFindMininimum() {
        Throwable nullArrayException = assertThrows(IllegalArgumentException.class, () -> {
            RecursionArrayMin.findMinimum(null);
        });
        assertEquals("Input Array must not be null or empty.", nullArrayException.getMessage());
    }
}
