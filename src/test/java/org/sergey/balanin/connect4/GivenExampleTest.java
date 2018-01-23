package org.sergey.balanin.connect4;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public final class GivenExampleTest extends GameHelper {

    public GivenExampleTest() {
        super(Connect4.STANDARD_OPTIONS);
    }

    @Test
    public void givenExampleScenario() throws IOException {
        GameResult result = prepareGame(new int[]{4, 4, 5, 5, 3, 2, 6}).play();
        assertEquals(GameResult.RED_WINS, result);
        assertTrue(consoleOutput().contains("Player 1 [RED] wins!"));
    }
}
