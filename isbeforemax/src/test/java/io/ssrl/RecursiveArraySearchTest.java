package io.ssrl;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SuppressWarnings("MagicNumber")
public class RecursiveArraySearchTest {
    private static ByteArrayOutputStream outContent;

    private String getActualOutput() {
        return outContent.toString().replaceAll("[\r\n]", "");
    }

    @BeforeAll
    public static void setup() {
        outContent = new ByteArrayOutputStream();
    }

    @BeforeEach
    void prepareOut() {
        System.setOut(new PrintStream(outContent));
        outContent.reset();
    }

    @Test
    void nullProofing() {
        RecursiveArraySearch.isMaxBeforeItem(new int[] {}, 2);
        assertEquals("Warn: Array nullo in ingresso.", getActualOutput());
    }

    @Test
    void singoloElemento() {
        RecursiveArraySearch.isMaxBeforeItem(new int[] {2}, 2);
        assertEquals("L'array contiene solo: 2", getActualOutput());
    }

    @Test
    void regularTestOne() {
        RecursiveArraySearch.isMaxBeforeItem(new int[] {1, 4, 2, 3, 2}, 2);
        assertEquals("Valore non trovato prima del massimo.", getActualOutput());
    }

    @Test
    void regularTestTwo() {
        RecursiveArraySearch.isMaxBeforeItem(new int[] {2, 3, 5, 19, -1}, -1);
        assertEquals("Valore non trovato prima del massimo.", getActualOutput());
    }

    @Test
    void regularTestThree() {
        RecursiveArraySearch.isMaxBeforeItem(new int[] {2, 3, 5, 45, -1, -1, 45}, -1);
        assertEquals("Valore non trovato prima del massimo.", getActualOutput());
    }

    @Test
    void regularTestFour() {
        RecursiveArraySearch.isMaxBeforeItem(new int[] {-12, -21, 5, -6, -7, -8}, -21);
        assertEquals("Il valore compare nell'array: 1 volte.", getActualOutput());
    }

    @Test
    void regularTestFive() {
        RecursiveArraySearch.isMaxBeforeItem(new int[] {-12, -21, 5, -6, -7, -8}, -21);
        assertEquals("Il valore compare nell'array: 1 volte.", getActualOutput());
    }

    @Test
    void regularTestSix() {
        RecursiveArraySearch.isMaxBeforeItem(new int[] {30, 12, 45, 12, 12}, 12);
        assertEquals("Il valore compare nell'array: 3 volte.", getActualOutput());
    }

}
