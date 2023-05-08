package io.ssrl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SuppressWarnings("MagicNumber")

class AppTest extends TestConstants {
    private static ByteArrayOutputStream outContent;
    private static ByteArrayInputStream inContent;

    public String getActualOutput() {
        String cleanedOutput = outContent.toString();
        cleanedOutput = cleanedOutput.replaceAll(GameConstants.CHOOSE_MODE_MSG, "").replaceAll(GameConstants.GAME_RULES,
                "");

        cleanedOutput = cleanedOutput.replaceAll(GameConstants.COMPUTER_OPPONENT, "");
        cleanedOutput = cleanedOutput.replaceAll("[^a-zA-Z0-9]+|\\s+", " ");
        cleanedOutput = cleanedOutput.replaceAll(" Clearing Windows cmd prompt H 2J ", "");
        cleanedOutput = cleanedOutput.replaceAll("Sasso carta forbice", "");
        cleanedOutput = cleanedOutput.replaceAll("Punti", "");

        cleanedOutput = cleanedOutput.replaceAll("Giocatore 1", "P1");
        cleanedOutput = cleanedOutput.replaceAll("Giocatore 2", "P2");
        cleanedOutput = cleanedOutput.replaceAll("Giocatore", "P1");
        cleanedOutput = cleanedOutput.replaceAll("Computer", "PC");

        cleanedOutput = cleanedOutput.replaceAll("\\s+", " ");

        cleanedOutput = cleanedOutput.replaceAll("vince il round", "roundWin");
        cleanedOutput = cleanedOutput.replaceAll("vince il round", "roundWin");
        cleanedOutput = cleanedOutput.replaceAll("P1 P1", "P1");
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
        String expectedOutput = "P1 0 P2 0 P1 0 P2 0 Game Over Programma terminato";
        assertEquals(expectedOutput, getActualOutput());
    }

    @Test
    public void testMainPCMethod() {
        inContent = new ByteArrayInputStream("\nquit".getBytes());
        System.setIn(inContent); // set System.in to use the simulated input

        // call the main method
        App.main(new String[0]);

        // check the output produced by the program
        String expectedOutput = "P1 0 PC 0 P1 0 PC 0 Game Over Programma terminato";

        assertEquals(expectedOutput, getActualOutput());
    }

    @RepeatedTest(TEST_DELTA)
    public void testMoves() {
        int playerWins = 0;
        int computerWins = 0;
        for (int i = 0; i < TESTS_TO_RUN; i++) {
            String moveList = "\n\n";
            for (int j = 0; j < 30; j++) {
                moveList += Move.randomMove().toPrintString();
                moveList += "\n";
            }
            inContent = new ByteArrayInputStream(moveList.getBytes());
            System.setIn(inContent);
            App.main(new String[0]);

            if (getActualOutput().contains("PC=Winner")) {
                computerWins++;
            } else if (getActualOutput().contains("P1=Winner")) {
                playerWins++;
            }
        }
        assertEquals(computerWins, playerWins, TEST_DELTA);
    }
}
