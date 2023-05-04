package io.ssrl;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AppTest extends TestConstants {
    private static ByteArrayOutputStream outContent;

    @BeforeAll
    public static void setup() {
        outContent = new ByteArrayOutputStream();
    }

    @BeforeEach
    void prepareOut() {
        System.setOut(new PrintStream(outContent));
    }

    @Test
    void testApp() {
        App.main(new String[0]);
    }
    
    @RepeatedTest(TESTS_TO_RUN)
    void testRandomApp() {
        outContent.reset();
        ArrayList<String> argsList = new ArrayList<String>();
        for (int i = 0; i < ARRAYS_FOR_TEST; i++) {
            String arrayToAdd = ArrayUtils.generateIntArray(MIN_VALUE, MAX_VALUE, MAX_LENGTH, LENGTH_DELTA).toString();
            argsList.add(arrayToAdd);
        }
        String[] args = argsList.toArray(new String[argsList.size()]);
        assertEquals(argsList.size(), args.length);
        App.main(args);
        String actualOutput = outContent.toString().trim();
        String expectedString = "Minimo trovato: ";
        int appearenceCount = 0;

        while (actualOutput.contains(expectedString)) {
            actualOutput = actualOutput.replaceFirst(expectedString, "");
            appearenceCount++;
        }
        assertEquals(ARRAYS_FOR_TEST, appearenceCount);
    }
}
