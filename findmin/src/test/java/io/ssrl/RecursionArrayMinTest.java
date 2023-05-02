package io.ssrl;

import org.junit.jupiter.api.RepeatedTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RecursionArrayMinTest extends TestConstants {

    @RepeatedTest(TESTS_TO_RUN)
    void testRandomArrays() {
        int[] intArray;
        intArray = ArrayUtils.generateIntArray(MIN_VALUE, MAX_VALUE, MAX_LENGTH, DELTA);
        int iterativeMin = MAX_VALUE;
        for (int j = 0; j < intArray.length; j++) {
            iterativeMin = intArray[j] < iterativeMin ? intArray[j] : iterativeMin;
        }
        assertEquals(RecursionArrayMin.findMinimum(intArray), iterativeMin);
    }
}
