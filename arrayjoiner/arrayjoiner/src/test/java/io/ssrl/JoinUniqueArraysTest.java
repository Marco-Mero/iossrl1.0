package io.ssrl;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

@SuppressWarnings({"checkstyle:MissingJavadocType", "checkstyle:MissingJavadocMethod"})
public class JoinUniqueArraysTest extends TestConstants {

    private static Random random;

    @BeforeAll
    public static void setup() {
        random = new Random();
    }

    @Test
    void testJoinNull() {
        assertArrayEquals(JoinUniqueArrays.joinArrays(null, null), new int[0]);
    }

    @Test
    void testJoinEmpty1() {
        int maxValue = random.nextInt(MAX_VALUE);
        int maxLength = random.nextInt(MAX_LENGTH);
        int[] intArray1 = new int[0];
        int[] intArray2 = UtilsArray.generateIntArray(maxValue, maxLength);

        int[] paragonTerm = Arrays
                .stream(IntStream.concat(Arrays.stream(intArray1), Arrays.stream(intArray2)).distinct().toArray())
                .toArray();

        assertArrayEquals(JoinUniqueArrays.joinArrays(intArray1, intArray2), paragonTerm);
    }

    @Test
    void testJoinEmpty2() {

        int maxValue = random.nextInt(MAX_VALUE);
        int maxLength = random.nextInt(MAX_LENGTH);
        int[] intArray1 = UtilsArray.generateIntArray(maxValue, maxLength);
        int[] intArray2 = new int[0];

        int[] paragonTerm = Arrays
                .stream(IntStream.concat(Arrays.stream(intArray1), Arrays.stream(intArray2)).distinct().toArray())
                .toArray();

        assertArrayEquals(JoinUniqueArrays.joinArrays(intArray1, intArray2), paragonTerm);

    }

    @RepeatedTest(TESTS_TO_RUN)
    void testRandomArrays() {
        int[] intArray1;
        int[] intArray2;

        int maxValue = random.nextInt(MAX_VALUE);
        int length = random.nextInt(MAX_LENGTH);
        intArray1 = UtilsArray.generateIntArray(maxValue, length);

        maxValue = random.nextInt(MAX_VALUE);
        length = random.nextInt(MAX_LENGTH);
        intArray2 = UtilsArray.generateIntArray(maxValue, length);

        int[] paragonTerm = Arrays
                .stream(IntStream.concat(Arrays.stream(intArray1), Arrays.stream(intArray2)).distinct().toArray())
                .toArray();

        assertArrayEquals(JoinUniqueArrays.joinArrays(intArray1, intArray2), paragonTerm);
    }
}
