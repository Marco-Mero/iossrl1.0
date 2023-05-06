package io.ssrl;

@SuppressWarnings("DeclarationOrder")
public final class GameConstants {
    private GameConstants() {
    }

    public static final String ESCAPE_STRING = "quit";
    public static final int POINTS_TO_WIN = 3;

    public static final String TITLE = "~~~ ~~~ ~~~ ~~~ ~~~ ~~~\n"
            + "|| Sasso, carta, forbice\n~~~ ~~~ ~~~ ~~~ ~~~ ~~~\n";
    public static final String CHOOSE_MODE_MSG = "~~~ Digitare multi per il multiplayer;\n"
            + "~~~ Premere invio per sfidare il computer;\n"
            + "~~~ Digitare 'quit' in qualunque momento per terminare il programma.\n";
    public static final String COMPUTER_OPPONENT = "~~~Computer selezionato come avversario. Buona fortuna!~~~";
    public static final String GAME_RULES = "'Carta' batte 'Sasso' che batte 'Forbice' che batte 'Carta'\n";

    public static final String SEPARATOR = "\n- - - - - - - - - - - - - - - - - - -\n";
    public static final String GAME_OVER_MSG = "\n        ~~~ Game Over ~~~\n\nProgramma terminato.";
}
