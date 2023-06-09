package io.ssrl;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Random;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SuppressWarnings({ "checkstyle:MissingJavadocType", "checkstyle:MissingJavadocMethod" })
class AppTest extends TestConstants {
    private static Random random;
    private static ByteArrayOutputStream outContent;

    @BeforeAll
    public static void setup() {
        random = new Random();
        outContent = new ByteArrayOutputStream();

    }

    @BeforeEach
    void prepareOut() {
        System.setOut(new PrintStream(outContent));
    }

    @Test
    void testNullArg() {
        App.main(new String[0]);
        String actualOutput = outContent.toString().trim();
        String expectedString = "String[] args deve contenere stringhe valide.";
        assertTrue(actualOutput.endsWith(expectedString));
    }

    @Test
    void testSingleEmpty() {
        App.main(new String[1]);
        String actualOutput = outContent.toString().trim();
        String expectedString = "[ ]";
        assertTrue(actualOutput.endsWith(expectedString));
    }

    @RepeatedTest(TESTS_TO_RUN)
    void testAppRandomArrays() {
        ByteArrayOutputStream paragon = new ByteArrayOutputStream();

        int desiredLength = random.nextInt(MAX_LENGTH);
        int maxValue = random.nextInt(MAX_VALUE);
        int[] intArray1 = ArrayUtils.generateIntArray(maxValue, desiredLength);

        desiredLength = random.nextInt(MAX_LENGTH);
        maxValue = random.nextInt(MAX_VALUE);

        int[] intArray2 = ArrayUtils.generateIntArray(maxValue, desiredLength);
        String[] args = {Arrays.toString(intArray1), Arrays.toString(intArray2)};

        App.main(args);
        String actualOutput = outContent.toString().trim();
        System.setOut(new PrintStream(paragon));
        ArrayUtils.printArr(JoinUniqueArrays.joinArrays(intArray1, intArray2));

        String expectedString = paragon.toString().trim();
        assertTrue(actualOutput.endsWith(expectedString));
    }
}
