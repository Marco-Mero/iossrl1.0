package io.ssrl;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Random;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SuppressWarnings({"checkstyle:MissingJavadocType", "checkstyle:MissingJavadocMethod"})
public class UtilsArrayTest extends TestConstants {

    private static Random random;
    private static ByteArrayOutputStream outContent;

    @BeforeAll
    public static void setup() {
        random = new Random();
        outContent = new ByteArrayOutputStream();
    }

    @Test
    void emptyStringToIntArray() {
        int[] expected = new int[0];
        int[] result = UtilsArray.stringToIntArray(null);
        assertArrayEquals(expected, result);
    }

    @RepeatedTest(TESTS_TO_RUN)
    void randomStringToIntArray() {
        int[] expected = new int[random.nextInt(MAX_LENGTH)];
        for (int i = 0; i < expected.length; i++) {
            expected[i] = random.nextInt(MAX_VALUE);
        }
        int[] result = UtilsArray.stringToIntArray(java.util.Arrays.toString(expected));
        assertArrayEquals(expected, result);
    }

    @RepeatedTest(TESTS_TO_RUN)
    public void randomPrintArr() {
        outContent.reset();
        int[] intArray = new int[random.nextInt(MAX_LENGTH) + 1];
        for (int j = 0; j < intArray.length; j++) {
            intArray[j] = random.nextInt(MAX_VALUE - 1) + 1;
        }
        UtilsArray.printArr(intArray);
        assertEquals(java.util.Arrays.toString(intArray).replaceAll(",", ""), outContent.toString());
    }

    @Test
    public void emptyPrintArr() {
        outContent.reset();
        int[] intArray = {};
        System.setOut(new PrintStream(outContent));
        UtilsArray.printArr(intArray);
        assertEquals("[ ]", outContent.toString());
    }

    @Test
    public void singlePositionPrintArr() {
        outContent.reset();
        int randomNumber = random.nextInt(MAX_VALUE);
        int[] intArray = {randomNumber};
        System.setOut(new PrintStream(outContent));
        UtilsArray.printArr(intArray);
        assertEquals("[" + randomNumber + "]", outContent.toString());
    }

    @RepeatedTest(TESTS_TO_RUN)
    public void testGenerateIntArraySimple() {
        int maxValue = random.nextInt(MAX_VALUE);
        int length = random.nextInt(MAX_LENGTH);
        int[] result = UtilsArray.generateIntArray(maxValue, length);
        assertEquals(length, result.length);
        for (int i = 0; i < result.length; i++) {
            assertTrue(result[i] >= 0 && result[i] <= maxValue);
        }
    }

    @RepeatedTest(TESTS_TO_RUN)
    public void testGenerateIntArrayComplex() {
        int minValue = 0;
        int inputtedMax = random.nextInt(MAX_VALUE);
        int desiredLength = random.nextInt(MAX_LENGTH);
        int lengthDelta = random.nextInt(MAX_VALUE);
        int[] result = UtilsArray.generateIntArray(minValue, inputtedMax, desiredLength, lengthDelta);
        assertTrue(desiredLength <= result.length);
        for (int k = 0; k < result.length; k++) {
            assertTrue(result[k] >= minValue && result[k] <= inputtedMax);
        }
    }

}
