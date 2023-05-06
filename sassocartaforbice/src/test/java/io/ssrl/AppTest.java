package io.ssrl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit test for simple App.
 */

class AppTest extends TestConstants {
    private static ByteArrayOutputStream outContent;
    private static ByteArrayInputStream inContent;

    public String getActualOutput() {
        String cleanedOutput = outContent.toString();

        cleanedOutput = cleanedOutput.replaceAll("[^a-zA-Z0-9]+|\\s+", " ");

        cleanedOutput = cleanedOutput.replaceAll(" Clearing Windows cmd prompt H 2J ", "");
        cleanedOutput = cleanedOutput.replaceAll("Sasso carta forbice", "");
        cleanedOutput = cleanedOutput.replaceAll(
                "Carta batte Sasso che batte Forbice che batte Carta Digitare multi per il multiplayer Premere invio per sfidare il computer Digitare quit in qualunque momento per terminare il programma",
                "");
        cleanedOutput = cleanedOutput.replaceAll("Punti", "");

        cleanedOutput = cleanedOutput.replaceAll("Giocatore 1", "P1");
        cleanedOutput = cleanedOutput.replaceAll("Giocatore 2", "P2");
        cleanedOutput = cleanedOutput.replaceAll("Giocatore", "P1");
        cleanedOutput = cleanedOutput.replaceAll("Computer", "PC");

        cleanedOutput = cleanedOutput.replaceAll("\\s+", " ");

        cleanedOutput = cleanedOutput.replaceAll("vince il round", "roundWin");
        cleanedOutput = cleanedOutput.replaceAll("vince il round", "roundWin");

        cleanedOutput = cleanedOutput.replaceAll("P1 vince Game Over", "P1=winner");
        cleanedOutput = cleanedOutput.replaceAll("PC vince Game Over", "PC=winner");

        return cleanedOutput.trim();
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
    public void testMainMultiMethod() {
        inContent = new ByteArrayInputStream("multi\nquit".getBytes());
        System.setIn(inContent); // set System.in to use the simulated input

        // call the main method
        App.main(new String[0]);

        // check the output produced by the program
        String expectedOutput = "P1 0 P2 0 P1 P1 0 P2 0 Game Over Programma terminato";
        assertEquals(expectedOutput, getActualOutput());
    }

    @Test
    public void testMainPCMethod() {
        inContent = new ByteArrayInputStream("\nquit".getBytes());
        System.setIn(inContent); // set System.in to use the simulated input

        // call the main method
        App.main(new String[0]);

        // check the output produced by the program
        String expectedOutput = "PC selezionato come avversario Buona fortuna P1 0 PC 0 P1 P1 0 PC 0 Game Over Programma terminato";

        assertEquals(expectedOutput, getActualOutput());
    }

    @RepeatedTest(10)
    public void testMoves() {
        int playerWins = 0;
        int computerWins = 0;
        for (int i = 0; i < TESTS_TO_RUN; i++) {
            inContent = new ByteArrayInputStream(
                    "\n\nsasso\ncarta\nforbici\nsasso\ncarta\nforbici\nsasso\ncarta\nforbici\nsasso\ncarta\nforbici\nsasso\ncarta\nforbici\nsasso\ncarta\nforbici\nsasso\ncarta\nforbici\nsasso\ncarta\nforbici\nsasso\ncarta\nforbici\nsasso\ncarta\nforbici\nsasso\ncarta\nforbici\nsasso\ncarta\nforbici\nsasso\ncarta\nforbici\nsasso\ncarta\nforbici\nsasso\ncarta\nforbici\nsasso\ncarta\nforbici\nsasso\ncarta\nforbici\nsasso\ncarta\nforbici\nsasso\ncarta\nforbici\nquit"
                            .getBytes());
            System.setIn(inContent);
            App.main(new String[0]);
            if (getActualOutput().contains("PC=Winner")) {
                computerWins++;
            } else if (getActualOutput().contains("P1=Winner")) {
                playerWins++;
            }
        }
        assertEquals(computerWins, playerWins, TESTS_TO_RUN / 10);
    }
}
