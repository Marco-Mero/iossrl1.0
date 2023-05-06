package io.ssrl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.RepeatedTest;

public class MoveTest extends TestConstants {
    private int sassi = 0;
    private int carte = 0;
    private int forbici = 0;

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
            }
        }

        assertEquals(forbici, sassi, TESTS_TO_RUN * 0.1);
        assertEquals(carte, forbici, TESTS_TO_RUN * 0.1);
    }
}
