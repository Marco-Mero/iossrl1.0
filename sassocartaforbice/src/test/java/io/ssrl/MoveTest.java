package io.ssrl;

import org.junit.jupiter.api.RepeatedTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MoveTest extends TestConstants {
    private int sassi;
    private int carte;
    private int forbici;

    @RepeatedTest(TESTS_TO_RUN)
    void fairness() {
        for (int i = 0; i < TESTS_TO_RUN; i++) {
            switch (Move.randomMove()) {
                case FORBICE:
                    forbici++;
                    break;
                case SASSO:
                    sassi++;
                    break;
                case CARTA:
                    carte++;
                    break;
                default:
                    break;
            }
        }

        assertEquals(forbici, sassi, TESTS_TO_RUN);
        assertEquals(carte, forbici, TESTS_TO_RUN);
    }
}
