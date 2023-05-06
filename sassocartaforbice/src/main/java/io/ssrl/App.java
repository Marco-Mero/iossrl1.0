package io.ssrl;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

import static io.ssrl.GameConstants.CHOOSE_MODE_MSG;
import static io.ssrl.GameConstants.COMPUTER_OPPONENT;
import static io.ssrl.GameConstants.ESCAPE_STRING;
import static io.ssrl.GameConstants.GAME_OVER_MSG;
import static io.ssrl.GameConstants.GAME_RULES;
import static io.ssrl.GameConstants.SEPARATOR;
import static io.ssrl.GameConstants.TITLE;

public final class App {
    private static String[] possibleChoices = {"multi", ESCAPE_STRING, ""};

    private App() {
    }

    private static void chooseProgram(String choice, Scanner playerInput) {

        switch (choice) {
            case "multi":
                GameLogic multiMatch = new GameLogic(playerInput, "Giocatore 1", "Giocatore 2");
                multiMatch.playerMatch();
                break;

            case ESCAPE_STRING:
                break;

            case "":
                System.out.println(COMPUTER_OPPONENT);
                GameLogic singleMatch = new GameLogic(playerInput);
                singleMatch.playerMatch();
                break;
            default:
                System.out.println(SEPARATOR + "\nStringa non riconosciuta, riprovare:\n" + SEPARATOR);
        }

    }

    private static void enableClrScrn() {
        if (System.getProperty("os.name").contains("Windows")) {
            try {
                ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", "cls");
                builder.inheritIO().start().waitFor();
                System.setProperty("file.encoding", "UTF-8");
            } catch (IOException e) {
                System.out.println(
                        "Sequenze ANSI per pulizia schermo non abilitate, funzionamento subottimale. ");
            } catch (InterruptedException e) {
                System.out.println(
                        "Sequenze ANSI per pulizia schermo non abilitate, funzionamento subottimale. ");
            }
        }
    }

    public static void main(String[] args) {
        try (Scanner playerInput = new Scanner(System.in)) {
            String chosenProgram = null;

            enableClrScrn();
            System.out.println(TITLE);
            System.out.println(GAME_RULES);

            do {
                System.out.println(CHOOSE_MODE_MSG);
                chosenProgram = playerInput.nextLine();
                chooseProgram(chosenProgram, playerInput);

            } while (!Arrays.asList(possibleChoices).contains(chosenProgram));

        }
        System.out.println(GAME_OVER_MSG);
    }
}
