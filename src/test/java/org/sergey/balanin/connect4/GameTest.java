package org.sergey.balanin.connect4;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public final class GameTest extends GameHelper {

    public GameTest() {
        super(new GameOptions(3, 4, 3));
    }

    @Test
    public void shouldWarnAboutFilledColumn() throws IOException {
        GameResult result = prepareGame(new int[]{1, 1, 1, 1, 1}).play();
        assertEquals(GameResult.INCOMPLETE, result);
        assertTrue(consoleOutput().contains("This column is full, please choose another"));
    }

    @Test
    public void shouldDetectRowWin() throws IOException {
        GameResult result = prepareGame(new int[]{1, 1, 3, 3, 2}).play();
        assertEquals(GameResult.RED_WINS, result);
        assertTrue(consoleOutput().contains("|G| |G| |"));
        assertTrue(consoleOutput().contains("|R|R|R| |"));
    }

    @Test
    public void shouldDetectColumnWin() throws IOException {
        GameResult result = prepareGame(new int[]{1, 2, 1, 2, 1}).play();
        assertEquals(GameResult.RED_WINS, result);
        assertTrue(consoleOutput().contains("|R| | | |"));
        assertTrue(consoleOutput().contains("|R|G| | |"));
        assertTrue(consoleOutput().contains("|R|G| | |"));
    }

    @Test
    public void shouldDetectAscendingWin() throws IOException {
        GameResult result = prepareGame(new int[]{2, 1, 3, 3, 1, 3, 1, 2}).play();
        assertEquals(GameResult.GREEN_WINS, result);
        assertTrue(consoleOutput().contains("|R| |G| |"));
        assertTrue(consoleOutput().contains("|R|G|G| |"));
        assertTrue(consoleOutput().contains("|G|R|R| |"));
    }

    @Test
    public void shouldDetectDescendingWin() throws IOException {
        GameResult result = prepareGame(new int[]{1, 2, 3, 1, 1, 4, 2}).play();
        assertEquals(GameResult.RED_WINS, result);
        assertTrue(consoleOutput().contains("|R| | | |"));
        assertTrue(consoleOutput().contains("|G|R| | |"));
        assertTrue(consoleOutput().contains("|R|G|R|G|"));
    }

    @Test
    public void shouldReportDrawWhenAllMovesAreDoneWithoutWinner() throws IOException {
        GameResult result = prepareGame(new int[]{1, 2, 3, 4, 2, 1, 4, 3, 2, 1, 4, 3}).play();
        assertEquals(GameResult.DRAW, result);
        assertTrue(consoleOutput().contains("|G|R|G|R|"));
        assertTrue(consoleOutput().contains("|G|R|G|R|"));
        assertTrue(consoleOutput().contains("|R|G|R|G|"));
    }
}
