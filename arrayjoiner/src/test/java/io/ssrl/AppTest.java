package io.ssrl;

import java.util.Arrays;
import java.util.Random;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

// import static org.junit.jupiter.api.Assertions.assertEquals;

@SuppressWarnings({"checkstyle:MissingJavadocType", "checkstyle:MissingJavadocMethod"})
class AppTest extends TestConstants {
    private static Random random;

    @BeforeAll
    public static void setup() {
        random = new Random();
    }

    @Test
    void testAppEmptyArgs() {
        String[] args = new String[0];
        App.main(args);
    }

    @RepeatedTest(TESTS_TO_RUN)
    void testAppRandomArrays() {

        int desiredLength = random.nextInt(MAX_LENGTH);
        int maxValue = random.nextInt(MAX_VALUE);
        int[] intArray1 = UtilsArray.generateIntArray(maxValue, desiredLength);

        desiredLength = random.nextInt(MAX_LENGTH);
        maxValue = random.nextInt(MAX_VALUE);

        int[] intArray2 = UtilsArray.generateIntArray(maxValue, desiredLength);
        String[] args = {Arrays.toString(intArray1), Arrays.toString(intArray2)};
        App.main(args);
    }
}
