package io.ssrl;

import java.io.IOException;
import java.util.Scanner;

import static io.ssrl.GameConstants.POINTS_TO_WIN;
import static io.ssrl.GameConstants.SEPARATOR;

public class GameLogic {

    private String lastRoundWinner;
    private PlayerController playerOne;
    private PlayerController playerTwo;

    private Move p1LastMove;

    public GameLogic(Scanner inputSource, String nameOne, String nameTwo) {
        playerOne = new PlayerController(nameOne, inputSource);
        playerTwo = new PlayerController(nameTwo, inputSource);
    }

    public GameLogic(Scanner inputSource) {
        this(inputSource, "Giocatore", "Computer");
    }

    public void clearScreen() {
        try {
            Runtime.getRuntime().exec("cls");
        } catch (IOException e) {
            System.out.println("Clearing Windows cmd prompt. . .");
        }
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private void printScoreBoard() {
        System.out.println("- - - - - - - - Punti - - - - - - - - \n");
        playerOne.tellScore();
        System.out.print(" || ");
        playerTwo.tellScore();
        System.out.println("\n" + SEPARATOR);
    }

    public void printLastRound() {
        if (p1LastMove != null) {
            System.out.print("\n" + playerOne.getName() + ": " + p1LastMove.toPrintString() + " ~ ");
            System.out.print(playerTwo.getName() + ": " + playerTwo.getMoveName() + "\n\n");
        }

        if (lastRoundWinner == null) {
            System.out.println("Pareggio!");
        } else {
            System.out.println(lastRoundWinner + " vince il round!");
        }
        System.out.println(SEPARATOR);
    }

    public void printWinner() {
        if (playerOne.getScore() > playerTwo.getScore()) {
            playerOne.wins();
        } else {
            playerTwo.wins();
        }
    }

    private void drawGameElements() {
        clearScreen();
        printScoreBoard();
        if (playerOne.getScore() == POINTS_TO_WIN || playerTwo.getScore() == POINTS_TO_WIN) {
            printWinner();
        } else if (playerOne.getMove() != null && playerTwo.getMove() != null) {
            printLastRound();
        }
    }

    public void setRoundWinner() {
        p1LastMove = playerOne.getMove();
        if (playerOne.getMove() == playerTwo.getMove()) {
            lastRoundWinner = null;
        } else if (playerOne.getMove().beats(playerTwo.getMove())) {
            playerOne.score();
            lastRoundWinner = playerOne.getName();
        } else {
            playerTwo.score();
            lastRoundWinner = playerTwo.getName();
        }
    }

    public void setPlayerMoves() {
        playerOne.promptMove();
        if (playerOne.getMove() != null) {
            drawGameElements();
            if (playerTwo.isNamed("computer")) {
                playerTwo.setRandomMove();
            } else {
                playerTwo.promptMove();
            }
            drawGameElements();
        }
    }

    public void playerMatch() {
        do {
            drawGameElements();
            setPlayerMoves();
            if (playerOne.getMove() != null && playerTwo.getMove() != null) {
                setRoundWinner();
            } else {
                break;
            }
        } while (playerOne.getScore() < POINTS_TO_WIN && playerTwo.getScore() < POINTS_TO_WIN);
        drawGameElements();
    }
}
